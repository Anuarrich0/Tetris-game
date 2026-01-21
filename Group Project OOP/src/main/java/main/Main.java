package main;

import database.DBManager;
import ui.MenuSystem;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- LAUNCHING THE GAME ---");
        
        if (DBManager.checkConnection()) {
            MenuSystem menu = new MenuSystem();
            menu.start();
        } else {
            System.out.println("⛔ GAME STOPPED: Check your PostgreSQL settings (password or pg_hba.conf).");
        }
    }
}