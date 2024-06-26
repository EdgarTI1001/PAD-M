package padm.io.pad_m.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import padm.io.pad_m.domain.TipoEvento;
import padm.io.pad_m.service.TipoEventoService;

@RestController
@RequestMapping("/api/tiposEventos")
public class TipoEventoRestController {
	@Autowired
	TipoEventoService tipoEventoService;

	@GetMapping
	public ResponseEntity<List<TipoEvento>> findAll() {
		return ResponseEntity.ok(tipoEventoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<TipoEvento> findById(@PathVariable("id") Integer id) {
		System.out.println("=======================");
		TipoEvento tipoEvento = tipoEventoService.findById(id).get();
		return ResponseEntity.ok(tipoEvento);
	}

}
