package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        String msg = this.movieService.addMovie(movie);
        return ResponseEntity.ok(msg);
    }
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director dr){
        String msg = this.movieService.addDirector(dr);
        return ResponseEntity.ok(msg);
    }

    @PostMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movieName, @RequestParam String directorName){
        return ResponseEntity.ok(this.movieService.addPair(movieName, directorName));
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name ){
        Movie obj = null;
        try {
            obj = this.movieService.getMovie(name);
            return ResponseEntity.of( Optional.of( obj ));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String name ){
        Director obj = null;
        try {
            obj = this.movieService.getDirector(name);
            return ResponseEntity.of( Optional.of( obj ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List< String >> getMoviesByDirectorName(@PathVariable("director") String name ){
        List<String> li = null;
        try {
            li = this.movieService.getMovieByDir(name);
            return ResponseEntity.of( Optional.of(li) );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity< List<String> > findAllMovies(){
        List<String> li = null;
        try {
            li = this.movieService.getAllMovieService();
            if( li.size() != 0 ){
                return ResponseEntity.of( Optional.of(li) );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String name ){

        return ResponseEntity.ok().body(this.movieService.deleteDirWork( name ) );
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        String msg = this.movieService.deleteEverythingRelatedtoPair();
        if( msg.equals( "success") ){
            return ResponseEntity.ok(msg);
        }
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
    }



}

