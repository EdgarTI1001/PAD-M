package padm.io.pad_m.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import padm.io.pad_m.domain.Interessado;
import padm.io.pad_m.service.InteressadoService;

@RestController
@RequestMapping("/api/interessados")
public class InteressadoRestController {
	@Autowired
	InteressadoService interessadoService;

	@GetMapping
	public ResponseEntity<List<Interessado>> findAll() {
		return ResponseEntity.ok(interessadoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Interessado> findById(@PathVariable("id") Integer id) {
		System.out.println("=======================");
		Interessado interessado = interessadoService.findById(id).get();
		return ResponseEntity.ok(interessado);
	}

}
