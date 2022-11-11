package com.js.movies.config;

import com.js.movies.dao.interfaz.UsuarioRepository;
import com.js.movies.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LoginConfig {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        List<UserDetails> usuarios = new ArrayList<>();

        List<Usuario> usuariosDB = this.usuarioRepository.findAll();
        usuarios.add(User.withUsername("admin").password("{noop}jonat2810").roles("ADMIN", "USER").build());

        usuariosDB.stream().forEach(user ->
                usuarios.add(User.withUsername(user.getUsername()).password("{noop}" + user.getContrasena()).roles("USER").build())
        );

        return new InMemoryUserDetailsManager(usuarios);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web.ignoring().antMatchers("/home", "/register");
    }


    @Bean
    public SecurityFilterChain filtrar(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/login").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/login").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/genero").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/pelicula").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/delete/genero").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/plan").hasAnyRole("ADMIN")
                .and().csrf().disable();
        return http.build();
    }
}
