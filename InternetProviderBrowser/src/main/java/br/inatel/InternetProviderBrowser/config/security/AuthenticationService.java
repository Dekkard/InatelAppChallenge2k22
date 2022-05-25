package br.inatel.InternetProviderBrowser.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.inatel.InternetProviderBrowser.config.security.model.Usuario;

@Service
public class AuthenticationService implements UserDetailsService{

	@Autowired
	UserRepo ur;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> userOpt = ur.findByEmail(username);
		if(userOpt.isPresent())
			return userOpt.get();
		else
			return null;
	}

}
