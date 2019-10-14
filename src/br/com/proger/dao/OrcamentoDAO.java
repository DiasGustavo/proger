package br.com.proger.dao;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.proger.domain.Orcamento;
import br.com.proger.util.HibernateUtil;

public class OrcamentoDAO {

	public Long salvar(Orcamento orcamento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long codigo = null;
		try{
			transacao = sessao.beginTransaction();
			codigo = (Long) sessao.save(orcamento);
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
	public List<Orcamento> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Orcamento> listaOrcamentos = null;
		try{
			Query consulta = sessao.getNamedQuery("Orcamento.listar");
			listaOrcamentos = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaOrcamentos;
	}
	
	public Orcamento buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Orcamento orcamento = null;
		try{
			Query consulta = sessao.getNamedQuery("Orcamento.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			orcamento = (Orcamento)consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return orcamento;
	}
	
	public void editar(Orcamento orcamento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(orcamento);
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
	
	public void excluir(Orcamento orcamento){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(orcamento);
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
