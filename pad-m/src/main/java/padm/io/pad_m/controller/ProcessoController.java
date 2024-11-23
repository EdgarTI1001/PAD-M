package padm.io.pad_m.controller;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import padm.io.pad_m.domain.Doc;
import padm.io.pad_m.domain.Evento;
import padm.io.pad_m.domain.Processo;
import padm.io.pad_m.domain.Setor;
import padm.io.pad_m.domain.Usuario;
import padm.io.pad_m.security.AuthenticationFacade;
import padm.io.pad_m.service.ClassifService;
import padm.io.pad_m.service.DocService;
import padm.io.pad_m.service.EventoService;
import padm.io.pad_m.service.ProcessoService;
import padm.io.pad_m.service.SetorService;
import padm.io.pad_m.service.SigiloService;
import padm.io.pad_m.service.TipoEventoService;
import padm.io.pad_m.service.TipoProcessoService;
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
	private TipoEventoService tipoEventoService;
	
	@Autowired
	private TipoProcessoService tipoProcessoService;
	
	@Autowired
	private EventoService eventoService;

	@Autowired
	private DocService documentoService;

	@Autowired
	private SetorService setorService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ClassifService classifService;
	
	@Autowired
	private SigiloService sigiloService;

	@GetMapping
	public ModelAndView findAll(@RequestParam("tipagem") Optional<Integer> tipagem) {
		ModelAndView mv = new ModelAndView("consulta/processos");		
		List<Processo> processos = new ArrayList<Processo>();				
		if(tipagem.isPresent()){			
			if(tipagem.get() == 1){
				processos = processoService.findAllTramitadosBySetor(session.getUsuario().getLotacao_id().getId());				
			}else if(tipagem.get() == 2){ 
				processos = processoService.findAllBySetor(session.getUsuario().getLotacao_id().getId());				
			}else if(tipagem.get() == 3){ 
				processos = processoService.findAllArquivadosBySetor(session.getUsuario().getLotacao_id().getId());		
				
			}
		}
		mv.addObject("processos", processos);	
		mv.addObject("setor", session.getUsuario().getLotacao_id().getId());	
		mv.addObject("activePage", "mnuServidor");
		return mv;
	}

	@GetMapping("/new")
	public String frmCadastrar(Model model, @ModelAttribute("processo") Processo processo) {
		model.addAttribute("tipos", tipoProcessoService.findAll());
		model.addAttribute("processo", processo);
		return "form/frmProcesso";
	}

	@PostMapping("/passo2")
	public String frmCadastrarProcessoPasso2(Model model, @ModelAttribute("processo") Processo processo) {
		Optional<Usuario> usuario = usuarioService.findById(session.getUsuario().getId());
		Optional<Setor> setor = setorService.findById(usuario.get().getLotacao_id().getId());

		processo.setUsucriadorId(usuario.get().getId());
		processo.setSetorcriadorId(setor.get());
		processo.setDatacriacao(LocalDateTime.now());
		processo.setFlaganexoprincId(0);
		processo.setTramitado(0);
		processo.setArquivado(0);
		int ano = Year.now().getValue();
		processo.setAno(ano);
		processoService.save(processo);
		
		processo.setNumproc("000"+processo.getId());
		processo.setNumanoproc("000"+processo.getId() + "/" + ano);


		Evento evento = new Evento();
		evento.setTipo_id(tipoEventoService.findById(4).get());
		evento.setProc_id(processo);
		evento.setDoc_id(processo.getDocumento());
		evento.setDataevento(LocalDateTime.now());
		evento.setDatainicio(LocalDateTime.now());
		evento.setUser_id(session.getUsuario());
		evento.setSetor_Id(setorService.findById(session.getUsuario().getLotacao_id().getId()).get());
		String dataFormatada = LocalDateTime.now().format(parser);
		evento.setEvento("Usuario: " + session.getUsuario().getNome() + " Criou o Processo: " + processo.getNumanoproc() + " em " + dataFormatada );
		evento.setFlag(1);
		evento.setPlaced(1);
		eventoService.save(evento);
		
		model.addAttribute("processo", processo);
		model.addAttribute("setores", setorService.findAll());
		model.addAttribute("usuarios", usuarioService.findAll());
		model.addAttribute("classificacoes", classifService.findAll());
		model.addAttribute("visibilidades", sigiloService.findAll());
		return "form/frmProcesso2";
	}

	@PostMapping("/finalizar")
	public String frmCadastrarProcessoFinalizar(@RequestParam(value = "page", defaultValue = "1") int page, Model model,
			@ModelAttribute("processo") Processo processo) {
		processoService.save(processo);
		model.addAttribute("processo", processo);
		List<Doc> docs = documentoService.findAllDocsByUsuarioId(session.getUsuario().getId());
		Doc doc = new Doc();
		
		
		model.addAttribute("doc", doc);
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
		ModelAndView model = new ModelAndView("form/frmProcesso");

		return model;
	}
}
