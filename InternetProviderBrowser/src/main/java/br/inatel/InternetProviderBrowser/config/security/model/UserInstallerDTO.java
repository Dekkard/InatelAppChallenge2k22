package br.inatel.InternetProviderBrowser.config.security.model;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.inatel.InternetProviderBrowser.model.Installer;

@SuppressWarnings("unused")
public class UserInstallerDTO {
	private String name;
	private String email;
	private String password;
	private String priceKm;
	private String lat;
	private String lng;

	public UserInstallerDTO() {
	}

	public UserInstallerDTO(String name, String email, String password, String priceKm, String lat, String lng) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.priceKm = priceKm;
		this.lat = lat;
		this.lng = lng;
	}
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getPriceKm() {
		return priceKm;
	}

	public String getLat() {
		return lat;
	}

	public String getLng() {
		return lng;
	}

	public Installer getInstallerInfo() {
		return new Installer(name, 1, new BigDecimal(priceKm), new BigDecimal(lat), new BigDecimal(lng));
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
