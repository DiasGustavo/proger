package br.com.proger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.proger.domain.Financeiro;
import br.com.proger.util.HibernateUtil;

public class FinanceiroDAO {

	public Long salvar(Financeiro financeiro){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long codigo = null;
		try{
			transacao = sessao.beginTransaction();
			codigo = (Long) sessao.save(financeiro);
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
	public List<Financeiro> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Financeiro> listaFinanceiros = null;
		try{
			Query consulta = sessao.getNamedQuery("Financeiro.listar");
			listaFinanceiros = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaFinanceiros;
	}
	
	public Financeiro buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Financeiro financeiro = null;
		try{
			Query consulta = sessao.getNamedQuery("Financeiro.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			financeiro = (Financeiro) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		
		return financeiro;
	}
	
	public void editar (Financeiro financeiro){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(financeiro);
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
	
	public void excluir (Financeiro financeiro){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(financeiro);
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
