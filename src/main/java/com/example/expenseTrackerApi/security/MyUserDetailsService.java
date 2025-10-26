package com.example.expenseTrackerApi.security;

import com.example.expenseTrackerApi.model.UserPrincipal;
import com.example.expenseTrackerApi.model.entity.Users;
import com.example.expenseTrackerApi.repo.UserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepo repo;

    public MyUserDetailsService(UserRepo r) {
        this.repo = r;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var u = repo.
                findByEmail(email).
                orElseThrow(
                        () -> new UsernameNotFoundException(email));
        return User.withUsername(
                u.getEmail())
                .password(u.getPasswordHash())
                .roles("USER").build();
    }
}
