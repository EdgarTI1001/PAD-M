package padm.io.pad_m.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import padm.io.pad_m.domain.Servidor;
import padm.io.pad_m.domain.Setor;
import padm.io.pad_m.domain.Usuario;
import padm.io.pad_m.service.ServidorService;
import padm.io.pad_m.service.SetorService;
import padm.io.pad_m.service.UnidadeLotacaoService;
import padm.io.pad_m.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final String frmUsuario = "form/frmCadUsuario";

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ServidorService servidorService;

	@Autowired
	private UnidadeLotacaoService unidadeService;
	
	@Autowired
	private SetorService setorService;

	
	@Value("${path.upload}")
	String upload;
	


	@GetMapping
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("consulta/usuarios");
		mv.addObject("usuarios", usuarioService.findAll());
		mv.addObject("activePage", "mnuCandidato");
		return mv;
	}

	@GetMapping("/new")
	public String frmCadastrar(Model model, @ModelAttribute("usuario") Usuario usuario) {
		List<Servidor> servidores = servidorService.findAll();
		List<Setor> setores = setorService.findAll();
		model.addAttribute("usuario", usuario);
		model.addAttribute("servidores", servidores);
		model.addAttribute("setores", setores);
		model.addAttribute("activePage", "mnuCandidato");
		return "form/frmCadUsuario";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView frmEditar(@PathVariable(name = "id") Integer id) {
		ModelAndView model = new ModelAndView(frmUsuario);
		Usuario user = usuarioService.findById(id).get();
		List<Servidor> servidores = servidorService.findAll();
		model.addObject("servidores", servidores);
		model.addObject("usuario", user);
		return model;
	}

	@PostMapping("/save")
	public String saveObject(@ModelAttribute("usuario") Usuario usuario,  @RequestParam("file") MultipartFile file, BindingResult result) {
		try {			
			usuario.setFlag(1);
			usuario.setDatacriacao(LocalDateTime.now());
			usuario.setUltimoacesso(LocalDateTime.now());			
			System.out.println(usuario.getServidorId().getId());
			Optional<Servidor> s = servidorService.findById(usuario.getServidorId().getId());
		
			if(!file.isEmpty()) { 
				StringBuilder fileNames = new StringBuilder();
		        Path fileNameAndPath = Paths.get(upload, file.getOriginalFilename());
		        fileNames.append(file.getOriginalFilename());
		        Files.write(fileNameAndPath, file.getBytes()); 
		        
		        usuario.setImage(file.getOriginalFilename());
		      
			};
		
			  usuario.setAtivo("S");
			
	        
	        
			usuarioService.save(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/usuarios";
	}

}
