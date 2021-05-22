import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class FirstRatings2 {

    public ArrayList<Movie> loadMovies(String fname){
        ArrayList<Movie>movies = new ArrayList<Movie>();
        FileResource fr= new FileResource("data/"+fname+".csv");
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
        movies=loadMovies("ratedmovies_full");
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

    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> raterList = new ArrayList<>();
        FileResource fr = new FileResource("data/"+filename+".csv");
        //Set-up variables to track current raterID and current raterList index
        String currID = "0";        int currIndex = -1;
        
        for (CSVRecord record : fr.getCSVParser()){
            String raterID = record.get("rater_id");
            String movieID = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
                        
            if (!currID.equals(raterID)){
                Rater rater = new Rater(raterID);
                rater.addRating(movieID, rating);
                raterList.add(rater);
                                  //Update variables
                currID = raterID;
                currIndex += 1;
            }
            else if (currID.equals(raterID))
                raterList.get(currIndex).addRating(movieID ,rating);
        }
        return raterList;
    }

    public void testLoadRaters(){
        ArrayList<Rater> raters= new ArrayList<Rater>();
        raters=loadRaters("ratings");//_short
        String giveId="193",movieID="1798709";
        int large=0,mv=0;
        System.out.println("Total Raters: "+raters.size());
            HashMap<String,Integer> map= new HashMap<String,Integer>();
        for(Rater rater: raters){
            String id=rater.getID();
            int n=rater.numRatings();
            
            if(large<n)large=n;
            ArrayList<String>items=rater.getItemsRated();
            if(giveId.equals(id))System.out.println(id+" No. has "+n+" ratings");
            if(rater.hasRating(movieID))mv++;
            //System.out.println("Raters id: "+id+": "+"Number of Ratings: "+n+" ");
            //for(String item: items)System.out.println(item+": "+rater.getRating(item));
            for(String item: items){
                if (!map.containsKey(item))map.put(item,1);
                else map.put(item,map.get(item)+1);
            }
            
        }
        
        System.out.println("Movie: "+movieID+" was rated by "+mv+" raters\nTotal Different Rated Movies: "+map.size());
        for(Rater rater:raters)
            if(rater.numRatings()==large)
                System.out.println("Highest rater's ID: "+rater.getID()+": "+rater.numRatings());
        
    }


}
