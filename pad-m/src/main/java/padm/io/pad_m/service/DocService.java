package padm.io.pad_m.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
	
	public byte[] createZip(List<File> files) throws IOException {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    
	    try (ZipOutputStream zipOut = new ZipOutputStream(baos)) {
	        for (File file : files) {
	            if (file.exists()) {
	                System.out.println("Adicionando ao ZIP: " + file.getAbsolutePath());

	                ZipEntry zipEntry = new ZipEntry(file.getName());
	                zipOut.putNextEntry(zipEntry);
	                Files.copy(file.toPath(), zipOut);
	                zipOut.closeEntry();
	            } else {
	                System.out.println("Arquivo não encontrado: " + file.getAbsolutePath());
	            }
	        }
	    }
	    
	    

	    return baos.toByteArray();
	}
}
