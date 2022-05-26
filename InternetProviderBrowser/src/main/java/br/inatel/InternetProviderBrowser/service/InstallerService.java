package br.inatel.InternetProviderBrowser.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.InternetProviderBrowser.model.Installer;

@Service
@Transactional
public class InstallerService implements ServiceModel<Installer, Long> {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Installer> list() {
		return em.createQuery("SELECT i FROM Installer i", Installer.class).getResultList();
	}

	@Override
	public Installer find(Long id) {
		return em.find(Installer.class, id);
	}

	@Override
	public Installer insert(Installer installer) {
		return em.merge(installer);
	}

	@Override
	public Installer update(Long id, Installer installer) {
		Installer p = em.find(Installer.class, id);
		p = new Installer(installer);
		return em.merge(p);
	}

	@Override
	public void delete(Long id) {
		Installer p = em.find(Installer.class, id);
		em.remove(p);
	}

}
