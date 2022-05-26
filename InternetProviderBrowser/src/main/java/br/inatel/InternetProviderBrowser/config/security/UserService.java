package br.inatel.InternetProviderBrowser.config.security;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.InternetProviderBrowser.config.security.model.User;

@Service
@Transactional
public class UserService {
	@PersistenceContext
	EntityManager em;

	public Optional<User> findByEmail(String email) {
		return em.createQuery("SELECT U FROM User U", User.class)//
				.getResultStream()//
				.filter(u -> u.getEmail().equals(email))//
				.findFirst(); 
	}

	public User find(Long idUser) {
		return em.find(User.class, idUser);
	}
	
	public User insert(User user) {
		return em.merge(user);
	}

}
