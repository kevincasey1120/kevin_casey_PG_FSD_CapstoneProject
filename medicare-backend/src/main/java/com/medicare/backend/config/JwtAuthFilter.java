package com.medicare.backend.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.medicare.backend.MedicareBackendApplication;
import com.medicare.backend.services.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * @author fsd developer:  kevin casey
 *
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	private static Logger LOG = LoggerFactory.getLogger(MedicareBackendApplication.class);
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtils jwtutils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestTokenHeader=request.getHeader("Authorization");
		
		System.out.println("requestTokenHeader:" + requestTokenHeader);
		
		String username=null;
		String jwtToken=null;
		
		if(requestTokenHeader !=null && requestTokenHeader.startsWith("Bearer ")) {
			
			jwtToken = requestTokenHeader.substring(7);
		    try {
		    	
			username=this.jwtutils.extractUsername(jwtToken);
			
		    }catch(ExpiredJwtException e) {
		    	e.printStackTrace();
		    	System.out.println("Jwt token has expired");
		    }catch(Exception e) {
		    	e.printStackTrace();
		    	System.out.println("Exception..");
		    }
		}else {
		 	LOG.info("<JWTAUTHFILTER -FAILED>  TOKEN IS NOT VALID - MISSING BEARER STRING");
		}
		
		

		//----------------------------------------------------------------------
		//> TOKEN VALIDATION PROCESS
		if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			final UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
			
			//-----------------------------------------------------------------
			if(this.jwtutils.validateToken(jwtToken, userDetails)) {
				//-----------------------------------------
				UsernamePasswordAuthenticationToken usernamePasswordAuth=
						new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				
				//-----------------------------------------
				usernamePasswordAuth.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuth);
				
			 	LOG.info("<JWTAUTHFILTER> --SUCCESS-- USER PASSWORD AUTHENTICATRED ");
			 	
			}else {
			 	LOG.info("<JWTAUTHFILTER -FAILED>  TOKEN IS NOT VALID!!!!!");
			}
			
		}else {
			if(username == null) {
			 	LOG.info("<JWTAUTHFILTER -FAILED>  TOKEN IS NOT VALID ('username' Was NULL)");
			}else {
			 	LOG.info("<JWTAUTHFILTER -FAILED>  TOKEN IS NOT VALID!!!!!");
			}
		}
		
		filterChain.doFilter(request, response);
	}
}
