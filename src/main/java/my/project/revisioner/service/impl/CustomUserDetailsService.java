package my.project.revisioner.service.impl;

import lombok.RequiredArgsConstructor;
import my.project.revisioner.dao.UserDao;
import my.project.revisioner.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author s.melekhin
 * @since 29 март 2023 г.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userDao.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username = %s not found", username)));
        return build(user);
    }

    public User loadUserById(Long id) {
        return userDao.findUserById(id).orElse(null);
    }

    public static User build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
        return new User(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
    }

}
