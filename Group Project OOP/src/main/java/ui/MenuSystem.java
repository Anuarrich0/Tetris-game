package ui;

import database.DBManager;
import game.GameEngine;
import game.ConsoleColors; 
import java.util.Scanner;

public class MenuSystem {
    private Scanner scanner = new Scanner(System.in);
    private int currentUserId = -1;
    private String currentUsername = "";

    public void start() {
        clearScreen();
        printHeader();

        while (true) {
            if (currentUserId == -1) {
                showAuthMenu();
            } else {
                showMainMenu();
            }
        }
    }
    
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    private void printHeader() {
        System.out.println(ConsoleColors.BRIGHT_CYAN + "=============================================" + ConsoleColors.RESET + ConsoleColors.BLUE + "======================================================" + ConsoleColors.RESET + ConsoleColors.PURPLE + "==================================================" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BRIGHT_CYAN + "                                     " + "IIIIIIII" + ConsoleColors.RESET + ConsoleColors.BLUE + "III    IIIIIIII   IIIIIIIIIII   IIIIIIII      IIIIIIII " + ConsoleColors.RESET + ConsoleColors.PURPLE + "  IIIIII" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BRIGHT_CYAN + "                                     " + "     I  " + ConsoleColors.RESET + ConsoleColors.BLUE + "       I               I        I       I        II    " + ConsoleColors.RESET + ConsoleColors.PURPLE + "  I    I " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BRIGHT_CYAN + "                                     " + "     I  " + ConsoleColors.RESET + ConsoleColors.BLUE + "       I               I        I       I        II    " + ConsoleColors.RESET + ConsoleColors.PURPLE + "  I      " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BRIGHT_CYAN + "                                     " + "     I  " + ConsoleColors.RESET + ConsoleColors.BLUE + "       IIIIIIII        I        IIIIIIIII        II    " + ConsoleColors.RESET + ConsoleColors.PURPLE + "  IIIIII" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BRIGHT_CYAN + "                                     " + "     I  " + ConsoleColors.RESET + ConsoleColors.BLUE + "       I               I        I    I           II    " + ConsoleColors.RESET + ConsoleColors.PURPLE + "       I" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BRIGHT_CYAN + "                                     " + "     I  " + ConsoleColors.RESET + ConsoleColors.BLUE + "       I               I        I     I          II    " + ConsoleColors.RESET + ConsoleColors.PURPLE + "  I    I" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BRIGHT_CYAN + "                                     " + "     I  " + ConsoleColors.RESET + ConsoleColors.BLUE + "       IIIIIIII        I        I      I      IIIIIIII " + ConsoleColors.RESET + ConsoleColors.PURPLE + "  IIIIII " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BRIGHT_CYAN + "=============================================" + ConsoleColors.RESET + ConsoleColors.BLUE + "======================================================" + ConsoleColors.RESET + ConsoleColors.PURPLE + "==================================================" + ConsoleColors.RESET);
    }

    private void showAuthMenu() {
        System.out.println(ConsoleColors.PURPLE + "\n===================" + ConsoleColors.RESET + "                                       " +  ConsoleColors.YELLOW + "======================" + ConsoleColors.RESET + ConsoleColors.RESET + "                                                  " +  ConsoleColors.CYAN + "==================" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.PURPLE + "=======Login=======" + ConsoleColors.RESET + "                                       " + ConsoleColors.YELLOW + "=======Register=======" + ConsoleColors.RESET + ConsoleColors.RESET + "                                                  " +  ConsoleColors.CYAN + "=======Exit=======" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.PURPLE + "===================" + ConsoleColors.RESET + "                                       " +  ConsoleColors.YELLOW + "======================" + ConsoleColors.RESET + ConsoleColors.RESET + "                                                  " +  ConsoleColors.CYAN + "==================" + ConsoleColors.RESET);

        System.out.print("\nLogin(1);Register(2);Exit(3)> ");
        String choice = scanner.nextLine();
        
        if (choice.equals("3")) {
            System.out.print("\033[H\033[2J");
            System.exit(0);
        }

        if (choice.equals("1") || choice.equals("2")) {
            System.out.print("=======Username: ");
            String user = scanner.nextLine();
            System.out.print("=======Password: ");
            String pass = scanner.nextLine();

            if (choice.equals("1")) {
                int id = DBManager.loginUser(user, pass);
                if (id != -1) {
                    currentUserId = id;
                    currentUsername = user;
                    clearScreen(); 
                    System.out.println(ConsoleColors.GREEN + "Welcome, " + currentUsername + "!" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED + "Invalid login." + ConsoleColors.RESET);
                }
            } else if (choice.equals("2")) {
                if (DBManager.registerUser(user, pass)) {
                    System.out.println(ConsoleColors.GREEN + "Registered successfully." + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED + "Registration failed." + ConsoleColors.RESET);
                }
            }
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void showMainMenu() {
        System.out.println("\n" + ConsoleColors.BRIGHT_YELLOW + "--- MAIN MENU (" + currentUsername + ") ---" + ConsoleColors.RESET);
        System.out.println("1. Solo (Zen Mode)");
        System.out.println("2. Leaderboard");
        System.out.println("3. Config");
        System.out.println("4. Logout");
        System.out.print("> ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1": 
                showSoloMenu(); 
                break;
            case "2": 
                clearScreen();
                DBManager.showLeaderboard(); 
                break;
            case "3": 
                clearScreen();
                showConfig(); 
                break;
            case "4": 
                currentUserId = -1; 
                clearScreen();
                System.out.println("Logged out.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void showSoloMenu() {
        System.out.println(ConsoleColors.CYAN + "\nStarting ZEN..." + ConsoleColors.RESET);
        try { Thread.sleep(500); } catch (Exception e) {}
        
        GameEngine game = new GameEngine("Zen", currentUserId);
        game.start();
        
        clearScreen();
    }

    private void showConfig() {
        System.out.println("\n--- CONFIG ---");
        System.out.println("A/D - Move Left/Right");
        System.out.println("S - Soft Drop");
        System.out.println("Space - Hard Drop");
        System.out.println("W - Rotate");
        System.out.println("C - Hold");
        System.out.println("R - Reset ");
        System.out.println("Q - Exit to Menu");
    }
}