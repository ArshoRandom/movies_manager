package ui;

import util.color.Color;
import util.color.ColorChanger;
/**
 * In the class methods returning menu templates are presented
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
public class Templates {

    private Templates(){}

    public static String getAuthTemplate() {
        ColorChanger.changeColor(Color.GREEN);
        return "\nAuthentication" +
                "\n1. Login" +
                "\n2. Register";
    }

    public static String getWelcomeTemplate() {
        ColorChanger.changeColor(Color.BLUE);
        return ("\nWelcome to my MovieManager application, for exit enter 'exit'" +
                "\nFor executing command enter command number." +
                "\nAfter completing management you can save yor movies data in file" +
                "\nGood luck!!!");
    }

    public static String getMainMenuTemplate() {
        ColorChanger.changeColor(Color.GREEN);
        return "\n1. Add movie" +
                "\n2. Print all movies" +
                "\n3. Print all movies by age" +
                "\n4. Print all movies by genre" +
                "\n5. Print all award-winning movies" +
                "\n6. Print all Oscar-winning movies" +
                "\n7. Print my info" +
                "\n8. Individual actions with movies";
    }

    public static String getSubMenuTemplate() {
        ColorChanger.changeColor(Color.GREEN);
        return "\n1. Print movie info" +
                "\n2. Print movie awards" +
                "\n3. Print movie age" +
                "\n4. Print movie rating" +
                "\n5. Remove movie" +
                "\n6. Back to main menu";
    }

    public static String getMovieTypeMenu() {
        ColorChanger.changeColor(Color.YELLOW);
        return "Choose movie type number\n" +
                "\n1. Feature film" +
                "\n2. Animation" +
                "\n3. Music film" +
                "\n4. Soap opera" +
                "\n5. Back to main menu";
    }
}
