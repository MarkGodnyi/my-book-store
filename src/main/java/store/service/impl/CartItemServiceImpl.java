package store.service.impl;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.dto.request.CreateCartItemRequestDto;
import store.dto.request.UpdateCartItemRequestDto;
import store.dto.response.CartItemResponseDto;
import store.dto.response.ShoppingCartResponseDto;
import store.exception.EntityNotFoundException;
import store.mapper.CartItemDtoMapper;
import store.mapper.ShoppingCartDtoMapper;
import store.model.Book;
import store.model.CartItem;
import store.model.ShoppingCart;
import store.repository.BookRepository;
import store.repository.CartItemRepository;
import store.service.CartItemService;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;
    private final ShoppingCartDtoMapper shoppingCartDtoMapper;
    private final CartItemDtoMapper cartItemMapper;

    @Override
    public ShoppingCartResponseDto getShoppingCart(Long id) {
        Set<CartItem> cartItems = cartItemRepository.findAllByShoppingCartId(id);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(id);
        shoppingCart.setCartItems(cartItems);
        return shoppingCartDtoMapper.toDto(shoppingCart);
    }

    @Override
    public CartItemResponseDto save(CreateCartItemRequestDto requestDto) {
        String exAnswer = String.format("Book with id: %s doesn't found", requestDto.getBookId());
        Book book = bookRepository.findById(requestDto.getBookId()).orElseThrow(
                () -> new EntityNotFoundException(exAnswer));
        CartItem cartItem = cartItemMapper.toModel(requestDto);
        cartItem.getBook().setTitle(book.getTitle());
        cartItemRepository.save(cartItem);
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    public CartItemResponseDto update(UpdateCartItemRequestDto requestDto, Long id) {
        String exAnswer = String.format("Cart item with id: %s doesn't found", id);
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(exAnswer));
        cartItem.setQuantity(requestDto.getQuantity());
        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }

    @Override
    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }
}
