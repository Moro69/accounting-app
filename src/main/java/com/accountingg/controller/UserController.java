package com.accountingg.controller;

import com.accountingg.entity.User;
import com.accountingg.model.CreateUserRequest;
import com.accountingg.model.UserDto;
import com.accountingg.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/public/users")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto add(@RequestBody @Valid CreateUserRequest request) {
        return userService.create(request);
    }

    @PostMapping("/login")
    public UserDto login(@ApiIgnore @AuthenticationPrincipal User user) {
        return userService.getAuthenticated(user);
    }
}
