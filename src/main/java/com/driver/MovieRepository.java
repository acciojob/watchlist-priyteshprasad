package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    private HashMap<String, Movie> moviemap = new HashMap<>();
    private HashMap<String, Director> dirmap = new HashMap<>();
    private HashMap<String, List<String>> pair = new HashMap<String, List<String>>();

    public String addMovieRepo(Movie m){
        String name = m.getName();
        moviemap.put(name, m);
        return "success";
    }
    public String addDirRepo(Director d){
        String name = d.getName();
        dirmap.put(name, d);
        return "success";
    }

    public String addPairRepo(String movieName, String directorName){
        List<String> li = null;
        if(moviemap.containsKey(movieName) && dirmap.containsKey(directorName)){
            if(pair.containsKey(directorName)){
                li = pair.get(directorName);
                li.add(movieName);
                pair.put(directorName, li);
            }else{
                li = new ArrayList<>();
                li.add(movieName);
                pair.put(directorName, li);
            }
        }
        return "success";
    }
    public Movie getMovieRepo(String name ){
        if( moviemap.containsKey(name)){
            return moviemap.get(name);
        }
        return null;
    }
    public Director getDirectorRepo( String name ){
        if( dirmap.containsKey(name)){
            return dirmap.get(name);
        }
        return null;
    }
    public List<String> getMovieList( String directorName ){
        if( pair.containsKey(directorName) ){
            return pair.get( directorName );
        }
        return new LinkedList<>();
    }
    public List<String> getAllMovieRepo(){
        List<String> movieList  = new LinkedList<>();
        for( String str : moviemap.keySet() ){
            movieList.add( str );
        }
        return movieList;
    }

    public String deleteDirWorkRepo(String name ) {
        // get dir movies
        if (pair.containsKey(name)) {
            List<String> dirMovies = pair.get(name);
            pair.remove(name);
            dirmap.remove(name);
            for (String m : dirMovies) {
                if (moviemap.containsKey(m)) {
                    moviemap.remove(m);
                }
            }
        }
        return "success";
    }
    public String deleteAllMovieRelatedToDirector(){
        for( Map.Entry< String, List<String> > entry : pair.entrySet() ){
            String dirName = entry.getKey();
            List<String> list = entry.getValue();
            for( String name : list  ){
                if( moviemap.containsKey( name ) ){
                    moviemap.remove( name );
                }
            }
            pair.remove( dirName );
            if( dirmap.containsKey(dirName)){
                dirmap.remove( dirName );
            }
        }
        return "success";
    }


}
