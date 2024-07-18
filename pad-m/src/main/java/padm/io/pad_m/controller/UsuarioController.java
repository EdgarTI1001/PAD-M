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

import padm.io.pad_m.domain.Usuario;
import padm.io.pad_m.enums.Perfil;
import padm.io.pad_m.service.UsuarioService;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final String frmUsuario = "form/frmCadUsuario";
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	//@Autowired
	//private BCryptPasswordEncoder pe;
	
	//@Autowired
   // private LogService logService;
	

	//@Autowired
	//AuthenticationFacade session;

	@GetMapping
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("consultas/usuarios");
		mv.addObject("usuarios", usuarioService.findAll());
		mv.addObject("activePage", "mnuCandidato"); 
		return mv;
	}

	@GetMapping("/new")
	public String frmCadastrar(Model model, @ModelAttribute("usuario") Usuario usuario) {
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("activePage", "mnuCandidato");		
		
		
		return "form/frmCadUsuario";
	}
	
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/save")
	public String saveObject(@ModelAttribute("usuario") Usuario usuario, BindingResult result) {
		try {
			
		
		
			
			//u.setEmail(usuario.getEmail());
			//u.setSenha(pe.encode(usuario.getSenha()));
			usuario.setAtivo("1");
			usuario.setPerfilId(1);
			
			usuarioService.save(usuario);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	
		return "redirect:/usuarios";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView frmEditar(@PathVariable(name = "id") Integer id) {
		ModelAndView model = new ModelAndView("form/frmCandidato");
		Usuario user = usuarioService.findById(id).get();		
		model.addObject("candidato", user);		
		return model;
	}
}
