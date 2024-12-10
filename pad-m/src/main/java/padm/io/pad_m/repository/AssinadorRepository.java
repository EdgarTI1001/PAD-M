package padm.io.pad_m.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import padm.io.pad_m.domain.Assinador;

public interface AssinadorRepository extends JpaRepository<Assinador, Integer> {

	@Query("SELECT a FROM Assinador a WHERE a.hashdoc = :hash")
	Optional<Assinador> findAByHash(@Param("hash") String hash);
	
	//Assinador findFirstByProcId_id(@Param("idProcesso") Integer idProcesso);
	
	Optional<Assinador> findFirstByDoc_idOrderByIdDesc(@Param("idDoc") Integer idDoc);

}
