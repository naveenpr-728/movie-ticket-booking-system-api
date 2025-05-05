package com.example.mtb.service;

import com.example.mtb.dto.FeedbackRequest;
import com.example.mtb.dto.FeedbackResponse;
import jakarta.validation.Valid;

public interface FeedbackService {
    FeedbackResponse createFeedback(String movieId, @Valid FeedbackRequest feedbackRequest, String email);
}
