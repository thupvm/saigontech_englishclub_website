package vn.edu.saigontech.SGTEnglishClub.Configurations;

import static java.util.Collections.emptyList;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import vn.edu.saigontech.SGTEnglishClub.DAOs.AdminDAO;
import vn.edu.saigontech.SGTEnglishClub.Responses.CustomResponseEntity;
import vn.edu.saigontech.SGTEnglishClub.Responses.LoginResponse;

public class TokenAuthenticationService {
	static final long EXPIRATIONTIME = 120_000;
	static final String SECRET = "Saigontech";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	public static void addAuthentication(HttpServletResponse res, String username) throws IOException {
		String JWT = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS256, SECRET).compact();
		res.setContentType("application/json");

		CustomResponseEntity cre = new CustomResponseEntity();

		cre.setErrorCode(0);
		cre.setMessage("Log in successfully");

		LoginResponse lR = new LoginResponse(username, JWT);
		cre.setData(lR);
		String json = new ObjectMapper().writeValueAsString(cre);

		res.getWriter().write(json);
		res.flushBuffer();

	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
					.getSubject();

			return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
		}
		return null;
	}

	public static void failAuthentication(HttpServletResponse res, String failException) throws IOException {
		res.setStatus(HttpStatus.BAD_REQUEST.value());
		res.setContentType("application/json");
		CustomResponseEntity cre = new CustomResponseEntity();
		cre.setErrorCode(1);
		cre.setMessage("Wrong username or password");
		cre.setData(null);
		String json = new ObjectMapper().writeValueAsString(cre);

		res.getWriter().write(json);
		res.flushBuffer();
	}
}