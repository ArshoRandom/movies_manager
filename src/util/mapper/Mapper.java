package util.mapper;

import exceptions.InvalidDataFormatException;

import java.util.List;
import java.util.Map;
/**
 * Base interface for mappers
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see UserMapper
 * @see MovieMapper
 */
public interface Mapper<K,T> {

    /**
     * This method maps {@link List} of string values to {@link java.util.Map} where as a key {@linkplain K} instance is used,
     * and as a value {@link models.movies.base.Movie} instance is used
     * @param rawData list of string data
     * @return {@link Map} of {@linkplain T}
     */
    Map<K,T> map(List<String> rawData) throws InvalidDataFormatException;

}
