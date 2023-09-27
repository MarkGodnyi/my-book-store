package store.repository;

import java.util.List;
import java.util.Optional;
import store.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();

    Optional<Book> findBookById(long id);
}
