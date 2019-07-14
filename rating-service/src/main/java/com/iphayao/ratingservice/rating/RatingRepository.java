package com.iphayao.ratingservice.rating;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findRatingsByBookId(Long bookId);
}
