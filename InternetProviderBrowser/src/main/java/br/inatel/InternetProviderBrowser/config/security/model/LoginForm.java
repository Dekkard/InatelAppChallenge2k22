package br.inatel.InternetProviderBrowser.config.security.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
	private String email;
	private String password;

	public LoginForm(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
}
