package obss.pokemon.model.user;

import obss.pokemon.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private final User user;


    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user != null && !CollectionUtils.isEmpty(user.getRoles())) {
            return user.getRoles().stream().map(t -> new SimpleGrantedAuthority(t.getName())).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getActive();
    }

    @Override
    public boolean isEnabled() {
        return user.getActive();
    }
}
