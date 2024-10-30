package padm.io.pad_m.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import padm.io.pad_m.domain.Processo;
import padm.io.pad_m.domain.TipoProcesso;

@Service
public class TipoProcessoService {
	@Autowired
	TipoProcessoService tipoProcessoService;

	public List<TipoProcesso> findAll() {
		return tipoProcessoService.findAll();
	}

	public Optional<TipoProcesso> findById(Integer id) {
		return tipoProcessoService.findById(id);
	}

	public List<TipoProcesso> findAllBySetor(Integer id) {
		return tipoProcessoService.findAllBySetor(id);
	}

	@Transactional
	public TipoProcesso save(TipoProcesso obj) {
		return tipoProcessoService.save(obj);
	}

	@Transactional
	public void delete(TipoProcesso obj) {
		tipoProcessoService.delete(obj);
	}

}
