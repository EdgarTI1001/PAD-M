package padm.io.pad_m.controller;

import java.time.LocalDateTime;
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

import padm.io.pad_m.domain.Tramite;
import padm.io.pad_m.security.AuthenticationFacade;
import padm.io.pad_m.service.ProcessoService;
import padm.io.pad_m.service.SetorService;
import padm.io.pad_m.service.SigiloService;
import padm.io.pad_m.service.TramiteService;
import padm.io.pad_m.service.UsuarioService;

@Controller
@RequestMapping("/tramite")
public class TramiteController {

	DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Autowired
	private ProcessoService processoService; 
	
	@Autowired
	private TramiteService tramiteService;
	
	@Autowired
	private SigiloService sigiloService;
	
	@Autowired
	private SetorService setorService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	AuthenticationFacade session;
	
	@GetMapping
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("consulta/tramites");
		
		return mv;
	}

	@GetMapping("/new/{idProcesso}")
	public String frmCadastrar(Model model,@PathVariable("idProcesso") Integer idProcesso, @ModelAttribute("tramite") Tramite tramite) {
		
		model.addAttribute("processo", processoService.findById(idProcesso).get());		
		
		model.addAttribute("setores", setorService.findAll());	
		model.addAttribute("usuarios", usuarioService.findAll());	
		model.addAttribute("tramite", tramite);
		return "form/frmTramite";
	}

	@PostMapping("/save")
	public String saveObject(@ModelAttribute("tramite") Tramite tramite, BindingResult result) {
		try {
			System.out.println("=======================================");
			tramite.setDatasaida(LocalDateTime.now());
			tramite.setDatarecebimento(LocalDateTime.now());
			tramite.setSeq(1);
			tramite.setPlaced(1);
			tramite.setUserId(session.getUsuario());
			tramite.setSetororigem(session.getUsuario().getLotacao_id());
			tramite.setSigiloId(sigiloService.findById(1).get());
			System.out.println(tramite.toString());
			tramiteService.save(tramite);
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
