package pl.michal.SuperligaApp.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"club", "user"})
@ToString(exclude = {"club", "user"})
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private int number;
    private int goals;
    private int assists;

    @ManyToOne
    private Club club;

    @OneToOne
    private User user;


    public void addGoals(int goals){
        setGoals(getGoals() + goals);
    }
    public void addAssists(int assists){
        setAssists(getAssists() + assists);
    }
}
