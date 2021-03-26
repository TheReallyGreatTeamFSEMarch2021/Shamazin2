package com.talentpath.shamazin.showItemPage.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//@Configuration //tells Spring that this is to be used once for config
//going to make one instance of this and use it
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    //sets up the filter that will decode incoming jwts into
        //the basic username/password authentication tokens
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    JwtAuthEntryPoint entryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        super.configure(auth);
    }

    //jwt - json web token
    //entry point, they login, we hand them web login
    //client (frontend) will store token in frontend
    //every time request, they hand us back same web login
    //every time close window/app, will be logged out
        //upside is not have to mess with sending tokens back out

    //can rearrange order after .and() -> resetting initial object after that

    //SessionCreationPolicy.STATELESS
    //ideally with REST, no sessions, don't want to try maintain any state on Server side
        //want to get all info that we need to process each request every time
        //won't kick us out of any old sessions but won't create any new ones
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling()
                    .authenticationEntryPoint(entryPoint)
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()

                        //TODO: limit this to GET
                        .antMatchers("/").permitAll()
                        .antMatchers("/api/auth/**").permitAll() //want all to see login and register pages
                        .antMatchers("/api/userdata/**").hasRole("ADMIN") //spring sec has prefix ROLE_ by default, should be part of role name in general in Enum
                        .antMatchers(HttpMethod.GET, "/api/productPhotosForItem/**", "/api/productPhotos/**").permitAll()
                        .anyRequest().authenticated(); //all others require authenticated

    }
}

//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable()
//                .authorizeRequests().antMatchers("/").permitAll();
//    }
//}
