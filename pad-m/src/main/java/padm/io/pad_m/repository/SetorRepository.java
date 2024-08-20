package padm.io.pad_m.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import padm.io.pad_m.domain.Setor;

public interface SetorRepository extends JpaRepository<Setor, Integer> {
	
	@Query(value="SELECT s FROM Setor s WHERE s.unidadelotacaoid = :unidade ORDER BY nome")
	Optional<Setor> findSetorByUnidade(@Param("unidade") Integer unidade );
	
	
}
