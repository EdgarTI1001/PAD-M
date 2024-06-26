package padm.io.pad_m.controller.rest;

import java.util.List;

import javax.print.Doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import padm.io.pad_m.service.DocService;

@RestController
@RequestMapping("/api/docs")
public class DocRestController {
	@Autowired
	DocService docService;

	@GetMapping
	public ResponseEntity<List<Doc>> findAll() {
		return ResponseEntity.ok(docService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Doc> findById(@PathVariable("id") Integer id) {
		System.out.println("=======================");
		Doc doc = docService.findById(id).get();
		return ResponseEntity.ok(doc);
	}

}
