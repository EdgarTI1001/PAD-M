package padm.io.pad_m.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import padm.io.pad_m.domain.Doc;
import padm.io.pad_m.domain.Usuario;

public interface DocRepository extends JpaRepository<Doc, Integer> {

    @Query("SELECT d FROM Doc d WHERE d.usu_id = :usuario")
    List<Doc> findAllDocumentosByUsuario(@Param("usuario") Usuario usuario);
}
