package com.suporte.atendimento.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private boolean enabled;
    private boolean isAdmin;

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();

        // Adicionar a role de usuário
        roles.add("ROLE_USER");

        // Verificar se o usuário tem a role de administrador
        if (isAdmin) {
            roles.add("ROLE_ADMIN");
        }

        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Adicionar a role de usuário
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // Verificar se o usuário tem a role de administrador
        if (isAdmin) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
