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
                .name("AKS II")
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
                .name("Bichał")
                .lastName("Mulanda")
                .goals(0)
                .assists(0)
                .number(8)
                .club(club1)
                .build();

        club1.addPlayer(player1);
        club1.addPlayer(player2);

        clubRepository.save(club1);
        clubRepository.save(club2);

        playerRepository.save(player1);
        playerRepository.save(player2);
    }
}
