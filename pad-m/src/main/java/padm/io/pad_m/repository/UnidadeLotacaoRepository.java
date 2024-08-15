package padm.io.pad_m.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import padm.io.pad_m.domain.UnidadeLotacao;

public interface UnidadeLotacaoRepository extends JpaRepository<UnidadeLotacao, Integer> {
	
	
	List<UnidadeLotacao> findAll(); 
	
}
