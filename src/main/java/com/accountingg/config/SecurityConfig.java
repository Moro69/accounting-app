package com.accountingg.config;

import com.accountingg.service.DefaultUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String[] SWAGGER_API_PATTERNS = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/api/v2/api-docs/**",
            "/v2/api-docs/**"
    };

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder, DefaultUserDetailsService detailsService) throws Exception {
        auth.userDetailsService(detailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/public/**").permitAll()
                .antMatchers(SWAGGER_API_PATTERNS).permitAll()
                .anyRequest().authenticated()
                .and().cors().disable();
    }
}
