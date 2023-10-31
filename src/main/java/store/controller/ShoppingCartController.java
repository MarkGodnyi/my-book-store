package store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import store.dto.request.CreateCartItemRequestDto;
import store.dto.request.UpdateCartItemRequestDto;
import store.dto.response.CartItemResponseDto;
import store.dto.response.ShoppingCartResponseDto;
import store.model.User;
import store.service.CartItemService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/cart")
public class ShoppingCartController {
    private final CartItemService cartItemService;

    @GetMapping
    public ShoppingCartResponseDto retrieveShoppingCart(Authentication authentication) {
        Long userId = ((User) authentication.getPrincipal()).getId();
        return cartItemService.getShoppingCart(userId);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CartItemResponseDto addCartItemToShoppingCart(
            @Valid @RequestBody CreateCartItemRequestDto requestDto) {
        return cartItemService.save(requestDto);
    }

    @PutMapping("/cart-items/{cartItemId}")
    public CartItemResponseDto updateItem(
            @Valid @RequestBody UpdateCartItemRequestDto requestDto,
            @PathVariable Long cartItemId) {
        return cartItemService.update(requestDto, cartItemId);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/cart-items/{cartItemId}")
    public void deleteItem(@PathVariable Long cartItemId) {
        cartItemService.delete(cartItemId);
    }
}
