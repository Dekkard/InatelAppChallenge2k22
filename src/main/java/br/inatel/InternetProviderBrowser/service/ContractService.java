package br.inatel.InternetProviderBrowser.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.InternetProviderBrowser.model.Client;
import br.inatel.InternetProviderBrowser.model.Contract;
import br.inatel.InternetProviderBrowser.model.Plan;

@Service
@Transactional
public class ContractService implements ServiceModel<Contract, Long> {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Contract> list() {
		return em.createQuery("SELECT c from Contract c", Contract.class).getResultList();
	}

	public List<Contract> listClientContract() {
		return em.createQuery("SELECT co FROM Contract co WHERE EXISTS " + "(SELECT 1 " + "FROM Client c, Plan p"
				+ "WHERE co.client_id = c.id " + "and co.plan_id = p.id)", Contract.class).getResultList();
	}

	public List<Contract> lisByClient(Long id) {
		return em.createQuery(
				"SELECT co FROM Contract co WHERE EXISTS (SELECT 1 FROM Client c, Plan p WHERE co.client_id = c.id and co.plan_id = p.id and c.id = ?1)",
				Contract.class).setParameter(1, id).getResultList();
	}

	public Client findClient(Long contractId) {
		return em.createQuery("SELECT c FROM Client c WHERE EXISTIS (SELECT 1 FROM Contract co WHERE co.id = ?1 )",
				Client.class).setParameter(1, contractId).getSingleResult();
	}

	public Client findClient(Long contractId, Long clientId) {
		return em.createQuery(
				"SELECT c FROM Client c WHERE c.id = ?1 and EXISTIS (SELECT 1 FROM Contract co WHERE co.id = ?2 )",
				Client.class).setParameter(1, contractId).setParameter(2, clientId).getSingleResult();
	}

	public Client findPlan(Long contractId) {
		return em.createQuery("SELECT p FROM Plan p WHERE EXISTIS (SELECT 1 FROM Contract co WHERE co.id = ?1 )",
				Client.class).setParameter(1, contractId).getSingleResult();
	}

	public Client findPlan(Long contractId, Long planId) {
		return em.createQuery(
				"SELECT p FROM Plan p WHERE c.id = ?1 and EXISTIS (SELECT 1 FROM Contract co WHERE co.id = ?1 )",
				Client.class).setParameter(1, contractId).setParameter(2, planId).getSingleResult();
	}

	@Override
	public Contract find(Long id) {
//		return em.find(Contract.class, id);
		return em.createQuery(
				"SELECT co FROM Contract co WHERE co.id EXISTS (SELECT c FROM Client c, Plan p WHERE co.client_id = c.id and co.plan_id = p.id and co.id = ?1)",
				Contract.class).setParameter(1, id).getSingleResult();
	}

	@Override
	public Contract insert(Contract contract) {
		return em.merge(contract);
	}

	@Override
	public Contract update(Long id, Contract contract) {
		Contract c = em.find(Contract.class, id);
		c.setClient(contract.getClient());
		c.setInstallStatus(contract.getInstallStatus());
		c.setPlan(contract.getPlan());
		c.setTotalPrice(contract.getTotalPrice());
		return em.merge(c);
	}

	@Override
	public void delete(Long id) {
		em.remove(em.find(Contract.class, id));
	}

	public int updateClientId(Long contractId, Long clientId) {
		em.find(Client.class, clientId);
		em.find(Contract.class, contractId);
		return em.createQuery("UPDATE Contract c SET client_id = ?1 WHERE c.id = ?2")//
				.setParameter(1, clientId)//
				.setParameter(2, contractId).executeUpdate();
	}

	public int updatePlanId(Long contractId, Long planId) {
		em.find(Plan.class, planId);
		em.find(Contract.class, contractId);
		return em.createQuery("UPDATE Contract c SET client_id = ?1 WHERE c.id = ?2")//
				.setParameter(1, planId)//
				.setParameter(2, contractId).executeUpdate();
	}

}
