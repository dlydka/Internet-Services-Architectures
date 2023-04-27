package pl.edu.pg.eti.kask.rpg.character.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.eti.kask.rpg.character.dto.PostMageRequest;
import pl.edu.pg.eti.kask.rpg.character.dto.GetMageResponse;
import pl.edu.pg.eti.kask.rpg.character.dto.GetMagesResponse;
import pl.edu.pg.eti.kask.rpg.character.dto.PutMageRequest;
import pl.edu.pg.eti.kask.rpg.character.entity.Mage;
import pl.edu.pg.eti.kask.rpg.character.service.MageService;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("api/mages")
@CrossOrigin
public class MageController {

    private MageService mageService;

    @Autowired
    public MageController(MageService mageService) {
        this.mageService = mageService;
    }

    /**
     * @return list of characters which will be converted to JSON
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})//When empty JSON is default
    public ResponseEntity<GetMagesResponse> getCharacters() {
        return ResponseEntity
                .ok(GetMagesResponse.entityToDtoMapper().apply(mageService.findAll()));
    }

    /**
     * @param id id of the character
     * @return single character in JSON format or 404 when character does not exist
     */
    @GetMapping("{id}")
    public ResponseEntity<GetMageResponse> getCharacter(@PathVariable("id") long id) {
//        return characterService.find(id).map(new Function<Character, ResponseEntity<GetCharacterResponse>>() {
//            @Override
//            public ResponseEntity<GetCharacterResponse> apply(Character character) {
//                return ResponseEntity.ok(GetCharacterResponse.entityToDtoMapper().apply(character));
//            }
//        }).orElseGet(new Supplier<ResponseEntity<GetCharacterResponse>>() {
//            @Override
//            public ResponseEntity<GetCharacterResponse> get() {
//                return ResponseEntity.notFound().build();
//            }
//        });
        return mageService.find(id)
                .map(value -> ResponseEntity
                        .ok(GetMageResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    /**
     * @param request new character parsed from JSON
     * @param builder URI builder
     * @return response with location header
     */
    @PostMapping
    public ResponseEntity<Void> postCharacter(@RequestBody PostMageRequest request, UriComponentsBuilder builder) {
//        Character character = CreateCharacterRequest.dtoToEntityMapper(new Function<String, Profession>() {
//            @Override
//            public Profession apply(String name) {
//                return professionService.find(name).orElseThrow();
//            }
//        }, new Supplier<User>() {
//            @Override
//            public User get() {
//                return null;
//            }
//        }).apply(request);
       Mage mage = PostMageRequest
                .dtoToEntityMapper(() -> null)
                .apply(request);

        mage = mageService.create(mage);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "characters", "{id}")
                        .buildAndExpand(mage.getId()).toUri())
                .build();
    }

    /**
     * Deletes selected character.
     *
     * @param id character's id
     * @return accepted for not found if character does not exist
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable("id") long id) {
        Optional<Mage> mage = mageService.find(id);
        if (mage.isPresent()) {
            mageService.delete(mage.get().getId());
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
     * Updates existing character.
     *
     * @param request character's data parsed from JSON
     * @param id      character's id
     * @return accepted or not found if character does not exist
     */
    @PutMapping("{id}")
    public ResponseEntity<Void> putCharacter(@RequestBody PutMageRequest request, @PathVariable("id") long id) {
        Optional<Mage> mage = mageService.find(id);
        if (mage.isPresent()) {
            PutMageRequest.dtoToEntityUpdater().apply(mage.get(), request);
            mageService.update(mage.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
