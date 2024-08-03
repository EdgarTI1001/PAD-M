package padm.io.pad_m.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import padm.io.pad_m.domain.Usuario;
import padm.io.pad_m.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {
	@Autowired
	UsuarioService usuarioService;

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

}
