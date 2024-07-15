package org.jitzerttok51.social.run.config;

import org.passay.LengthRule;
import org.passay.MessageResolver;
import org.passay.PasswordValidator;
import org.passay.WhitespaceRule;
import org.passay.spring.SpringMessageResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // TODO: Add javadoc
    @Bean
    public PasswordValidator passwordValidator(MessageSource messageSource) {
        return new PasswordValidator(new SpringMessageResolver(messageSource), List.of(
                new LengthRule(8, 30),
                new WhitespaceRule()
        ));
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x -> x
                        .requestMatchers(HttpMethod.POST, "/api/auth", "/api/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/storage/**").permitAll() // Images serving endpoint
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers(HttpMethod.GET,"/**").permitAll() // UI serving endpoint
                        .requestMatchers("/error").permitAll() // UI serving endpoint
                        .anyRequest().authenticated())
//                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
//                .httpBasic(AbstractHttpConfigurer::disable)
                .anonymous(AbstractHttpConfigurer::disable)
                .build();
    }
}
