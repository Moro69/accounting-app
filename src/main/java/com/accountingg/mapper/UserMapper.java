package com.accountingg.mapper;

import com.accountingg.entity.User;
import com.accountingg.entity.UserRole;
import com.accountingg.model.CreateUserRequest;
import com.accountingg.model.UserDto;
import org.mapstruct.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    default User toEntity(CreateUserRequest request, PasswordEncoder encoder) {
        var user = new User();
        user.setEmail(request.getEmail());
        user.setRole(UserRole.ROLE_USER);
        user.setPassword(encoder.encode(request.getPassword()));

        return user;
    }
}
