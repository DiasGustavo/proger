package br.com.proger.bean;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.proger.dao.FinanceiroDAO;
import br.com.proger.domain.Financeiro;
import br.com.proger.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FinanceiroBean {

	private Financeiro financeiroCadastro;
	
	private List<Financeiro> listaFinanceiros;
	private List<Financeiro> listaFinanceirosFiltrados;
	
	private String acao;
	private Long codigo;
	public Financeiro getFinanceiroCadastro() {
		return financeiroCadastro;
	}
	public void setFinanceiroCadastro(Financeiro financeiroCadastro) {
		this.financeiroCadastro = financeiroCadastro;
	}
	public List<Financeiro> getListaFinanceiros() {
		return listaFinanceiros;
	}
	public void setListaFinanceiros(List<Financeiro> listaFinanceiros) {
		this.listaFinanceiros = listaFinanceiros;
	}
	public List<Financeiro> getListaFinanceirosFiltrados() {
		return listaFinanceirosFiltrados;
	}
	public void setListaFinanceirosFiltrados(List<Financeiro> listaFinanceirosFiltrados) {
		this.listaFinanceirosFiltrados = listaFinanceirosFiltrados;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public void novo(){
		financeiroCadastro = new Financeiro();
	}
	
	public void salvar(){
		try{
			FinanceiroDAO fdao = new FinanceiroDAO();
			fdao.salvar(financeiroCadastro);
			
			FacesUtil.addMsgInfo("Financeiro cadastrado com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar o financeiro");
		}
	}
	
	public void listar(){
		try{
			FinanceiroDAO fdao = new FinanceiroDAO();
			listaFinanceiros = fdao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao lista os financeiros " + ex.getMessage());
		}
	}
	
	public void carregarDados(){
		try{
			if(codigo != null){
				FinanceiroDAO fdao = new FinanceiroDAO();
				financeiroCadastro = fdao.buscarPorCodigo(codigo);
			}else{
				financeiroCadastro = new Financeiro();
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar os dados do financeiro " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			FinanceiroDAO fdao = new FinanceiroDAO();
			fdao.editar(financeiroCadastro);
			
			FacesUtil.addMsgInfo("Financeiro editado com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o financeiro");
		}
	}
	
	public void excluir(){
		try{
			FinanceiroDAO fdao = new FinanceiroDAO();
			fdao.excluir(financeiroCadastro);
			
			FacesUtil.addMsgInfo("Financeiro excluído com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o financeiro");
		}
	}
}
