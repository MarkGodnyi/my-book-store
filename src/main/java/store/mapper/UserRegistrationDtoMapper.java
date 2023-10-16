package store.mapper;

import org.mapstruct.Mapper;
import store.config.MapperConfig;
import store.dto.request.UserRegistrationRequestDto;
import store.dto.response.UserRegistrationResponseDto;
import store.model.User;

@Mapper(config = MapperConfig.class)
public interface UserRegistrationDtoMapper {
    UserRegistrationResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto requestDto);
}
