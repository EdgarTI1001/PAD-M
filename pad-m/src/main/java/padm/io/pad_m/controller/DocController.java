package padm.io.pad_m.controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

import com.itextpdf.html2pdf.HtmlConverter;
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

import padm.io.pad_m.domain.Doc;
import padm.io.pad_m.domain.Usuario;
import padm.io.pad_m.fileserver.FilesStorageService;
import padm.io.pad_m.security.IAuthenticationFacade;
import padm.io.pad_m.service.DocService;
import padm.io.pad_m.utils.AlertMessage;

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
    public String uploadFile(RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file, @ModelAttribute("doc") Doc docNew) {

        AlertMessage alertMessage;

        try {
            String fileNameHash = storageService.save(file, "documentos");

            Usuario usuario = authentication.getUsuario();

            Doc doc = new Doc();
            doc.setNomdoc(docNew.getNomdoc());
            doc.setExtdoc(file.getContentType());
            doc.setUsu_id(usuario);
            doc.setData(LocalDateTime.now());
            doc.setHashdoc(fileNameHash);
            docService.save(doc);

            alertMessage = new AlertMessage("success", "Arquivo enviado com sucesso: " + file.getOriginalFilename());
        } catch (Exception e) {
            alertMessage = new AlertMessage("danger", "Não foi possível fazer upload do arquivo: " + file.getOriginalFilename() + ". Error: " + e.getMessage());
        }

        //model.addAttribute("alertMessage", alertMessage);
        redirectAttributes.addFlashAttribute("alertMessage", alertMessage);
        return "redirect:/docs";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        Doc doc = docService.findById(id);
        model.addAttribute("doc", doc);
        return "docs/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoc(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {

        AlertMessage alertMessage;

        Doc doc = docService.findById(id);

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
            alertMessage = new AlertMessage("danger", "Não foi possível fazer upload do arquivo: " + doc.getNomdoc() + ". Error: " + e.getMessage());
          }

        redirectAttributes.addFlashAttribute("alertMessage", alertMessage);
        return "redirect:/docs";
    }

    @PostMapping("/gerarPdf")
    public String gerarPdf(@ModelAttribute("doc") Doc doc, RedirectAttributes redirectAttributes) {

        AlertMessage alertMessage;

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
            docNew.setNomdoc(doc.getNomdoc());
            docNew.setExtdoc("pdf");
            docNew.setUsu_id(usuario);
            docNew.setData(LocalDateTime.now());
            docNew.setHashdoc(fileNameHash);
            docNew.setConteudo(doc.getConteudo());
            docService.save(docNew);

            alertMessage = new AlertMessage("success", "Arquivo gerado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage("danger", "Não foi possível fazer upload do arquivo: " + doc.getNomdoc() + ". Error: " + e.getMessage());
        }

        redirectAttributes.addFlashAttribute("alertMessage", alertMessage);

        return "redirect:/docs";
    }
}
