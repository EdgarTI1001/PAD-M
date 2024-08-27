package padm.io.pad_m.controller;

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

import padm.io.pad_m.domain.Doc;
import padm.io.pad_m.service.DocService;

@Controller
@RequestMapping("/docs")
public class DocController {
    
    @Autowired
    private DocService docService;

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

    @PostMapping("/save")
    public String saveDoc(@ModelAttribute("doc") Doc doc) {
        docService.save(doc);
        return "redirect:/docs";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        Doc doc = docService.findById(id);
        model.addAttribute("doc", doc);
        return "docs/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoc(@PathVariable("id") Integer id) {
        docService.deleteById(id);
        return "redirect:/docs";
    }
}
