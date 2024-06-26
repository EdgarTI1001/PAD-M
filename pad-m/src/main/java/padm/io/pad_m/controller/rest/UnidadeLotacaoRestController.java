package padm.io.pad_m.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import padm.io.pad_m.domain.UnidadeLotacao;
import padm.io.pad_m.service.UnidadeLotacaoService;

@RestController
@RequestMapping("/api/unidadesLotacoes")
public class UnidadeLotacaoRestController {
	@Autowired
	UnidadeLotacaoService unidadeLotacaoService;

	@GetMapping
	public ResponseEntity<List<UnidadeLotacao>> findAll() {
		return ResponseEntity.ok(unidadeLotacaoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UnidadeLotacao> findById(@PathVariable("id") Integer id) {
		System.out.println("=======================");
		UnidadeLotacao unidadeLotacao = unidadeLotacaoService.findById(id).get();
		return ResponseEntity.ok(unidadeLotacao);
	}

}
