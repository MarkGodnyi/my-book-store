package store.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import store.dto.request.CreateBookRequestDto;
import store.dto.response.BookDto;
import store.service.BookService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/books")
@Tag(name = "Book endpoints",
        description = "Send requests to different endpoints to access and manage book db")
public class BookController {
    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Get all books", description = "Get a list of all books")
    public List<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book book by id",
            description = "EntityNotFound exception will be thrown if can't get book by id")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Add book to db", description = "All fields of book must be present")
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto createBookRequestDto) {
        return bookService.save(createBookRequestDto);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update book by id",
            description = "EntityNotFound exception will be thrown if can't get book by id")
    public BookDto updateBookById(@RequestBody @Valid CreateBookRequestDto requestDto,
                                  @PathVariable Long id) {
        return bookService.update(requestDto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    @Operation(summary = "Delete book by id", description = "Safe delete a book by id")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBookById(@PathVariable Long id) {
        bookService.delete(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Search for books by parameters",
            description = "Locates books based on specified criteria, "
                    + "such as title, price, or author.")
    public List<BookDto> searchBooks(@RequestBody Map<String, List<String>> params,
                                     Pageable pageable) {
        return bookService.searchByParams(params, pageable);
    }
}
