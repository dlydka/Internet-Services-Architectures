package pl.edu.pg.eti.kask.rpg.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import pl.edu.pg.eti.kask.rpg.user.entity.Tower;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetTowersResponse {

    @Singular
    private List<String> towers;

    public static Function<Collection<Tower>, GetTowersResponse> entityToDtoMapper() {
        return mages -> {
            GetTowersResponseBuilder response = GetTowersResponse.builder();
            mages.stream()
                    .map(Tower::getName)
                    .forEach(response::tower);
            return response.build();
        };
    }
}
