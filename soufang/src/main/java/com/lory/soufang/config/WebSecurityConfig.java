package com.lory.soufang.config;

import com.lory.soufang.security.AuthProvider;
import com.lory.soufang.security.LoginAuthFailHandler;
import com.lory.soufang.security.LoginUrlEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public AuthProvider authenticationProvider(){
        return new AuthProvider();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/login").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN","USER")
                .antMatchers(("/api/user/**")).hasAnyRole("ADMIN","USER")
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .failureHandler(loginAuthFailHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/page")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(urlEntryPoint())
                .accessDeniedPage("/403");
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
    }


    @Bean
    public LoginUrlEntryPoint urlEntryPoint(){
        return new LoginUrlEntryPoint("/user/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider()).eraseCredentials(true);
    }

    @Bean
    public LoginAuthFailHandler loginAuthFailHandler(){
        return new LoginAuthFailHandler(urlEntryPoint());
    }
}
