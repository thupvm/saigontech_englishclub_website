package vn.edu.saigontech.SGTEnglishClub.Configurations;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;

public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		
		try {
			Authentication authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(request, response);
		} catch (ExpiredJwtException e) {
			res.setStatus(HttpStatus.BAD_REQUEST.value());
			res.setContentType("application/json");
			
			String json = new ObjectMapper().writeValueAsString(CustomResponseEntity.getExpiredTimeResponse());

			res.getWriter().write(json);
			res.flushBuffer();
			
		} catch (MalformedJwtException e) {
			res.setStatus(HttpStatus.BAD_REQUEST.value());
			res.setContentType("application/json");
			
			String json = new ObjectMapper().writeValueAsString(CustomResponseEntity.getHackerWarningResponse());

			res.getWriter().write(json);
			res.flushBuffer();
		}
		
	}
}