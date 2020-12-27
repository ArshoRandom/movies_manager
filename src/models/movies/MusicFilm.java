package models.movies;

import models.movies.constants.MovieType;
/**
 * Class is for modeling Music Films objects
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
public class MusicFilm extends FeatureFilm {

    private String mainMusic;

    /**
     * Returns {@link MusicFilm} main music name
     * @return main music name
     */
    public String getMainMusic() {
        return mainMusic;
    }

    /**
     * Sets {@link MusicFilm} music film name
     * @param mainMusic data for setting
     */
    public void setMainMusic(String mainMusic) {
        this.mainMusic = mainMusic;
    }

    public MusicFilm() {
        this.type = MovieType.MUSIC_FILM;
    }

    /**
     * Prettifies and prints {@link MusicFilm} instance info
     */
    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Main music : " + this.mainMusic);
    }

    /**
     * Returns instance formatted info for writing in file
     * @return formatted data
     */
    @Override
    public String toString() {
        String data = super.toString();
        return data + "|" + this.mainMusic;
    }
}
