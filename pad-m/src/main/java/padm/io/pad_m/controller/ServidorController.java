package padm.io.pad_m.controller;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import padm.io.pad_m.domain.Servidor;
import padm.io.pad_m.service.ServidorService;

@Controller
@RequestMapping("/servidores")
public class ServidorController {

	DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");	

	@Autowired
	private ServidorService servidorService;

	@GetMapping
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("consultas/servidores");
		mv.addObject("servidores", servidorService.findAll());
		mv.addObject("activePage", "mnuServidor");
		return mv;
	}

	@GetMapping("/new")
	public String frmCadastrar(Model model, @ModelAttribute("servidor") Servidor servidor) {
		model.addAttribute("servidor", servidor);		
		return "form/frmServidor";
	}

	@PostMapping("/save")
	public String saveObject(@ModelAttribute("servidor") Servidor servidor, BindingResult result) {
		try {
			Servidor s = new Servidor();
			servidorService.save(s);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/servidores";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView frmEditar(@PathVariable(name = "id") Integer id) {
		ModelAndView model = new ModelAndView("form/frmServidor");
		Servidor s = servidorService.findById(id).get();
		model.addObject("servidor", s);
		return model;
	}
}
