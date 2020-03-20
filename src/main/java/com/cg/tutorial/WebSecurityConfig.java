package com.cg.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

        @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    };



    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return  authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    protected void configure(HttpSecurity http) throws Exception {

        http//.authorizeRequests()//.antMatchers("/admin/*").hasRole("ADMIN")
                //.and()
                .authorizeRequests().antMatchers("/user/*").hasAnyRole("ADMIN", "USER")
                .and()
                .authorizeRequests().antMatchers("/","/home","/assets-admin/**","/upload/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/api/**").permitAll()
                //using for code
                .and()
                .authorizeRequests().antMatchers("/admin/**").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                //.antMatchers("/","/index","/home","/assets-admin/*","/upload/*").permitAll()
                .and().formLogin()
                .loginPage("/user/login")
                .usernameParameter("username").passwordParameter("password")
                .loginProcessingUrl("/user/postLogin").permitAll()
                .successHandler(customAuthenticationSuccessHandler)
                .failureUrl("/user/loginFailed").and().logout().logoutUrl("/user/doLogout")
                .logoutSuccessUrl("/user/logout").permitAll()
                .and().csrf().disable();
    }
}
