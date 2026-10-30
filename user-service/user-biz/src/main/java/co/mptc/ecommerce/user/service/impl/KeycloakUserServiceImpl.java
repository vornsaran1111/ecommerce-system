package co.mptc.ecommerce.user.service.impl;

import co.mptc.ecommerce.user.config.KeycloakAdminClientConfig;
import co.mptc.ecommerce.user.exception.KeycloakException;
import co.mptc.ecommerce.user.service.KeycloakUserService;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakUserServiceImpl implements KeycloakUserService {

    private final Keycloak keycloak;

    /// @param userRepresentation
    @Override
    public void createKeycloakUser(UserRepresentation userRepresentation) {
        try (Response response = keycloak.realm("ecommerce")
                .users()
                .create(userRepresentation)) {
            if (response.getStatus() != HttpStatus.CREATED.value()) {
                throw new KeycloakException(
                        HttpStatus.valueOf(response.getStatus()), "Keycloak User creation failed" + response.getStatus()
                );
            }
        }
    }

    /// @param username
    /// @return
    @Override
    public UserRepresentation getKeycloakUserByUsername(String username) {

        return keycloak.realm(KeycloakAdminClientConfig.REALM_NAME)
                .users()
                .search(username, true)
                .stream()
                .findFirst()
                .orElseThrow(() -> new KeycloakException(HttpStatus.NOT_FOUND, "User not found"));
    }

    /// @param id
    /// @param userRepresentation
    @Override
    public void updateKeycloakUser(String id, UserRepresentation userRepresentation) {

        try {
            keycloak.realm(KeycloakAdminClientConfig.REALM_NAME)
                    .users()
                    .get(id)
                    .update(userRepresentation);


        } catch (Exception e){
            log.error(e.getMessage(), e);
        }
    }
}
