package br.com.proger.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.proger.dao.PessoaJuridicaDAO;
import br.com.proger.domain.PessoaJuridica;
import br.com.proger.util.FacesUtil;

@ManagedBean
@ViewScoped
public class PessoaJuridicaBean {

	private PessoaJuridica pessoaJuridicaCadastro;
	
	private List<PessoaJuridica> listaPjs;
	private List<PessoaJuridica> listaPjsFiltradas;
	
	private String acao;
	private Long codigo;
	
	public PessoaJuridica getPessoaJuridicaCadastro() {
		return pessoaJuridicaCadastro;
	}
	public void setPessoaJuridicaCadastro(PessoaJuridica pessoaJuridicaCadastro) {
		this.pessoaJuridicaCadastro = pessoaJuridicaCadastro;
	}
	public List<PessoaJuridica> getListaPjs() {
		return listaPjs;
	}
	public void setListaPjs(List<PessoaJuridica> listaPjs) {
		this.listaPjs = listaPjs;
	}
	public List<PessoaJuridica> getListaPjsFiltradas() {
		return listaPjsFiltradas;
	}
	public void setListaPjsFiltradas(List<PessoaJuridica> listaPjsFiltradas) {
		this.listaPjsFiltradas = listaPjsFiltradas;
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
		pessoaJuridicaCadastro = new PessoaJuridica();
	}
	
	public void salvar(){
		try{
			PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
			pjdao.salvar(pessoaJuridicaCadastro);
			
			FacesUtil.addMsgInfo("A Pessoa Jurídica foi cadastrada com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar a Pessoa Jurídica");
		}
	}
	
	public void listar(){
		try{
			PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
			listaPjs = pjdao.listar();
			
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao listar as Pessoas Jurídicas");
		}
	}
	
	public void carregarDados(){
		try{
			if(codigo != null){
				PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
				pessoaJuridicaCadastro = pjdao.buscarPorCodigo(codigo);
			}else{
				pessoaJuridicaCadastro = new PessoaJuridica();
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar a Pessoa Jurídica "+ ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
			pjdao.editar(pessoaJuridicaCadastro);
			
			FacesUtil.addMsgInfo("A Pessoa Jurídica foi editada com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar a Pessoa Jurídica");
		}
	}
	
	public void excluir(){
		try{
			PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
			pjdao.excluir(pessoaJuridicaCadastro);
			
			FacesUtil.addMsgInfo("A Pessoa Jurídica foi excluída com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir a Pessoa Jurídica");
		}
	}
}
