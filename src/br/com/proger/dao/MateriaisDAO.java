package br.com.proger.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.proger.domain.Materiais;
import br.com.proger.util.HibernateUtil;

public class MateriaisDAO {

	public Long salvar(Materiais materiais){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long codigo = null;
		try{
			transacao = sessao.beginTransaction();
			codigo = (Long) sessao.save(materiais);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}
		return codigo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Materiais> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Materiais> listaMateriais = null;
		try{
			Query consulta = sessao.getNamedQuery("Materiais.listar");
			listaMateriais = consulta.list();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return listaMateriais;
	}
	
	public Materiais buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Materiais materiais = null;
		try{
			Query consulta = sessao.getNamedQuery("Materiais.buscarPorCodigo");
			materiais = (Materiais) consulta.uniqueResult();
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		return materiais;
	}
	
	public void editar (Materiais materiais){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.update(materiais);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}
		
	}
	
	public void excluir (Materiais materiais){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(materiais);
			transacao.commit();
		}catch(RuntimeException ex){
			if(transacao != null){
				transacao.rollback();
			}
			throw ex;
		}
		
	}
}
