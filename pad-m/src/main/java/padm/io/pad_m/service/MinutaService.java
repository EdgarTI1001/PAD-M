package padm.io.pad_m.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import padm.io.pad_m.domain.Minuta;
import padm.io.pad_m.repository.MinutaRepository;

@Service
public class MinutaService {
	@Autowired
	MinutaRepository minutaRepository;

	public List<Minuta> findAll() {
		return minutaRepository.findAll();
	}

	public Optional<Minuta> findById(Integer id) {
		return minutaRepository.findById(id);
	}

	@Transactional
	public void save(Minuta obj) {
		minutaRepository.save(obj);
	}

	@Transactional
	public void delete(Minuta obj) {
		minutaRepository.delete(obj);
	}

}
