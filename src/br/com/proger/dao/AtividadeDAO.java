package br.com.proger.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.proger.domain.Atividade;
import br.com.proger.util.HibernateUtil;

public class AtividadeDAO {

	public Long salvar(Atividade atividade){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long codigo = null;
		try{
			transacao = sessao.beginTransaction();
			codigo = (Long) sessao.save(atividade);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
		return codigo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Atividade> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Atividade> listaAtividades = null;
		try{
			Query consulta = sessao.getNamedQuery("Atividade.listar");
			listaAtividades = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaAtividades;
	}
	
	@SuppressWarnings("unchecked")
	public Set<Atividade> buscarListaAtividades (Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Set<Atividade> atividades = null;
		List<Atividade> listaAtividades = null;
		try{
			Query consulta = sessao.getNamedQuery("Atividade.buscarAtividades");
			consulta.setLong("codigo", codigo);
			listaAtividades = consulta.list();
			atividades = new HashSet<Atividade>(listaAtividades);
			atividades.addAll(listaAtividades);
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return atividades;
	}
	
	public Atividade buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Atividade atividade = null;
		try{
			Query consulta = sessao.getNamedQuery("Atividade.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			atividade = (Atividade) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return atividade;
	}
	
	public void editar (Atividade atividade){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(atividade);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
		
	}
	
	public void excluir (Atividade atividade){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(atividade);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
		
	}
}
