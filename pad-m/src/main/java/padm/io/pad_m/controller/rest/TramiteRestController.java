package padm.io.pad_m.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import padm.io.pad_m.domain.Tramite;
import padm.io.pad_m.service.TramiteService;

@RestController
@RequestMapping("/api/tramites")
public class TramiteRestController {
	@Autowired
	TramiteService tramiteService;

	@GetMapping
	public ResponseEntity<List<Tramite>> findAll() {
		return ResponseEntity.ok(tramiteService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tramite> findById(@PathVariable("id") Integer id) {
		System.out.println("=======================");
		Tramite tramite = tramiteService.findById(id).get();
		return ResponseEntity.ok(tramite);
	}

}
