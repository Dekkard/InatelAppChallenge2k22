package br.inatel.InternetProviderBrowser.config.security.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.inatel.InternetProviderBrowser.model.Client;

@SuppressWarnings("unused")
public class UserClientDTO {
	private String name;
	private Long cpf;
	private String birthDate;
	private String lat;
	private String lng;
	private String email;
	private String password;

	public UserClientDTO() {
	}

	public UserClientDTO(String name, Long cpf, String birthDate, String lat, String lng, String email,
			String password) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.lat = lat;
		this.lng = lng;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public Long getCpf() {
		return cpf;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public String getLat() {
		return lat;
	}

	public String getLng() {
		return lng;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Client getClientInfo() {
		LocalDate birthDate = LocalDate.parse(this.birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return new Client(name, cpf, birthDate, new BigDecimal(lat), new BigDecimal(lng));
	}

	public User getUserInfo() {
		return new User(genUsernameInitials(name), new BCryptPasswordEncoder().encode(password), email);
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
		return name.toLowerCase().replace(" ", "").substring(0, 20);
	}
}
