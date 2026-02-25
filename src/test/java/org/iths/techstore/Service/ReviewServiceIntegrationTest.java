package org.iths.techstore.Service;

import jakarta.transaction.Transactional;
import org.iths.techstore.Model.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
// Reset the database
@Transactional
public class ReviewServiceIntegrationTest {

    @Autowired
    private ReviewService reviewService;

    // Get all reviews returns list
    @Test
    void getAllReviewsShouldReturnList() {

        // Save a review
        Review review = new Review(
                null,
                "Samsung Galaxy S24 Ultra",
                7,
                "Okay phone, I guess...",
                LocalDate.now(),
                "J.Y Park"
        );
        reviewService.createReview(review);

        // Act
        List<Review> result = reviewService.getAllReviews();

        // Assert
        assertEquals(1, result.size());
    }

    //Get review by id works
    @Test
    void getReviewByIdShouldReturnReview() {

        // Save review
        Review review = new Review(
                null,
                "Samsung Galaxy S25 Ultra",
                8,
                "Almost perfect",
                LocalDate.now(),
                "BoA"
        );
        Review saved = reviewService.createReview(review);

        // Act
        Review found = reviewService.getReviewById(saved.getId());

        // Assert
        assertEquals("Samsung Galaxy S25 Ultra", found.getProductName());
        assertEquals("BoA", found.getReviewerName());
    }

    // Create review saved correctly
    @Test
    void createReviewShouldSaveReview() {

        // Create test review
        Review review = new Review(
                null,
                "Samsung Galaxy S26 Ultra",
                10,
                "Early release, great phone!",
                LocalDate.now(),
                "J.Y Park"
        );

        // Act
        Review saved = reviewService.createReview(review);

        // Assert
        assertNotNull(saved.getId());
        assertEquals("Samsung Galaxy S26 Ultra", saved.getProductName());
    }
}
