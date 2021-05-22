import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings () {
        SecondRatings secondRatings = new SecondRatings("ratedmovies_full", "ratings");
        
        System.out.println("Total number of movies : " + secondRatings.getMovieSize());
        System.out.println("Total number of raters : " + secondRatings.getRaterSize());
        
        int MinNumOfRatings = 20; // variable
        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(MinNumOfRatings);
        Collections.sort(averageRatings);
        int ic=0;
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + secondRatings.getTitle(rating.getItem()));
            if(ic++==10)break;
        }
        System.out.println("There are " + averageRatings.size() + " movies with " +
        MinNumOfRatings + " or more ratings");
    }
    
    public void getAverageRatingOneMovie () {
        SecondRatings secondRatings = new SecondRatings ("ratedmovies_full", "ratings");
        
        String title = "The Maze Runner"; // variable
        int MinNumOfRatings = 1; // variable
        
        String movieID = secondRatings.getID(title);
        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(MinNumOfRatings);
        for (Rating rating : averageRatings) {
            if (rating.getItem().equals(movieID)) {
                System.out.println("For movie \"" + title + "\" the average rating is " 
                + rating.getValue());
            }
        }
    }
}