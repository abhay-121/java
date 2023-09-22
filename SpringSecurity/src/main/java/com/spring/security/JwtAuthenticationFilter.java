package com.spring.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.model.CustomUserDetail;
import com.spring.model.CustomUserDetailService;
import com.spring.model.JwtHelper;

import io.jsonwebtoken.ExpiredJwtException;

@Controller
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtHelper jwtTokenHelper;
	
	@Autowired 
	private CustomUserDetailService customUserDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			String requestTokenHeader = request.getHeader("Authorization");
			String username = null;
			String jwtToken = null;
			if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
				jwtToken = requestTokenHeader.substring(7);
				try {
					username = this.jwtTokenHelper.extractUsername(jwtToken);
				}catch(IllegalArgumentException e) {
					System.out.println("Unable to get jwt token");
				}catch(ExpiredJwtException e) {
					System.out.println("Token has Expired");
				}
				
			}else {
				logger.warn("JWT token is not starting with Bearer string");
			}
			
			//Once we get the token, now validate
			//Initially authentication should be null
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				CustomUserDetail userDetails = customUserDetailService.loadUserByUsername(username);
				if(jwtTokenHelper.validateToken(jwtToken, userDetails)){
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
							new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					//Set authentication
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
				else {
					System.out.println("Invalid jwt token");
				}
			}else {
				System.out.println("Username is null or context is not null");
			}
			filterChain.doFilter(request, response);
	}

}
