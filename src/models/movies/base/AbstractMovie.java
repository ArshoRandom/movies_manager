package models.movies.base;

import models.movies.constants.Genre;
import models.movies.constants.MovieType;
import util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
/**
 * Abstract implementation {@link Movie}
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see Movie
 * @see models.movies.SoapOpera
 * @see models.movies.MusicFilm
 * @see models.movies.Animation
 * @see StringUtils
 */
public abstract class AbstractMovie implements Movie {

    private String title;
    private String country;
    private Date premiereDate;
    private int ageRestriction;
    private Set<Genre> genre;
    private Map<String, Set<Integer>> awardMap;

    protected MovieType type;


    /**
     * Returns {@link AbstractMovie} title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Capitalizes and sets movie title
     * @param title data for setting
     */
    public void setTitle(String title) {
        char firstSymbol = title.charAt(0);
        if (Character.isLetter(firstSymbol) && !Character.isUpperCase(firstSymbol)) {
            title = StringUtils.capitalize(title);
        }
        this.title = title;
    }

    /**
     * Returns {@link AbstractMovie} country name
     * @return country name
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets movie country name
     * @param country data for setting
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns {@link Date} instance which contains movie premiere date
     * @return {@link Date} instance
     */
    public Date getPremiereDate() {
        return premiereDate;
    }

    /**
     * Converts and sets movie premiere date :
     * Converts special formatted string to {@link Date} instance
     * @param date string date
     */
    public void setPremiereDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.premiereDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * Returns {@link AbstractMovie} restriction age
     * @return int age
     */
    public int getAgeRestriction() {
        return ageRestriction;
    }

    /**
     * Sets movie age of restriction
     * @param ageRestriction
     */
    public void setAgeRestriction(int ageRestriction) {

        this.ageRestriction = ageRestriction;

    }

    /**
     * Returns {@link AbstractMovie} genres set
     * @return set of {@link Genre}
     */
    public Set<Genre> getGenre() {
        return genre;
    }

    /**
     * Sets movie genres
     * @param genre
     */
    public void setGenre(Set<Genre> genre) {
        this.genre = genre;
    }

    /**
     * Returns {@link Map} where keys are awards as string and values are {@link Set} of awarding years
     * @return awards map
     */
    public Map<String, Set<Integer>> getAwardMap() {
        return awardMap;
    }

    public void setAwardMap(Map<String, Set<Integer>> awardMap) {
        this.awardMap = awardMap;
    }

    /**
     * Prettifies and prints {@link AbstractMovie} sub classes instance info
     */
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


    /**
     * Returns instance formatted info for writing in file
     * @return formatted data
     */
    @Override
    public String toString() {
        String simpleDate = StringUtils.formatDateToString(this.premiereDate);
        return String.format("%s : %s|%s|%d|%s|%s|%s",
                this.type.name(),
                this.title,
                this.country,
                this.ageRestriction,
                this.genre,
                simpleDate,
                this.awardMap.toString());
    }
}
