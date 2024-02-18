package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.entity.PlayerRepo;
import se.ju23.typespeeder.entity.Role;

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
        player.setRole(Role.valueOf("user"));

        playerRepo.save(player);
    }
}
