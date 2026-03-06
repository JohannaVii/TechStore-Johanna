package org.iths.techstore;

import jakarta.transaction.Transactional;
import org.iths.techstore.Model.Review;
import org.iths.techstore.Repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Start server
@SpringBootTest
@AutoConfigureMockMvc

// Reset database
@Transactional
public class ReviewIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReviewRepository reviewRepository;

    // Get all reviews returns list
    @Test
    void getAllReviewsShouldReturnList() throws Exception {

        // Create review
        Review review = new Review(
                null,
                "Samsung Galaxy S24 Ultra",
                7,
                "Okay phone, I guess...",
                LocalDate.now(),
                "J.Y Park"
        );

        // Save review
        reviewRepository.save(review);

        // Send GET
        mockMvc.perform(get("/reviews"))
                .andExpect(status().isOk())
                .andExpect(view().name("reviews"))
                .andExpect(model().attributeExists("reviews"));

        // Check count
        assertEquals(1, reviewRepository.count());
    }

    // Get review by id works
    @Test
    void getReviewByIdShouldReturnReview() throws Exception {
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
        mockMvc.perform(get("/reviews/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("review"))
                .andExpect(model().attributeExists("review"));
    }

    // Create review save correctly
    @Test
    void createReviewShouldSaveReview() throws Exception {

        // Send POST
        mockMvc.perform(post("/reviews")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("productName", "Samsung Galaxy S26 Ultra")
                        .param("rating", "10")
                        .param("comment", "Early release, great phone!")
                        .param("reviewDate", "2024-01-01")
                        .param("reviewerName", "J.Y Park")
                )
                .andExpect(status().isFound());

        // Check count
        assertEquals(1, reviewRepository.count());

        // Get saved
        Review saved = reviewRepository.findAll().get(0);

        // Check value
        assertEquals("Samsung Galaxy S26 Ultra", saved.getProductName());
    }
}
