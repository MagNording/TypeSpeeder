package se.ju23.typespeeder;

import org.junit.jupiter.api.Test;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.repositories.PlayerRepo;
import se.ju23.typespeeder.service.AuthenticationService;
import se.ju23.typespeeder.ui.HandleMenuOptions;
import se.ju23.typespeeder.ui.Menu;
import se.ju23.typespeeder.util.UserInputService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthenticationServiceTest {

    @Test
    public void login() {
        Menu mockMenu = mock(Menu.class);
        when(mockMenu.getLanguage()).thenReturn("sv");

        UserInputService mockUserInputService = mock(UserInputService.class);
        when(mockUserInputService.nextLine()).thenReturn("testuser", "password");

        PlayerRepo mockPlayerRepo = mock(PlayerRepo.class);
        Player mockPlayer = new Player();
        mockPlayer.setPlayername("TestUser");
        when(mockPlayerRepo.findByUsernameAndPassword("testuser", "password")).thenReturn(mockPlayer);

        HandleMenuOptions mockHandleMenuOptions = mock(HandleMenuOptions.class);

        AuthenticationService authService = new AuthenticationService(mockMenu, mockPlayerRepo, mockUserInputService, mockHandleMenuOptions);
        authService.login();

        assertNotNull(authService.getPlayer());
    }
}