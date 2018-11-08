package vn.edu.saigontech.SGTEnglishClub.Configurations;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;

public class AccessDeniedEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res,
			AuthenticationException authException) throws IOException, ServletException {
		res.setStatus(HttpStatus.BAD_REQUEST.value());
		res.setContentType("application/json");
		
		String json = new ObjectMapper().writeValueAsString(CustomResponseEntity.getAccessDeniedResponse());

		res.getWriter().write(json);
		res.flushBuffer();
	}

}