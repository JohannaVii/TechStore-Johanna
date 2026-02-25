package org.iths.techstore.Service;

import org.iths.techstore.Exceptions.ReviewNotFoundException;
import org.iths.techstore.Model.Review;
import org.iths.techstore.Repository.ReviewRepository;
import org.iths.techstore.Validator.ReviewValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    // Attribute
    private final ReviewRepository reviewRepository;

    // Validator
    private final ReviewValidator reviewValidator;

    // Constructor
    public ReviewService(ReviewRepository reviewRepository, ReviewValidator reviewValidator) {
        this.reviewRepository = reviewRepository;
        this.reviewValidator = reviewValidator;
    }

    // Get all reviews
    public List<Review> getAllReviews() {

        // Log request
        logger.info("Get all reviews");

        return reviewRepository.findAll();
    }

    // Get review by id
    public Review getReviewById(Long id) {

        // Log id request
        logger.info("Get review with id {}", id);

        return reviewRepository.findById(id).orElseThrow(() -> {

            // Log missing review
            logger.warn("Review with id {} not found", id);

            return new ReviewNotFoundException("No review found with id " + id);
        });
    }

    // Create new review
    public Review createReview(Review review) {

        // Log create request
        logger.info("Create review");

        reviewValidator.validate(review);

        return reviewRepository.save(review);
    }

    // Update existing review
    public Review updateReview(Long id, Review review) {

        // Log update request
        logger.info("Update review with id {}", id);

        reviewValidator.validate(review);

        Review found = reviewRepository.findById(id).orElseThrow(() -> {

            // Log update error
            logger.warn("Update failed. Review with id {} not found", id);

            return new ReviewNotFoundException("No review found with id " + id);
        });

        // Keep original id
        review.setId(found.getId());

        return reviewRepository.save(review);
    }

    // Delete existing review
    public void deleteReview(Long id) {

        // Log delete request
        logger.info("Delete review with id {}", id);

        reviewRepository.findById(id).orElseThrow(() -> {

            // Log delete error
            logger.warn("Delete failed. Review with id {} not found", id);

            return new ReviewNotFoundException("No review found with id " + id);
        });

        // Delete review
        reviewRepository.deleteById(id);
    }
}
