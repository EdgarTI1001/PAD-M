package padm.io.pad_m.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import padm.io.pad_m.domain.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

	List<Log> findAllByOrderByIdDesc();

}
