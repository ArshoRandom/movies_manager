package util.color;
/**
 * Class is for changing console font color
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see Color
 */
public class ColorChanger {

    private ColorChanger(){}

    /**
     * Changes console color to any color enumerated in {@link Color}
     *
     * @param color color code
     */
    public static void changeColor(int color){
        System.out.print("\u001B[3"+ color + "m");
    }

}
