package padm.io.pad_m.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import padm.io.pad_m.domain.Doc;
import padm.io.pad_m.domain.Usuario;

public interface DocRepository extends JpaRepository<Doc, Integer> {

    List<Doc> findByUsu_id(Usuario usu_id);
}
