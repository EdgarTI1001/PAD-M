package padm.io.pad_m.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import padm.io.pad_m.domain.Tramite;

public interface TramiteRepository extends JpaRepository<Tramite, Integer> {

	@Query("SELECT t FROM Tramite t WHERE t.setordestino =:idSetor ")
	Integer estaNoSetor(@Param("idSetor") Integer idSetor);

	@Query(value = "SELECT * FROM tbtramite WHERE proc_id = :idProcesso ORDER BY id DESC LIMIT 1", nativeQuery = true)
	Tramite findFirstByProcessoId(@Param("idProcesso") Integer idProcesso);
	
	@Query(value = "SELECT * FROM tbtramite WHERE proc_id = :idProcesso ORDER BY id DESC", nativeQuery = true)
	List<Tramite> findAllByIdProcesso(@Param("idProcesso") Integer idProcesso);
	
}
