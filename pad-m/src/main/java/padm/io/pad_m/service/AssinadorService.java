package padm.io.pad_m.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import padm.io.pad_m.domain.Assinador;
import padm.io.pad_m.repository.AssinadorRepository;

@Service
public class AssinadorService {
	@Autowired
	AssinadorRepository assinadorRepositor;

	public List<Assinador> findAll() {
		return assinadorRepositor.findAll();
	}

	public Optional<Assinador> findById(Integer id) {
		return assinadorRepositor.findById(id);
	}

	public Optional<Assinador> findAByHash(String hash) {
		return assinadorRepositor.findAByHash(hash);
	}
	
	public Optional<Assinador> findFirstByDoc_id(Integer idDoc) {
		return assinadorRepositor.findFirstByDoc_id(idDoc);
	}

	@Transactional
	public void save(Assinador obj) {
		assinadorRepositor.save(obj);
	}

	@Transactional
	public void delete(Assinador obj) {
		assinadorRepositor.delete(obj);
	}

}
