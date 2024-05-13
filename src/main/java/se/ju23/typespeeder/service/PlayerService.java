package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.repositories.PlayerRepo;

@Service
public class PlayerService {
    private final PlayerRepo playerRepo;

    @Autowired
    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public void addNewPlayer(String playername, String username, String password) {
        Player player = new Player();
        player.setPlayername(playername);
        player.setUsername(username);
        player.setPassword(password);
        player.setLevel(1);
        player.setXP(1);
        playerRepo.save(player);
    }

    //all CRUD för användare
}
