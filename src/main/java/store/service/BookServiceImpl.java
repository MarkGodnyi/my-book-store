package store.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.dto.request.CreateBookRequestDto;
import store.dto.response.BookDto;
import store.exception.EntityNotFoudException;
import store.mapper.BookDtoMapper;
import store.model.Book;
import store.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookDtoMapper bookDtoMapper;

    @Override
    public BookDto save(CreateBookRequestDto createBookRequestDto) {
        Book book = bookDtoMapper.toModel(createBookRequestDto);
        return bookDtoMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getById(Long id) {
        String exAnswer = String.format("Book with id: %s isn't found", id);
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoudException(exAnswer));
        return bookDtoMapper.toDto(book);
    }

    @Override
    public BookDto update(CreateBookRequestDto requestDto, Long id) {
        String exAnswer = String.format("Book with id: %s isn't found", id);
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoudException(exAnswer));
        bookDtoMapper.updateBookFromDto(requestDto, book);
        bookRepository.save(book);
        return bookDtoMapper.toDto(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
