package com.dpiqb.notes.auth;

import com.dpiqb.notes.NoteCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@EnableWebSecurity
@Service
public class SecurityConfig{
  private final NamedParameterJdbcTemplate jdbcTemplate;
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .authorizeHttpRequests(
        auth -> auth
          .requestMatchers("/note/**").authenticated()
          .anyRequest().authenticated()
      )
      .authenticationProvider(authenticationProvider())
      .httpBasic(Customizer.withDefaults())
      .formLogin(Customizer.withDefaults());
    return http.build();
  }

  @Bean
  public AuthenticationProvider authenticationProvider(){
    final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    return authenticationProvider;
  }
  @Bean
  public PasswordEncoder passwordEncoder(){
    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  public UserDetailsService userDetailsService(){
    return new UserDetailsService() {
      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //https://youtu.be/b9O9NI-RJ3o?t=3962

        // TODO - Incorrect column count: expected 1, actual 3
        // TODO - to make normal repo
        String sql = "SELECT name, password, authorities FROM note_creator WHERE name = :name";
        NoteCreator noteCreator = jdbcTemplate
          .queryForObject(
            sql,
            Map.of("name", username),
            NoteCreator.class
          );
        System.out.println(noteCreator);
        return null;
      }
    };
  }
}
