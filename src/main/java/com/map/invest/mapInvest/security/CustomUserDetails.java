package com.map.invest.mapInvest.security;

import com.map.invest.mapInvest.entity.Acesso;
import com.map.invest.mapInvest.util.constantes.TipoPerfilEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends Acesso implements UserDetails {
    private Collection<? extends GrantedAuthority> authorities;
    private String username;
    private String password;

    public CustomUserDetails(Acesso byUsername) {
        this.username = byUsername.getLogin();
        this.password = byUsername.getSenha();
        List<GrantedAuthority> auths = new ArrayList<>();

        if (byUsername.getPerfis() != null) {
            if (byUsername.getPerfis().getNomePerfil() == TipoPerfilEnum.ADMINISTRADOR)
                auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            auths.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        this.authorities = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }
}
