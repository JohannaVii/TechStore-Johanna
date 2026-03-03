package org.iths.techstore.Controller;

import jakarta.transaction.Transactional;
import org.iths.techstore.Model.Review;
import org.iths.techstore.Repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Start server
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

// Reset database
@Transactional
public class ReviewControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ReviewRepository reviewRepository;

    // Get all reviews returns list
    @Test
    void getAllReviewsShouldReturnList() {

        // Create review
        Review review = new Review(
                null,
                "Samsung Galaxy S24 Ultra",
                7,
                "Okay phone, I guess...",
                LocalDate.now(),
                "J.Y Park"
        );

        // Send POST
        webTestClient.post()
                .uri("/reviews")
                .bodyValue(review)
                .exchange()
                .expectStatus().isCreated();

        // Check count
        assertEquals(1, reviewRepository.count());

        // Get saved
        Review saved = reviewRepository.findAll().get(0);

        // Check value
        assertEquals("Samsung Galaxy S26 Ultra", saved.getProductName());
    }

    // Get review by id works
    @Test
    void getReviewByIdShouldReturnReview() {

        // Create review
        Review review = new Review(
                null,
                "Samsung Galaxy S25 Ultra",
                8,
                "Almost perfect",
                LocalDate.now(),
                "BoA"
        );

        // Save review
        Review saved = reviewRepository.save(review);

        // Get review
        webTestClient.get()
                .uri("/reviews/" + saved.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Review.class)
                .value(savedReview -> assertEquals("Samsung Galaxy S25 Ultra", savedReview.getProductName()));
    }

    // Create review save correctly
    @Test
    void createReviewShouldSaveReview() {

        // Create review
        Review review = new Review(
                null, "Samsung Galaxy S26 Ultra",
                10,
                "Early release, great phone!",
                LocalDate.now(),
                "J.Y Park"
        );

        // Send POST
        webTestClient.post()
                .uri("/reviews")
                .bodyValue(review)
                .exchange()
                .expectStatus().isCreated();

        // Check count
        assertEquals(1, reviewRepository.count());

        // Get saved
        Review savedReview = reviewRepository.findAll().get(0);

        // Check value
        assertEquals("Samsung Galaxy S26 Ultra", savedReview.getProductName());
    }
}
