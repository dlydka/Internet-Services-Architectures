package pl.edu.pg.eti.kask.rpg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.kask.rpg.character.entity.Mage;
import pl.edu.pg.eti.kask.rpg.character.service.MageService;
import pl.edu.pg.eti.kask.rpg.user.entity.Tower;
import pl.edu.pg.eti.kask.rpg.user.service.TowerService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Component for interaction with user using command line. Components annotated with {@link @Component} implementing
 * {@link CommandLineRunner} are executed automatically.
 */
@Component
public class CommandLine implements CommandLineRunner {

    private MageService mageService;
    private TowerService towerService;

    @Autowired
    public CommandLine(MageService mageService, TowerService towerService) {
        this.mageService = mageService;
        this.towerService = towerService;
    }

    @Override
    public void run(String... args) throws Exception {
        mageService.findAll().forEach(System.out::println);
        System.out.println();
        towerService.findAll().forEach(System.out::println);
        System.out.println();
        towerService.find("Tower1", "white").ifPresent(System.out::println);
        System.out.println();
        mageService.find(towerService.find("Tower2").orElseThrow(), 1L).ifPresent(System.out::println);
        System.out.println();
        mageService.create(Mage.builder().name("Sigrid").build());
        System.out.println();
        mageService.find(5L).ifPresent(System.out::println);
    }

}