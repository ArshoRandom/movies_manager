package models.movies;

import exceptions.InvalidPropertyException;
import models.movies.base.AbstractMovie;
import models.movies.constants.MovieType;

public class Animation extends AbstractMovie {

    private boolean isDrawn;

    public boolean isDrawn() {
        return isDrawn;
    }

    public void setDrawn(boolean drawn) {
        isDrawn = drawn;
    }

    public Animation() {
        this.type = MovieType.ANIMATION;
    }



    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Is Drawn - " + (this.isDrawn ? "Yes" : "No"));
    }

    @Override
    public String toString() {
        String data = super.toString();
        return data + " , " + this.isDrawn;
    }
}
