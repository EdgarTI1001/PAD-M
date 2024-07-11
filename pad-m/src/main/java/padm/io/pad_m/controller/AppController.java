package padm.io.pad_m.controller;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import padm.io.pad_m.service.UsuarioService;

@Controller
@RequestMapping("/")
public class AppController {
	private static final String REDIRECT_STEP1 = "redirect:/step1";
	DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Autowired
    private UsuarioService userService;
	

	
	@GetMapping
	public ModelAndView index() {
	
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	/*
	@GetMapping("/login")
	public ModelAndView indexLogin(@ModelAttribute("credencial") CredenciaisDTO credencial, HttpSession httpSession) {
		if(credencial.getUsername()==null) {
			return new ModelAndView(REDIRECT_STEP1);
		}
		ModelAndView mv = new ModelAndView("frmLogin");
		mv.addObject("activePage", "mnuLogin");
		boolean isProducao = session.isProducao();
		credencial.setUsername((String)httpSession.getAttribute("username"));
		mv.addObject("isProducao", isProducao);
		return mv;
	}
	
	@GetMapping("/step1")
	public ModelAndView step_first(@ModelAttribute("credencial") CredenciaisDTO credencial) {
		ModelAndView mv = new ModelAndView("frmStep1");
		mv.addObject("activePage", "mnuLogin");
		boolean isProducao = session.isProducao();
		mv.addObject("isProducao", isProducao);
		return mv;
	}
	
	@PostMapping("/2factor")
	public ModelAndView step_second(@Valid @ModelAttribute("credencial") CredenciaisDTO credencial, 
			 								HttpSession httpSession, RedirectAttributes redirAttr) {
		ModelAndView mv = new ModelAndView("frmLogin");
		
		Optional<Usuario> usuario = userService.findByEmail(credencial.getUsername());
		if(!usuario.isPresent()) {
			redirAttr.addFlashAttribute("error", "Usuário ou senha inválidos!");
			return new ModelAndView(REDIRECT_STEP1);
		}
		
		// verifica se a senha informada
		boolean senhasCoincidem = passwordEncoder.matches(credencial.getPassword(), usuario.get().getSenha());
		if (!senhasCoincidem) {
			redirAttr.addFlashAttribute("error", "Usuário ou senha inválidos!");
			return new ModelAndView(REDIRECT_STEP1);
		}

		httpSession.setAttribute("username", credencial.getUsername());
		httpSession.setAttribute("password", credencial.getPassword());
		boolean isProducao = session.isProducao();
		mv.addObject("activePage", "mnuLogin");
		mv.addObject("isProducao", isProducao);
		return mv;
	}
	*/
	
}
