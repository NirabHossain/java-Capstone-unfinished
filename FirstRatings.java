import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String fname){
        ArrayList<Movie>movies = new ArrayList<Movie>();
        FileResource fr= new FileResource("data/"+fname);
        for(CSVRecord rec: fr.getCSVParser()){
            Movie movie= new Movie(rec.get("id"), rec.get("title"), rec.get("year"),
                rec.get("genre"),rec.get("director"),rec.get("country"),
                rec.get("poster"),Integer.parseInt(rec.get("minutes")));
            movies.add(movie);
        }
        return movies;
    }
    public void testLoadMovies(){
        ArrayList<Movie> movies;
        movies=loadMovies("ratedmovies_full.csv");
        int gen=0,gret=0;
        for(Movie movie: movies){
            //System.out.println(movie);
            if(movie.getGenres().contains("Comedy"))gen++;
            if(movie.getMinutes()>150)gret++;
        }
        
        
        System.out.println("Total movies: "+movies.size()+"\nComedy Genre movies: "+
            gen+"\nMovies longer than 150 minutes: "+gret);
        countDirectorMap(movies);

    }

    
    public void countDirectorMap(ArrayList<Movie> movies){
        HashMap<String,Integer> map = new HashMap<String,Integer>();
    
        for(Movie movie : movies){
            String w = movie.getDirector();
            if (!map.containsKey(w))map.put(w,1);
            else map.put(w,map.get(w)+1);
        }
        
        int total = 0,large=0;
        String name="";
        for(String w : map.keySet()){
            int value = map.get(w);
            if(large<value){
                large=value;
                name=w;
            }
            //if (value > 10)System.out.println(value+"\t"+w);    
            total += value;
        }
        System.out.println("\nTotal movies count: "+total+"\nDifferent movies count= "+map.keySet().size()+"\nMaximum number of movies: "+name+" "+large);
    }

    public HashMap<String,Rater> loadRaters(String filename){
        HashMap<String,Rater> map = new HashMap<String,Rater>();
        FileResource fr = new FileResource("data/"+filename);
        for (CSVRecord record : fr.getCSVParser()){
            String raterID = record.get("rater_id");
            String movieID = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
            if (!map.equals(raterID)){
                Rater rater = new Rater(raterID);
                rater.addRating(movieID, rating);
                map.put(raterID,rater);
            }
            else{
                Rater rater=new Rater(raterID);
                rater.addRating(movieID ,rating);
                map.put(raterID,rater);
            }
            
        }
        return map;
    }

    public void testLoadRaters(){
        HashMap<String,Rater> map= new HashMap<String,Rater>();
        map=loadRaters("ratings.csv");
        for(String id: map.keySet()){
            //System.out.println(id+": "+map.get(id).numRatings());
        }
    }


}
