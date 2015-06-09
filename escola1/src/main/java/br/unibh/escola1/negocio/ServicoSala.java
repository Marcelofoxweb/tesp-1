package br.unibh.escola1.negocio;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.unibh.escola1.entidades.Sala;
@Stateless
@LocalBean
public class ServicoSala implements DAO<Sala, Long> {
	
	@Inject
	EntityManager em;
	@Inject
	private Logger log;
	
	@Override
	public Sala insert(Sala t) throws Exception {
	
		log.info("Persistindo " + t);
		em.persist(t);
		return t;
	}

	@Override
	public Sala update(Sala t) throws Exception {
		log.info("Atualizando " + t);
		return em.merge(t);
	}

	@Override
	public void delete(Sala t) throws Exception {
		log.info("Removendo " + t);
		Object c = em.merge(t);
		em.remove(c);
	}

	@Override
	public Sala find(Long k) throws Exception {
		log.info("Encontrando Sala " + k);
		return em.find(Sala.class, k);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Sala> findAll() throws Exception {
		log.info("Encontrando todos os Sala");
		return em.createQuery("from Sala").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sala> findByName(String name) throws Exception {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Sala> findByCapacidade(int capacidadeValue){
		return em.createNamedQuery("Sala.findByCapacidade")
				.setParameter("capacidade", capacidadeValue).getResultList();
	}
	

}