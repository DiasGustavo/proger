package br.com.proger.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.proger.dao.EnderecoDAO;
import br.com.proger.dao.PessoaFisicaDAO;
import br.com.proger.dao.ProfissaoDAO;
import br.com.proger.domain.Endereco;
import br.com.proger.domain.PessoaFisica;
import br.com.proger.domain.Profissao;
import br.com.proger.util.FacesUtil;



@ManagedBean
@ViewScoped
public class PessoaFisicaBean {

	private PessoaFisica pfisicaCadastro;
	private List<PessoaFisica> listaPessoasFisicas;
	private List<PessoaFisica> listaPessoasFisicasFiltradas;
	private List<Endereco> listaEnderecos;
	private List<Profissao> listaProfissoes;
		
	private String acao;
	private Long codigo;	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public PessoaFisica getPfisicaCadastro() {
		if (pfisicaCadastro == null){
			pfisicaCadastro = new PessoaFisica();
		}
		return pfisicaCadastro;
	}
	public void setPfisicaCadastro(PessoaFisica pfisicaCadastro) {
		this.pfisicaCadastro = pfisicaCadastro;
	}
	public List<PessoaFisica> getListaPessoasFisicas() {
		return listaPessoasFisicas;
	}
	public void setListaPessoasFisicas(List<PessoaFisica> listaPessoasFisicas) {
		this.listaPessoasFisicas = listaPessoasFisicas;
	}
	public List<PessoaFisica> getListaPessoasFisicasFiltradas() {
		return listaPessoasFisicasFiltradas;
	}
	public void setListaPessoasFisicasFiltradas(List<PessoaFisica> listaPessoasFisicasFiltradas) {
		this.listaPessoasFisicasFiltradas = listaPessoasFisicasFiltradas;
	}	
	public List<Endereco> getListaEnderecos() {
		return listaEnderecos;
	}
	public void setListaEnderecos(List<Endereco> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}
	public List<Profissao> getListaProfissoes() {
		return listaProfissoes;
	}
	public void setListaProfissoes(List<Profissao> listaProfissoes) {
		this.listaProfissoes = listaProfissoes;
	}
	public void novo(){
		pfisicaCadastro = new PessoaFisica();
	}
	
	public void salvar(){
		try{
			PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
			pfdao.salvar(pfisicaCadastro);
			
			pfisicaCadastro = new PessoaFisica();
			
			FacesUtil.addMsgInfo("Pessoa Fisica cadastrada com sucesso");
		}catch (RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar a pessoa" + ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
			listaPessoasFisicas = pfdao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar as pessoas" + ex.getMessage());
		}
	}
	
	public void carregarDados(){
		try{
			if(codigo != null){
				PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
				pfisicaCadastro = pfdao.buscarPorCodigo(codigo);
			}else{
				pfisicaCadastro = new PessoaFisica();
				
			}
			ProfissaoDAO pdao = new ProfissaoDAO();
			listaProfissoes = pdao.listar();
			
			EnderecoDAO edao = new EnderecoDAO();
			listaEnderecos = edao.listar();
						
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Erro ao carregar os dados da pessoa" + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
			pfdao.editar(pfisicaCadastro);
			
			pfisicaCadastro = new PessoaFisica();
			
			FacesUtil.addMsgInfo("Pessoa Fisica editar com sucesso");
		}catch (RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar a pessoa" + ex.getMessage());
		}

	}
	
	public void excluir(){
		try{
			PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
			pfdao.excluir(pfisicaCadastro);
			
			pfisicaCadastro = new PessoaFisica();
			
			FacesUtil.addMsgInfo("Pessoa Fisica excluída com sucesso");
		}catch (RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir a pessoa" + ex.getMessage());
		}

	}
}
