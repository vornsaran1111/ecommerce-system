package co.mptc.ecommerce.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

//        1.Endpoints(Secure, policy, permit)

        http.authorizeHttpRequests(endpoints -> endpoints
                .requestMatchers("/nacos/config").permitAll()
                .anyRequest().authenticated());
//2. stateless
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//3. disable csrf
        http.csrf(AbstractHttpConfigurer::disable);

//4. disable form login
        http.formLogin(AbstractHttpConfigurer::disable);
//5. security mechanisms
        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(Customizer.withDefaults()));

        return http.build();
    }
}
