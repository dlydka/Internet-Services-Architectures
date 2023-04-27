package pl.edu.pg.eti.kask.rpg.character.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.edu.pg.eti.kask.rpg.character.entity.Mage;

import java.util.function.Function;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetMageResponse {
    private Long id;

    private String name;

    private int age;

    private int level;

    public static Function<Mage, GetMageResponse> entityToDtoMapper() {
        return character -> GetMageResponse.builder()
                .id(character.getId())
                .name(character.getName())
                .age(character.getAge())
                .level(character.getLevel())
                .build();
    }
}
