package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(Movie mv){
        return this.movieRepository.addMovieRepo(mv);
    }
    public String addDirector(Director dr){
        return this.movieRepository.addDirRepo(dr);
    }
    public String addPair( String mv, String dr ){
        return this.movieRepository.addPairRepo( mv, dr );
    }

    public Movie getMovie( String name ){
        return this.movieRepository.getMovieRepo(name );
    }

    public Director getDirector( String name ){
        return this.movieRepository.getDirectorRepo(name);
    }

    public List<String> getMovieByDir(String name ){
        return this.movieRepository.getMovieList(name);
    }

    public List<String> getAllMovieService(){
        return this.movieRepository.getAllMovieRepo();
    }


    public String deleteDirWork( String name ){
        return this.movieRepository.deleteDirWorkRepo(name);
    }

    public String deleteEverythingRelatedtoPair(){
        return this.movieRepository.deleteAllMovieRelatedToDirector();
    }

    public MovieRepository getMovierepository() {
        return movieRepository;
    }

    public void setMovierepository(MovieRepository movierepository) {
        this.movieRepository = movierepository;
    }

}
