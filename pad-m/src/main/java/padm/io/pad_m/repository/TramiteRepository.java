package padm.io.pad_m.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import padm.io.pad_m.domain.Evento;
import padm.io.pad_m.domain.Tramite;

public interface TramiteRepository extends JpaRepository<Tramite, Integer> {

	@Query("SELECT t FROM Tramite t WHERE t.setordestino =:idSetor ")
	Integer estaNoSetor(@Param("idSetor") Integer idSetor);

	@Query(value = "SELECT * FROM tbtramite WHERE proc_id = :idProcesso ORDER BY id ASC LIMIT 1", nativeQuery = true)
	Tramite findFirstByProcessoId(@Param("idProcesso") Integer idProcesso);
}
