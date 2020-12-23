package util;

import models.movies.constants.Genre;

import java.text.SimpleDateFormat;
import java.util.*;

public class StringUtils {

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

    public static Set<Genre> mapStringToSetOfGenres(String genres){
        Set<Genre> genreSet;
        if (genres.contains(",")){
            genreSet = new HashSet<>();
            String[] tokens = genres.split(",");
            for (String genreToken : tokens){
                genreSet.add(getGenreByStr(genreToken));
            }
        }else {
            genreSet = Collections.singleton(getGenreByStr(genres));
        }
        return genreSet;
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
        if (formattedData.startsWith("[")){
            formattedData = formattedData.replace("[","").replace("]","");
        }
        StringTokenizer tokenizer = new StringTokenizer(formattedData,",");
        Set<Genre> genreSet = new HashSet<>(7); // possible maximum size
        String genreStr;
        while (tokenizer.hasMoreElements()){
            genreStr = tokenizer.nextToken().trim();
            genreSet.add(getGenreByStr(genreStr));
        }
        return genreSet;
    }

    public static Map<String, Set<Integer>> mapStringToAwardMap(String strMap){
        Map<String, Set<Integer>> map = new HashMap<>();
        strMap = strMap.replace("{","").replace("}","");
        String[] tokens = strMap.split("]");
        for (String tk : tokens){
            String[] awardAndValue = tk.split("=");
            String award = awardAndValue[0];
            if (award.startsWith(",")){
                award = award.replace(",","").trim();
            }
            Set<Integer> years = mapStringToIntSet(awardAndValue[1]);
            map.put(award,years);
        }
        return map;
    }

    public static Set<Integer> mapStringToIntSet(String strSet){
        strSet = strSet.replace("[","").replace("]","");
        Set<Integer> set = new HashSet<>();
        StringTokenizer tokenizer = new StringTokenizer(strSet,",");
        while (tokenizer.hasMoreElements()){
            set.add(Integer.parseInt(tokenizer.nextToken().trim()));
        }
        return set;
    }

    public static String formatDateToString(Date premiereDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(premiereDate);
    }

}
