package com.vks.serverproductmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
//In case of  @PreAuthorize("hasRole('ADMIN')") annotation | ant matcher won't require
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().disable()//kept disable because API is used by non browser client. | otherwise post,put don't work
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())//in order to send cookie |setting http req false
                .and()
                .authorizeRequests()
                //to skip the authentication
                .antMatchers("/**/register", "/**/login").permitAll()
                //to access URLs on Role Basis
                .antMatchers("/**/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //basic inMemory authentication with custom user and password
        auth.inMemoryAuthentication().withUser("user").password(this.passwordEncoder()
                .encode("pass")).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(this.passwordEncoder()
                .encode("pass")).roles("ADMIN");

    }

    /*
    ROLE:High level overview: USER->Read
    AUTHORITY:Permissions->Read,Write,Update
    Eg: ADMIN(Role):->(Authority)Read,Write,Update
     */


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
