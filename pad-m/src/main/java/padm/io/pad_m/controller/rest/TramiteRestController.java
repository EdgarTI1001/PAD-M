package padm.io.pad_m.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import padm.io.pad_m.domain.Tramite;
import padm.io.pad_m.domain.dto.ResultDTO;
import padm.io.pad_m.service.TramiteService;
import padm.io.pad_m.service.UsuarioService;

@RestController
@RequestMapping("/api/tramites")
public class TramiteRestController {
	@Autowired
	TramiteService tramiteService;
	
	@Autowired
	UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Tramite>> findAll() {
		return ResponseEntity.ok(tramiteService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tramite> findById(@PathVariable("id") Integer id) {	
		Tramite tramite = tramiteService.findById(id).get();
		return ResponseEntity.ok(tramite);
	}
	
	@GetMapping("/processo/{idProcesso}")
	public ResponseEntity<Tramite> findFirstByProcessoId(@PathVariable("idProcesso") Integer idProcesso) {	
		Tramite tramite = tramiteService.findFirstByProcId(idProcesso);
		return ResponseEntity.ok(tramite);
	}
	
	
	@GetMapping("/processo/all/{idProcesso}")
	public ResponseEntity<List<Tramite>> findAllByIdProcesso(@PathVariable("idProcesso") Integer idProcesso) {	
		List<Tramite> tramites = tramiteService.findAllByIdProcesso(idProcesso);
		return ResponseEntity.ok(tramites);
	}
	
	
	@GetMapping("/processo/all/vencidosBySetor/{idSetor}")
	public ResponseEntity<List<Tramite>> findAllTramitesVencidos(@PathVariable("idSetor") Integer idSetor) {	
		List<Tramite> tramites = tramiteService.findAllTramitesVencidos(idSetor);
		return ResponseEntity.ok(tramites);
	}
	
	
	@GetMapping("/processo/id/{idProcesso}/responsavel/{idResponsavel}")
	public ResponseEntity<ResultDTO> findLastTramiteByProcesso(@PathVariable("idProcesso") Integer idProcesso, @PathVariable("idResponsavel") Integer idResponsavel) {		
		ResultDTO result = new ResultDTO();
		try {
			Tramite tramite = tramiteService.findFirstByProcId(idProcesso);			
			tramite.setResponsavelId(usuarioService.findById(idResponsavel).get());
			tramiteService.save(tramite);
			result.setRet(1);
			result.setMensagem("Realizado Com Sucesso");
			result.setType("success");
		} catch (Exception e) {		
			e.printStackTrace();
			result.setRet(0);
			result.setMensagem("Ocorreu um Erro");
			result.setType("error");
		}
		
		return ResponseEntity.ok(result);
	}
	
	

}
