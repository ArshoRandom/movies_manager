package models.movies.base;

import models.movies.constants.Genre;
import models.movies.constants.MovieType;
import util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMovie implements Movie {

    private String title;
    private String country;
    private Date premiereDate;
    private int ageRestriction;
    private Set<Genre> genre;
    private Map<String, Set<Integer>> awardMap;

    protected MovieType type;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        char firstSymbol = title.charAt(0);
        if (Character.isLetter(firstSymbol) && !Character.isUpperCase(firstSymbol)) {
            title = StringUtils.capitalize(title);
        }
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {

        this.country = country;
    }

    public Date getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.premiereDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {

        this.ageRestriction = ageRestriction;

    }

    public Set<Genre> getGenre() {
        return genre;
    }

    public void setGenre(Set<Genre> genre) {
        this.genre = genre;
    }

    public Map<String, Set<Integer>> getAwardMap() {
        return awardMap;
    }

    public void setAwardMap(Map<String, Set<Integer>> awardMap) {
        this.awardMap = awardMap;
    }

    @Override
    public void printInfo() {

        String simpleDate = StringUtils.formatDateToString(this.premiereDate);
        String template = String.format("\n*****-%s-*****\n" +
                        "Premier date - %s\n" +
                        "Country - %s\n" +
                        "Age of Restriction - %d\n" +
                        "Genre - %s\n" +
                        "Awards - %s\n",
                this.title,
                simpleDate,
                this.country,
                this.ageRestriction,
                this.genre.toString(),
                this.awardMap.toString());
        System.out.print(template);

    }


    @Override
    public String toString() {
        String simpleDate = StringUtils.formatDateToString(this.premiereDate);
        return String.format("%s : %s , %s , %d , %s , %s , %s",
                this.type.name(),
                this.title,
                this.country,
                this.ageRestriction,
                this.genre,
                simpleDate,
                this.awardMap.toString());
    }
}
