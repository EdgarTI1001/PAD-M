package padm.io.pad_m.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.html2pdf.HtmlConverter;

import padm.io.pad_m.domain.Doc;
import padm.io.pad_m.domain.Usuario;
import padm.io.pad_m.fileserver.FilesStorageService;
import padm.io.pad_m.security.IAuthenticationFacade;
import padm.io.pad_m.service.DocService;
import padm.io.pad_m.utils.AlertMessage;
import padm.io.pad_m.utils.FileSizeUtil;

@Controller
@RequestMapping("/docs")
public class DocController {

	@Autowired
	private DocService docService;

	@Autowired
	private FilesStorageService storageService;

	@Autowired
	private IAuthenticationFacade authentication;

	@GetMapping
	public String listDocs(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
		Pageable pageable = PageRequest.of(page, 10); // 5 items por página
		Page<Doc> docPage = docService.findAll(pageable);
		model.addAttribute("docPage", docPage);
		return "docs/list";
	}

	@GetMapping("/create")
	public String createForm(Model model) {
		model.addAttribute("doc", new Doc());
		return "docs/form";
	}

	@GetMapping("/files/new")
	public String createFormUpload(Model model) {
		model.addAttribute("doc", new Doc());
		return "docs/form-upload";
	}

	@PostMapping("/save")
	public String saveDoc(@ModelAttribute("doc") Doc doc) {
		docService.save(doc);
		return "redirect:/docs";
	}

	@PostMapping("/files/upload")
	public String uploadFile(RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file,
			@ModelAttribute("doc") Doc docNew) {
		AlertMessage alertMessage;
		final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10 MB em bytes
		final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList("image/png", "image/jpeg", "video/mp4",
				"application/pdf", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .docx
				"application/msword" // .doc
		);

		// Verifica o tamanho do arquivo
		if (file.getSize() > MAX_FILE_SIZE) {
			alertMessage = new AlertMessage("danger", "O arquivo excede o limite de 10 MB.");
		}
		// Verifica o tipo do arquivo
		else if (!ALLOWED_CONTENT_TYPES.contains(file.getContentType())) {
			alertMessage = new AlertMessage("danger",
					"Tipo de arquivo não permitido. Somente PNG, JPG, MP4, PDF, DOC e DOCX são aceitos.");
		} else {
			try {
				String fileNameHash = storageService.save(file, "documentos");
				Usuario usuario = authentication.getUsuario();

				Doc doc = new Doc();
				doc.setNomdoc(docNew.getNomdoc());
				doc.setExtdoc(file.getContentType());
				doc.setUsu_id(usuario);
				doc.setData(LocalDateTime.now());
				doc.setHashdoc(fileNameHash);

				if (!file.isEmpty()) {
					doc.setTamdoc(FileSizeUtil.formatFileSize(file.getSize()));
				}
				docService.save(doc);

				alertMessage = new AlertMessage("success",
						"Arquivo enviado com sucesso: " + file.getOriginalFilename());
			} catch (Exception e) {
				alertMessage = new AlertMessage("danger", "Não foi possível fazer upload do arquivo: "
						+ file.getOriginalFilename() + ". Error: " + e.getMessage());
			}
		}

		redirectAttributes.addFlashAttribute("alertMessage", alertMessage);
		return "redirect:/docs";
	}

	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		Doc doc = docService.findById(id);
		model.addAttribute("doc", doc);
		return "docs/form";
	}

	@GetMapping("/form/assinar/{idDocumento}")
	public String assinarDoc(@PathVariable("idDocumento") Integer idDocumento, Model model) {
		Doc doc = docService.findById(idDocumento);		
		model.addAttribute("doc", doc);
		return "docs/form-assinar";
	}
	
	
	@PostMapping("/files/assinar")
    public String formAssinarDoc(@RequestParam(name="id") Integer id) {
       //GET EM DOCUMENTO diretorio "documentos" no Sistema
    	System.out.println("==============" + id);
    	return "docs/form-assinar";
    }

	@GetMapping("/delete/{id}")
	public String deleteDoc(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {

		AlertMessage alertMessage = null;

		Doc doc = docService.findById(id);

		if (doc != null && doc.getHashdoc() != null) {
			try {

				boolean existed = storageService.delete(doc.getHashdoc(), "documentos");

				if (existed) {
					alertMessage = new AlertMessage("success", "Arquivo excluido com sucesso! " + doc.getNomdoc());
					// docService.deleteById(id);
				} else {
					redirectAttributes.addFlashAttribute("message", "Arquivo não existe!");
					alertMessage = new AlertMessage("danger", "Arquivo não existe!");
				}
			} catch (Exception e) {
				alertMessage = new AlertMessage("danger",
						"Não foi possível excluir arquivo: " + doc.getNomdoc() + ". Error: " + e.getMessage());
			}
		}

		if (doc != null) {
			docService.deleteById(id);
			alertMessage = new AlertMessage("success", "Arquivo excluido com sucesso! " + doc.getNomdoc());
		}

		redirectAttributes.addFlashAttribute("alertMessage", alertMessage);
		return "redirect:/docs";
	}

	@PostMapping("/gerarPdf")
	public String gerarPdf(@ModelAttribute("doc") Doc doc, RedirectAttributes redirectAttributes) {

		AlertMessage alertMessage = null;

		if (doc != null && doc.getFlag() == 0) {
			try {

				// Gerar nome único para o arquivo PDF
				String nomeArquivo = "arquivo_" + System.currentTimeMillis() + ".pdf";

				// Criar um arquivo temporário para o PDF
				File tempPdfFile = File.createTempFile("temp", ".pdf");

				// Converter o conteúdo HTML em PDF e salvar no arquivo temporário
				try (OutputStream outputStream = new FileOutputStream(tempPdfFile)) {
					HtmlConverter.convertToPdf(doc.getConteudo(), outputStream);
				}

				// Criar um InputStream do arquivo temporário
				InputStream inputStream = new FileInputStream(tempPdfFile);

				// Implementar MultipartFile manualmente
				MultipartFile multipartFile = new MultipartFile() {
					@Override
					public String getName() {
						return "file";
					}

					@Override
					public String getOriginalFilename() {
						return nomeArquivo;
					}

					@Override
					public String getContentType() {
						return "application/pdf";
					}

					@Override
					public boolean isEmpty() {
						return tempPdfFile.length() == 0;
					}

					@Override
					public long getSize() {
						return tempPdfFile.length();
					}

					@Override
					public byte[] getBytes() throws IOException {
						return Files.readAllBytes(tempPdfFile.toPath());
					}

					@Override
					public InputStream getInputStream() throws IOException {
						return new FileInputStream(tempPdfFile);
					}

					@Override
					public void transferTo(File dest) throws IOException, IllegalStateException {
						Files.copy(tempPdfFile.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
					}
				};

				// Usar o método existente save para salvar o arquivo
				String fileNameHash = storageService.save(multipartFile, "documentos");

				Usuario usuario = authentication.getUsuario();

				Doc docNew = new Doc();
				docNew.setId(doc.getId() == null ? null : doc.getId());
				docNew.setNomdoc(doc.getNomdoc());
				docNew.setExtdoc("pdf");
				docNew.setUsu_id(usuario);
				docNew.setData(LocalDateTime.now());
				docNew.setHashdoc(fileNameHash);
				docNew.setConteudo(doc.getConteudo());
				docNew.setFlag(0);
				if (!multipartFile.isEmpty()) {
					docNew.setTamdoc(FileSizeUtil.formatFileSize(multipartFile.getSize()));
				}
				docService.save(docNew);

				alertMessage = new AlertMessage("success", "Arquivo gerado com sucesso!");

			} catch (Exception e) {
				e.printStackTrace();
				alertMessage = new AlertMessage("danger",
						"Não foi possível fazer upload do arquivo: " + doc.getNomdoc() + ". Error: " + e.getMessage());
			}
		} else if (doc != null && doc.getFlag() == 1) {
			Usuario usuario = authentication.getUsuario();

			Doc docNew = new Doc();
			docNew.setId(doc.getId() == null ? null : doc.getId());
			docNew.setNomdoc(doc.getNomdoc());
			docNew.setExtdoc(null);
			docNew.setUsu_id(usuario);
			docNew.setData(LocalDateTime.now());
			docNew.setHashdoc(null);
			docNew.setConteudo(doc.getConteudo());
			docNew.setFlag(1);
			docService.save(docNew);
			alertMessage = new AlertMessage("success", "Rascunho salvo com sucesso!");
		} else {
			alertMessage = new AlertMessage("danger", "Erro ao salvar arquivo!");
		}

		redirectAttributes.addFlashAttribute("alertMessage", alertMessage);

		return "redirect:/docs";
	}
}
