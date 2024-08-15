package padm.io.pad_m.controller;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import padm.io.pad_m.domain.Usuario;
import padm.io.pad_m.service.UsuarioService;

@Controller
@RequestMapping("/")
public class AppController {
	private static final String REDIRECT_STEP1 = "redirect:/login";
	DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Autowired
    private UsuarioService userService;
	
	@Value("${path.upload}")
	String upload;

	
	@GetMapping()
	public ModelAndView index(Model model) {
		System.out.println("===============");
		ModelAndView mv = new ModelAndView("index");
		upload += "\\";		
		String path = upload.substring(0, 11);
		
		model.addAttribute("upload", path);
		model.addAttribute("activePage", "mnuMeuRegistro");
		return mv;
	}
	@GetMapping("/login")
	public ModelAndView setupLogin(Model model) {
		ModelAndView mv = new ModelAndView("frmLogin");
		//model.addAttribute("isProducao", ambiente.isAmbienteProducao());
		return mv;
	}
	
	@PostMapping("/step1")
	public ModelAndView step_first(@ModelAttribute("usuario") Usuario user,HttpSession httpSession,RedirectAttributes redirAttr) {
		
		
		Optional<Usuario> usuario = userService.findByNickName(user.getNickname());
		if(!usuario.isPresent()) {
			redirAttr.addFlashAttribute("error", "Usuário ou senha inválidos!");
			return new ModelAndView(REDIRECT_STEP1);
		}
	
		httpSession.setAttribute("username", usuario.get(). getNickname());
		httpSession.setAttribute("password", usuario.get().getSenha());
		ModelAndView mv = new ModelAndView("index2");
		mv.addObject("activePage", "mnuLogin");
		//boolean isProducao = session.isProducao();
		//mv.addObject("isProducao", isProducao);
		return mv;
	}
	/*
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
