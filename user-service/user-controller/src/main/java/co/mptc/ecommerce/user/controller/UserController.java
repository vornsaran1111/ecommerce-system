package co.mptc.ecommerce.user.controller;

import co.mptc.ecommerce.user.dto.UserCreateRequest;
import co.mptc.ecommerce.user.dto.UserResponse;
import co.mptc.ecommerce.user.dto.UserUpdateRequest;
import co.mptc.ecommerce.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponse create(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        return userService.create(userCreateRequest);
    }

    @PatchMapping("/{id}")
    public UserResponse update(@PathVariable @RequestBody String id, UserUpdateRequest userUpdateRequest) {
        return userService.update(id, userUpdateRequest);
    }


}
