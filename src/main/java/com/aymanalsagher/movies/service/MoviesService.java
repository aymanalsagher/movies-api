package com.aymanalsagher.movies;

import com.aymanalsagher.movies.model.Movie;
import com.aymanalsagher.movies.repository.MoviesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoviesService {

    private final MoviesRepository moviesRepository;

    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public List<Movie> allMovies(){
        return moviesRepository.findAll();
    }

    public Optional<Movie> singleMovie(String imdbId){
        return moviesRepository.findByImdbId(imdbId);
    }

}
