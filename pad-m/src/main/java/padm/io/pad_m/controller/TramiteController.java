package padm.io.pad_m.controller;

import java.time.LocalDateTime;
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

import padm.io.pad_m.domain.Evento;
import padm.io.pad_m.domain.Setor;
import padm.io.pad_m.domain.Tramite;
import padm.io.pad_m.security.AuthenticationFacade;
import padm.io.pad_m.service.AtendenteService;
import padm.io.pad_m.service.EventoService;
import padm.io.pad_m.service.FinalidadeService;
import padm.io.pad_m.service.GestorService;
import padm.io.pad_m.service.ModeradorService;
import padm.io.pad_m.service.ProcessoService;
import padm.io.pad_m.service.SetorService;
import padm.io.pad_m.service.SigiloService;
import padm.io.pad_m.service.TipoEventoService;
import padm.io.pad_m.service.TramiteService;
import padm.io.pad_m.service.UsuarioService;

@Controller
@RequestMapping("/tramite")
public class TramiteController {

	DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Autowired
	private ProcessoService processoService; 
	
	@Autowired
	private TipoEventoService tipoEventoService; 
	
	@Autowired
	private FinalidadeService finalidadeService; 
	
	@Autowired
	private ModeradorService moderadorService; 
	
	@Autowired
	private GestorService gestorService; 
	
	@Autowired
	private AtendenteService atendenteService; 
	
	@Autowired
	private TramiteService tramiteService;
	
	@Autowired
	private SigiloService sigiloService;
	
	@Autowired
	private EventoService eventoService;
	
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
			Optional<Setor> setor =  setorService.findById(tramite.getSetordestino().getId());
			Evento evento = eventoService.findFirstByProcessoId(tramite.getProcId().getId()); 
			evento.setDatasaida(LocalDateTime.now());
			evento.setDatatermino(LocalDateTime.now());
			eventoService.save(evento);
			
			tramite.setTipo("TIPO");
			
			tramite.setSetororigem(setorService.findById(session.getUsuario().getLotacao_id()).get() ); 
			tramite.setSetorcriador(setorService.findById( tramite.getProcId().getSetorcriadorId().getId()).get());			
			tramite.setLocaltramite(session.getUsuario().getLotacao_id());
			
			tramite.setDatachegada(LocalDateTime.now());			
			tramite.setDatasaida(null);
			tramite.setDatacheck(LocalDateTime.now());	
			tramite.setDatavencimentoresposta(LocalDateTime.now().plusDays(tramite.getQtdDiasResposta())); 
			
			tramite.setUserId(session.getUsuario());
			tramite.setUsercriadorId(session.getUsuario().getId());
			tramite.setResponsavelId(session.getUsuario().getId());
			
			tramite.setAtendenteId(atendenteService.findById(1).get());
			tramite.setModeradorId(moderadorService.findById(1).get());
			tramite.setGestorId(gestorService.findById(1).get());
			
			
			tramite.setTramitacao("Processo: " +tramite.getProcId().getNumanoproc() + " - " + tramite.getProcId().getAssunto() + " Recebido no Setor " + 
					setor.get().getNome() + " Em " + LocalDateTime.now());
			tramite.setFinalidadeId(finalidadeService.findById(1).get().getId());
			
			
			tramite.setDataarquivamento(null);
			tramite.setDatadesarquivamento(null);
			tramite.setSigiloId(sigiloService.findById(1).get());
			
			tramite.setVisibilidade("Publico");
			tramite.setRecebimento(1);
			tramite.setDatarecebimento(LocalDateTime.now());		
				
			tramite.setEspera(1);
			tramite.setDatainicioespera(LocalDateTime.now());
			tramite.setDatafimespera(null);
			
			tramite.setSeq(1);
			tramite.setFlag(1);
			tramite.setPlaced(1);

			tramiteService.save(tramite);
			
			Evento novoEvento = new Evento();
			
		
			novoEvento.setDatachegada(LocalDateTime.now());
			novoEvento.setDataevento(LocalDateTime.now());
			novoEvento.setDatainicio(LocalDateTime.now());
			novoEvento.setProc_id(processoService.findById(tramite.getProcId().getId()).get());
			novoEvento.setDoc_id(tramite.getProcId().getDocumento());
			novoEvento.setTipo_id(tipoEventoService.findById(1).get());
			novoEvento.setEvento("Usuario : " + session.getUsuario().getNome() + " Enviou Processo : " + tramite.getProcId().getNumanoproc() + " - " +  tramite.getProcId().getAssunto()
					+ " para o Setor " + setor.get().getNome()  + " Em " + LocalDateTime.now());
			
			eventoService.save(novoEvento);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "redirect:/processos";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView frmEditar(@PathVariable(name = "id") Integer id) {
		ModelAndView model = new ModelAndView("form/frmSetor");
		return model;
	}
	
	
	
	
}
