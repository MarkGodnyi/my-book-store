package store.mapper;

import org.mapstruct.Mapper;
import store.config.MapperConfig;
import store.dto.response.ShoppingCartResponseDto;
import store.model.ShoppingCart;

@Mapper(config = MapperConfig.class, uses = CartItemDtoMapper.class)
public interface ShoppingCartDtoMapper {
    ShoppingCartResponseDto toDto(ShoppingCart shoppingCart);
}
