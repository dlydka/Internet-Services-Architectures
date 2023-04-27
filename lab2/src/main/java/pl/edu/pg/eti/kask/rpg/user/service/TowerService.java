package pl.edu.pg.eti.kask.rpg.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.kask.rpg.character.entity.Mage;
import pl.edu.pg.eti.kask.rpg.user.entity.Tower;
import pl.edu.pg.eti.kask.rpg.user.repository.TowerRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for all business actions regarding user entity.
 */
@Service
public class TowerService {

    /**
     * Mock of the database. Should be replaced with repository layer.
     */
    private TowerRepository repository;

    @Autowired
    public TowerService(TowerRepository repository) {
        this.repository = repository;
    }

    /**
     * @param name user's name
     * @return container with user
     */
    public Optional<Tower> find(String name) {
        return repository.findById(name);
    }

    public List<Tower> findAll() {
        return repository.findAll();
    }

    /**
     * Seeks for single user using name and colour. Can be use in authentication module.
     *
     * @param name    user's name
     * @param colour user's colour (hash)
     * @return container (can be empty) with user
     */
    public Optional<Tower> find(String name, String colour) {
        return repository.findByNameAndColour(name, colour);
    }

    /**
     * Stores new user in the storage.
     *
     * @param tower new user
     */
    @Transactional
    public void create(Tower tower) {
        repository.save(tower);
    }



}
