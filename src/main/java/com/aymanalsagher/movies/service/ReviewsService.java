package com.aymanalsagher.movies;

import com.aymanalsagher.movies.model.Movie;
import com.aymanalsagher.movies.model.Review;
import com.aymanalsagher.movies.repository.ReviewsRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewsService {

    private final MongoTemplate mongoTemplate;

    private final ReviewsRepository reviewsRepository;

    public ReviewsService(ReviewsRepository reviewsRepository, MongoTemplate mongoTemplate) {
        this.reviewsRepository = reviewsRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Review createReview(String reviewBody, String imdbId){
        Review review = reviewsRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;
    }
}
