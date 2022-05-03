package com.accountingg.service;

import com.accountingg.entity.User;
import com.accountingg.entity.UserRole;
import com.accountingg.model.CreateUserRequest;
import com.accountingg.model.UserDto;
import com.accountingg.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto create(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        var user = new User();
        user.setEmail(request.getEmail());
        user.setRole(UserRole.ROLE_USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        var dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setCreatedDate(user.getCreatedDate());

        return dto;
    }

    public UserDto getAuthenticated(User user) {
        return userRepository.findByEmail(user.getEmail())
                .map(u -> {
                    var dto = new UserDto();
                    dto.setId(u.getId());
                    dto.setEmail(u.getEmail());
                    dto.setCreatedDate(u.getCreatedDate());

                    return dto;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
