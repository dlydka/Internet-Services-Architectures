package pl.edu.pg.eti.kask.rpg.character.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.kask.rpg.character.entity.Mage;
import pl.edu.pg.eti.kask.rpg.user.entity.Tower;

import java.util.List;
import java.util.Optional;

/**
 * Repository for character entity. Repositories should be used in business layer (e.g.: in services).
 */
@Repository
public interface MageRepository extends JpaRepository<Mage, Long> {

    Optional<Mage> findByIdAndTower(Long id, Tower tower);

    List<Mage> findAllByTower(Tower tower);
}
