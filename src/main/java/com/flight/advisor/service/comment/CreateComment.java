package com.flight.advisor.service.comment;

import com.flight.advisor.model.City;
import com.flight.advisor.model.Comment;
import com.flight.advisor.repository.CommentRepository;
import com.flight.advisor.service.city.GetCityById;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateComment {

    private final CommentRepository commentRepository;
    private final GetCityById getCityById;

    public Comment execute(Comment comment, UUID cityId) {
        log.info("Trying to save comment for city: {}", cityId);

        final City city = getCityById.execute(cityId);

        comment.setCity(city);
        final Comment newComment = commentRepository.save(comment);
        log.info("Comment created: {} for city: {}", newComment.getId(), cityId);

        return newComment;
    }
}
