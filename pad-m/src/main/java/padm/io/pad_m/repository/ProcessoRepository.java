package padm.io.pad_m.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import padm.io.pad_m.domain.Processo;

public interface ProcessoRepository extends JpaRepository<Processo, Integer> {
	
	@Query("SELECT p from Processo p WHERE p.recrutamento.id =:idRec AND r.avaliador IS null ORDER BY r.tarefa.descricao")
	List<Processo> findAllTafNotAval(@Param("idRec") Long idRec); 
	
}
