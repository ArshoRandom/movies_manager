package util;

import models.movies.constants.Genre;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private StringUtils(){}

    public static String capitalize(String str) {
        str = str.trim();
        if (str.contains(" ")) {
            String[] tokens = str.split(" ");
            for (int i = 0; i < tokens.length; i++) {
                tokens[i] = capitalize0(tokens[i]);
            }
            return String.join(" ", tokens);
        } else {
            return capitalize0(str);
        }
    }

    private static String capitalize0(String str) {
        str = str.trim();
        if (str.length() > 0) {
            String upperCase = str.substring(0, 1).toUpperCase();
            return upperCase + str.substring(1);
        }
        return "";
    }


    public static Genre getGenreByStr(String gv) {
        gv = gv.trim().toUpperCase();
        switch (gv) {
            case "ACTION":
                return Genre.ACTION;
            case "DRAMA":
                return Genre.DRAMA;
            case "MELODRAMA":
                return Genre.MELODRAMA;
            case "THRILLER":
                return Genre.THRILLER;
            case "ADVENTURE":
                return Genre.ADVENTURE;
            case "COMEDY":
                return Genre.COMEDY;
            case "FANTASY":
                return Genre.FANTASY;
            case "HORROR":
                return Genre.HORROR;
            case "MYSTERY":
                return Genre.MYSTERY;
            case "HISTORICAL":
                return Genre.HISTORICAL;
            default:
                throw new RuntimeException("Invalid genre");
        }
    }

    public static Set<Genre> mapStringToGenreSet(String formattedData){
        Pattern genresRegexp = Pattern.compile("[a-zA-Z]+");
        Matcher genresMatcher = genresRegexp.matcher(formattedData);
        Set<Genre> genres = new HashSet<>();
        while (genresMatcher.find()){
            genres.add(getGenreByStr(genresMatcher.group()));
        }
        return genres;
    }

    public static Map<String, Set<Integer>> mapStringToAwardMap(String strMap){
        Map<String, Set<Integer>> map = new HashMap<>();

        Pattern entryRegexp = Pattern.compile("[a-zA-Z\\s]+\\s?=\\s?\\[(\\d{4}\\s?,?\\s?)+]");
        Matcher entryMatcher = entryRegexp.matcher(strMap);

        Pattern awardsRegexp = Pattern.compile("[a-zA-Z\\s]+");
        Matcher awardMatcher;

        Pattern yearsRegexp = Pattern.compile("\\d{4}");
        Matcher yearsMatcher;
        Set<Integer> years;

        while (entryMatcher.find()){
            String entry = entryMatcher.group();
            awardMatcher = awardsRegexp.matcher(entry);
            yearsMatcher = yearsRegexp.matcher(entry);
            if (awardMatcher.find()){
                years = new TreeSet<>();
                while (yearsMatcher.find()) {
                    years.add(Integer.parseInt(yearsMatcher.group().trim()));
                }
                String awardName = awardMatcher.group().trim();
                if (map.containsKey(awardName)){
                    Set<Integer> existsData = map.get(awardName);
                    existsData.addAll(years);
                    map.put(awardName,existsData);
                }else {
                    map.put(awardMatcher.group().trim(), years);
                }
            }
        }
        return map;
    }


    public static String formatDateToString(Date premiereDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(premiereDate);
    }

    public static Map<String,String> propertyArrayToMap(String... array){
        Map<String,String> properties = new HashMap<>(array.length);
        properties.put("title",array[0]);
        properties.put("country",array[1]);
        properties.put("ageRestriction",array[2]);
        properties.put("genre",array[3]);
        properties.put("premiereDate",array[4]);
        properties.put("awardMap",array[5]);
        if (array.length > 6) {
            properties.put("optional", array[6]);
        }
        return properties;
    }

}
