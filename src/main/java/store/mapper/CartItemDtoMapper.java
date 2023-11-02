package store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import store.config.MapperConfig;
import store.dto.request.AddToCartRequestDto;
import store.dto.response.CartItemResponseDto;
import store.model.CartItem;

@Mapper(config = MapperConfig.class, uses = BookDtoMapper.class)
public interface CartItemDtoMapper {
    @Mapping(source = "bookId", target = "book", qualifiedByName = "bookFromId")
    CartItem toModel(AddToCartRequestDto cartItemRequestDto);

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    CartItemResponseDto toDto(CartItem cartItem);
}
