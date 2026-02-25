package org.iths.techstore.Validator;

import org.iths.techstore.Exceptions.ReviewNotValidException;
import org.iths.techstore.Model.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ReviewValidator {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(ReviewValidator.class);

    // Validate review
    public void validate(Review review) {

        // Log validation start
        logger.info("Validate review");

        checkReview(review);
        checkName(review.getReviewerName());
        checkComment(review.getComment());
        checkRating(review.getRating());
    }

    // Verify input
    private void checkReview(Review review) {
        if (review == null) {

            // Log null review
            logger.warn("Error. Review is null");

            throw new ReviewNotValidException("Review is null");
        }
    }

    // Name required
    private void checkName(String name) {
        if (name == null || name.isBlank()) {

            // Log name error
            logger.warn("Error. Review name is null");

            throw new ReviewNotValidException("Reviewer name is null");
        }
    }

    // Comment required
    private void checkComment(String comment) {
        if (comment == null) {

            // Log comment error
            logger.warn("Review comment is null");

            throw new ReviewNotValidException("No review comment found");
        }
    }

    // Rating valid
    private void checkRating(int rating) {
        if (rating < 0 || rating > 10) {

            // Log rating error
            logger.warn("Review rating {} is out of range", rating);

            throw new ReviewNotValidException("Review rating is not valid");
        }
    }
}
