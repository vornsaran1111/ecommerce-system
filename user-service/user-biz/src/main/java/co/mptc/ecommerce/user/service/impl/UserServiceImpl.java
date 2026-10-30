package co.mptc.ecommerce.user.service.impl;

import co.mptc.ecommerce.user.dto.UserCreateRequest;
import co.mptc.ecommerce.user.dto.UserResponse;
import co.mptc.ecommerce.user.dto.UserUpdateRequest;
import co.mptc.ecommerce.user.entity.UserEntity;
import co.mptc.ecommerce.user.exception.PasswordMismatchException;
import co.mptc.ecommerce.user.exception.UserNotFoundException;
import co.mptc.ecommerce.user.exception.UsernameConflictException;
import co.mptc.ecommerce.user.mapper.UserMapper;
import co.mptc.ecommerce.user.repository.UserRepository;
import co.mptc.ecommerce.user.service.KeycloakRoleService;
import co.mptc.ecommerce.user.service.KeycloakUserService;
import co.mptc.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Keycloak keycloak;
    private final KeycloakUserService  keycloakUserService;
    private final KeycloakRoleService keycloakRoleService;

    /// @param userCreateRequest
    /// @return
    @Override
    public UserResponse create(UserCreateRequest userCreateRequest) {

        UserEntity userEntity = userMapper.toEntity(userCreateRequest);

        // Validate username
        if (userRepository.existsByUsername(userCreateRequest.username())) {
            throw new UsernameConflictException(HttpStatus.CONFLICT,
                    "Username already exists!");
        }

        // Validate password
        if (!userCreateRequest.password().equals(userCreateRequest.confirmPassword())) {
            throw new PasswordMismatchException(HttpStatus.BAD_REQUEST,
                    "Password does not match");
        }

        //save user into keycloak
        //after
        // Save user into Keycloak
        // After saved user, save user into DB
        UserRepresentation userRepresentation = userMapper.toRepresentation(userCreateRequest);
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(userCreateRequest.password());

        userRepresentation.setCredentials(List.of(credentialRepresentation));


        keycloakUserService.createKeycloakUser(userRepresentation);
        UserRepresentation createdUser = keycloakUserService.getKeycloakUserByUsername(
                userEntity.getUsername()
        );
        log.info("User created: {}", createdUser);
        keycloakRoleService.assignRoles(createdUser.getId(), userCreateRequest.roleName());

        // Saving user into database
        userEntity.setId(createdUser.getId());
        userRepository.save(userEntity);

        return userMapper.toResponse(userEntity, createdUser);
    }

    /// @param userUpdateRequest
    /// @return
    @Override
    public UserResponse update(String id,UserUpdateRequest userUpdateRequest) {

        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(()-> new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found"));

        userMapper.toEntity(userUpdateRequest, userEntity);
        userRepository.save(userEntity);

        return userMapper.toResponse(userEntity);
    }
}
