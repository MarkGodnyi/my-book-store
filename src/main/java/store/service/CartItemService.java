package store.service;

import store.dto.request.CreateCartItemRequestDto;
import store.dto.request.UpdateCartItemRequestDto;
import store.dto.response.CartItemResponseDto;
import store.dto.response.ShoppingCartResponseDto;

public interface CartItemService {
    ShoppingCartResponseDto getShoppingCart(Long id);

    CartItemResponseDto save(CreateCartItemRequestDto requestDto);

    CartItemResponseDto update(UpdateCartItemRequestDto requestDto, Long id);

    void delete(Long id);
}
