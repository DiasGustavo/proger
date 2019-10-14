package br.com.proger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.proger.domain.Profissao;
import br.com.proger.util.HibernateUtil;



public class ProfissaoDAO {

	public Long salvar(Profissao profissao){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long codigo = null;
		try{
			transacao = sessao.beginTransaction();
			codigo = (Long)sessao.save(profissao);
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
	public List<Profissao> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Profissao> listaProfissoes = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Profissao.listar");
			listaProfissoes = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaProfissoes;
	}
	
	public Profissao buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Profissao profissao = null;
		try{
			Query consulta = sessao.getNamedQuery("Profissao.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			profissao = (Profissao)consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return profissao;
	}
	
	public void editar(Profissao profissao){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(profissao);
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
	
	public void excluir(Profissao profissao){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(profissao);
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
