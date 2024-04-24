package com.example.project4.configuration;


import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(auth ->{

            try {
                httpSecurity.csrf().disable();  //cross site request forgery attack!!!
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            auth.requestMatchers(HttpMethod.POST,"/api/v1/product")  //modifica starea BD nu putem decat daca dezactivam csrf
                    .permitAll()

                    .requestMatchers(HttpMethod.GET,"/api/v1/product")
                    .hasAuthority("manager")  //permite daca are rolul de manager
                    .anyRequest().authenticated();                             // aici te lasa fara autentificare

            //auth.anyRequest().authenticated();  //orice trece, trebuie sa fie autentificat in spatele programului

        }).httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }


    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder(); //ne returneaza un obiect ce ne codeaza parolele pe baza unui algoritm
    }


    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject((AuthenticationManagerBuilder.class));
                authenticationManagerBuilder.inMemoryAuthentication()
                        .withUser("user1")
                        .password(passwordEncoder().encode("1234"))
                        .authorities("manager"); //asa merge, sare peste "manager"
                //la proiect se va face cu UserDetailsService ce e un fel de repository, va folosi un Authentification Provider
    return authenticationManagerBuilder.build();
    }
}
