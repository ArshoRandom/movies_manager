package ui.processor;

import exceptions.InvalidCommandException;
import exceptions.ModelNotFoundException;
import models.movies.base.AbstractMovie;
import models.movies.constants.MovieType;
import services.MovieService;
import services.UserService;
import ui.Templates;
import util.Questionnaire;
import util.StringUtils;
import util.cache.MovieCache;
import util.cache.Session;
import util.color.Color;
import util.color.ColorChanger;
import util.movieutil.MovieSupplier;

/**
 *
 * Process main application commands
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see CommandsProcessor
 * @see Questionnaire
 * @see MovieService
 * @see MovieCache
 */
public class MainCommandsProcessor implements CommandsProcessor {
    private Questionnaire questionnaire;

    public MainCommandsProcessor() {
        this.questionnaire = Questionnaire.getInstance();
    }


    /**
     *
     * Process commands imputed by the user
     * command must be number
     * @param command user command number
     * @throws InvalidCommandException if command is invalid
     * @throws NumberFormatException if command is not a number
     */
    public void processMainCommands(String command) throws InvalidCommandException, NumberFormatException {

        if (!command.matches("[1-8]")) {
            throw new InvalidCommandException(command);
        }
        int commandNumber = Integer.parseInt(command);

        if (MovieCache.getCache().isEmpty() && (commandNumber != 1 && commandNumber != 7)) { // '1' for adding new film and '7' for printing user info
            System.err.println("At first add movies");
            return;
        }

        switch (commandNumber) {
            case 1:

                int typeNumber = Integer.parseInt(questionnaire.askQuestion(Templates.getMovieTypeMenu()));
                processMovieCreation(typeNumber);
                ColorChanger.changeColor(Color.GREEN);
                break;
            case 2:
                MovieService.printAllFilms(MovieCache.getCache());
                break;
            case 3:
                int age = Integer.parseInt(questionnaire.askQuestion("Enter age"));
                MovieService.printAllFilmsByAgeRestriction(MovieCache.getCache(), age);
                break;
            case 4:
                String genres = questionnaire.askQuestion("Enter genres (drama,melodrama...)");
                MovieService.printAllFilmsByGenre(MovieCache.getCache(), StringUtils.mapStringToGenreSet(genres));
                break;
            case 5:
                MovieService.sortAndPrintAllAwardWinningFilms(MovieCache.getCache());
                break;
            case 6:
                MovieService.printAllOscarWinningFilms(MovieCache.getCache());
                break;
            case 7:
                UserService.printUserInfo(Session.getCurrentUser());
                break;
            default:
                throw new InvalidCommandException(command);
        }

    }

    /**
     * Create and add {@link AbstractMovie} in movie cache
     * @param typeNumber command number
     * @throws InvalidCommandException if command is invalid
     */
    private void processMovieCreation(int typeNumber) throws InvalidCommandException {
        try {
            switch (typeNumber) {
                case 1:
                    MovieCache.cache(MovieSupplier.getMovieByType(MovieType.FEATURE_FILM));
                    break;
                case 2:
                    MovieCache.cache(MovieSupplier.getMovieByType(MovieType.ANIMATION));
                    break;
                case 3:
                    MovieCache.cache(MovieSupplier.getMovieByType(MovieType.MUSIC_FILM));
                    break;
                case 4:
                    MovieCache.cache(MovieSupplier.getMovieByType(MovieType.SOAP_OPERA));
                    break;
                case 5:
                    return;
                default:
                    throw new InvalidCommandException(String.valueOf(typeNumber));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Process sub commands imputed by the user
     * command must be number
     * @param command user command number
     * @throws InvalidCommandException if command is invalid
     * @throws NumberFormatException if command is not a number
     */
    public void processSubCommands(String command) throws InvalidCommandException, NumberFormatException {
        int commandNumber = Integer.parseInt(command.trim());
        if (!(commandNumber > 0 && commandNumber < 7)) {
            throw new InvalidCommandException(String.valueOf(commandNumber));
        }
        Question question = new Question();
        try {
            switch (commandNumber) {
                case 1:
                    question.getMovieByQuestion().printInfo();
                    break;
                case 2:
                    MovieService.printFilmAwards(question.getMovieByQuestion());
                    break;
                case 3:
                    System.out.println(MovieService.getFilmAge(question.getMovieByQuestion()));
                    break;
                case 4:
                    MovieService.printFilmRating(question.getMovieByQuestion());
                    break;
                case 5:
                    MovieService.deleteMovie(questionnaire.askQuestion("Enter movie name")
                            ,questionnaire.askQuestion("Enter movie country"));
                    break;
                default:
                    throw new InvalidCommandException(command);
            }
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper class for excluding code duplicates
     */
    private class Question {

        private <T extends AbstractMovie> T getMovieByQuestion() throws ModelNotFoundException {
            String title = questionnaire.askQuestion("Enter movie name");
            String country = questionnaire.askQuestion("Enter movie country");
            return MovieService.search(title, country);
        }
    }
}
