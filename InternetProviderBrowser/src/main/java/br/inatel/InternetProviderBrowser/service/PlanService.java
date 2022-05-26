package br.inatel.InternetProviderBrowser.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.InternetProviderBrowser.model.Plan;

@Service
@Transactional
public class PlanService implements ServiceModel<Plan, Long> {
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Plan> list() {
		return em.createQuery("SELECT sp FROM ServiceProvided sp", Plan.class).getResultList();
	}

	@Override
	public Plan find(Long id) {
		return em.find(Plan.class, id);
	}

	@Override
	public Plan insert(Plan plan) {
		return em.merge(plan);
	}

	@Override
	public Plan update(Long id, Plan plan) {
		Plan sp = em.find(Plan.class, id);
		sp = new Plan(plan);
		return em.merge(sp);
	}

	@Override
	public void delete(Long id) {
		Plan sp = em.find(Plan.class, id);
		em.remove(sp);
	}

}
