package pl.michal.SuperligaApp.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.michal.SuperligaApp.model.Club;
import pl.michal.SuperligaApp.model.Player;
import pl.michal.SuperligaApp.repository.ClubRepository;
import pl.michal.SuperligaApp.repository.PlayerRepository;

@Component
@RequiredArgsConstructor
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Club club1 = Club.builder()
                .name("AKS WS")
                .points(0)
                .goalsConceded(0)
                .goalsScored(0)
                .build();

        Club club2 = Club.builder()
                .name("Real Madrid")
                .points(0)
                .goalsConceded(0)
                .goalsScored(0)
                .build();

        Player player1 = Player.builder()
                .name("Michał")
                .lastName("Bulanda")
                .goals(0)
                .assists(0)
                .number(9)
                .club(club1)
                .build();

        Player player2 = Player.builder()
                .name("Jan")
                .lastName("Kowalski")
                .goals(0)
                .assists(0)
                .number(8)
                .club(club1)
                .build();

        Player player3 = Player.builder()
                .name("Jan")
                .lastName("Nowak")
                .goals(0)
                .assists(0)
                .number(7)
                .club(club1)
                .build();

        Player player4 = Player.builder()
                .name("Jan")
                .lastName("Malinowski")
                .goals(0)
                .assists(0)
                .number(1)
                .club(club1)
                .build();

        Player player5 = Player.builder()
                .name("Kacper")
                .lastName("Kowalski")
                .goals(0)
                .assists(0)
                .number(15)
                .club(club1)
                .build();

        Player player6 = Player.builder()
                .name("Luis")
                .lastName("Ronaldo")
                .goals(0)
                .assists(0)
                .number(12)
                .club(club1)
                .build();

        Player player11 = Player.builder()
                .name("Sergio")
                .lastName("Ramos")
                .goals(0)
                .assists(0)
                .number(4)
                .club(club2)
                .build();

        Player player12 = Player.builder()
                .name("Karim")
                .lastName("Benzema")
                .goals(0)
                .assists(0)
                .number(9)
                .club(club2)
                .build();

        Player player13 = Player.builder()
                .name("Toni")
                .lastName("Kroos")
                .goals(0)
                .assists(0)
                .number(8)
                .club(club2)
                .build();

        Player player14 = Player.builder()
                .name("Dani")
                .lastName("Carvajal")
                .goals(0)
                .assists(0)
                .number(2)
                .club(club2)
                .build();

        Player player15 = Player.builder()
                .name("Thibaut")
                .lastName("Courtouis")
                .goals(0)
                .assists(0)
                .number(1)
                .club(club2)
                .build();

        Player player16 = Player.builder()
                .name("Luka")
                .lastName("Modric")
                .goals(0)
                .assists(0)
                .number(15)
                .club(club2)
                .build();

        club1.addPlayer(player1);
        club1.addPlayer(player2);
        club1.addPlayer(player3);
        club1.addPlayer(player4);
        club1.addPlayer(player5);
        club1.addPlayer(player6);

        club2.addPlayer(player11);
        club2.addPlayer(player12);
        club2.addPlayer(player13);
        club2.addPlayer(player14);
        club2.addPlayer(player15);
        club2.addPlayer(player16);


        clubRepository.save(club1);
        clubRepository.save(club2);

        playerRepository.save(player1);
        playerRepository.save(player2);
        playerRepository.save(player3);
        playerRepository.save(player4);
        playerRepository.save(player5);
        playerRepository.save(player6);

        playerRepository.save(player11);
        playerRepository.save(player12);
        playerRepository.save(player13);
        playerRepository.save(player14);
        playerRepository.save(player15);
        playerRepository.save(player16);
    }
}
