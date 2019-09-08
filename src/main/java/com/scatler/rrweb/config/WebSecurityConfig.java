package com.scatler.rrweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.savedrequest.RequestCache;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userService;
    @Autowired
    RequestCache myRequestCache;
    @Autowired
    AccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
*/

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/tickets/start", "/station/timeTable").hasAnyRole("ADMIN", "USER")
                .antMatchers("/station/add").hasRole("ADMIN")
                .antMatchers("/webjars/**", "/resources/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/loginAction").permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll()
                .and()
                .csrf().disable()
                .requestCache()
                .requestCache(myRequestCache)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler);


    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
            http.csrf().disable()
/*                    .authorizeRequests()
                    //.antMatchers("/**").not().hasAuthority("ROLE_CANDIDATE")
                    //.antMatchers("/**").hasRole("USER")
                    .antMatchers("/anonymous*").anonymous()
                    .antMatchers("/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage("/login").loginProcessingUrl("/loginPerform").permitAll();*/
                    .authorizeRequests()
                    //.antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/anonymous*").anonymous()
                    .antMatchers("/dev-release/auth.html").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/dev-release/auth.html")
                    .loginProcessingUrl("/perform_login")
                    .defaultSuccessUrl("/dev-release/index.html", true);
/*                    //.failureUrl("/login.html?error=true")
                    //.failureHandler(authenticationFailureHandler())
                    .and()
                    .logout()
                    .logoutUrl("/perform_logout")
                    .deleteCookies("JSESSIONID");
                    //.logoutSuccessHandler(logoutSuccessHandler());*/
    }

/*    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/app/**") //TODO check why?
                .antMatchers("/app/**"); //TODO check why?
    }*/
}
