package models.movies.constants;
/**
 *
 *
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
public enum Genre {
    ACTION, MELODRAMA, DRAMA, THRILLER, ADVENTURE, COMEDY, FANTASY, HORROR, MYSTERY, HISTORICAL;

    @Override
    public String toString() {
       return this.name().toLowerCase();
    }
}
