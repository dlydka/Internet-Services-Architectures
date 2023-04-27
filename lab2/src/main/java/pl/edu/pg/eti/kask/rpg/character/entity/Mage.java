package pl.edu.pg.eti.kask.rpg.character.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.rpg.user.entity.Tower;

import java.io.Serializable;

import javax.persistence.*;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "mages")
public class Mage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;

    private int age;

    private int level;

    @ManyToOne
    @JoinColumn(name ="tower")
    private Tower tower;

}
