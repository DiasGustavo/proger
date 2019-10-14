package br.com.proger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.proger.domain.PessoaFisica;
import br.com.proger.util.HibernateUtil;



public class PessoaFisicaDAO {

	public void salvar(PessoaFisica pfisica){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.save(pfisica);
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
	
	@SuppressWarnings("unchecked")
	public List<PessoaFisica> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<PessoaFisica> pfisicas = null;
		
		try{
			Query consulta = sessao.getNamedQuery("PessoaFisica.listar");
			pfisicas = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return pfisicas;
	}
	
	public PessoaFisica buscarPorCodigo(Long id){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		PessoaFisica pfisica = null;
		
		try{
			Query consulta = sessao.getNamedQuery("PessoaFisica.buscarPorCodigo");
			consulta.setLong("codigo", id);
			
			pfisica = (PessoaFisica) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return pfisica;
	}
	
	public void editar(PessoaFisica pfisica){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(pfisica);
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
	
	public void excluir (PessoaFisica pfisica){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(pfisica);
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
