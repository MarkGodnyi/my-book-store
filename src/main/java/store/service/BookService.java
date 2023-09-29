package store.service;

import java.util.List;
import java.util.Map;
import store.dto.request.CreateBookRequestDto;
import store.dto.response.BookDto;

public interface BookService {
    BookDto save(CreateBookRequestDto createBookRequestDto);

    List<BookDto> findAll();

    BookDto getById(Long id);

    BookDto update(CreateBookRequestDto requestDto, Long id);

    void delete(Long id);

    List<BookDto> searchByParams(Map<String, List<String>> params);
}
