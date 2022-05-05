package com.accountingg.service;

import com.accountingg.entity.User;
import com.accountingg.mapper.UserMapper;
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
    private final UserMapper userMapper;

    public UserDto create(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        var user = userMapper.toEntity(request, passwordEncoder);
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public UserDto getAuthenticated(User user) {
        return userRepository.findByEmail(user.getEmail())
                .map(this.userMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
