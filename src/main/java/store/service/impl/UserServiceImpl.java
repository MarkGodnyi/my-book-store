package store.service.impl;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import store.dto.request.UserRegistrationRequestDto;
import store.dto.response.UserRegistrationResponseDto;
import store.exception.EntityNotFoundException;
import store.exception.RegistrationException;
import store.mapper.UserRegistrationDtoMapper;
import store.model.Role;
import store.model.ShoppingCart;
import store.model.User;
import store.repository.RoleRepository;
import store.repository.ShoppingCartRepository;
import store.repository.UserRepository;
import store.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Role.RoleName USER = Role.RoleName.USER;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRegistrationDtoMapper userRegistrationDtoMapper;
    private final PasswordEncoder encoder;

    @Override
    public UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findUserByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Can't register user: " + requestDto);
        }
        User user = userRegistrationDtoMapper.toModel(requestDto);
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = roleRepository.getRoleByRoleName(USER)
                .orElseThrow(() ->
                        new EntityNotFoundException("There is no such role in db: " + USER));
        user.setRoles(Set.of(userRole));
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
        return userRegistrationDtoMapper.toDto(userRepository.save(user));
    }
}
