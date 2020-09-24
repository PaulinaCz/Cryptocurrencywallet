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

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private CustomLoginSuccessHandler successHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
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
        auth.authenticationProvider(authenticationProvider());
    }

    /*
     * TODO: Check User role and User Permission from Spring Security
     *  Create valid enum for ApplicationUserRole and ApplicationUserPermission
     *  Combine these enums with Spring Security -->>> AmigosCode project
     * */

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests ().antMatchers (
                "/registration**","/js/**","/css/**","/img/**")
                .permitAll ()
                .antMatchers("/user/**").hasAnyAuthority("ROLE_USER")
                .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest ().authenticated ()
                .and ()
                // form login
                .formLogin ()
                .loginPage ("/login")
//                .loginProcessingUrl("/login")
                .successHandler(successHandler)
//                .successHandler(myAuthenticationSuccessHandler())
                .permitAll ()
                .and ()
                // form logout
                .logout ()
                .invalidateHttpSession (true)
                .clearAuthentication (true)
                .logoutRequestMatcher (new AntPathRequestMatcher ("/logout"))
                .logoutSuccessUrl ("/login?logout")
                .permitAll ();
    }



/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers(
                "/registration**", // << -- provide access to different URL
                "/js/**",
                "/css/**",
                "/img/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login") // << -- Access to custom login page
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout") // << -- Access to custom logout page
                .permitAll();
    }*/
}
