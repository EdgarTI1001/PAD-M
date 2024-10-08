package padm.io.pad_m.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import padm.io.pad_m.domain.Processo;
import padm.io.pad_m.repository.ProcessoRepository;

@Service
public class ProcessoService {
	@Autowired
	ProcessoRepository processoRepository;

	public List<Processo> findAll() {
		return processoRepository.findAll();
	}

	public Optional<Processo> findById(Integer id) {
		return processoRepository.findById(id);
	}

	public List<Processo> findAllBySetor(Integer id) {
		return processoRepository.findAllBySetor(id);
	}

	public List<Processo> findAllByUserCriador(Integer id) {
		return processoRepository.findAllByUserCriador(id);
	}

	@Transactional
	public Processo save(Processo obj) {
		return processoRepository.save(obj);
	}

	@Transactional
	public void delete(Processo obj) {
		processoRepository.delete(obj);
	}

}
