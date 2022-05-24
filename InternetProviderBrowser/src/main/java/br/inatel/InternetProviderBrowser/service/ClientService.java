package br.inatel.InternetProviderBrowser.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.InternetProviderBrowser.model.Client;

@Service
@Transactional
public class ClientService implements ServiceModel<Client, Long> {
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Client> list() {
		return em.createQuery("SELECT c FROM Cliente c", Client.class).getResultList();
	}

	@Override
	public Client find(Long id) {
		return em.find(Client.class, id);
	}

	@Override
	public Client insert(Client client) {
		return em.merge(client);
	}

	@Override
	public Client update(Long id, Client client) {
		Client c = em.find(Client.class, client.getId());
		c.setName(client.getName());
		c.setCpf(client.getCpf());
		c.setBirthDate(client.getBirthDate());
		return em.merge(c);
	}

	@Override
	public void delete(Long id) {
		Client c = em.find(Client.class, id);
		em.remove(c);
	}

}
