package com.iphayao.ratingservice;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> findAllRatings() {
        return ratingRepository.findAll();
    }

    public Rating findRatingByBookId(Long ratingId) {
        return ratingRepository.findById(ratingId)
                .orElseThrow(() -> new RatingNotFoundException("Rating not found. ID: " + ratingId));
    }

    public Rating createRating(Rating rating) {
        Rating newRating = Rating.builder()
                .bookId(rating.getBookId())
                .stars(rating.getStars())
                .build();
        return ratingRepository.save(newRating);
    }

    public Rating updateRating(Rating rating, Long ratingId) {
        Preconditions.checkNotNull(rating);
        Preconditions.checkState(rating.getId() == ratingId);
        Preconditions.checkNotNull(ratingRepository.findById(ratingId));
        return ratingRepository.save(rating);
    }

    public Rating updateRating(Map<String, String> updates, Long ratingId) {
        final Rating rating = findRatingByBookId(ratingId);
        updates.keySet()
                .forEach(key -> {
                    switch (key) {
                        case "stars":
                            rating.setStars(Integer.parseInt(updates.get(key)));
                            break;
                    }
                });
        return ratingRepository.save(rating);
    }

    public void deleteRating(Long ratingId) {
        ratingRepository.deleteById(ratingId);
    }
}
