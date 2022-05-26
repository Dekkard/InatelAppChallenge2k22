package br.inatel.InternetProviderBrowser.config.security.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import br.inatel.InternetProviderBrowser.model.Client;

@SuppressWarnings("unused")
public class UserClientDTO {
	private String name;
	private String email;
	private Long cpf;
	private String birthDate;
	private String password;
	
	public UserClientDTO() {
	}

	public UserClientDTO(String name, String email, Long cpf, String birthDate, String password) {
		super();
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.password = password;
	}
	
	public Client getClientInfo() {
		LocalDate birthDate = LocalDate.parse(this.birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return new Client(name,cpf,birthDate);
	}
	
	public User getUserInfo() {
		return new User(genUsernameInitials(name),password,email);
	}
	
	private String genUsernameInitials(String name) {
		StringBuilder username = new StringBuilder();
		String[] nameSplit = name.toLowerCase().split(" ");
		username.append(nameSplit[0]);
		Arrays.stream(nameSplit).skip(1l).forEach(ns -> username.append(ns.substring(0, 1)));
		;
		return username.toString();
	}

	private String genUsernameTruncate(String name) {
		return name.toLowerCase().replace(" ", "").substring(0,20);
	}
}
