package com.talentpath.shamazin.showItemPage.security;

import com.talentpath.shamazin.showItemPage.daos.UserRepository;
import com.talentpath.shamazin.showItemPage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//to link between User model and UserDetailImpl
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User matching = userRepo
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user with name: " + username));
        return new UserDetailImpl(matching);
    }
}

