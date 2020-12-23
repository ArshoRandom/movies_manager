package util;

import java.util.Scanner;

public class ScannerReader {

    private ScannerReader(){}

    public String readLine(String question){
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }

    public static ScannerReader getInstance(){
        return ScannerInstance.scanner;
    }

    private static class ScannerInstance{
        private static final ScannerReader scanner = new ScannerReader();
    }
}
