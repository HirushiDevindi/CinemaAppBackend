package com.example.CinemaAppBackend.Config;

import com.example.CinemaAppBackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import static org.springframework.security.config.Customizer.withDefaults;

//--------------------------
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableJpaRepositories(basePackages = "com.example.CinemaAppBackend.Repo")
@ComponentScan(basePackages = "com.example.CinemaAppBackend")
public class SecurityConfig {
    @Autowired
    //private UserService userService;

    private UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

//    public SecurityConfig(UserService userService) {
//        this.userService = userService;
//    }

//    private UserService Uds;
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(Uds);
//    }
    public SecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    //spring security
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
   }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
                //.csrf(csrf -> csrf.disable())
                .cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests((authorize) -> {
                    //authorize.anyRequest().authenticated()
                    //authorize.mvcMatchers(HttpMethod.GET,"/api/**").permitAll()
                    //authorize.antMatchers("/api/user/**").permitAll(); // any users can access
                    authorize.antMatchers("/api/user/home").hasAuthority("USER");
                    authorize.antMatchers("/api/user/logout").permitAll();
                    authorize.antMatchers("/api/user/signup").permitAll();
                    authorize.antMatchers("/api/user/signin").permitAll();
                    authorize.antMatchers( "/api/user/{id}").hasAuthority("USER");
                    authorize.antMatchers("/movie","/movie/{id}").hasAuthority("USER");
                    authorize.antMatchers("/show/**").hasAuthority("USER");
                    authorize.antMatchers("/seat/**").hasAuthority("USER");
                    authorize.antMatchers("/api/v1/kafka/**").permitAll();
                    //authorize.antMatchers("/movie","/movie/**").authenticated();
                    authorize.anyRequest().authenticated();
                })
                .logout()
                                        .logoutUrl("/logout")
                                        .logoutSuccessUrl("/api/user/home")
                                        .invalidateHttpSession(true)
                                        .deleteCookies("JSESSIONID")
                .and().httpBasic();


        return http.build();

    }


    //setting up user authentication and validation in a Spring Security-enabled application
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider
                = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return  provider;
    }


}
//---------------------------



