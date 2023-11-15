package store.service;

import store.dto.request.AddToCartRequestDto;
import store.dto.request.UpdateCartItemRequestDto;
import store.dto.response.ShoppingCartResponseDto;

public interface ShoppingCartService {
    ShoppingCartResponseDto getById(Long id);

    ShoppingCartResponseDto addToCart(AddToCartRequestDto requestDto, Long id);

    ShoppingCartResponseDto updateCartItem(UpdateCartItemRequestDto requestDto,
                                           Long cartItemId,
                                           Long id);

    ShoppingCartResponseDto removeCartItem(Long cartItemId, Long id);
}
