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

import padm.io.pad_m.domain.Usuario;
import padm.io.pad_m.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final String frmUsuario = "form/frmCadUsuario";

	@Autowired
	private UsuarioService usuarioService;



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

	@GetMapping("/edit/{id}")
	public ModelAndView frmEditar(@PathVariable(name = "id") Integer id) {
		ModelAndView model = new ModelAndView(frmUsuario);
		Usuario user = usuarioService.findById(id).get();
		model.addObject("candidato", user);
		return model;
	}

	@PostMapping("/save")
	public String saveObject(@ModelAttribute("usuario") Usuario usuario, BindingResult result) {
		try {			
			usuario.setPerfilId(1);
			usuario.setDatacriacao(LocalDateTime.now());
			usuario.setUltimoacesso(LocalDateTime.now());
			usuarioService.save(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/usuarios";
	}

}
