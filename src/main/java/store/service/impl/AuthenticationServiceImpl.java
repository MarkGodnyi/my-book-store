package store.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import store.dto.request.UserLoginRequestDto;
import store.dto.response.UserLoginResponseDto;
import store.security.JwtUtil;
import store.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public UserLoginResponseDto authenticate(UserLoginRequestDto requestDto) {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDto.email(),
                            requestDto.password()));
            return new UserLoginResponseDto(jwtUtil.generateToken(authentication.getName()));
        } catch (Exception e) {
            throw new RuntimeException("Can't authenticate user", e);
        }
    }
}
