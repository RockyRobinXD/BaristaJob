package rockyrobin.be.baristajob.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//    @Bean
//    public UserDetailsService users(PasswordEncoder encoder) {
//        UserDetails user = User.builder()
//                .username("nameUser")
//                .password(encoder.encode("12345678"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("nameAdmin")
//                .password(encoder.encode("admin1234"))
//                .roles("USER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return  PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
    @Bean
    public BCryptPasswordEncoder passwordBCryptEncoder() {
        return new BCryptPasswordEncoder();
    }
//
//    @Bean
//    public JdbcUserDetailsManager userDetailsService(DataSource dataSource) {
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//        return manager;
//    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login**", "/css/**", "/home**", "/images/favicon.svg", "/error**" ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}
