package br.inatel.InternetProviderBrowser.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.inatel.InternetProviderBrowser.config.security.model.User;

@Service
public class ClientAuthenticationService implements UserDetailsService{

	@Autowired
	UserService clientService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOpt = clientService.findByEmail(username);
		if(userOpt.isPresent())
			return userOpt.get();
		else
			return null;
	}

}
