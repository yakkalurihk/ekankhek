package com.ekankhek.ekankhek.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ekankhek.ekankhek.handler.CustomAccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	RoleBasedAuthenticationProvider roleBasedAuthenticationProvider;
	
	@Autowired
	CustomAccessDeniedHandler accessDeniedHandler;
	
	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
	
	@Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(roleBasedAuthenticationProvider);
	}
	
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
			http.headers().frameOptions().disable();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/js/**", "/vendor/**","/css/**","/images/**","/font/**").permitAll()
                .antMatchers("/login*").permitAll()
                .antMatchers("/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                
                .anyRequest().authenticated().and()
                .httpBasic().and()
                .formLogin()
					.loginPage("/login.html")
					.defaultSuccessUrl("/index.html")
					.failureUrl("/login.html?error")
					.permitAll()
				.and().logout().deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login.html").invalidateHttpSession(true).permitAll()
				.and()
         .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
         .and()
         .sessionManagement().sessionFixation().none();
	 }
}
