package mx.edu.utez.scimec.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Document
@Data
public class User implements UserDetails {

    @Id
    private String id;
    private String username;
    private String password;
    private String role;
    private Boolean enabled;


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole() != null ? Collections.singletonList(new SimpleGrantedAuthority(getRole())) : Collections.emptyList();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

   
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }
}
