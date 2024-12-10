package padm.io.pad_m.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import padm.io.pad_m.domain.Doc;
import padm.io.pad_m.domain.Usuario;
import padm.io.pad_m.repository.DocRepository;

@Service
public class DocService {

	@Autowired
	private DocRepository docRepository;

	public Page<Doc> findAll(Pageable pageable) {
		return docRepository.findAll(pageable);
	}

	public Doc findById(Integer id) {
		return docRepository.findById(id).orElse(null);
	}

	public Optional<Doc> findByHashdoc(String hash) {
		return docRepository.findByHashdoc(hash);
	}

	public Doc save(Doc doc) {
		return docRepository.save(doc);
	}

	public void deleteById(Integer id) {
		docRepository.deleteById(id);
	}

	public List<Doc> findAllDocsByUsuario(Usuario usuario) {
		return docRepository.findAllDocumentosByUsuario(usuario);
	}

	public List<Doc> findAllDocsByUsuarioId(Integer usuarioId) {
		Usuario usuario = new Usuario();
		usuario.setId(usuarioId);
		return findAllDocsByUsuario(usuario);
	}
}
