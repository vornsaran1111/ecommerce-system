package co.mptc.ecommerce.user.service;

import co.mptc.ecommerce.user.dto.UserCreateRequest;
import co.mptc.ecommerce.user.dto.UserResponse;

public interface UserService {

    UserResponse create(UserCreateRequest userCreateRequest);
}
