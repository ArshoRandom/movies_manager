package models.movies;

import exceptions.InvalidPropertyException;
import models.movies.base.AbstractMovie;
import models.movies.constants.MovieType;
/**
 * Class is for modeling Soap Opera's objects
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 */
public class SoapOpera extends AbstractMovie {

    private int seriesCount;

    /**
     * Returns {@link SoapOpera} series count
     * @return series count
     */
    public int getSeriesCount() {
        return seriesCount;
    }

    public SoapOpera() {
        this.type = MovieType.SOAP_OPERA;
    }

    /**
     * Validates and sets {@link SoapOpera} series count
     * @param seriesCount data for setting
     * @exception InvalidPropertyException if series count is lesser than 2
     */
    public void setSeriesCount(int seriesCount) {
        InvalidPropertyException.check((seriesCount < 2),"soap opera must have more than 2 series");
        this.seriesCount = seriesCount;
    }

    /**
     * Prettifies and prints {@link SoapOpera} instance info
     */
    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Series count : " + this.seriesCount);
    }

    /**
     * Returns instance formatted info for writing in file
     * @return formatted data
     */
    @Override
    public String toString() {
        String data = super.toString();
        return data + "|" + this.seriesCount;
    }
}
