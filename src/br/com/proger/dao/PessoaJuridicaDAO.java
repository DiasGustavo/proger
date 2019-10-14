package br.com.proger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.proger.domain.PessoaJuridica;
import br.com.proger.util.HibernateUtil;



public class PessoaJuridicaDAO {

	public void salvar(PessoaJuridica pjuridica){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.save(pjuridica);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PessoaJuridica> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<PessoaJuridica> pessoasjuridicas = null;
		
		try{
			Query consulta = sessao.getNamedQuery("PessoaJuridica.listar");
			pessoasjuridicas = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return pessoasjuridicas;
	}
	
	public PessoaJuridica buscarPorCodigo(Long id){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		PessoaJuridica pessoajuridica = null;
		
		try{
			Query consulta = sessao.getNamedQuery("PessoaJuridica.buscarPorCodigo");
			consulta.setLong("codigo", id);
			pessoajuridica = (PessoaJuridica)consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return pessoajuridica;
	}
	
	public void editar(PessoaJuridica pjuridica){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(pjuridica);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
	
	public void excluir(PessoaJuridica pjuridica){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(pjuridica);
			transacao.commit();
		}catch(RuntimeException ex){
			if (transacao != null){
				transacao.rollback();
			}
			throw ex;
		}finally{
			sessao.close();
		}
	}
}
