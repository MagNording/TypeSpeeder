package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.repositories.PlayerRepo;
import se.ju23.typespeeder.ui.Menu;
import se.ju23.typespeeder.util.Messages;
import se.ju23.typespeeder.util.UserInputService;

@Service
public class PlayerService {

    private final PlayerRepo playerRepo;
    private final UserInputService userInputService;
    private final Menu menu;
    private Messages messages;

    @Autowired
    public PlayerService(PlayerRepo playerRepo, UserInputService userInputService, Menu menu) {
        this.playerRepo = playerRepo;
        this.userInputService = userInputService;
        this.menu = menu;
        this.messages = new Messages(menu.getLanguage());
    }

    public void updateMessages() {
        this.messages = new Messages(menu.getLanguage());
    }

    public void confirmMessage() {
        updateMessages();
        System.out.println(messages.get("confirm.message"));
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

    public void createNewPlayer() {
        updateMessages();
        System.out.println(messages.get("enter.player.name"));
        String playername = userInputService.nextLine();

        System.out.println(messages.get("enter.username"));
        String username = userInputService.nextLine();

        System.out.println(messages.get("enter.password"));
        String password = userInputService.nextLine();

        addNewPlayer(playername, username, password);
        System.out.println(messages.get("player.created.success"));
    }

    public void editUserName(Player player) {
        updateMessages();
        System.out.println(messages.get("enter.new.name"));
        String newName = userInputService.nextLine();
        player.setUsername(newName);
        confirmMessage();
        playerRepo.save(player);
    }

    public void editPassword(Player player) {
        updateMessages();
        System.out.println(messages.get("enter.new.password"));
        String newPassword = userInputService.nextLine();
        player.setPassword(newPassword);
        confirmMessage();
        playerRepo.save(player);
    }

    public void editPlayerName(Player player) {
        updateMessages();
        System.out.println(messages.get("enter.new.player.name"));
        String newPlayerName = userInputService.nextLine();
        player.setPlayername(newPlayerName);
        confirmMessage();
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
