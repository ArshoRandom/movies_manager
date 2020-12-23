package models.movies.constants;

public enum Genre {
    ACTION, MELODRAMA, DRAMA, THRILLER, ADVENTURE, COMEDY, FANTASY, HORROR, MYSTERY, HISTORICAL;

    @Override
    public String toString() {
       return this.name().toLowerCase();
    }
}
