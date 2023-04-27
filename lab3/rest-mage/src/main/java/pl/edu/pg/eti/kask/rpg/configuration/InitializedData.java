package pl.edu.pg.eti.kask.rpg.configuration;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.kask.rpg.character.entity.Mage;
import pl.edu.pg.eti.kask.rpg.character.service.MageService;
import pl.edu.pg.eti.kask.rpg.user.entity.Tower;
import pl.edu.pg.eti.kask.rpg.user.service.TowerService;

import javax.annotation.PostConstruct;
import java.io.InputStream;

/**
 * Listener started automatically on CDI application context initialized. Injects proxy to the services and fills
 * database with default content. When using persistence storage application instance should be initialized only during
 * first run in order to init database with starting data. Good place to create first default admin user.
 */
@Component
public class InitializedData {

    /**
     * Service for characters operations.
     */
    private final MageService mageService;

    /**
     * Service for users operations.
     */
    private final TowerService towerService;

    @Autowired
    public InitializedData(MageService mageService, TowerService towerService) {
        this.mageService = mageService;
        this.towerService = towerService;
    }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should
     * be created only once.
     */
    @PostConstruct
    private synchronized void init() {
        Tower t1 = Tower.builder()
                .name("Tower1")
                .build();

        Tower t2 = Tower.builder()
                .name("Tower2")
                .build();

        Tower t3 = Tower.builder()
                .name("Tower3")
                .build();

        towerService.create(t1);
        towerService.create(t2);
        towerService.create(t3);

        Mage calvian = Mage.builder()
                .name("Calvian")
                .age(18)
                .level(4)
                .tower(t2)
                .build();

        Mage uhlbrecht = Mage.builder()
                .name("Uhlbrecht")
                .age(37)
                .level(17)
                .tower(t2)
                .build();

        Mage eloise = Mage.builder()
                .name("Eloise")
                .age(32)
                .level(24)
                .tower(t3)
                .build();

        Mage zereni = Mage.builder()
                .name("Zereni")
                .age(20)
                .level(11)
                .tower(t3)
                .build();

        mageService.create(calvian);
        mageService.create(uhlbrecht);
        mageService.create(eloise);
        mageService.create(zereni);
    }

    /**
     * @param name name of the desired resource
     * @return array of bytes read from the resource
     */
    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }

}
