package com.RasikGroup.assignment_3;

import com.RasikGroup.assignment_3.entity.service.UserService;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityBeans {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(
                    (authorize) -> authorize
                            .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
//                            .requestMatchers(HttpMethod.POST, "/user/signup").permitAll()
                            .requestMatchers(HttpMethod.POST, "/jobs/postjob").permitAll()
                            .requestMatchers("/admin").hasAuthority("ADMIN")
                            .requestMatchers("/jobs/**").permitAll()
                            .requestMatchers("/jobs/{id}").permitAll()
                            .requestMatchers("/categories/all").permitAll()
                            .requestMatchers("/role/**").permitAll()
                            .requestMatchers("/user/signup").permitAll()
                            .requestMatchers("/user/**").permitAll()
                            .requestMatchers("/jobs/applied/{id}").permitAll()
                            .requestMatchers("/jobs/applied/delete").permitAll()
                            .requestMatchers("/jobs/posted/update").permitAll()
                            .anyRequest().authenticated())
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractAuthenticationFilterConfigurer::permitAll);

        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }


}
