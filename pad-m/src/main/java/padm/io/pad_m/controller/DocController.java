package padm.io.pad_m.controller;

import java.time.LocalDateTime;

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
        Pageable pageable = PageRequest.of(page, 5); // 5 items por página
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
    public String uploadFile(Model model, @RequestParam("file") MultipartFile file) {

        String message = "";

        try {
            String fileNameHash = storageService.save(file, "documentos");

            Usuario usuario = authentication.getUsuario();

            Doc doc = new Doc();
            doc.setNomdoc(file.getOriginalFilename());
            doc.setExtdoc(file.getContentType());
            doc.setUsu_id(usuario);
            doc.setData(LocalDateTime.now());
            doc.setNomhash(fileNameHash);
            docService.save(doc);

            message = "Arquivo enviado com sucesso: " + file.getOriginalFilename();
            model.addAttribute("message", message);
        } catch (Exception e) {
            message = "Não foi possível fazer upload do arquivo: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            model.addAttribute("message", message);
        }

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

        Doc doc = docService.findById(id);

        try {

            boolean existed = storageService.delete(doc.getNomhash(), "documentos");
      
            if (existed) {
              redirectAttributes.addFlashAttribute("message", "Arquivo excluido com sucesso! " + doc.getNomdoc());
              docService.deleteById(id);
            } else {
              redirectAttributes.addFlashAttribute("message", "Arquivo não existe!");
            }
          } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message",
                "Não foi possível fazer upload do arquivo: " + doc.getNomdoc() + ". Error: " + e.getMessage());
          }
        
        return "redirect:/docs";
    }
}
