package padm.io.pad_m.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import padm.io.pad_m.domain.Tramite;

public interface TramiteRepository extends JpaRepository<Tramite, Integer> {
	
	
	@Query("SELECT t FROM Tramite t WHERE t.setordestino =:idSetor ")
	Integer estaNoSetor( @Param("idSetor") Integer idSetor );
}
