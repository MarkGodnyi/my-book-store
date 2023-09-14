package store.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import store.model.Book;

@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Book save(Book book) {
        EntityManager manager = null;
        EntityTransaction transaction = null;
        try {
            manager = entityManagerFactory.createEntityManager();
            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save to db: " + book, e);
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }

    @Override
    public List<Book> findAll() {
        try (EntityManager manager = entityManagerFactory.createEntityManager()) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Book> query = builder.createQuery(Book.class);
            query.from(Book.class);
            return manager.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get books from db", e);
        }
    }

    @Override
    public Optional<Book> findBookById(long id) {
        try (EntityManager manager = entityManagerFactory.createEntityManager()) {
            Book book = manager.find(Book.class, id);
            return Optional.ofNullable(book);
        } catch (Exception e) {
            throw new RuntimeException("Can't get book from db by id: " + id, e);
        }
    }
}
