package com.suporte.atendimento.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/users/getAll").permitAll()
                .antMatchers("/users/getUser/**").permitAll()
                .antMatchers("/users/create").permitAll()
                .antMatchers("/users/update/**").hasRole("USER")
                .antMatchers("/users").hasRole("ADMIN")
                .antMatchers("/agente-atendimento/**").permitAll()  // Adicionei essa linha para permitir acesso aos endpoints do AgenteAtendimentoController
                .antMatchers("/atendimento/**").permitAll()  // Adicionei essa linha para permitir acesso aos endpoints do AtendimentoController
                .antMatchers("/status-atendimento/**").permitAll()  // Adicionei essa linha para permitir acesso aos endpoints do StatusAtendimentoController
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
