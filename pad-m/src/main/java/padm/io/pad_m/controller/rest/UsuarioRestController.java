package padm.io.pad_m.controller.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import padm.io.pad_m.domain.Usuario;
import padm.io.pad_m.domain.dto.ResultDTO;
import padm.io.pad_m.security.UserDetailsImpl;
import padm.io.pad_m.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {
	@Autowired
	UsuarioService usuarioService;
	
	@Value("${path.upload}")
	String upload;

	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		return ResponseEntity.ok(usuarioService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable("id") Integer id) {		
		Usuario usuario = usuarioService.findById(id).get();
		return ResponseEntity.ok(usuario);
	}
	
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		Optional<Usuario> user = usuarioService.findById(id);
		usuarioService.delete(user.get());
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping("/{id}/{ativo}")
	public ResultDTO ativar(@PathVariable(name = "id") Integer id, @PathVariable(name = "ativo") String ativo,
			@AuthenticationPrincipal UserDetailsImpl loggedUser) {
		ResultDTO result = new ResultDTO();

		try {
			Usuario u = new Usuario();
			u =  usuarioService.findById(id).get();
			u.setAtivo(ativo);
			usuarioService.save(u);

			result.setMsg("Processamento realizado", "success", 1);

		} catch (Exception e) {
			result.setMsg("Erro", "danger", 0);
			e.printStackTrace();
		}
		return result;
	}
	
	@GetMapping("/display/image/{id}")
    public void  displayItemImage(@PathVariable Integer id,HttpServletRequest request,HttpServletResponse response) throws IOException {
    	Usuario u = new Usuario();
		u =  usuarioService.findById(id).get();
		
		 File file = new File(upload, u.getImage());
	        response.setHeader("Content-Type", "JPG");
	        response.setHeader("Content-Length", String.valueOf(file.length()));
	        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
	        Files.copy(file.toPath(), response.getOutputStream());
	        
    	
    }

}
