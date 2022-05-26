package br.inatel.InternetProviderBrowser.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.inatel.InternetProviderBrowser.config.security.model.User;

public class AuthenticatorTokenFilter extends OncePerRequestFilter {

	private TokenService ts;
	private UserService ur;
	public AuthenticatorTokenFilter(TokenService ts, UserService ur) {
		super();
		this.ts = ts;
		this.ur = ur;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		boolean valido = ts.isValid(token);
		if(valido) {
			authenticateClient(token);
		}
		filterChain.doFilter(request, response);
	}

	private void authenticateClient(String token) {
		Long idUser = ts.getUserId(token);
		User user = ur.find(idUser);
		UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authenticate);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer "))
			return null;
		else
			return token.substring(7);
	}

}
