package com.flight.advisor.service.comment;

import com.flight.advisor.exception.comment.CommentNotFoundException;
import com.flight.advisor.model.Comment;
import com.flight.advisor.repository.CommentRepository;
import com.flight.advisor.util.TestsHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCommentByIdTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private GetCommentById getCommentById;

    @Test
    void getCommentById_CommentId_ReturnsCommentWithProvidedId() {
        // Setup
        final UUID id = UUID.randomUUID();
        final Comment comment = TestsHelper.getComments().get(0);
        comment.setId(id);

        // Mocks
        when(commentRepository.findById(any(UUID.class))).thenReturn(Optional.of(comment));

        // Act
        final Comment result = getCommentById.execute(id);

        // Assert
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getText()).isNotBlank();
    }

    @Test
    void getCommentById_CommentId_ThrowsCommentNotFoundException() {
        // Setup

        // Mocks
        when(commentRepository.findById(any(UUID.class))).thenThrow(new CommentNotFoundException(UUID.randomUUID()));

        // Act & Assert
        assertThrows(CommentNotFoundException.class, () -> getCommentById.execute(UUID.randomUUID()));
    }
}