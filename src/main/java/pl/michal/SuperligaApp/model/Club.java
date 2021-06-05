package pl.michal.SuperligaApp.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "players")
@ToString(exclude = "players")
public class Club {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int points;
    private int goalsScored;
    private int goalsConceded;

    @OneToMany(mappedBy = "club")
    @Builder.Default
    private Set<Player> players = new HashSet<>();
}
