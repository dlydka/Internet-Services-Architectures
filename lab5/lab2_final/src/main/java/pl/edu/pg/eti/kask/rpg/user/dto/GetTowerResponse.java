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

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetTowerResponse {

    private String name;

    private String colour;

    private double height;

    public static Function<Tower, GetTowerResponse> entityToDtoMapper() {
        return tower -> GetTowerResponse.builder()
                .name(tower.getName())
                .colour(tower.getColour())
                .height(tower.getHeight())
                .build();
    }
}
