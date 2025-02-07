package padm.io.pad_m.controller.rest;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import padm.io.pad_m.domain.Doc;
import padm.io.pad_m.service.DocService;

@RestController
@RequestMapping("/api/docs")
public class DocRestController {
	private final Path root = Paths.get("./uploads");
	private String pdfDir = "documentos";

	@Autowired
	private DocService docService;

	@PostMapping("/files/download/multiples")
	public ResponseEntity<InputStreamResource> downloadMultipleFiles(@RequestBody int[] idDocs) throws IOException {
	    List<File> files = new ArrayList<>();
	    for (int id : idDocs) {
	        Doc doc = docService.findById(id);
	        File file = new File(root.resolve(pdfDir) + "/" + doc.getHashdoc());
	        if (file.exists()) {
	            files.add(file);
	        }
	    }

	    if (files.isEmpty()) {
	        return ResponseEntity.badRequest().body(null);
	    }

	    byte[] zipBytes = docService.createZip(files);

	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"arquivos.zip\"");
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    headers.setContentLength(zipBytes.length);

	    return ResponseEntity.ok()
	            .headers(headers)
	            .body(new InputStreamResource(new ByteArrayInputStream(zipBytes)));
	}

}
