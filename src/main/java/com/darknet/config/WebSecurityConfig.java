package com.darknet.config;

import com.darknet.service.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .formLogin()
                .loginPage("/logIn").permitAll()
                .usernameParameter("email")
                .defaultSuccessUrl("/itemslist")
                .loginProcessingUrl("/logIn")
                .and()
                .logout()
                .logoutSuccessUrl("/logIn")
                .and()
                .authorizeRequests()
                .antMatchers("/regUser").permitAll()
                .antMatchers("/logIn").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/register").hasAuthority("ADMIN")
                .antMatchers("/admin/").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/itemslist").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/singleitem").hasAnyAuthority("USER", "ADMIN")
                .anyRequest().authenticated();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
