package ui;

import util.color.Color;
import util.color.ColorChanger;

public class Templates {

    public static String getAuthTemplate(){
        ColorChanger.changeColor(Color.GREEN);
        return "\nAuthentication"+
               "\n1. Login"+
               "\n2. Register";
    }

    public static String  getWelcomeTemplate(){
        ColorChanger.changeColor(Color.BLUE);
        return ("\nWelcome to my MovieManager application, for exit enter 'exit'" +
                "\nFor executing command enter command number." +
                "\nYou can import movies data from saved files using 'import <path>' " +
                "\nAfter completing management you can save yor movies data in file" +
                "\nGood luck!!!");
    }

    public static String getMainMenuTemplate(){
        ColorChanger.changeColor(Color.GREEN);
        return  "\n1. Add movie" +
                "\n2. Print all movies" +
                "\n3. Print all movies by age" +
                "\n4. Print all movies by genre" +
                "\n5. Print all award-winning movies" +
                "\n6. Print all Oscar-winning movies" +
                "\n7. Print my info" +
                "\n8. Individual actions with movies";
    }

    public static String getSubMenuTemplate(){
        ColorChanger.changeColor(Color.GREEN);
        return  "\n1. Print movie info" +
                "\n2. Print movie awards" +
                "\n3. Print movie age" +
                "\n4. Print movie rating" +
                "\n5. Back to main menu";
    }

    public static void printMovieTypeMenu(){
        ColorChanger.changeColor(Color.YELLOW);
        System.out.println("Choose movie type number");
        System.out.println(
                "\n1. Feature film" +
                        "\n2. Animation" +
                        "\n3. Music film" +
                        "\n4. Soap opera" +
                        "\n5. Back to main menu");
    }
}
