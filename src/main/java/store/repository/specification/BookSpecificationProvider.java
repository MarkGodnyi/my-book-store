package store.repository.specification;

import jakarta.persistence.criteria.Predicate;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import store.model.Book;

@Component
public class BookSpecificationProvider implements SpecificationProvider<Book> {
    @Override
    public Specification<Book> getSpecification(List<String> params, String fieldName) {
        return (root, query, criteriaBuilder) -> {
            Predicate inPredicate = root.get(fieldName).in(params);
            return criteriaBuilder.and(inPredicate);
        };
    }
}
