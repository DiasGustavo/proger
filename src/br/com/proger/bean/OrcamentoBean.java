package br.com.proger.bean;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.proger.dao.OrcamentoDAO;
import br.com.proger.domain.Orcamento;
import br.com.proger.util.FacesUtil;

@ManagedBean
@ViewScoped
public class OrcamentoBean {

	private Orcamento orcamentoCadastro;
	
	private List<Orcamento> listaOrcamentos;
	private List<Orcamento> listaOrcamentosFiltrados;
	
	private String acao;
	private Long codigo;
	public Orcamento getOrcamentoCadastro() {
		return orcamentoCadastro;
	}
	public void setOrcamentoCadastro(Orcamento orcamentoCadastro) {
		this.orcamentoCadastro = orcamentoCadastro;
	}
	public List<Orcamento> getListaOrcamentos() {
		return listaOrcamentos;
	}
	public void setListaOrcamentos(List<Orcamento> listaOrcamentos) {
		this.listaOrcamentos = listaOrcamentos;
	}
	public List<Orcamento> getListaOrcamentosFiltrados() {
		return listaOrcamentosFiltrados;
	}
	public void setListaOrcamentosFiltrados(List<Orcamento> listaOrcamentosFiltrados) {
		this.listaOrcamentosFiltrados = listaOrcamentosFiltrados;
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
		orcamentoCadastro = new Orcamento();
	}
	
	public void salvar(){
		try{
			OrcamentoDAO odao = new OrcamentoDAO();
			odao.salvar(orcamentoCadastro);
			
			FacesUtil.addMsgInfo("Orçamento Cadastrado com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar o orçamento.");
		}
	}
	
	public void listar(){
		try{
			OrcamentoDAO odao = new OrcamentoDAO();
			listaOrcamentos = odao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao listar os orcamentos " + ex.getMessage());
		}
	}
	
	public void carregarDados(){
		try{
			if(codigo != null){
				OrcamentoDAO odao = new OrcamentoDAO();
				orcamentoCadastro = odao.buscarPorCodigo(codigo);
			}else{
				orcamentoCadastro = new Orcamento();
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar os dados do orcamento "+ ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			OrcamentoDAO odao = new OrcamentoDAO();
			odao.editar(orcamentoCadastro);
			
			FacesUtil.addMsgInfo("Orçamento editado com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o orçamento.");
		}
	}
	
	public void excluir(){
		try{
			OrcamentoDAO odao = new OrcamentoDAO();
			odao.salvar(orcamentoCadastro);
			
			FacesUtil.addMsgInfo("Orçamento excluído com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o orçamento.");
		}
	}
}
