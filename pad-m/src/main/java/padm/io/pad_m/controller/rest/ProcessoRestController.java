package padm.io.pad_m.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import padm.io.pad_m.domain.Processo;
import padm.io.pad_m.security.AuthenticationFacade;
import padm.io.pad_m.service.ProcessoService;

@RestController
@RequestMapping("/api/processos")
public class ProcessoRestController {
	@Autowired
	ProcessoService processoService;
	
	@Autowired
	AuthenticationFacade session;

	@GetMapping
	public ResponseEntity<List<Processo>> findAll() {
		return ResponseEntity.ok(processoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Processo> findById(@PathVariable("id") Integer id) {
		Processo processo = processoService.findById(id).get();
		return ResponseEntity.ok(processo);
	}
	
	@GetMapping("/usuario/tipo/{tipoProcesso}")
	public ResponseEntity<List<Processo>> findAllByUserCriador(@PathVariable("tipoProcesso") String tipoProcesso) {		
		List<Processo> processos = new ArrayList<Processo>();
		
		if(tipoProcesso.equals("Servidor")){ 		
			processos = processoService.findAllByUserCriador(session.getUsuario().getId());
		}else{
			processos = processoService.findAllBySetor(session.getUsuario().getLotacao_id());
		}
		
		return ResponseEntity.ok(processos);
	}
	
	

}
