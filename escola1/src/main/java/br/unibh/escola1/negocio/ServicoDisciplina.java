package br.unibh.escola1.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unibh.escola1.entidades.Aluno;
import br.unibh.escola1.entidades.Disciplina;

@Stateless
@LocalBean
public class ServicoDisciplina implements DAO<Disciplina, Long> {
	
	@Inject  
	EntityManager em;  
	
	@Inject  
	private Logger log;

	@Override
	public Disciplina insert(Disciplina t) throws Exception {
		log.info("Persistindo "+t);  
		ArrayList<Aluno> lista = new ArrayList<Aluno>();
		for (Aluno a: t.getAlunos()) lista.add(em.merge(a));
		t.setAlunos(lista);
	
		em.persist(t);
		return t;
	}

	@Override
	public Disciplina update(Disciplina t) throws Exception {
		log.info("Atualizando "+t);   
		return em.merge(t); 
	}

	@Override
	public void delete(Disciplina t) throws Exception {
		log.info("Removendo "+t);
		Object c = em.merge(t); 
		em.remove(c); 
	}

	@Override
	public Disciplina find(Long k) throws Exception {
		log.info("Encontrando disciplina "+k);
		Query query = em.createQuery(
				"select d from Disciplina d join fetch d.alunos "
				+ "where d.id = :id");
		query.setParameter("id", k);
		return (Disciplina) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Disciplina> findAll() throws Exception {
		log.info("Encontrando todos as disciplinas");
		return em.createQuery("from Disciplina").getResultList();
	}

	@Override
	public List<Disciplina> findByName(String name) throws Exception {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Disciplina> findByNomeECurso(String nome, String curso)
	{
		log.info("Encontrando disciplinas "+ nome + " " + curso);
		return em.createNamedQuery("Disciplina.findByNomeECurso")
				.setParameter("nome", nome + "%").setParameter("curso", curso + "%").getResultList();
	}
}