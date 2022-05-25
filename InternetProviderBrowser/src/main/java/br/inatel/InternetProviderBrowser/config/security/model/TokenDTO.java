package br.inatel.InternetProviderBrowser.config.security.model;

public class TokenDTO {
	private String token;
	private String tipo;

	public TokenDTO(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}
	@Override
	public String toString() {
		return tipo+" "+token;
	}
}
