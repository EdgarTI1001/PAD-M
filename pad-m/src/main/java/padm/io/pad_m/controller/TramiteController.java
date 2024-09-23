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

import padm.io.pad_m.domain.Processo;
import padm.io.pad_m.domain.Setor;
import padm.io.pad_m.domain.Tramite;
import padm.io.pad_m.service.ProcessoService;
import padm.io.pad_m.service.SetorService;
import padm.io.pad_m.service.TramiteService;

@Controller
@RequestMapping("/tramite")
public class TramiteController {

	DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Autowired
	private ProcessoService processoService; 
	
	@Autowired
	private TramiteService tramiteService;
	
	@Autowired
	private SetorService setorService;
	
	@GetMapping
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("consulta/tramites");
		
		return mv;
	}

	@GetMapping("/new/{idProcesso}")
	public String frmCadastrar(Model model,@PathVariable("idProcesso") Integer idProcesso, @ModelAttribute("tramite") Tramite tramite) {		
		model.addAttribute("processo", processoService.findById(idProcesso).get());		
		Processo p = processoService.findById(idProcesso).get();		
		model.addAttribute("setores", setorService.findAll());		
		model.addAttribute("tramite", tramite);
		return "form/frmTramite";
	}

	@PostMapping("/save")
	public String saveObject(@ModelAttribute("setor") Setor setor, BindingResult result) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/setores";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView frmEditar(@PathVariable(name = "id") Integer id) {
		ModelAndView model = new ModelAndView("form/frmSetor");
		return model;
	}
}
