package store.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import store.dto.request.CreateBookRequestDto;
import store.dto.response.BookDto;
import store.dto.response.BookDtoWithoutCategoryIds;
import store.exception.EntityNotFoundException;
import store.mapper.BookDtoMapper;
import store.model.Book;
import store.repository.BookRepository;
import store.repository.specification.BookSpecificationProvider;
import store.service.BookService;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookDtoMapper bookDtoMapper;
    private final BookSpecificationProvider bookSpecificationProvider;

    @Override
    public BookDto save(CreateBookRequestDto createBookRequestDto) {
        Book book = bookDtoMapper.toModel(createBookRequestDto);
        return bookDtoMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getById(Long id) {
        String exAnswer = String.format("Book with id: %s doesn't found", id);
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(exAnswer));
        return bookDtoMapper.toDto(book);
    }

    @Override
    public BookDto update(CreateBookRequestDto requestDto, Long id) {
        String exAnswer = String.format("Book with id: %s doesn't found", id);
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(exAnswer));
        bookDtoMapper.updateBookFromDto(requestDto, book);
        bookRepository.save(book);
        return bookDtoMapper.toDto(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> searchByParams(Map<String, List<String>> params, Pageable pageable) {
        Specification<Book> specification = Specification.where(null);
        for (Map.Entry<String, List<String>> entry: params.entrySet()) {
            Specification<Book> spec = bookSpecificationProvider
                    .getSpecification(entry.getValue(), entry.getKey());
            specification = specification.and(spec);
        }
        return bookRepository.findAll(specification, pageable).stream()
                .map(bookDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDtoWithoutCategoryIds> findAllByCategoryId(Long categoryId, Pageable pageable) {
        return bookRepository.findAllByCategoryId(categoryId, pageable).stream()
                .map(bookDtoMapper::toBookDtoWithoutCategoryIds)
                .collect(Collectors.toList());
    }
}
