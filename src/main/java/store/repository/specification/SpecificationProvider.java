package store.repository.specification;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<T> {
    Specification<T> getSpecification(List<String> params, String fieldName);
}
