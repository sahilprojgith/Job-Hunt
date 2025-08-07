package com.sahil.spring_boot_rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Autowired
    public UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));

//        provider.setUserDetailsPasswordService();
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

//        Customizer<CsrfConfigurer<HttpSecurity>> custCsrf = new Customizer<CsrfConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
//                httpSecurityCsrfConfigurer.disable();
//            }
//        };
//        httpSecurity.csrf(custCsrf);
//
//        Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> custHttp = new Customizer<AuthorizeHttpRequestsConfigurer<org.springframework.security.config.annotation.web.builders.HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>() {
//            @Override
//            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizationManagerRequestMatcherRegistry) {
//                authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
//
//            }
//        };
//
//        httpSecurity.authorizeHttpRequests(custHttp);

        httpSecurity.csrf(customizer -> customizer.disable());
        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());
//        httpSecurity.formLogin(Customizer.withDefaults()); // to make it stateless you don't need a form
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return httpSecurity.build();
    }




//    @Bean
//    public UserDetailsService userDetailService(PasswordEncoder passwordEncoder){
//
////        UserDetails user = User.withDefaultPasswordEncoder().username("amey").roles("HR").build(); //deprecated
//        UserDetails user = User.builder()
//                .username("sahil")
//                .password(passwordEncoder.encode("your_password_here"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin123"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager();
//    }


}
