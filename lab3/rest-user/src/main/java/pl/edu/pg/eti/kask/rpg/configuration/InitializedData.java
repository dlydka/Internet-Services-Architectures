package pl.edu.pg.eti.kask.rpg.configuration;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
     * Service for users operations.
     */
    private final TowerService towerService;

    @Autowired
    public InitializedData(TowerService towerService) {
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
                .colour("white")
                .height(2.5)
                .build();

        Tower t2 = Tower.builder()
                .name("Tower2")
                .colour("black")
                .height(7.1)
                .build();

        Tower t3 = Tower.builder()
                .name("Tower3")
                .colour("red")
                .height(12.5)
                .build();

        towerService.create(t1);
        towerService.create(t2);
        towerService.create(t3);
    }
}
