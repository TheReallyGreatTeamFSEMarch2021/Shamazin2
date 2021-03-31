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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration //tells Spring that this is to be used once for config
//going to make one instance of this and use it
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtAuthEntryPoint entryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    //sets up the filter that will decode incoming jwts into
        //the basic username/password authentication tokens
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthTokenFilter jwtFilter(){return new AuthTokenFilter();}

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
//                        .antMatchers("/").permitAll()
                        .antMatchers("/api/auth/**").permitAll() //want all to see login and register pages
                //Accessing, editing, deleting User data
                        .antMatchers("/api/userdata/**").hasRole("ADMIN") //spring sec has prefix ROLE_ by default, should be part of role name in general in Enum
                //ItemFamily
                        .antMatchers("/api/itemFamily/**").permitAll()
                //Item
                        .antMatchers("/api/item/**").permitAll()

                //ProductPhotos
                        .antMatchers(HttpMethod.GET, "/api/productPhotosForItem/**", "/api/productPhotos/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/productPhotos").authenticated()
                        .antMatchers(HttpMethod.PATCH, "/api/productPhotos").authenticated()
                        .antMatchers(HttpMethod.DELETE, "/api/productPhotos/**").authenticated()
                //Questions
                        .antMatchers(HttpMethod.GET, "/api/question/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/question/").authenticated()
                        .antMatchers(HttpMethod.PUT, "/api/question/").authenticated()
                        .antMatchers(HttpMethod.DELETE, "/api/question/**").authenticated()
                //Answers
                        .antMatchers(HttpMethod.GET, "/api/answer/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/answer/**").authenticated()
                        .antMatchers(HttpMethod.PUT, "/api/answer/**").authenticated()
                        .antMatchers(HttpMethod.DELETE, "/api/answer/**").authenticated()
                //Review
                        .antMatchers(HttpMethod.GET, "/api/review/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/review/**").authenticated()
                        .antMatchers(HttpMethod.PUT, "/api/review/**").authenticated()
                        .antMatchers(HttpMethod.DELETE, "/api/review/**").authenticated()
                //Related Items
                        .antMatchers(HttpMethod.GET, "/api/related/**").permitAll()
                .anyRequest().authenticated().and() //all others require authenticated
                //before run that kind of authentication filter, should take what we have and convert it
                //why we went process of building UserNamePasswordAuthenticationToken
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);



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
