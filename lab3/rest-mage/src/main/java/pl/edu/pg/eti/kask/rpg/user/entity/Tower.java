package pl.edu.pg.eti.kask.rpg.user.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.rpg.character.entity.Mage;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;


/**
 * Entity for system user. Represents information about particular user as well as credentials for authorization and
 * authentication needs.
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "towers")
public class Tower implements Serializable {

    @Id
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "tower")
    @ToString.Exclude
    private List<Mage> mages;

}
