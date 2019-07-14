package com.iphayao.ratingservice;

import com.iphayao.ratingservice.rating.Rating;
import com.iphayao.ratingservice.rating.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    RatingService ratingService;

    @Autowired
    public DataLoader(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.ratingService.createRating(create(1L, 1));
        this.ratingService.createRating(create(1L, 2));
        this.ratingService.createRating(create(2L, 3));
        this.ratingService.createRating(create(2L, 4));
        this.ratingService.createRating(create(2L, 5));
    }

    private Rating create(Long bookId, int stars) {
        return Rating.builder()
                .bookId(bookId)
                .stars(stars)
                .build();
    }
}
