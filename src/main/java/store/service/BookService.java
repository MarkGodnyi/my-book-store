package store.service;

import java.util.List;
import store.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();

}
