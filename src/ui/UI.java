package ui;

import exceptions.InvalidCommandException;
import exceptions.InvalidPropertyException;
import exceptions.ModelNotFoundException;
import ui.processor.AuthCommandProcessor;
import ui.processor.MainCommandsProcessor;
import util.cache.MovieCache;
import util.cache.UserCache;
import util.color.Color;
import util.color.ColorChanger;
import util.io.impl.MovieDataWriter;

import java.io.IOException;
import java.util.Scanner;

public class UI {

    private static MovieDataWriter movieDataWriter;
    static {
        movieDataWriter = new MovieDataWriter();
    }

    public static void start() {
        System.out.println(Templates.getWelcomeTemplate());

        Scanner scanner = new Scanner(System.in);

        AuthCommandProcessor authProcessor = new AuthCommandProcessor(scanner);
        MainCommandsProcessor movieProcessor = new MainCommandsProcessor(scanner);

        while (true) {
            System.out.println(Templates.getAuthTemplate());
            String authCommand = scanner.nextLine();
            try {
                authProcessor.processMainCommands(authCommand);
            } catch (InvalidCommandException | ModelNotFoundException | InvalidPropertyException e) {
                e.printStackTrace();
                continue;
            }
            break;
        }

        boolean inAction = true;
        boolean turnOnSecondaryMenu = false;

        String menuTemplate = Templates.getMainMenuTemplate();


        outer:
        while (inAction) {

            ColorChanger.changeColor(Color.GREEN);
            System.out.println(menuTemplate);
            String command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "exit":
                    System.out.println("Do yo want save your movies? (Y/N)");
                    String action = scanner.nextLine();
                    if (action.equalsIgnoreCase("y")){
                        try {
                            movieDataWriter.write(MovieCache.getCache(),String.format("src/resources/%s.txt", UserCache.getCurrentUser().getUsername()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if (!action.equalsIgnoreCase("n")){
                        continue outer;
                    }
                    System.out.println("Bye!!!");
                    inAction = false;
                    continue outer;
                case "8": // turn on secondary menu
                    menuTemplate = Templates.getSubMenuTemplate();
                    turnOnSecondaryMenu = true;
                    continue outer;
                case "5": // turn of secondary menu
                    if (turnOnSecondaryMenu) {
                        menuTemplate = Templates.getMainMenuTemplate();
                        turnOnSecondaryMenu = false;
                        continue outer;
                    }

            }

            try {
                if (!turnOnSecondaryMenu) {
                    movieProcessor.processMainCommands(command);
                } else {
                    movieProcessor.processSubCommands(command);
                }
            } catch (InvalidPropertyException | InvalidCommandException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

}
