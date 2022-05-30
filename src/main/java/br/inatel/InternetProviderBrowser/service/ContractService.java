package br.inatel.InternetProviderBrowser.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.InternetProviderBrowser.model.Contract;

@Service
@Transactional
public class ContractService implements ServiceModel<Contract, Long>{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Contract> list() {
		return em.createQuery("SELECT c from Contract c", Contract.class).getResultList();
	}

	@Override
	public Contract find(Long id) {
		return em.find(Contract.class, id);
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

}
