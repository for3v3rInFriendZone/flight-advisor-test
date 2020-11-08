package com.flight.advisor.service.comment;

import com.flight.advisor.exception.city.CityNotFoundException;
import com.flight.advisor.exception.comment.CommentNotFoundException;
import com.flight.advisor.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RemoveCommentTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private RemoveComment removeComment;

    @Test
    void removeComment_CommentId_SuccessfulRemoval() {
        // Setup
        final UUID id = UUID.randomUUID();
        // Mocks
        when(commentRepository.existsById(any(UUID.class))).thenReturn(true);

        // Act
        removeComment.execute(id);

        // Assert
        verify(commentRepository, times(1)).deleteById(id);
    }

    @Test
    void removeComment_CommentId_ThrowsCommentNotFoundException() {
        // Setup
        final UUID id = UUID.randomUUID();
        // Mocks
        when(commentRepository.existsById(any(UUID.class))).thenReturn(false);

        // Act

        // Act & Assert
        assertThrows(CommentNotFoundException.class, () -> removeComment.execute(id));
    }
}