package store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT count(ci) > 0 FROM CartItem ci "
            + "WHERE ci.book.id = :bookId "
            + "AND ci.shoppingCart.id = :shoppingCartId")
    Boolean isDuplicated(@Param("bookId") Long bookId,
                         @Param("shoppingCartId") Long shoppingCartId);
}
