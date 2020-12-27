package util.validators;

import exceptions.InvalidPropertyException;
import exceptions.InvalidUserDataException;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * In class validators are presented for general use cases
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
public class ValidatorUtils {

    private ValidatorUtils(){}

    /**
     * Returns {@code true} if email is valid , otherwise returns {@code false}
     * @param email data for validation
     * @return {@code true} if email is valid
     */
    private static boolean isValidEmail(String email) {
        Pattern emailRegexp =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailRegexp.matcher(email);
        return matcher.find();
    }

    /**
     * Returns {@code true} if length of password bigger than 8 and password consist of at least 3 numbers
     * and least 2 uppercase letters
     * @param password data for validation
     * @return {@code true} if password is valid
     */

    private static boolean isValidPassword(String password) {
        Pattern  passwordPattern = Pattern.compile("^(?=.*[0-9]{3,})(?=.*[A-Z]{2,}).{8,}$");
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }

    /**
     * Returns {@code true} if data starts with uppercase letter and continues with lowercase letters
     * and contains only letters
     * @param data data for validation
     * @return {@code true} if data is valid
     */
    private static boolean isValidPersonalData(String data) {
        if (data.trim().length() <= 1) {
            return false;
        }
        char[] symbols = data.trim().toCharArray();
        if (Character.isLetter(symbols[0]) && Character.isUpperCase(symbols[0])) {
            for (int i = 1; i < symbols.length; i++) {
                if (!Character.isLetter(symbols[i]) || Character.isUpperCase(symbols[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Returns {@code true} if username length is bigger than 10 and contains only digits and letters
     * @param username data for validation
     * @return {@code true} if data is valid
     */
    private static boolean isValidUsername(String username) {
        if (username.trim().length() < 10) {
            return false;
        }
        Pattern usernameRegexp = Pattern.compile("\\w+",Pattern.CASE_INSENSITIVE);
        Matcher matcher = usernameRegexp.matcher(username);
        return matcher.matches();
    }

    /**
     * Validate all user properties
     * @exception InvalidPropertyException if property is invalid
     * @param name name property
     * @param surname surname property
     * @param username username property
     * @param email email property
     * @param password password property
     */
    public static void validateProperties(String name, String surname, String username, String email, String password) {
        InvalidUserDataException.check(!isValidPersonalData(name), name + " : name must be starts with uppercase and must have length greater than 1");
        InvalidUserDataException.check(!isValidPersonalData(surname), surname + " : surname must be starts with uppercase and must have length greater than 1");
        InvalidUserDataException.check(!isValidUsername(username), username + " : username must have length greater than 10");
        InvalidUserDataException.check(!isValidEmail(email), email + " : invalid email format");
        InvalidUserDataException.check(!isValidPassword(password), "password must contain 2 or more uppercase letter and 3 or more numbers");
    }


    /**
     * Compare awarding years with premier year , if awarding year is invalid than it is ignored
     * @exception InvalidPropertyException if awarding year is before premier year
     * @param premierYear movie premier year
     * @param awardsAndYear movie awarding year
     */
    public static void validateAwardYear(String premierYear, String awardsAndYear) {
        Pattern pattern = Pattern.compile("\\d{4}");
        Matcher matcher = pattern.matcher(awardsAndYear);
        premierYear = premierYear.split("\\.")[2];
        while (matcher.find()) {
            InvalidPropertyException.check(Calendar.getInstance().get(Calendar.YEAR) < Integer.parseInt(matcher.group()), "awarding year must be lesser than current year");
            InvalidPropertyException.check(Integer.parseInt(premierYear) > Integer.parseInt(matcher.group()), "awarding year must be greater than premier year");
        }

    }

    /**
     * Validate movie date, with dd.MM.yyyy format
     * @exception InvalidPropertyException if month, year or day is invalid
     * @param date data for validation
     */
    public static void validateMoviePremierDate(String date){
        InvalidPropertyException.check(!date.matches("\\d{1,2}\\.\\d{1,2}\\.\\d{4}"), "use format dd.MM.yyyy");
        String[] dateChunks = date.split("\\.");
        int year = Integer.parseInt(dateChunks[2]);
        int month = Integer.parseInt(dateChunks[1]);
        int day = Integer.parseInt(dateChunks[0]);
        InvalidPropertyException.check((year > Calendar.getInstance().get(Calendar.YEAR) || year < 1900), "year must be less than current year and greater than 1900");
        InvalidPropertyException.check((month > 12 || month <= 0), "incorrect month " + month);
        InvalidPropertyException.check((day > 31 || day <= 0), "incorrect day " + day);
    }

    /**
     * Validate movie producer country
     * @exception  InvalidPropertyException if data is empty or contains not letter symbols
     * @param data data for validation
     */
    public static void validateMovieProducerCountry(String data){
        InvalidPropertyException.check((data.trim().isEmpty()),"country could not be empty");
        char[] symbols = data.toCharArray();
        for (char symbol : symbols) {
            InvalidPropertyException.check(!Character.isLetter(symbol), "country name must consist of letter");
        }
    }

    /**
     * Validate movie producer country
     * @exception  InvalidPropertyException if data is empty or contains not letter symbols
     * @param data data for validation
     */
    public static void validateMovieMainMusic(String data){
        InvalidPropertyException.check(data.trim().length() <= 3,"main music must be contains 3 or more characters");
    }

    /**
     * Validate movie age of restriction
     * @exception  InvalidPropertyException if age more than 25 or is negative or 0
     * @param ageRestriction data for validation
     */
    public static void validateAgeRestriction(int ageRestriction){
        InvalidPropertyException.check((ageRestriction > 25 || ageRestriction < 0), "age must be less than 25 and positive");
    }

    /**
     * Validate animation age of restriction
     * @exception  InvalidPropertyException if age more than 16 or is negative or 0
     * @param ageRestriction data for validation
     */
    public static void validateAnimationAgeRestriction(int ageRestriction){
        InvalidPropertyException.check((ageRestriction > 16 || ageRestriction < 0),"age must be less than 16 and positive");
    }

    /**
     * checks if property is empty
     * @exception  InvalidPropertyException if data is empty
     * @param data data for validation
     * @param  propName property name for message
     */
    public static void isEmpty(String data, String propName) {
        InvalidPropertyException.check((data.trim().isEmpty()),propName + " could not be empty");
    }

}
