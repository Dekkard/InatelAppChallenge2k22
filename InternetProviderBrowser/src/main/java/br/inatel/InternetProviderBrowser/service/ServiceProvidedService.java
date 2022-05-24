package br.inatel.InternetProviderBrowser.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.InternetProviderBrowser.model.ServiceProvided;

@Service
@Transactional
public class ServiceProvidedService implements ServiceModel<ServiceProvided, Long> {
	@PersistenceContext
	EntityManager em;

	@Override
	public List<ServiceProvided> list() {
		return em.createQuery("SELECT sp FROM ServiceProvided sp", ServiceProvided.class).getResultList();
	}

	@Override
	public ServiceProvided find(Long id) {
		return em.find(ServiceProvided.class, id);
	}

	@Override
	public ServiceProvided insert(ServiceProvided serviceProvided) {
		return em.merge(serviceProvided);
	}

	@Override
	public ServiceProvided update(Long id, ServiceProvided serviceProvided) {
		ServiceProvided sp = em.find(ServiceProvided.class, id);
		sp.setName(serviceProvided.getName());
		sp.setDescription(serviceProvided.getDescription());
		sp.setPrice(sp.getPrice());
		return em.merge(sp);
	}

	@Override
	public void delete(Long id) {
		ServiceProvided sp = em.find(ServiceProvided.class, id);
		em.remove(sp);
	}

}
