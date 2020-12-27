package util.helpers;

import java.util.Objects;
/**
 * Helper class for general use, for creating pair instances
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see java.util.Map
 * @see models.user.User
 * @see models.movies.base.Movie
 * @see util.cache.MovieCache
 */
public class KeyPair<F,S> {

    private F first;
    private S second;

    /**
     * Constructor for crating pair using generic types
     * @param first
     * @param second
     */
    public KeyPair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyPair<?, ?> keyPair = (KeyPair<?, ?>) o;
        return Objects.equals(first, keyPair.first) &&
                Objects.equals(second, keyPair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
