package padm.io.pad_m.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

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

	@Transactional
	public void deleteById(Integer id) {
		docRepository.deleteById(id);
	}
	
	@Transactional
	public void delete(Doc obj) {
		docRepository.delete(obj);
	}


	public List<Doc> findAllDocsByUsuario(Usuario usuario) {
		return docRepository.findAllDocumentosByUsuario(usuario);
	}

	public List<Doc> findAllDocsByUsuarioId(Integer usuarioId) {
		Usuario usuario = new Usuario();
		usuario.setId(usuarioId);
		return findAllDocsByUsuario(usuario);
	}
	
	 public void downloadZipFile(HttpServletResponse response, List<String> listOfFileNames) {
	        response.setContentType("application/zip");
	        response.setHeader("Content-Disposition", "attachment; filename=arquivos_do_processo.zip");
	        try(ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
	            for(String fileName : listOfFileNames) {
	                FileSystemResource fileSystemResource = new FileSystemResource(fileName);
	                ZipEntry zipEntry = new ZipEntry(fileSystemResource.getFilename());
	                zipEntry.setSize(fileSystemResource.contentLength());
	                zipEntry.setTime(System.currentTimeMillis());

	                zipOutputStream.putNextEntry(zipEntry);

	                StreamUtils.copy(fileSystemResource.getInputStream(), zipOutputStream);
	                zipOutputStream.closeEntry();
	            }
	            zipOutputStream.finish();
	        } catch (IOException e) {
	        	e.printStackTrace();
	            //logger.error(e.getMessage(), e);
	        }
	    }
}
