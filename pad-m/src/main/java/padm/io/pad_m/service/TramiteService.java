package padm.io.pad_m.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import padm.io.pad_m.domain.Tramite;

@Service
public class TramiteService {
	@Autowired
	TramiteService tramiteService;

	public List<Tramite> findAll() {
		return tramiteService.findAll();
	}

	public Optional<Tramite> findById(Integer id) {
		return tramiteService.findById(id);
	}

	@Transactional
	public void save(Tramite obj) {
		try {
			tramiteService.save(obj);
		} catch (Exception e) {
			//e.printStackTrace();
			
		}
		
	}

	@Transactional
	public void delete(Tramite obj) {
		tramiteService.delete(obj);
	}

}
