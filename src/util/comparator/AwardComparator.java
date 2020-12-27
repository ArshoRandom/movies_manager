package util.comparator;

import models.movies.base.AbstractMovie;

import java.util.Comparator;
/**
 *Comparator implementation for comparing {@link models.movies.base.Movie} awards
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see Comparator
 * @see AbstractMovie
 */
public class AwardComparator implements Comparator<AbstractMovie> {

    /**
     * Comparing 2 movie awards count
     * returns 0 if count of both movies is equal
     * returns positive number if first movie awards count bigger than second movie awards count
     * otherwise returns negative number
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     */
    @Override
    public int compare(AbstractMovie o1, AbstractMovie o2) {
        return o1.getAwardMap().size() - o2.getAwardMap().size();
    }
}
