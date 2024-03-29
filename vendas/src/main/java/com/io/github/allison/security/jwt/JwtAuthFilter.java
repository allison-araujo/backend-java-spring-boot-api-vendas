package com.io.github.allison.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.io.github.allison.service.impl.UserServiceImpl;

public class JwtAuthFilter  extends OncePerRequestFilter{

    private JwtService jwtService;
    private UserServiceImpl userService;

    

    public JwtAuthFilter(JwtService jwtService, UserServiceImpl userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String authorization = request.getHeader("Authorization");

              if(authorization != null && authorization.startsWith("Bearer")){
                    String token = authorization.split(" ")[1];
                    boolean isValid = jwtService.tokenValid(token);

                    if(isValid){
                        String loginUser = jwtService.getLoginUser(token);
                        UserDetails user = userService.loadUserByUsername(loginUser);

                        UsernamePasswordAuthenticationToken userAuth = new 
                                UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                        userAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(userAuth);
                    }
              }
          filterChain.doFilter(request, response);    
    }
    
}
