package util;

import java.util.Scanner;

public class Questionnaire {

    private Scanner scanner;

    private Questionnaire(){
        this.scanner = new Scanner(System.in);
    }

    public String askQuestion(String question){
        System.out.println(question);
        return this.scanner.nextLine();
    }

    public static Questionnaire getInstance(){
        return ScannerInstance.scanner;
    }

    private static class ScannerInstance{
        private static final Questionnaire scanner = new Questionnaire();
    }
}
