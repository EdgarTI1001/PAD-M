package padm.io.pad_m.controller;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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
import padm.io.pad_m.domain.Usuario;
import padm.io.pad_m.security.AuthenticationFacade;
import padm.io.pad_m.service.ClassifService;
import padm.io.pad_m.service.ProcessoService;
import padm.io.pad_m.service.SetorService;
import padm.io.pad_m.service.UsuarioService;

@Controller
@RequestMapping("/processos")
public class ProcessoController {

	DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");	

	@Autowired
	AuthenticationFacade session;
	
	@Autowired
	private ProcessoService processoService;
	
	@Autowired
	private SetorService setorService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ClassifService classifService;

	@GetMapping
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("consulta/processos");
		Optional<Usuario> usuario = usuarioService.findById(session.getUsuario().getId());		
		mv.addObject("usuario",usuario.get());
		mv.addObject("setor", setorService.findById(usuario.get().getLotacao_id()));
		mv.addObject("activePage", "mnuServidor");
		return mv;
	}

	@GetMapping("/new")
	public String frmCadastrar(Model model, @ModelAttribute("processo") Processo processo) {
		Optional<Usuario> usuario = usuarioService.findById(session.getUsuario().getId());
		Optional<Setor> setor = setorService.findById(usuario.get().getLotacao_id());		
		model.addAttribute("processo", processo);	
		model.addAttribute("setor",setor.get());
		model.addAttribute("usuario",usuario.get());
		model.addAttribute("classificacoes", classifService.findAll());	
		return "form/frmProcesso";
	}
	
	@PostMapping("/passo2")
	public String frmCadastrarProcessoPasso2(Model model, @ModelAttribute("processo") Processo processo) {
		Optional<Usuario> usuario = usuarioService.findById(session.getUsuario().getId());
		Optional<Setor> setor = setorService.findById(usuario.get().getLotacao_id());
		
		System.out.println(processo.getAssunto());
		model.addAttribute("processo", processo);	
		model.addAttribute("setor",setor.get());
		model.addAttribute("usuario",usuario.get());
		model.addAttribute("classificacoes", classifService.findAll());	
		return "form/frmProcesso2";
	}

	@PostMapping("/save")
	public String saveObject(@ModelAttribute("processo") Processo processo, BindingResult result) {
		try {
			int ano = Year.now().getValue();	
			processo.setAno(ano);

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
