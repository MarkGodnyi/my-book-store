package store.repository;

import java.util.List;
import store.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
