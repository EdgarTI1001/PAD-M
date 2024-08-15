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
import padm.io.pad_m.service.ProcessoService;

@Controller
@RequestMapping("/processos")
public class ProcessoController {

	DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");	

	@Autowired
	private ProcessoService processoService;

	@GetMapping
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("consulta/processos");
		mv.addObject("processos", processoService.findAll());
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
			
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/servidores";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView frmEditar(@PathVariable(name = "id") Integer id) {
		ModelAndView model = new ModelAndView("form/frmServidor");
		
		return model;
	}
}
