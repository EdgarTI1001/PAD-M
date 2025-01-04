package padm.io.pad_m.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.html2pdf.HtmlConverter;

import padm.io.pad_m.domain.Assinador;
import padm.io.pad_m.domain.Doc;
import padm.io.pad_m.domain.Evento;
import padm.io.pad_m.domain.Processo;
import padm.io.pad_m.domain.TipoEvento;
import padm.io.pad_m.domain.Usuario;
import padm.io.pad_m.domain.dto.InfoFileDTO;
import padm.io.pad_m.domain.dto.ProcessoDocumentoDTO;
import padm.io.pad_m.domain.dto.ResultDTO;
import padm.io.pad_m.fileserver.FilesStorageService;
import padm.io.pad_m.security.AuthenticationFacade;
import padm.io.pad_m.security.IAuthenticationFacade;
import padm.io.pad_m.service.AssinadorService;
import padm.io.pad_m.service.DocService;
import padm.io.pad_m.service.EventoService;
import padm.io.pad_m.service.ProcessoDocsService;
import padm.io.pad_m.service.ProcessoService;
import padm.io.pad_m.service.TipoDocService;
import padm.io.pad_m.service.TipoEventoService;
import padm.io.pad_m.utils.AlertMessage;
import padm.io.pad_m.utils.FileSizeUtil;
import padm.io.pad_m.utils.PDFHandler;

@Controller
@RequestMapping("/docs")
public class DocController {
	private final Path root = Paths.get("./uploads");
	DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	@Autowired
	AuthenticationFacade session;

	@Autowired
	private DocService docService;
	
	@Autowired
	private EventoService eventoService;

	@Autowired
	private TipoEventoService tipoEventoService;

	
	@Autowired
	private AssinadorService assinadorService;

	@Autowired
	private PDFHandler assinaturaService;

	@Autowired
	private ProcessoService processoService;

	@Autowired
	private ProcessoDocsService processoDocService;

	@Autowired
	private TipoDocService tipoDocService;

	@Autowired
	private FilesStorageService storageService;

	@Autowired
	private IAuthenticationFacade authentication;

	private String pdfDir = "documentos";

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

	@GetMapping("/verify")
	public String frmFileVerify(Model model) {
		model.addAttribute("activePage", "mnuVerify");
		return "docs/verify_form";
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

	@GetMapping("/frmAddDoc/{idProcesso}")
	public ModelAndView frmAddDoc(@ModelAttribute("doc") Doc doc,
			@PathVariable(name = "idProcesso") Integer idProcesso) {
		ModelAndView model = new ModelAndView("docs/form-proc-add-doc");
		ResultDTO r = new ResultDTO();
		Optional<Processo> p = processoService.findById(idProcesso);
		model.addObject("processo", p);
		model.addObject("r", r);

		return model;
	}

	@PostMapping("/files/upload/addDoc")
	public String uploadFileAddDocToProc(RedirectAttributes redirectAttributes,
			@RequestParam("file") MultipartFile file, @ModelAttribute("doc") Doc docNew,
			@RequestParam("id") Integer id) {
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
				ProcessoDocumentoDTO pd = new ProcessoDocumentoDTO();
				pd.setIdDocumento(doc.getId());
				pd.setIdProcesso(id);
				pd.setIdUsuario(authentication.getUsuario().getId());
				processoDocService.save(pd);

				Evento evento = new Evento();
				evento.setDataevento(LocalDateTime.now()); //14
				String dataFormatada = LocalDateTime.now().format(parser);
				evento.setEvento("Usuario : " + session.getUsuario().getNome() + " Adicionou o Documento : " + doc.getNomdoc() +
						 " Em : " + dataFormatada);
				
				TipoEvento tpEvento = new TipoEvento();
				tpEvento = tipoEventoService.findById(13).get(); // Adicionar Documento
				evento.setTipo_id(tpEvento);
				
				Processo p = new Processo();
				p = processoService.findById(id).get();
				evento.setProc_id(p);
				
				eventoService.save(evento);
				
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

	@GetMapping("/form/assinar/{idDocumento}/proc/{idProcesso}")
	public String assinarDoc(@PathVariable("idDocumento") Integer idDocumento, @PathVariable("idProcesso") Integer idProcesso, Model model) {
		Doc doc = docService.findById(idDocumento);
		model.addAttribute("doc", doc);
		model.addAttribute("idProcesso", idProcesso);
		return "docs/form-assinar";
	}

	@PostMapping("/files/assinar")
	public String formAssinarDoc(Model model,@RequestParam(name = "idProcesso") Integer idProcesso,  @RequestParam(name = "id") Integer id) throws IOException {
		// GET EM DOCUMENTO diretorio "documentos" no Sistema
		Doc doc = docService.findById(id);
		Integer ret = 99;
		String msg = "";
		String type = "";
		try {			
			
			String fileHash = assinaturaService.generateFileHash(doc, session.getUsuario(), "SHA-256");
			Integer isAssinou = 0;//assinadorService.findByUserAndDoc(session.getUsuario().getId(), id);
			if (isAssinou == 0) {
				Assinador a = new Assinador();
				a.setData(LocalDateTime.now());
				a.setDoc(doc);
				a.setUserId(session.getUsuario());
				a.setHashdoc(fileHash);
				assinadorService.save(a);				
				
				Evento evento = new Evento();
				evento.setDataevento(LocalDateTime.now()); //14
				String dataFormatada = LocalDateTime.now().format(parser);
				evento.setEvento("Usuario : " + session.getUsuario().getNome() + " Assinou o Documento : " + doc.getNomdoc() +
						 " Em : " + dataFormatada );				

				TipoEvento tpEvento = new TipoEvento();
				tpEvento = tipoEventoService.findById(14).get(); // Assinar Documento
				evento.setTipo_id(tpEvento);
				
				Processo p = new Processo();
				p = processoService.findById(idProcesso).get();
				evento.setProc_id(p);
				
				eventoService.save(evento);
				
				msg = "Documento Assinado com Sucesso!";
				type = "success";
				ret = 1;
			} else {
				msg = "O Usuario já assinou esse Documento!!";
				type = "danger";
				ret = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "Erro ao Assinar Documento!";
			type = "danger";
			ret = 0;
			// TODO: handle exception
		}

		model.addAttribute("msg", msg);
		model.addAttribute("type", type);
		model.addAttribute("ret", ret);
		model.addAttribute("doc", doc);
		return "docs/form-assinar";
	}

	@PostMapping("/files/hashverify")
	public String verifyFile(Model model, @RequestParam("file") MultipartFile file) {
		InfoFileDTO fileDTO = new InfoFileDTO();
		boolean verificado = false;

		ResultDTO msg = new ResultDTO();
		msg.setType("success");
		try {

			Optional<Doc> doc = docService.findByHashdoc(file.getOriginalFilename());
			Path filePath = Paths.get("uploads/verify/" + file.getOriginalFilename());
			Files.write(filePath, file.getBytes());

			Optional<Assinador> assinador = assinadorService.findFirstByDoc_id(doc.get().getId());
			verificado = assinaturaService.isFileUnchanged(file, assinador.get().getHashdoc(), "SHA-256");
			if (verificado) {
				msg.setMensagem("Assinatura eletrônica conferida!");
			} else {
				msg.setType("danger");
				msg.setMensagem("Assinatura eletrônica inválida!");
			}

			File arquivo = new File("uploads/verify/" + file.getOriginalFilename());
			arquivo.delete();
			model.addAttribute("obj", fileDTO);
			model.addAttribute("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setType("danger");
			msg.setMensagem("Ocorreu um erro ao tentar validar arquivo. Erro: " + e.getMessage());
			model.addAttribute("msg", msg);
		}

		return "docs/verify_form";
	}

	@GetMapping("/files/download/{id}")
	public ResponseEntity<Resource> download(@PathVariable("id") Integer id) throws IOException {

		try {

			Doc doc = docService.findById(id);
			// Path path = root.resolve(pdfDir).resolve(doc.getHashdoc());
			// Resource resource = new UrlResource(path.toUri());

			File file = new File(root.resolve(pdfDir) + "/" + doc.getHashdoc());
			Resource resource = new FileSystemResource(file);

			String contentType = "application/octet-stream"; // Default for binary files
			String header = "attachment; filename=\"" + resource.getFilename() + "\"";

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, header)
					.contentType(MediaType.parseMediaType(contentType)).body(resource);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

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
					docService.deleteById(id);
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
	public String gerarPdf(@ModelAttribute("doc") Doc doc, RedirectAttributes redirectAttributes,
			@RequestParam("idProcesso") Integer idProcesso) {

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
				docNew.setTipoDoc(doc.getTipoDoc());
				docNew.setData(LocalDateTime.now());
				docNew.setHashdoc(fileNameHash);
				docNew.setConteudo(doc.getConteudo());
				docNew.setFlag(0);
				if (!multipartFile.isEmpty()) {
					docNew.setTamdoc(FileSizeUtil.formatFileSize(multipartFile.getSize()));
				}

				docService.save(docNew);
				ProcessoDocumentoDTO procdoc = new ProcessoDocumentoDTO();
				procdoc.setIdDocumento(docNew.getId());
				procdoc.setIdProcesso(idProcesso);
				procdoc.setIdUsuario(authentication.getUsuario().getId());
				processoDocService.save(procdoc);

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

		return "redirect:/processos";
	}
}
