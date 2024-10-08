package padm.io.pad_m.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import padm.io.pad_m.domain.Processo;

public interface ProcessoRepository extends JpaRepository<Processo, Integer> {

	@Query("SELECT p from Processo p WHERE p.setorcriadorId.id =:idSetor AND p.tipo = 'Setor' ORDER BY p.id DESC")
	List<Processo> findAllBySetor(@Param("idSetor") Integer idSetor);
	

	@Query("SELECT p from Processo p WHERE p.usucriadorId =:idUser AND p.tipo = 'Servidor' ORDER BY p.id DESC")
	List<Processo> findAllByUserCriador(@Param("idUser") Integer idUser);


}
