package padm.io.pad_m.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import padm.io.pad_m.domain.Evento;
import padm.io.pad_m.domain.Tramite;
import padm.io.pad_m.repository.ProcessoRepository;
import padm.io.pad_m.repository.TramiteRepository;

@Service
public class TramiteService {
	@Autowired
	TramiteRepository tramiteRepo;
	
	@Autowired
	ProcessoRepository processoRepo;

	public List<Tramite> findAll() {
		return tramiteRepo.findAll();
	}

	public Optional<Tramite> findById(Integer id) {
		return tramiteRepo.findById(id);
	}

	@Transactional
	public void save(Tramite obj) {
		tramiteRepo.save(obj);
	}

	@Transactional
	public void delete(Tramite obj) {
		tramiteRepo.delete(obj);
	}

}
