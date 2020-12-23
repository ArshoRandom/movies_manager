package models.movies;

import models.movies.base.AbstractMovie;
import models.movies.constants.MovieType;

public class FeatureFilm extends AbstractMovie {

    public FeatureFilm() {
        this.type = MovieType.FEATURE_FILM;
    }

    @Override
    public void printInfo() {
        super.printInfo();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
