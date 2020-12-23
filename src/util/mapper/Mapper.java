package util.mapper;

import exceptions.InvalidDataFormatException;
import models.movies.base.AbstractMovie;

import java.util.List;
import java.util.Map;

public interface Mapper<K,T> {

    Map<K,T> map(List<String> rawData);

}
