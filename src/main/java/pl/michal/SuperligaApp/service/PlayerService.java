package pl.michal.SuperligaApp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.michal.SuperligaApp.exception.MissingPlayerException;
import pl.michal.SuperligaApp.model.Player;
import pl.michal.SuperligaApp.repository.PlayerRepository;
import pl.michal.SuperligaApp.web.rest.dto.PlayerResponse;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> getPlayersByClubId(Long clubId) {
        return playerRepository.findByClubId(clubId);
    }

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).orElseThrow( () -> new MissingPlayerException(id));
    }

    public Player createPlayer(Player playerToCreate) {
        return playerRepository.save(playerToCreate);
    }

    public boolean existsById(Long id){
        return playerRepository.existsById(id);
    }

    public Player savePLayer(Player playerById) {
        return playerRepository.save(playerById);
    }
}
