package models.movies;

import models.movies.constants.MovieType;

public class MusicFilm extends FeatureFilm {

    private String mainMusic;

    public String getMainMusic() {
        return mainMusic;
    }

    public void setMainMusic(String mainMusic) {
        this.mainMusic = mainMusic;
    }

    public MusicFilm() {
        this.type = MovieType.MUSIC_FILM;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Main music : " + this.mainMusic);
    }

    @Override
    public String toString() {
        String data = super.toString();
        return data + " , " + this.mainMusic;
    }
}
