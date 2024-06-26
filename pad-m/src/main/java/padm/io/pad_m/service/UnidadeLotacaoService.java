package padm.io.pad_m.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import padm.io.pad_m.domain.UnidadeLotacao;

@Service
public class UnidadeLotacaoService {
	@Autowired
	UnidadeLotacaoService unidadeLotacaoService;

	public List<UnidadeLotacao> findAll() {
		return unidadeLotacaoService.findAll();
	}

	public Optional<UnidadeLotacao> findById(Integer id) {
		return unidadeLotacaoService.findById(id);
	}

	@Transactional
	public void save(UnidadeLotacao obj) {
		unidadeLotacaoService.save(obj);
	}

	@Transactional
	public void delete(UnidadeLotacao obj) {
		unidadeLotacaoService.delete(obj);
	}

}
