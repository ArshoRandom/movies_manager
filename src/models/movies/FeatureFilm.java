package models.movies;

import models.movies.base.AbstractMovie;
import models.movies.constants.MovieType;
/**
 * Class is for modeling Feature films' objects
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
public class FeatureFilm extends AbstractMovie {

    public FeatureFilm() {
        this.type = MovieType.FEATURE_FILM;
    }

    /**
     * Prettifies and prints {@link FeatureFilm} instance info
     */
    @Override
    public void printInfo() {
        super.printInfo();
    }

    /**
     * Returns instance formatted info for writing in file
     * @return formatted data
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
