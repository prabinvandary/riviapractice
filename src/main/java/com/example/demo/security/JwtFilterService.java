package com.example.demo.security;

import com.example.demo.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Service
public class JwtFilterService extends OncePerRequestFilter {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    JwtTokenUtil aiiUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        String userName = null;
        String jwtToken = null;

        if (requestTokenHeader != null) {
            jwtToken = requestTokenHeader.substring(7);
            if (requestTokenHeader.startsWith("Bearer")) {
                try {
                    userName = jwtTokenUtil.getUsernameFromToken(jwtToken);

                } catch (IllegalArgumentException exception) {
                    throw new JwtException("Invalid jwt");
                } catch (ExpiredJwtException exception) {
                    throw new JwtException("Jwt expired");
                }

            }
        } else if (requestTokenHeader.startsWith("ApiKey")) {
            try {
                userName = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Api Key Invalid");
            }
        } else {
            logger.warn("Provided Key doesnot begin with Bearer or ApiKey");
        }

        if (userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=this.userDetailsService.loadUserByUsername(userName);
            logger.warn("User got"+ userDetails);
            if (requestTokenHeader.startsWith("Bearer")){
                if (jwtTokenUtil.validateToken(jwtToken,userDetails)){
                    setAuthentication(userDetails,request);
                } else if (requestTokenHeader.startsWith("ApiKey")) {
                    if (jwtTokenUtil.validateToken(jwtToken,userDetails)){
                        setAuthentication(userDetails,request);
                    }
                }
            }
        }
        filterChain.doFilter(request,response);
    }

    public void setAuthentication(UserDetails userDetails,HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails
        ,null,userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    }
}
