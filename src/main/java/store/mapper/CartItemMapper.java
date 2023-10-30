package store.mapper;

import org.mapstruct.Mapper;
import store.config.MapperConfig;
import store.dto.request.CreateCartItemRequestDto;
import store.dto.response.CartItemResponseDto;
import store.model.CartItem;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    CartItem toModel(CreateCartItemRequestDto cartItemRequestDto);

    CartItemResponseDto toDto(CartItem cartItem);
}
