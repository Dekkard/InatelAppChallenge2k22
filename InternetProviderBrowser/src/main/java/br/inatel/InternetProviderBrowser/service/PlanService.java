package br.inatel.InternetProviderBrowser.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.InternetProviderBrowser.model.Installer;
import br.inatel.InternetProviderBrowser.model.Plan;

@Service
@Transactional
public class PlanService implements ServiceModel<Plan, Long> {
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Plan> list() {
		return em.createQuery("SELECT p FROM Plan p", Plan.class).getResultList();
	}

	@Override
	public Plan find(Long id) {
		return em.find(Plan.class, id);
	}

	public Installer findInstaller(Long planId) {
		return em.createQuery(
				"SELECT i FROM Installer i WHERE EXISTS (SELECT p FROM Plan p WHERE p.id = ?1)",
				Installer.class).setParameter(1, planId).getSingleResult();
	}
	
	public Installer findInstaller(Long planId, Long installerId) {
		return em.createQuery(
				"SELECT i FROM Installer i WHERE i.id = ?1 and EXISTS (SELECT p FROM Plan p WHERE  p.id = ?2)",
				Installer.class).setParameter(1, installerId).setParameter(2, planId).getSingleResult();
	}

	@Override
	public Plan insert(Plan plan) {
		return em.merge(plan);
	}

	@Override
	public Plan update(Long id, Plan plan) {
		Plan p = em.find(Plan.class, id);
		p.setIsp(plan.getIsp());
		p.setDataCapacity(plan.getDataCapacity());
		p.setDownloadSpeed(plan.getDownloadSpeed());
		p.setUploadSpeed(plan.getUploadSpeed());
		p.setDescription(plan.getDescription());
		p.setPrice(plan.getPrice());
		p.setListContract(plan.getListContract());
		p.setInstaller(plan.getInstaller());
		return em.merge(p);
	}

	@Override
	public void delete(Long id) {
		Plan sp = em.find(Plan.class, id);
		em.remove(sp);
	}

	public int updateInstallerId(Long planId, Long installerId) {
		try {
			em.find(Installer.class, installerId);
			em.find(Plan.class, planId);
			return em.createQuery("UPDATE Plan p SET installer_id = ?1 WHERE p.id = ?2")//
					.setParameter(1, installerId)//
					.setParameter(2, planId).executeUpdate();
		} catch (NullPointerException e) {
			return 0;
		}
	}

}
