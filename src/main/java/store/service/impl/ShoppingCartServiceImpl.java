package store.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.dto.request.AddToCartRequestDto;
import store.dto.request.UpdateCartItemRequestDto;
import store.dto.response.ShoppingCartResponseDto;
import store.exception.DuplicatedItemException;
import store.exception.EntityNotFoundException;
import store.mapper.CartItemDtoMapper;
import store.mapper.ShoppingCartDtoMapper;
import store.model.Book;
import store.model.CartItem;
import store.model.ShoppingCart;
import store.repository.BookRepository;
import store.repository.CartItemRepository;
import store.repository.ShoppingCartRepository;
import store.service.ShoppingCartService;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartDtoMapper shoppingCartDtoMapper;
    private final CartItemDtoMapper cartItemMapper;

    @Override
    public ShoppingCartResponseDto getById(Long id) {
        return fetchShoppingCartDto(id);
    }

    @Override
    public ShoppingCartResponseDto addToCart(AddToCartRequestDto requestDto, Long id) {
        CartItem cartItem = cartItemMapper.toModel(requestDto);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(id);
        cartItem.setShoppingCart(shoppingCart);

        String duplicatedExAnswer = String.format(
                "Cart item with book id: %s and shopping cart id: %s already exists",
                requestDto.getBookId(), id);
        if (cartItemRepository.isDuplicated(requestDto.getBookId(), id)) {
            throw new DuplicatedItemException(duplicatedExAnswer);
        }

        String bookNotFoundAnswer = String.format(
                "Book with id: %s doesn't found", requestDto.getBookId());
        Book book = bookRepository.findById(requestDto.getBookId()).orElseThrow(
                () -> new EntityNotFoundException(bookNotFoundAnswer));
        cartItem.getBook().setTitle(book.getTitle());

        cartItemRepository.save(cartItem);
        return fetchShoppingCartDto(id);
    }

    @Override
    public ShoppingCartResponseDto updateCartItem(UpdateCartItemRequestDto requestDto,
                                                  Long cartItemId,
                                                  Long id) {
        String exAnswer = String.format("Cart item with id: %s doesn't found", cartItemId);
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(
                () -> new EntityNotFoundException(exAnswer));
        cartItem.setQuantity(requestDto.getQuantity());
        cartItemRepository.save(cartItem);
        return fetchShoppingCartDto(id);
    }

    @Override
    public ShoppingCartResponseDto removeCartItem(Long cartItemId, Long id) {
        cartItemRepository.deleteById(cartItemId);
        return fetchShoppingCartDto(id);
    }

    private ShoppingCartResponseDto fetchShoppingCartDto(Long id) {
        return shoppingCartDtoMapper.toDto(shoppingCartRepository.getShoppingCartById(id));
    }
}
