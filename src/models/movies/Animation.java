package models.movies;

import models.movies.base.AbstractMovie;
import models.movies.constants.MovieType;
/**
 * Class is for modeling Animations objects
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
public class Animation extends AbstractMovie {

    private boolean isDrawn;

    /**
     * Returns {@code true} if {@link Animation} is drawn, or false
     * @return true or false
     */
    public boolean isDrawn() {
        return isDrawn;
    }

    /**
     * Sets {@code true} if animation is drawn , otherwise sets false
     * @param drawn true or false
     */
    public void setDrawn(boolean drawn) {
        isDrawn = drawn;
    }

    public Animation() {
        this.type = MovieType.ANIMATION;
    }


    /**
     * Prettifies and prints {@link Animation} instance info
     */
    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Is Drawn - " + (this.isDrawn ? "Yes" : "No"));
    }

    /**
     * Returns instance formatted info for writing in file
     * @return formatted data
     */
    @Override
    public String toString() {
        String data = super.toString();
        return data + "|" + this.isDrawn;
    }
}
