package pl.michal.SuperligaApp.model;

import lombok.*;

import javax.persistence.*;
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

    public void addPlayer(Player player){
        players.add(player);
    }

    public void updateGoalsAndAddPoints(int goalsScored, int goalsConceded){
        setGoalsScored(getGoalsScored() + goalsScored);
        setGoalsConceded(getGoalsConceded() + goalsConceded);
        if (goalsScored > goalsConceded){
            setPoints(getPoints() + 3);
        } else if (goalsScored == goalsConceded){
            setPoints(getPoints() + 1);
        }
    }

}
