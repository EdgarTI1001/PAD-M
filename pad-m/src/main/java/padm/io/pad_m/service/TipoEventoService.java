package padm.io.pad_m.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import padm.io.pad_m.domain.TipoEvento;

@Service
public class TipoEventoService {
	@Autowired
	TipoEventoService tipoEventoService;

	public List<TipoEvento> findAll() {
		return tipoEventoService.findAll();
	}

	public Optional<TipoEvento> findById(Integer id) {
		return tipoEventoService.findById(id);
	}

	@Transactional
	public void save(TipoEvento obj) {
		tipoEventoService.save(obj);
	}

	@Transactional
	public void delete(TipoEvento obj) {
		tipoEventoService.delete(obj);
	}

}
