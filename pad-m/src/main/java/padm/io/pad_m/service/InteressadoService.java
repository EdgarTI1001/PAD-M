package padm.io.pad_m.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import padm.io.pad_m.domain.Interessado;
import padm.io.pad_m.repository.InteressadoRepository;

@Service
public class InteressadoService {
	@Autowired
	InteressadoRepository interessadoRepository;

	public List<Interessado> findAll() {
		return interessadoRepository.findAll();
	}

	public Optional<Interessado> findById(Integer id) {
		return interessadoRepository.findById(id);
	}

	@Transactional
	public void save(Interessado obj) {
		interessadoRepository.save(obj);
	}

	@Transactional
	public void delete(Interessado obj) {
		interessadoRepository.delete(obj);
	}

}
