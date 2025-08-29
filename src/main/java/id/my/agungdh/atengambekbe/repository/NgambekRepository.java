package id.my.agungdh.atengambekbe.repository;

import id.my.agungdh.atengambekbe.entity.Ngambek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NgambekRepository extends JpaRepository<Ngambek, Long> {

    Optional<Ngambek> findByUuid(UUID id);
}
