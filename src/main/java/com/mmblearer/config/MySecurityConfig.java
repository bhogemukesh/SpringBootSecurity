package com.mmblearer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @author Mukesh Bhoge
 **/
@Configuration
@EnableWebSecurity
///@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //### Case 1: Basic Authentication with Username : user and password: Generated at runtime.
        /*
        http.authorizeRequests()
                .anyRequest()
                        .authenticated()
                                .and()
                                        .httpBasic();
         */
        /* //### Case 2 : Basic Authentication with in memory username password
        // For this we need to follow below steps
        // 1) configure(AuthenticationManagerBuilder auth) method
        // 2) Set inMemoryAuthentication username , password and role mandatory
        // 3) Decide if you need encoding or not
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
         */
        //Case 3 : Basic Authentication with roles authentication for separate endpoint
        // Here Particular endpoint will be accessible to particular role only
        /*http.authorizeRequests()
                .antMatchers("/public/**").hasRole("NORMAL")
                .antMatchers("/users/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic(); */

        //Case 4 : Disable CSRF to access other POST , PUT and PATCH method
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/public/**").hasRole("NORMAL")
                .antMatchers("/users/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        //Case 5 : Cookie based CSRF Token CookieCsrfTokenRepository
        //This case token will be get added to cookie and will be available in response Header.
        //Then u can use that X-XSRF-TOKEN further. But generally we disable csrf for non browser client
        /*
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeRequests()
                .antMatchers("/public/**").hasRole("NORMAL")
                .antMatchers("/users/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        */
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("mukesh").password(this.passwordEncoder().encode("password")).roles("NORMAL")
                .and().withUser("admin").password(this.passwordEncoder().encode("admin_password")).roles("ADMIN");
    }

//    No password encoding
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return  NoOpPasswordEncoder.getInstance();
//    }

    /**
     * Password Encoding
     * */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
