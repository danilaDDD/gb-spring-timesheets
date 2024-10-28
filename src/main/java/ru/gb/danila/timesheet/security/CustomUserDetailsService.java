package ru.gb.danila.timesheet.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.gb.danila.timesheet.model.Role;
import ru.gb.danila.timesheet.model.User;
import ru.gb.danila.timesheet.repository.UserRepository;
import ru.gb.danila.timesheet.repository.UserRoleRepository;
import ru.gb.danila.timesheet.service.RoleService;
import ru.gb.danila.timesheet.service.UserService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("not user"));

        List<GrantedAuthority> roleNames = roleService.findRolesByUserId(user.getId()).stream()
                .map(role -> (GrantedAuthority)new SimpleGrantedAuthority(role.getName()))
                .toList();

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roleNames);
    }
}
