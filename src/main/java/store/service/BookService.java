package store.service;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;
import store.dto.request.CreateBookRequestDto;
import store.dto.response.BookDto;
import store.dto.response.BookDtoWithoutCategoryIds;

public interface BookService {
    BookDto save(CreateBookRequestDto createBookRequestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto getById(Long id);

    BookDto update(CreateBookRequestDto requestDto, Long id);

    void delete(Long id);

    List<BookDto> searchByParams(Map<String, List<String>> params, Pageable pageable);

    List<BookDtoWithoutCategoryIds> findAllByCategoryId(Long categoryId, Pageable pageable);
}
