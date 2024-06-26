package padm.io.pad_m.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import padm.io.pad_m.domain.Usuario;

@Service
public class UsuarioService {
	@Autowired
	UsuarioService usuarioService;

	public List<Usuario> findAll() {
		return usuarioService.findAll();
	}

	public Optional<Usuario> findById(Integer id) {
		return usuarioService.findById(id);
	}

	@Transactional
	public void save(Usuario obj) {
		usuarioService.save(obj);
	}

	@Transactional
	public void delete(Usuario obj) {
		usuarioService.delete(obj);
	}

}
