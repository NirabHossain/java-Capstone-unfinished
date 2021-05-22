import java.util.ArrayList;
import java.util.Arrays;
public class Movie {
    private String id,title,genres,director,country,poster;
    private int year,minutes;

    public Movie (String anID, String aTitle, String aYear, String theGenres) {
        id = anID.trim();// just in case data file contains extra whitespace
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
    }

    public Movie (String anID, String aTitle, String aYear, String theGenres, String aDirector,
    String aCountry, String aPoster, int theMinutes) {
        // just in case data file contains extra whitespace
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
        director = aDirector;
        country = aCountry;
        poster = aPoster;
        minutes = theMinutes;
    }
    // Returns ID, title, year, Genre, country
    public String getID () {return id;}

    public String getTitle(){return title;}

    public int getYear(){return year;}

    public String getGenres () {return genres;}

    public String getCountry(){return country;}

    public String getDirector(){return director;}

    public String getPoster(){return poster;}

    public int getMinutes(){return minutes;}

    public String toString () {
        String result = "Movie [id=" + id + ", title=" + title + ", year=" + year + ", genres= " + genres + "]";
        return result;
    }
}
