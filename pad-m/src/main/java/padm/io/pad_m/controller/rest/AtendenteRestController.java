package padm.io.pad_m.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import padm.io.pad_m.domain.Atendente;
import padm.io.pad_m.service.AtendenteService;

@RestController
@RequestMapping("/api/atendentes")
public class AtendenteRestController {
	@Autowired
	AtendenteService atendenteService;

	@GetMapping
	public ResponseEntity<List<Atendente>> findAll() {
		return ResponseEntity.ok(atendenteService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Atendente> findById(@PathVariable("id") Integer id) {
		System.out.println("=======================");
		Atendente atendente = atendenteService.findById(id).get();
		return ResponseEntity.ok(atendente);
	}

}
