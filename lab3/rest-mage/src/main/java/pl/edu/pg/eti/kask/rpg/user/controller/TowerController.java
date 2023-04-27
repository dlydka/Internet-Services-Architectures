package pl.edu.pg.eti.kask.rpg.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.eti.kask.rpg.user.dto.PostTowerRequest;
import pl.edu.pg.eti.kask.rpg.user.entity.Tower;
import pl.edu.pg.eti.kask.rpg.user.service.TowerService;

import java.util.Optional;

@RestController
@RequestMapping("api/towers")
public class TowerController {

    private TowerService towerService;

    @Autowired
    public TowerController(TowerService towerService) {
        this.towerService = towerService;
    }


    /**
     * Deletes selected user.
     *
     * @param name user's login
     * @return accepted for not found if character does not exist
     */
    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteUser(@PathVariable("name") String name) {
        Optional<Tower> tower = towerService.find(name);
        if (tower.isPresent()) {
            towerService.delete(tower.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    /**
     * @param request new user parsed from JSON
     * @param builder URI builder
     * @return response with location header
     */
    @PostMapping("")
    public ResponseEntity<Void> postUser(@RequestBody PostTowerRequest request, UriComponentsBuilder builder) {
        Tower tower = PostTowerRequest.dtoToEntityMapper().apply(request);
        towerService.create(tower);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "towers", "{name}")
                        .buildAndExpand(tower.getName()).toUri())
                .build();
    }
}
