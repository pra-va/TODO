package lt.pra_va.security.service;

import lt.pra_va.user.dao.UserRepository;
import lt.pra_va.user.dto.UserAuthenticationDTO;
import lt.pra_va.user.model.Authority;
import lt.pra_va.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User loadedUser = userRepository.getUserByUsername(username);
        List<Authority> authorities = new ArrayList<>();
        loadedUser.getAuthorities().stream().map(v -> authorities.add(new Authority(v)));

        return new UserAuthenticationDTO(loadedUser.getUsername(), loadedUser.getPassword(), authorities,
                loadedUser.isAccountNonExpired(), loadedUser.isAccountNonLocked(), loadedUser.isCredentialsNonExpired(),
                loadedUser.isEnabled());
    }

}