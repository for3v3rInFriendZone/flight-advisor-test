package com.flight.advisor.service.comment;

import com.flight.advisor.exception.city.CityNotFoundException;
import com.flight.advisor.model.City;
import com.flight.advisor.model.Comment;
import com.flight.advisor.repository.CommentRepository;
import com.flight.advisor.service.city.GetCityById;
import com.flight.advisor.util.TestsHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCommentTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private GetCityById getCityById;

    @InjectMocks
    private CreateComment createComment;

    @Test
    void createComment_TextAndCityIdProvided_ReturnsCreatedComment() {
        // Setup
        final UUID cityId = UUID.randomUUID();
        final City city = TestsHelper.getCity();
        city.setId(cityId);
        final Comment newComment = TestsHelper.getComments().get(0);
        final Comment returnedComment = TestsHelper.getComments().get(0);
        returnedComment.setCity(city);

        // Mocks
        when(getCityById.execute(any(UUID.class)))
                .thenReturn(city);

        when(commentRepository.save(any(Comment.class)))
                .thenReturn(returnedComment);

        // Act
        final Comment result = createComment.execute(newComment, cityId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getCity().getId()).isEqualTo(cityId);
    }

    @Test
    void createComment_TextAndCityIdProvided_ThrowsCityNotFoundException() {
        // Setup
        final UUID id = UUID.randomUUID();
        final City city = TestsHelper.getCity();
        city.setId(id);

        // Mocks
        when(getCityById.execute(any(UUID.class))).thenThrow(new CityNotFoundException(UUID.randomUUID()));

        // Act
        assertThrows(CityNotFoundException.class, () -> createComment.execute(any(Comment.class), id));
    }
}