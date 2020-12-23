package util.comparator;

import models.movies.base.AbstractMovie;

import java.util.Comparator;

public class AwardComparator implements Comparator<AbstractMovie> {

    @Override
    public int compare(AbstractMovie o1, AbstractMovie o2) {
        return o1.getAwardMap().size() - o2.getAwardMap().size();
    }
}
