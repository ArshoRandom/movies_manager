package ui;

import exceptions.InvalidCommandException;
import exceptions.InvalidPropertyException;
import exceptions.ModelNotFoundException;
import ui.processor.AuthCommandProcessor;
import ui.processor.MainCommandsProcessor;
import util.ScannerReader;
import util.cache.MovieCache;
import util.cache.UserCache;
import util.color.Color;
import util.color.ColorChanger;
import util.io.impl.MovieDataWriter;

import java.io.IOException;

public class UI {

    private static MovieDataWriter movieDataWriter;
    static {
        movieDataWriter = new MovieDataWriter();
    }

    public static void start() {
        System.out.println(Templates.getWelcomeTemplate());

        ScannerReader scanner = ScannerReader.getInstance();

        AuthCommandProcessor authProcessor = new AuthCommandProcessor();
        MainCommandsProcessor movieProcessor = new MainCommandsProcessor();

        while (true) {
            String authCommand = scanner.readLine(Templates.getAuthTemplate());
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
            String command = scanner.readLine(menuTemplate);

            switch (command.toLowerCase()) {
                case "exit":
                    String action = scanner.readLine("Do yo want save your movies? (Y/N)");
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
