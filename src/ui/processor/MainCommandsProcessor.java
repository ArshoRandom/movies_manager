package ui.processor;

import exceptions.InvalidCommandException;
import exceptions.ModelNotFoundException;
import models.movies.base.AbstractMovie;
import services.MovieService;
import services.UserService;
import ui.Templates;
import util.Questionnaire;
import util.StringUtils;
import util.cache.MovieCache;
import util.cache.UserCache;
import util.color.Color;
import util.color.ColorChanger;
import util.movieutil.MovieSupplier;
import models.movies.constants.MovieType;


public class MainCommandsProcessor implements CommandsProcessor {
    private Questionnaire questionnaire;

    public MainCommandsProcessor() {
        this.questionnaire = Questionnaire.getInstance();
    }

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
                MovieService.printAllFilmsByAgeRestriction(MovieCache.getCache(),age);
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
                UserService.printUserInfo(UserCache.getCurrentUser());
                break;
            default:
                throw new InvalidCommandException(command);
        }

    }

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


    public  void processSubCommands(String command) throws InvalidCommandException, NumberFormatException {
        int commandNumber = Integer.parseInt(command.trim());
        if (!(commandNumber > 0 && commandNumber < 6)) {
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
                default:
                    throw new InvalidCommandException(command);
            }
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
    }

    private  class Question{

        private  <T extends AbstractMovie> T  getMovieByQuestion() throws ModelNotFoundException {
            String title = questionnaire.askQuestion("Enter movie name");
            String country = questionnaire.askQuestion("Enter movie country");
            return MovieService.search(title,country);
        }
    }
}
