package com.example.mtb.mapper;

import com.example.mtb.dto.FeedbackResponse;
import com.example.mtb.entity.Feedback;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMapper {

    public FeedbackResponse feedbackResponseMapper(Feedback feedback) {
        if (feedback == null)
            return null;
        return new FeedbackResponse(
                feedback.getFeedbackId(),
                feedback.getRating(),
                feedback.getReview()
        );
    }
}
