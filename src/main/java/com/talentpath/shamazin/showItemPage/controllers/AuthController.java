package com.talentpath.shamazin.showItemPage.controllers;

import com.talentpath.shamazin.showItemPage.daos.RoleRepository;
import com.talentpath.shamazin.showItemPage.daos.UserRepository;
import com.talentpath.shamazin.showItemPage.models.Role;
import com.talentpath.shamazin.showItemPage.models.User;
import com.talentpath.shamazin.showItemPage.security.*;
import io.jsonwebtoken.SignatureAlgorithm;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    PasswordEncoder encoder;

    @Value("${shamazin.app.jwtexpirationms}")
    private Integer jwtExpirationMs;

    @Value("${shamazin.app.jwtsecret}")
    private String jwtSecret;

    @PostMapping("/signin")
    //question mark means that returned ResponseEntity could return any type
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request){
        //authenticate user and generate jwt token
        //generate response object
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetailImpl details = (UserDetailImpl) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = io.jsonwebtoken.Jwts.builder()
            .setSubject(request.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
        return ResponseEntity.ok(
                new JwtResponse(token, details.getId(), details.getUsername(), details.getEmail(),
                        details.getAuthorities().stream().map(
                                auth -> auth.getAuthority())
                                .collect(Collectors.toList())
                        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?>registerUser(@Valid @RequestBody RegisterRequest request){
        if(userRepo.existsByUsername(request.getUsername())){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Username is already taken."));
        }
        if(userRepo.existsByEmail(request.getEmail())){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("ERror: Email is already in use."));
        }
        //encoder.encode -- gives us a hash value, no way to decrypt (for our encoder)
        User toAdd = new User(request.getUsername(), request.getEmail(), encoder.encode(request.getPassword()));
        //getting to the empty set
        toAdd.getRoles().add(roleRepo.findByRole(Role.RoleName.ROLE_USER).get());
        toAdd = userRepo.saveAndFlush(toAdd);
        //responseEntity is a wrapper, what's inside is important(here Obj contains a string)
        return ResponseEntity.ok(new MessageResponse("User has registered successfully!!"));
    }


    @GetMapping("test")
    public String testSec(){
        return "Can reach auth controller. should be able to";
    }

}
