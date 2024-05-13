package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.repositories.PlayerRepo;
import se.ju23.typespeeder.ui.Menu;
import se.ju23.typespeeder.util.UserInputService;

@Service
public class PlayerService {
    private final PlayerRepo playerRepo;

    private final Menu menu;

    private final UserInputService userInputService;

    @Autowired
    public PlayerService(PlayerRepo playerRepo, Menu menu, UserInputService userInputService) {
        this.playerRepo = playerRepo;
        this.menu = menu;
        this.userInputService = userInputService;
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

    public void editUserName(Player player) {
            System.out.println("ange nytt namn/state new name: ");
            String newName = userInputService.nextLine();

            player.setUsername(newName);
            playerRepo.save(player);
    }

    public void editPassword(Player player) {
        System.out.println("ange nytt lösenord/state new password: ");
        String newPassword = userInputService.nextLine();

        player.setUsername(newPassword);
        playerRepo.save(player);
    }

    public void editPlayerName(Player player) {
        System.out.println("ange nytt visningsnamn/state new player name: ");
        String newPlayerName = userInputService.nextLine();

        player.setUsername(newPlayerName);
        playerRepo.save(player);
    }

    public void updateXPandLevel(Player player, int points){
        int currentXP = player.getXP();
        int newXP = currentXP + points;
        player.setXP(newXP);
        player.setLevel(newXP/100);
        playerRepo.save(player);
    }

}
