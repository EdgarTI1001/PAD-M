package padm.io.pad_m.controller;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import padm.io.pad_m.domain.Doc;
import padm.io.pad_m.domain.Processo;
import padm.io.pad_m.domain.Setor;
import padm.io.pad_m.domain.Usuario;
import padm.io.pad_m.security.AuthenticationFacade;
import padm.io.pad_m.service.ClassifService;
import padm.io.pad_m.service.DocService;
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
	private DocService documentoService;

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
		List<Processo> processosSetor =  processoService.findAllBySetor(session.getUsuario().getLotacao_id());
		List<Processo> processosUser =  processoService.findAllBySetor(session.getUsuario().getId());
		mv.addObject("usuario", usuario.get());
		mv.addObject("processosSetor",processosSetor);
		mv.addObject("processosUser",processosUser);
		mv.addObject("setor", setorService.findById(usuario.get().getLotacao_id()));
		mv.addObject("activePage", "mnuServidor");
		return mv;
	}

	@GetMapping("/new")
	public String frmCadastrar(Model model, @ModelAttribute("processo") Processo processo) {
		model.addAttribute("processo", processo);
		return "form/frmProcesso";
	}

	@PostMapping("/passo2")
	public String frmCadastrarProcessoPasso2(Model model, @ModelAttribute("processo") Processo processo) {
		Optional<Usuario> usuario = usuarioService.findById(session.getUsuario().getId());
		Optional<Setor> setor = setorService.findById(usuario.get().getLotacao_id());

		processo.setUsucriadorId(usuario.get().getId());
		processo.setSetorcriadorId(setor.get());
		processo.setDatacriacao(LocalDateTime.now());
		int ano = Year.now().getValue();
		processo.setAno(ano);
		processoService.save(processo);
		processo.setNumproc(processo.getId());
		processo.setNumanoproc(processo.getId() + "/" + ano);

		model.addAttribute("processo", processo);
		model.addAttribute("setores", setorService.findAll());
		model.addAttribute("usuarios", usuarioService.findAll());
		model.addAttribute("classificacoes", classifService.findAll());
		return "form/frmProcesso2";
	}

	@PostMapping("/finalizar")
	public String frmCadastrarProcessoFinalizar(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
			@ModelAttribute("processo") Processo processo) {

		processoService.save(processo);
		model.addAttribute("processo", processo);
		List<Doc> docs = documentoService.findAllDocsByUsuarioId(18);
		model.addAttribute("documentos", docs);

		return "form/frmProcesso3";
	}

	@PostMapping("/save")
	public String saveObject(@ModelAttribute("processo") Processo processo, BindingResult result) {
		try {

			processoService.save(processo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/processos";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView frmEditar(@PathVariable(name = "id") Integer id) {
		ModelAndView model = new ModelAndView("form/frmServidor");

		return model;
	}
}
