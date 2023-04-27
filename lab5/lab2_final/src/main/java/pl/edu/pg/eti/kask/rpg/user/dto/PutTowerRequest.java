package pl.edu.pg.eti.kask.rpg.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.edu.pg.eti.kask.rpg.user.entity.Tower;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutTowerRequest {

    private String name;
    private String colour;
    private double height;

    public static BiFunction<Tower, PutTowerRequest, Tower> dtoToEntityUpdater() {
        return (tower, request) -> {
            tower.setName(request.getName());
            tower.setColour(request.getColour());
            tower.setHeight(request.getHeight());
            return tower;
        };
    }
}
