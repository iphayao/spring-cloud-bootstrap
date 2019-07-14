package com.iphayao.ratingservice.rating;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> findAllRatings() {
        return ratingRepository.findAll();
    }

    public List<Rating> findRatingByBookId(Long bookId) {
        return ratingRepository.findRatingsByBookId(bookId);
    }

    public Rating findRatingById(Long ratingId) {
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
        final Rating rating = findRatingById(ratingId);
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
