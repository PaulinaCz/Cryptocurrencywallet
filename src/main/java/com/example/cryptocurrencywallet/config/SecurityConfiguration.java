package com.example.cryptocurrencywallet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserDetailsService;
    private final CustomLoginSuccessHandler successHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public SecurityConfiguration(MyUserDetailsService myUserDetailsService, CustomLoginSuccessHandler successHandler) {
        this.myUserDetailsService = myUserDetailsService;
        this.successHandler = successHandler;
    }

    /**
     *  Spring's Security DaoAuthenticationProvider is a simple authentication provider that uses a
     *  Data Access Object (DAO) to retrieve user information from a relational database.
     *  It leverages a UserDetailsService (as a DAO) in order to lookup the username, password and GrantedAuthority
     * */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(myUserDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider())
        .inMemoryAuthentication().withUser("kowalski@wp.pl").password("asd123").roles("USER");
    }

    /*
     * TODO: Check User role and User Permission from Spring Security
     *  Create valid enum for ApplicationUserRole and ApplicationUserPermission
     *  Combine these enums with Spring Security -->>> AmigosCode project
     * */

    /*
     * TODO: Disable default validation by Spring. When all forms are empty in registration form,
     *  it should display custom error message from RegistrationValidator.class.
     *  For instance, if all of the fields are empty it should, display message:
     *  "All fields must be completed."
     * */

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests().antMatchers(
                "/registration**", "/js/**", "/css/**", "/img/**")
                .permitAll()
                .antMatchers("/user/**").hasAnyAuthority("ROLE_USER")
                .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                // form login
                .formLogin()
                .loginPage("/login")
                .successHandler(successHandler)
                .permitAll()
                .and()
                // form logout
                .logout()
                .invalidateHttpSession(true)
//                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }

}