package com.example.mtb.service.serviceimpl;

import com.example.mtb.dto.FeedbackRequest;
import com.example.mtb.dto.FeedbackResponse;
import com.example.mtb.entity.Feedback;
import com.example.mtb.entity.User;
import com.example.mtb.exceptions.MovieNotFoundByIdException;
import com.example.mtb.mapper.FeedbackMapper;
import com.example.mtb.repository.FeedbackRepository;
import com.example.mtb.repository.MovieRepository;
import com.example.mtb.repository.UserRepository;
import com.example.mtb.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    @Override
    public FeedbackResponse createFeedback(String movieId, FeedbackRequest feedbackRequest, String email) {
        if(movieRepository.existsById(movieId)){
            Feedback feedback = copy(feedbackRequest, new Feedback(), movieId, email);

            return feedbackMapper.feedbackResponseMapper(feedback);
        }
        throw new MovieNotFoundByIdException("No movie found in database");
    }

    private Feedback copy(FeedbackRequest feedbackRequest, Feedback feedback, String movieId, String email) {
        feedback.setRating(feedbackRequest.rating());
        feedback.setReview(feedbackRequest.review());
        feedback.setMovie(movieRepository.findById(movieId).get());
        feedback.setUser((User) userRepository.findByEmail(email));
        feedbackRepository.save(feedback);
        return feedback;
    }
}
