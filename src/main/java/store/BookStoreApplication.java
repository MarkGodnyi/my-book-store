package store;

import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import store.model.Book;
import store.service.BookService;

@SpringBootApplication
public class BookStoreApplication {
    private final BookService bookService;

    public BookStoreApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setAuthor("Andrzej Sapkowski");
            book.setTitle("The Last Wish");
            book.setPrice(BigDecimal.valueOf(10));
            book.setCoverImage("mock");
            book.setIsbn("9788090091252");
            bookService.save(book);
            System.out.println(bookService.findAll());
        };
    }
}
