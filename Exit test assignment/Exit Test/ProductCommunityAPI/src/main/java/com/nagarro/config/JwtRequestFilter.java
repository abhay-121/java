package com.nagarro.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nagarro.helper.JwtUtil;
import com.nagarro.model.CustomerUserDetails;
import com.nagarro.service.CustomUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtil jwtTokenHelper;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	//This method is use to check token is valid or invalid if valid then allow the access to the API's
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenHelper.extractUsername(jwtToken);
			}
			catch(IllegalArgumentException e) {
				System.out.println("Unable to get jwt Token");
			}
			catch(ExpiredJwtException e) {
				System.out.println("Jwt token has Expired");
			}
		}
		else {
			logger.warn("JWT token is not starting with Bearer string");
		}
		
		//Validate
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			CustomerUserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
			if(jwtTokenHelper.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.
					setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			
		}
	chain.doFilter(request, response);
	}

}
