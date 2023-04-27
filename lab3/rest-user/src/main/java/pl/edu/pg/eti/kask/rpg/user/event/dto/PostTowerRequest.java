package pl.edu.pg.eti.kask.rpg.user.event.dto;

import lombok.*;
import pl.edu.pg.eti.kask.rpg.user.entity.Tower;

import java.util.function.Function;

/**
 * PSOT user request. Contains only fields that can be set during user creation. User is defined in
 * {@link pl.edu.pg.eti.kask.rpg.user.entity.Tower}.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostTowerRequest {

    /**
     * User's login.
     */
    private String name;

    /**
     * @return mapper for convenient converting dto object to entity object
     */
    public static Function<Tower, PostTowerRequest> entityToDtoMapper() {
        return entity -> PostTowerRequest.builder()
                .name(entity.getName())
                .build();
    }

}
