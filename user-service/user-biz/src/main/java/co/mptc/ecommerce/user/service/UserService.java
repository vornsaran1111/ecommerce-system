package co.mptc.ecommerce.user.service;

import co.mptc.ecommerce.customer.restapi.dto.PageResponse;
import co.mptc.ecommerce.user.dto.UserCreateRequest;
import co.mptc.ecommerce.user.dto.UserResponse;
import co.mptc.ecommerce.user.dto.UserUpdateRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponse create(UserCreateRequest userCreateRequest);

    UserResponse update(String id , UserUpdateRequest userUpdateRequest);

    UserResponse getById(String id);

    void deleteById(String id);

    UserResponse getByUsername(String username);

    PageResponse<UserResponse> getUsersByRoleName(String roleName, Pageable pageable);

    PageResponse<UserResponse> getUsers(PageRequest of);
}
