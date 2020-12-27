package ui;

import config.AppConfig;
import exceptions.InvalidCommandException;
import exceptions.InvalidPropertyException;
import exceptions.ModelNotFoundException;
import ui.processor.AuthCommandProcessor;
import ui.processor.MainCommandsProcessor;
import util.Questionnaire;
import util.cache.MovieCache;
import util.cache.Session;
import util.color.Color;
import util.color.ColorChanger;
import util.io.impl.MovieDataWriter;

import java.io.IOException;
/**
 *
 * Class is for processing user interface
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
public class UI {

    private UI(){}

    private static MovieDataWriter movieDataWriter;
    static {
        movieDataWriter = new MovieDataWriter();
    }

    /**
     * Application start point-method
     */
    public static void start() {
        System.out.println(Templates.getWelcomeTemplate());

        Questionnaire questionnaire = Questionnaire.getInstance();

        AuthCommandProcessor authProcessor = new AuthCommandProcessor();
        MainCommandsProcessor movieProcessor = new MainCommandsProcessor();

        while (true) {
            String authCommand = questionnaire.askQuestion(Templates.getAuthTemplate());
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
            String command = questionnaire.askQuestion(menuTemplate);

            switch (command.toLowerCase()) {
                case "exit":
                    String action = questionnaire.askQuestion("Do yo want save your movies? (Y/N)");
                    if (action.equalsIgnoreCase("y")){
                        try {
                            movieDataWriter.write(MovieCache.getCache(), AppConfig.getMoviesDataPathFor(Session.getCurrentUser().getUsername()));
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
