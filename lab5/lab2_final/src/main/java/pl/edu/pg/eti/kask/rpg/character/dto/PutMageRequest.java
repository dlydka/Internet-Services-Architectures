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

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutMageRequest {
    private String name;

    private int age;

    private int level;

    public static BiFunction<Mage, PutMageRequest, Mage> dtoToEntityUpdater() {
        return (mage, request) -> {
            mage.setName(request.getName());
            mage.setAge(request.getAge());
            mage.setLevel(request.getLevel());
            return mage;
        };
    }
}
