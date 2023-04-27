package pl.edu.pg.eti.kask.rpg.character.dto;

import jdk.jshell.Snippet;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

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
public class GetMagesResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Mage {

        private Long id;

        private String name;


    }

    @Singular
    private List<Mage> mages;


    public static Function<Collection<pl.edu.pg.eti.kask.rpg.character.entity.Mage>, GetMagesResponse> entityToDtoMapper() {
        return mages -> {
            GetMagesResponseBuilder response = GetMagesResponse.builder();
            mages.stream()
                    .map(mage -> Mage.builder()
                            .id(mage.getId())
                            .name(mage.getName())
                            .build())
                    .forEach(response::mage);
            return response.build();
        };
    }
}
