package co.mptc.ecommerce.user.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakAdminClientConfig {

    @Bean
    public Keycloak keycloak(){
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8888")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId("ecommerce-admin-client")
                .clientSecret("YDW586cMkw4JZTepo5f0kfJdjvZphvyKui2rQXSkRU1kWYfFLNQBmlHxqOxUBNCMKDygx112PFkd9erpn3Z6Il")
                .realm("ecommerce")
                .build();
    }

}
