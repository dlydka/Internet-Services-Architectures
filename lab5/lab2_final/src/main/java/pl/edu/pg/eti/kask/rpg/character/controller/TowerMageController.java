package pl.edu.pg.eti.kask.rpg.character.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.eti.kask.rpg.character.dto.PostMageRequest;
import pl.edu.pg.eti.kask.rpg.character.dto.GetMageResponse;
import pl.edu.pg.eti.kask.rpg.character.dto.GetMagesResponse;
import pl.edu.pg.eti.kask.rpg.character.dto.PutMageRequest;
import pl.edu.pg.eti.kask.rpg.character.entity.Mage;
import pl.edu.pg.eti.kask.rpg.character.service.MageService;
import pl.edu.pg.eti.kask.rpg.user.service.TowerService;
import pl.edu.pg.eti.kask.rpg.user.entity.Tower;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("api/towers/{name}/mages")
@CrossOrigin
public class TowerMageController {
    private MageService mageService;
    private TowerService towerService;

    @Autowired
    public TowerMageController(MageService mageService, TowerService towerService) {
        this.mageService = mageService;
        this.towerService = towerService;
    }

    /**
     * @param name existing user's username (login)
     * @return list of users' characters which will be converted to JSON or not found if user does not exist
     */
    @GetMapping
    public ResponseEntity<GetMagesResponse> getCharacters(@PathVariable("name") String name) {
        Optional<Tower> tower = towerService.find(name);
        return tower.map(value -> ResponseEntity.ok(GetMagesResponse.entityToDtoMapper().apply(mageService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * @param name existing user's username (login)
     * @param id       id of the character
     * @return single character in JSON format or 404 when character does not exist or not found if user does not exist
     */
    @GetMapping("{id}")
    public ResponseEntity<GetMageResponse> getCharacter(@PathVariable("name") String name,
                                                             @PathVariable("id") long id) {
        return mageService.find(name, id)
                .map(value -> ResponseEntity.ok(GetMageResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * @param username existing user's username (login)
     * @param request  new character parsed from JSON
     * @param builder  URI builder
     * @return response with location header or not found if user does not exist
     */
    @PostMapping
    public ResponseEntity<Void> postCharacter(@PathVariable("name") String username,
                                              @RequestBody PostMageRequest request,
                                              UriComponentsBuilder builder) {
        Optional<Tower> tower = towerService.find(username);
        if (tower.isPresent()) {
            Mage mage = PostMageRequest
                    .dtoToEntityMapper(tower::get)
                    .apply(request);

            mage = mageService.create(mage);
            return ResponseEntity.created(builder.pathSegment("api", "towers", "{name}", "mages", "{id}")
                    .buildAndExpand(tower.get().getName(), mage.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes selected character.
     *
     * @param name existing user's username (login)
     * @param id       character's id
     * @return accepted for not found if character does not exist or not found if user does not exist
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable("name") String name,
                                                @PathVariable("id") long id) {
        Optional<Mage> mage = mageService.find(name, id);
        if (mage.isPresent()) {
            mageService.delete(mage.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates existing character.
     *
     * @param username existing user's username (login)
     * @param request  character's data parsed from JSON
     * @param id       character's id
     * @return accepted or not found if character does not exist or not found if user does not exist
     */
    @PutMapping("{id}")
    public ResponseEntity<Void> putCharacter(@PathVariable("name") String username,
                                             @RequestBody PutMageRequest request,
                                             @PathVariable("id") long id) {
        Optional<Mage> mage = mageService.find(username, id);
        if (mage.isPresent()) {
            PutMageRequest.dtoToEntityUpdater().apply(mage.get(), request);
            mageService.update(mage.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
