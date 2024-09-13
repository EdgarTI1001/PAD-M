package padm.io.pad_m.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import padm.io.pad_m.domain.Doc;
import padm.io.pad_m.domain.Processo;
import padm.io.pad_m.domain.ProcessoDocs;
import padm.io.pad_m.domain.Usuario;
import padm.io.pad_m.domain.dto.ProcessoDocumentoDTO;
import padm.io.pad_m.repository.ProcessoDocsRepository;

@Service
public class ProcessoDocsService {
	
	@Autowired
	ProcessoDocsRepository procsDocsRepository;
	
	@Autowired
	DocService docService;
	
	@Autowired
	ProcessoService processoService;
	
	@Autowired
	UsuarioService usuarioService;

	public List<ProcessoDocs> findAll() {
		return procsDocsRepository.findAll();
	}
	
	public List<ProcessoDocs> findAllByUser(Integer usuario) {
		return procsDocsRepository.findAllByUser(usuario);
	}

	public Optional<ProcessoDocs> findById(Integer id) {
		return procsDocsRepository.findById(id);
	}
	
	public List<ProcessoDocs> findAllByProcesso(Integer idProcesso) {
		return procsDocsRepository.findAllByProcesso(idProcesso);
	}

	@Transactional
	public void save(ProcessoDocumentoDTO obj) {
		ProcessoDocs pdocs = new ProcessoDocs();
		Doc documento = docService.findById(obj.getIdDocumento()); 
		pdocs.setDocumento(documento);
		Processo processo = processoService.findById(obj.getIdProcesso()).get();
		pdocs.setProcesso(processo);
		Usuario usuario = usuarioService.findById(obj.getIdUsuario()).get();
		pdocs.setUsuario(usuario);
		pdocs.setDatacad(LocalDateTime.now().toString());			
		procsDocsRepository.save(pdocs);
		
	}

	@Transactional
	public Integer delete(ProcessoDocs obj) {
		Integer ret = 0;
		try {
			procsDocsRepository.delete(obj);
			ret = 1;
		} catch (Exception e) {
			
		}
		return ret;
	}

}
