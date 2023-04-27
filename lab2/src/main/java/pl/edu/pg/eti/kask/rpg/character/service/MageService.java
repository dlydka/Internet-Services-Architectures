package pl.edu.pg.eti.kask.rpg.character.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.kask.rpg.character.entity.Mage;
import pl.edu.pg.eti.kask.rpg.character.repository.MageRepository;
import pl.edu.pg.eti.kask.rpg.user.entity.Tower;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for all business actions regarding character entity.
 */
@Service
public class MageService {

    /**
     * Repository for character entity.
     */
    private MageRepository repository;


    /**
     * @param repository repository for character entity
     */
    @Autowired
    public MageService(MageRepository repository) {
        this.repository = repository;
    }

    /**
     * Finds single character.
     *
     * @param id character's id
     * @return container with character
     */
    public Optional<Mage> find(Long id) {
        return repository.findById(id);
    }

    /**
     * @param id   character's id
     * @param tower existing user
     * @return selected character for user
     */
    public Optional<Mage> find(Tower tower, Long id) {
        return repository.findByIdAndTower(id, tower);
    }

    /**
     * @return all available characters
     */
    public List<Mage> findAll() { return repository.findAll(); }

    /**
     * @param tower existing user, character's owner
     * @return all available characters of the selected user
     */
    public List<Mage> findAll(Tower tower) {
        return repository.findAllByTower(tower);
    }

    /**
     * Creates new character.
     *
     * @param mage new character
     */
    @Transactional
    public void create(Mage mage) {
        repository.save(mage);
    }

    /**
     * Updates existing character.
     *
     * @param mage character to be updated
     */
    @Transactional
    public void update(Mage mage) { repository.save(mage); }

    /**
     * Deletes existing character.
     *
     * @param character existing character's id to be deleted
     */
    @Transactional
    public void delete(Long character) { repository.deleteById(character); }

}
