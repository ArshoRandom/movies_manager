package models.movies;

import exceptions.InvalidPropertyException;
import models.movies.base.AbstractMovie;
import models.movies.constants.MovieType;

public class SoapOpera extends AbstractMovie {

    private int seriesCount;

    public int getSeriesCount() {
        return seriesCount;
    }

    public SoapOpera() {
        this.type = MovieType.SOAP_OPERA;
    }

    public void setSeriesCount(int seriesCount) {
        InvalidPropertyException.check((seriesCount < 2),"soap opera must have greater than 2 series");
        this.seriesCount = seriesCount;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Series count : " + this.seriesCount);
    }

    @Override
    public String toString() {
        String data = super.toString();
        return data + "|" + this.seriesCount;
    }
}
