package padm.io.pad_m.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import padm.io.pad_m.service.DocService;

@RestController
@RequestMapping("/api/docs")
public class DocRestController {
	@Autowired
	DocService docService;


}
