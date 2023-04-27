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

import java.time.LocalDate;
import java.util.function.Function;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostTowerRequest {

    private String name;

    private String colour;

    private double height;

    public static Function<PostTowerRequest, Tower> dtoToEntityMapper() {
        return request -> Tower.builder()
                .name(request.getName())
                .colour(request.getColour())
                .height(request.getHeight())
                .build();
    }
}
