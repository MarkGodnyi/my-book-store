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
import store.dto.request.AddToCartRequestDto;
import store.dto.request.UpdateCartItemRequestDto;
import store.dto.response.ShoppingCartResponseDto;
import store.model.User;
import store.service.ShoppingCartService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public ShoppingCartResponseDto retrieveShoppingCart(Authentication authentication) {
        return shoppingCartService.getById(getShoppingCartId(authentication));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ShoppingCartResponseDto addCartItemToShoppingCart(
            @Valid @RequestBody AddToCartRequestDto requestDto,
            Authentication authentication) {
        return shoppingCartService.addToCart(requestDto, getShoppingCartId(authentication));
    }

    @PutMapping("/cart-items/{cartItemId}")
    public ShoppingCartResponseDto updateItem(
            @Valid @RequestBody UpdateCartItemRequestDto requestDto,
            @PathVariable Long cartItemId,
            Authentication authentication) {
        return shoppingCartService.updateCartItem(requestDto,
                cartItemId,
                getShoppingCartId(authentication));
    }

    @DeleteMapping("/cart-items/{cartItemId}")
    public ShoppingCartResponseDto deleteItem(@PathVariable Long cartItemId,
                                              Authentication authentication) {
        return shoppingCartService.removeCartItem(cartItemId,
                getShoppingCartId(authentication));
    }

    private Long getShoppingCartId(Authentication authentication) {
        return ((User) authentication.getPrincipal()).getId();
    }
}
