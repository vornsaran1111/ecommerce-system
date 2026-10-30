package co.mptc.ecommerce.user.service;

import co.mptc.ecommerce.user.dto.UserCreateRequest;
import co.mptc.ecommerce.user.dto.UserResponse;
import co.mptc.ecommerce.user.dto.UserUpdateRequest;

public interface UserService {

    UserResponse create(UserCreateRequest userCreateRequest);

    UserResponse update(String id , UserUpdateRequest userUpdateRequest);
}
