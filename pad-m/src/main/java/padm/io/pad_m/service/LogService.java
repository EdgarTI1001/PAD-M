package padm.io.pad_m.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import padm.io.pad_m.domain.Log;
import padm.io.pad_m.repository.LogRepository;

@Service
public class LogService {
	@Autowired
	LogRepository logRepository;

	public List<Log> findAll() {
		return logRepository.findAllByOrderByIdDesc();
	}

	@Transactional
	public void save(Log obj) {
		logRepository.save(obj);
	}

	@Transactional
	public void delete(Log obj) {
		logRepository.delete(obj);
	}

}
