package store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}