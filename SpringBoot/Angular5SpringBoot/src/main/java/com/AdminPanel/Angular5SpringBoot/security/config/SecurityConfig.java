package com.AdminPanel.Angular5SpringBoot.security.config;

import com.AdminPanel.Angular5SpringBoot.repository.UserRepository;
import com.AdminPanel.Angular5SpringBoot.security.TokenAuthenticationEntryPoint;
import com.AdminPanel.Angular5SpringBoot.security.TokenAuthenticationFilter;
import com.AdminPanel.Angular5SpringBoot.security.TokenAuthenticationProvider;
import com.AdminPanel.Angular5SpringBoot.security.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(tokenAuthenticationProvider(auth.getDefaultUserDetailsService()));
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(userDetailsService);
//                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //    @Value("${application.tokenAuthentication.header}")
        String header = "X-Auth-Token";
        //    @Value("${application.tokenAuthentication.ignoreFault}")
        boolean ignoreFault = false;
        http
                .addFilterBefore
                        (new TokenAuthenticationFilter(authenticationManager(), tokenAuthenticationEntryPoint(),
                                header, ignoreFault), BasicAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers("/product/sAll",
                        "/auth/login",
                        "/auth/registration").hasRole("ANONYMOUS")
                .antMatchers("/customer/with/{userName}",
                        "/customer/{id}",
                        "/invoice/customer/{id}",
                        "/invoice/product/{id}",
                        "/auth/forgotPassword").hasRole("USER")
                .antMatchers("/customer/{username}",
                        "/invoice",
                        "/product/**",
                        "/auth/forgotPassword").hasRole("ADMIN")
                .antMatchers("/customer/**",
                        "/invoice/**",
                        "/product/**",
                        "/auth/forgotPassword").hasRole("SAD").
                and()
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable();


        http.headers().frameOptions().disable();
    }

    @Bean
    public AuthenticationEntryPoint tokenAuthenticationEntryPoint() {
        return new TokenAuthenticationEntryPoint();
    }

    @Bean
    public AuthenticationProvider tokenAuthenticationProvider(UserDetailsService userDetailsService) {
        return new TokenAuthenticationProvider(userDetailsService);
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository);
    }
}