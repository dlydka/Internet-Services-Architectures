package pl.edu.pg.eti.kask.rpg.character.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.kask.rpg.character.entity.Mage;
import pl.edu.pg.eti.kask.rpg.character.repository.MageRepository;
import pl.edu.pg.eti.kask.rpg.user.entity.Tower;
import pl.edu.pg.eti.kask.rpg.user.repository.TowerRepository;

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
    private MageRepository mageRepository;

    private TowerRepository towerRepository;



    @Autowired
    public MageService(MageRepository mageRepository, TowerRepository towerRepository) {
        this.mageRepository = mageRepository;
        this.towerRepository = towerRepository;
    }

    /**
     * Finds single character.
     *
     * @param id character's id
     * @return container with character
     */
    public Optional<Mage> find(Long id) {
        return mageRepository.findById(id);
    }

    /**
     * @param id   character's id
     * @param tower existing user
     * @return selected character for user
     */
    public Optional<Mage> find(Tower tower, Long id) {
        return mageRepository.findByIdAndTower(id, tower);
    }

    /**
     * @return all available characters
     */
    public List<Mage> findAll() { return mageRepository.findAll(); }

    /**
     * @param tower existing user, character's owner
     * @return all available characters of the selected user
     */
    public List<Mage> findAll(Tower tower) {
        return mageRepository.findAllByTower(tower);
    }

    public Optional<Mage> find(String name, Long id) {
        Optional<Tower> tower = towerRepository.findById(name);
        if (tower.isPresent()) {
            return mageRepository.findByIdAndTower(id, tower.get());
        } else {
            return Optional.empty();
        }
    }

    /**
     * Creates new character.
     *
     * @param mage new character
     * @return
     */
    @Transactional
    public Mage create(Mage mage) {
        return mageRepository.save(mage);
    }

    /**
     * Updates existing character.
     *
     * @param mage character to be updated
     */
    @Transactional
    public void update(Mage mage) { mageRepository.save(mage); }

    /**
     * Deletes existing character.
     *
     * @param character existing character's id to be deleted
     */
    @Transactional
    public void delete(Long character) { mageRepository.deleteById(character); }

}
