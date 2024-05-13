package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.service.AuthenticationService;
import se.ju23.typespeeder.ui.ChallangeMenu;
import se.ju23.typespeeder.util.UserInputService;

@Component
public class Challenge {

    @Autowired
    ChallangeMenu challangeMenu;

    @Autowired
    UserInputService userInputService;

    public void startChallenge(Player player) {
        int choice = 0;

        do {
            challangeMenu.displayMenu();
            choice = userInputService.getIntInput();

            switch (choice) {
                case 1 -> {
                    System.out.println(player.getPlayername());
                }
                case 2 -> {
//                    startStandardGameInEnglish(player);
                }
                case 3 -> {
//                    startSpecialCharacterGame(player);
                }
                case 4 -> {
//                    startHighlightedCharacterGame(player);
                }
            }
        } while (choice != 0);
    }

    public void lettersToType() {

    }
}
