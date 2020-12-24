package util.color;

public class ColorChanger {

    private ColorChanger(){}

    public static void changeColor(int color){
        System.out.print("\u001B[3"+ color + "m");
    }

}
