package br.inatel.InternetProviderBrowser.config.security;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.InternetProviderBrowser.config.security.model.Usuario;

@Service
@Transactional
public class UserRepo {
	@PersistenceContext
	EntityManager em;

	public Optional<Usuario> findByEmail(String email) {
		return em.createQuery("SELECT U FROM Usuario U", Usuario.class).getResultStream()
				.filter(u -> u.getEmail().equals(email))
				.findFirst();
	}

	public Usuario find(Long idUser) {
		return em.find(Usuario.class, idUser);
	}
}
