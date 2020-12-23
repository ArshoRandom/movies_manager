package util.movieutil;

import models.movies.Animation;
import models.movies.FeatureFilm;
import models.movies.MusicFilm;
import models.movies.SoapOpera;
import models.movies.base.AbstractMovie;
import models.movies.constants.Genre;
import models.movies.constants.MovieType;
import util.StringUtils;
import util.validators.ValidatorUtils;

import java.util.Map;
import java.util.Set;

public class MovieFactory {

    public static <T extends AbstractMovie> T createMovie(MovieType type, Map<String,String> properties){
        String title = properties.get("title");
        String country = properties.get("country");
        String premiereDate = properties.get("premiereDate");
        int ageRestriction = Integer.parseInt(properties.get("ageRestriction"));;
        String genre = properties.get("genre");;
        Map<String, Set<Integer>> awardMap = StringUtils.mapStringToAwardMap(properties.get("awardMap"));;

        ValidatorUtils.isEmpty(title,"title");
        ValidatorUtils.isEmpty(genre,"genre");
        ValidatorUtils.validateMovieCreatorCountry(country);
        ValidatorUtils.validateMoviePremierDate(premiereDate);
        ValidatorUtils.validateAwardYear(premiereDate,properties.get("awardMap"));
        Set<Genre> genres = StringUtils.mapStringToGenreSet(genre);


        switch (type){
            case SOAP_OPERA:
                ValidatorUtils.validateAgeRestriction(ageRestriction);
                SoapOpera soapOpera = new SoapOpera();
                soapOpera.setTitle(title);
                soapOpera.setCountry(country);
                soapOpera.setAgeRestriction(ageRestriction);
                soapOpera.setGenre(genres);
                soapOpera.setPremiereDate(premiereDate);
                soapOpera.setAwardMap(awardMap);
                soapOpera.setSeriesCount(Integer.parseInt(properties.get("optional")));
                return (T) soapOpera;
            case ANIMATION:
                ValidatorUtils.validateAnimationAgeRestriction(ageRestriction);
                Animation animation = new Animation();
                animation.setTitle(title);
                animation.setCountry(country);
                animation.setAgeRestriction(ageRestriction);
                animation.setGenre(genres);
                animation.setPremiereDate(premiereDate);
                animation.setAwardMap(awardMap);
                animation.setDrawn(Boolean.parseBoolean(properties.get("optional")));
                return (T) animation;
            case MUSIC_FILM:
                String mainMusic = properties.get("optional");
                ValidatorUtils.validateAgeRestriction(ageRestriction);
                ValidatorUtils.validateMovieMainMusic(mainMusic);
                MusicFilm musicFilm = new MusicFilm();
                musicFilm.setTitle(title);
                musicFilm.setCountry(country);
                musicFilm.setAgeRestriction(ageRestriction);
                musicFilm.setGenre(genres);
                musicFilm.setPremiereDate(premiereDate);
                musicFilm.setAwardMap(awardMap);
                musicFilm.setMainMusic(mainMusic);
                return (T) musicFilm;
            case FEATURE_FILM:
                ValidatorUtils.validateAgeRestriction(ageRestriction);
                FeatureFilm featureFilm = new FeatureFilm();
                featureFilm.setTitle(title);
                featureFilm.setCountry(country);
                featureFilm.setAgeRestriction(ageRestriction);
                featureFilm.setGenre(genres);
                featureFilm.setPremiereDate(premiereDate);
                featureFilm.setAwardMap(awardMap);
                return (T) featureFilm;
            default:
                throw new RuntimeException("Invalid type : " + type);
        }
    }
}
