package padm.io.pad_m.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import padm.io.pad_m.domain.Setor;
import padm.io.pad_m.service.SetorService;

@RestController
@RequestMapping("/api/setores")
public class SetorRestController {
	@Autowired
	SetorService setorService;

	@GetMapping
	public ResponseEntity<List<Setor>> findAll() {
		return ResponseEntity.ok(setorService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Setor> findById(@PathVariable("id") Integer id) {
		System.out.println("=======================");
		Setor setor = setorService.findById(id).get();
		return ResponseEntity.ok(setor);
	}

}
