package util;

import java.util.Scanner;

/**
 * Class is for reading questions from console , using {@link java.util.Scanner}
 *
 * @author Arshak Papoyan
 * @version 1.0
 * @since 25.12.2020
 */
public class Questionnaire {

    private Scanner scanner;

    private Questionnaire() {
        this.scanner = new Scanner(System.in);
    }


    /**
     * Showing question in console and read user answer from console
     *
     * @param question for answer
     * @return answer from console
     */
    public String askQuestion(String question) {
        System.out.println(question);
        return this.scanner.nextLine();
    }

    /**
     * Getting instance
     *
     * @return single instance of {@link Questionnaire}
     */
    public static Questionnaire getInstance() {
        return ScannerInstance.scanner;
    }

    private static class ScannerInstance {
        private static final Questionnaire scanner = new Questionnaire();
    }
}
