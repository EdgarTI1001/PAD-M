package padm.io.pad_m.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import padm.io.pad_m.domain.Usuario;
import padm.io.pad_m.domain.enums.Perfil;



@SuppressWarnings("serial")
public class UserDetailsImpl implements UserDetails {
	
	private Usuario user;
    
    public UserDetailsImpl(Usuario user) {
        this.user = user;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // APLICA POR DEFAULT 'role user' A TODOS OS USUARIOS AUTENTICADOS
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        for (Perfil p : user.getPerfis()) {
            authorities.add(new SimpleGrantedAuthority(p.getDescricao()));
        }
        return authorities;
    }
 
    @Override
    public String getPassword() {
        return user.getSenha();
    }
    
    public Usuario getUsuario() {
    	return this.user;
    }
    
    @Override
    public String getUsername() {
        return user.getCpf();
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
        //return user.isEnabled();
    	return true;
    }
 
}