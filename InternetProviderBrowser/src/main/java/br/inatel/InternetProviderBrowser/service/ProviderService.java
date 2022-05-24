package br.inatel.InternetProviderBrowser.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.InternetProviderBrowser.model.Provider;

@Service
@Transactional
public class ProviderService implements ServiceModel<Provider, Long> {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Provider> list() {
		return em.createQuery("SELECT p FROM Provider p", Provider.class).getResultList();
	}

	@Override
	public Provider find(Long id) {
		return em.find(Provider.class, id);
	}

	@Override
	public Provider insert(Provider provider) {
		return em.merge(provider);
	}

	@Override
	public Provider update(Long id, Provider provider) {
		Provider p = em.find(Provider.class, id);
		p.setName(provider.getName());
		p.setDescription(provider.getDescription());
		p.setCnpj(provider.getCnpj());
		return em.merge(p);
	}

	@Override
	public void delete(Long id) {
		Provider p = em.find(Provider.class, id);
		em.remove(p);
	}

}
