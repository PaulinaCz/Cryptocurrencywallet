package com.example.cryptocurrencywallet.config;

import com.example.cryptocurrencywallet.servic.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfiguration(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider ();
        authenticationProvider.setUserDetailsService (userService);
        authenticationProvider.setPasswordEncoder (passwordEncoder);
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider (authenticationProvider ());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests ().antMatchers (
                "/registration**",
                "/js/**",
                "/css/**",
                "/img/**")
                .permitAll ()
                .anyRequest ().authenticated ()
                .and ()
                .formLogin ()
                .loginPage ("/login")
                .permitAll ()
                .and ()
                .logout ()
                .invalidateHttpSession (true)
                .clearAuthentication (true)
                .logoutRequestMatcher (new AntPathRequestMatcher ("/logout"))
                .logoutSuccessUrl ("/login?logout")
                .permitAll ();
    }
}
