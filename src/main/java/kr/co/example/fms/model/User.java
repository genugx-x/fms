package kr.co.qsol.fishery.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Data
@Document(collection = "Users")
@TypeAlias("User")
public class User implements UserDetails {

    @Id
    private String id;

    @Indexed(unique = true, direction = IndexDirection.ASCENDING)
    private String username;
    private String password;
    private boolean enabled;
    private ROLE roles;
    private String fullName;
    private int deviceId;
    private String office;
    private String address;
    private int[] channels;

    public enum ROLE{ADMIN, USER};

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(this.roles.name()));
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
        return this.enabled;
    }
}
