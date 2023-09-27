package store.service;

import java.util.List;
import store.dto.request.CreateBookRequestDto;
import store.dto.response.BookDto;

public interface BookService {
    BookDto save(CreateBookRequestDto createBookRequestDto);

    List<BookDto> findAll();

    BookDto getById(Long id);

}
