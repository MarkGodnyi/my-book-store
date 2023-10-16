package store.service;

import store.dto.request.UserRegistrationRequestDto;
import store.dto.response.UserRegistrationResponseDto;
import store.exception.RegistrationException;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException;
}
