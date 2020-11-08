package com.flight.advisor.service.city;

import com.flight.advisor.dto.city.CityResponse;
import com.flight.advisor.model.City;
import com.flight.advisor.model.Comment;
import com.flight.advisor.repository.CommentRepository;
import com.flight.advisor.util.TestsHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllCitiesWithCommentsTest {

    @Mock
    private GetAllCitiesPaginated getAllCitiesPaginated;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private GetAllCitiesWithComments getAllCitiesWithComments;

    @Test
    void getAllCitiesWithCommentsPaginated_PaginationData_ReturnsAllCitiesAndItsCommentsWithinPagination() {
        // Setup
        final List<City> cities = TestsHelper.getCities();
        final List<Comment> comments = TestsHelper.getComments();
        final Integer page = 0;
        final Integer size = 10;
        final Integer commentsLimit = 5;

        // Mocks
        when(commentRepository.findAllByCityId(any(String.class), any(Integer.class)))
                .thenReturn(comments);

        when(getAllCitiesPaginated.execute(any(Integer.class), any(Integer.class)))
                .thenReturn(cities);

        // Act
        final List<CityResponse> result = getAllCitiesWithComments.execute(page, size, commentsLimit);

        // Assert
        assertThat(result.isEmpty()).isFalse();
        assertThat(result.get(0).getComments().size()).isEqualTo(comments.size());
        assertThat(result.get(0).getName()).contains(cities.get(0).getName());
    }

    @Test
    void getAllCitiesWithCommentsPaginated_PaginationData_ReturnsAllCitiesWithoutCommentsWithinPagination() {
        // Setup
        final List<City> cities = TestsHelper.getCities();
        final Integer page = 0;
        final Integer size = 10;
        final Integer commentsLimit = 5;

        // Mocks
        when(commentRepository.findAllByCityId(any(String.class), any(Integer.class)))
                .thenReturn(new ArrayList<>());

        when(getAllCitiesPaginated.execute(any(Integer.class), any(Integer.class)))
                .thenReturn(cities);

        // Act
        final List<CityResponse> result = getAllCitiesWithComments.execute(page, size, commentsLimit);

        // Assert
        assertThat(result.isEmpty()).isFalse();
        assertThat(result.get(0).getComments().isEmpty()).isTrue();
        assertThat(result.get(0).getName()).contains(cities.get(0).getName());
    }
}