package se.ju23.typespeeder.ui;

import java.util.List;
public interface MenuService {
    void displayMenu();
    List<String> getMenuOptions();
    boolean handleMenuOption(int option);
}




