package padm.io.pad_m.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import padm.io.pad_m.domain.Doc;
import padm.io.pad_m.repository.DocRepository;

@Service
public class DocService {
	@Autowired
	DocRepository docRepository;

	public List<Doc> findAll() {
		return docRepository.findAll();
	}

	public Optional<Doc> findById(Integer id) {
		return docRepository.findById(id);
	}

	@Transactional
	public void save(Doc obj) {
		docRepository.save(obj);
	}

	@Transactional
	public void delete(Doc obj) {
		docRepository.delete(obj);
	}

}
