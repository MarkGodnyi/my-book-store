package store.service;

import store.dto.request.UserLoginRequestDto;
import store.dto.response.UserLoginResponseDto;

public interface AuthenticationService {
    UserLoginResponseDto authenticate(UserLoginRequestDto requestDto);
}
