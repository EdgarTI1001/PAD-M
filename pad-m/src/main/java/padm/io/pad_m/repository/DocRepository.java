package padm.io.pad_m.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import padm.io.pad_m.domain.Doc;

public interface DocRepository extends JpaRepository<Doc, Integer> {

}
