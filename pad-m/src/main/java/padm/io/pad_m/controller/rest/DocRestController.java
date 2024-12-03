package padm.io.pad_m.controller.rest;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import padm.io.pad_m.service.DocService;

@RestController
@RequestMapping("/api/docs")
public class DocRestController {
	private final Path root = Paths.get("./uploads");
	private String pdfDir = "documentos";

	@Autowired
	private DocService docService;


}
