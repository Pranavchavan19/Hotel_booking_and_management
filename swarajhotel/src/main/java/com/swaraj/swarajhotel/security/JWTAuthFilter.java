package com.swaraj.swarajhotel.security;


import com.swaraj.swarajhotel.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthFilter {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private CachingUserDetailsService cachingUserDetailsService;
}
