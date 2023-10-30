package store.service;

import java.util.List;
import store.model.CartItem;

public interface CartItemService {
    List<CartItem> getAllByShoppingCartId(Long id);
}
