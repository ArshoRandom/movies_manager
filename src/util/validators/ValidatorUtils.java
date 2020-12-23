package util.validators;

import exceptions.InvalidPropertyException;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtils {

    private static boolean isValidEmail(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    private static boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        int upperCaseCount = 0;
        int numberCount = 0;

        for (char symbol : password.toCharArray()) {
            if (Character.isDigit(symbol)) {
                numberCount++;
            } else if (Character.isLetter(symbol) && Character.isUpperCase(symbol)) {
                upperCaseCount++;
            }
        }
        return upperCaseCount >= 2 && numberCount >= 3;

    }

    private static boolean isValidPersonalData(String data) {
        if (data.trim().length() <= 1) {
            return true;
        }
        char[] symbols = data.toCharArray();
        if (Character.isLetter(symbols[0]) && Character.isUpperCase(symbols[0])) {
            for (int i = 1; i < symbols.length; i++) {
                if (!Character.isLetter(symbols[i])) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private static boolean isValidUsername(String username) {
        return username.length() > 10;
    }

    public static void validateProperties(String name, String surname, String username, String email, String password) {
        InvalidPropertyException.check(isValidPersonalData(name), name + " : name must be starts with uppercase and must have length greater than 1");
        InvalidPropertyException.check(isValidPersonalData(surname), surname + " : surname must be starts with uppercase and must have length greater than 1");
        InvalidPropertyException.check(!isValidUsername(username), username + " : username must have length greater than 10");
        InvalidPropertyException.check(!isValidEmail(email), email + " : invalid email format");
        InvalidPropertyException.check(!isValidPassword(password), "password must contain 2 or more uppercase letter and 3 or more numbers");
    }

    public static void validateAwardYear(String premierYear, String awardsAndYear) {
        Pattern pattern = Pattern.compile("\\d{4}");
        Matcher matcher = pattern.matcher(awardsAndYear);
        premierYear = premierYear.split("\\.")[2];
        while (matcher.find()) {
            InvalidPropertyException.check(Integer.parseInt(premierYear) > Integer.parseInt(matcher.group()), "awarding year must be greater than premier year");
        }

    }

    public static void validateMoviePremierDate(String date){
        InvalidPropertyException.check(!date.matches("\\d{1,2}\\.\\d{1,2}\\.\\d{4}"), "use format dd.MM.yyyy");
        String[] dateChunks = date.split("\\.");
        int year = Integer.parseInt(dateChunks[2]);
        int month = Integer.parseInt(dateChunks[1]);
        int day = Integer.parseInt(dateChunks[0]);
        InvalidPropertyException.check((year > Calendar.getInstance().get(Calendar.YEAR) || year < 1900), "year must be less than current year and greater than 1895");
        InvalidPropertyException.check((month > 12 || month <= 0), "incorrect month " + month);
        InvalidPropertyException.check((day > 31 || day <= 0), "incorrect day " + day);
    }

    public static void validateMovieCreatorCountry(String data){
        char[] symbols = data.toCharArray();
        for (char symbol : symbols) {
            InvalidPropertyException.check(!Character.isLetter(symbol), "country name must consist of letter");
        }
    }

    public static void validateAgeRestriction(int ageRestriction){
        InvalidPropertyException.check((ageRestriction > 25 || ageRestriction < 0), "age must be less than 25 and positive");
    }
    public static void validateAnimationAgeRestriction(int ageRestriction){
        InvalidPropertyException.check((ageRestriction > 16 || ageRestriction < 0),"age must be less than 16 and positive");
    }



}
