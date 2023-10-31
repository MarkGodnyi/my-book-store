package store.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import store.config.MapperConfig;
import store.dto.request.CreateCartItemRequestDto;
import store.dto.response.CartItemResponseDto;
import store.model.CartItem;
import store.model.ShoppingCart;
import store.model.User;

@Mapper(config = MapperConfig.class, uses = BookDtoMapper.class)
public interface CartItemDtoMapper {
    @Mapping(source = "bookId", target = "book", qualifiedByName = "bookFromId")
    CartItem toModel(CreateCartItemRequestDto cartItemRequestDto);

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    CartItemResponseDto toDto(CartItem cartItem);

    @AfterMapping
    default void setShoppingCart(@MappingTarget CartItem cartItem) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((User) authentication.getPrincipal()).getId();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(userId);
        cartItem.setShoppingCart(shoppingCart);
    }
}
