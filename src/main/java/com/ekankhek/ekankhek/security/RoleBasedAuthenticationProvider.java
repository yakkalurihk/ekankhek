package com.ekankhek.ekankhek.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.ekankhek.ekankhek.domain.User;
import com.ekankhek.ekankhek.service.UserService;

@Component
public class RoleBasedAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			String username = authentication.getName();
	        String password = (String) authentication.getCredentials();   
		    User user = userService.findByUsername(username);
		    
	        if (user == null) {
	            throw new BadCredentialsException("Username not found.");
	        }
	 
	        if (!password.equals(user.getPassword())) {
	            throw new BadCredentialsException("Wrong password.");
	        }
	 
	        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
	 
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	throw new
            BadCredentialsException(" Authentication Failed ");
	    }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}


}
