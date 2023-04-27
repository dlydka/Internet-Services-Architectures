package pl.edu.pg.eti.kask.rpg.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.kask.rpg.user.entity.Tower;

import java.util.Optional;

/**
 * Repository for User entity. Repositories should be used in business layer (e.g.: in services).
 */
@Repository
public interface TowerRepository extends JpaRepository<Tower, String> {

}
