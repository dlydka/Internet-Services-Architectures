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
import pl.edu.pg.eti.kask.rpg.user.entity.Tower;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostMageRequest {
    private Long id;

    private String name;

    private int age;

    private int level;

    public static Function<PostMageRequest, Mage> dtoToEntityMapper(
            Supplier<Tower> towerSupplier) {
        return request -> Mage.builder()
                .name(request.getName())
                .age(request.getAge())
                .level(request.getLevel())
                .tower(towerSupplier.get())
                .build();
    }
}
