package br.com.proger.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.proger.dao.ProfissaoDAO;
import br.com.proger.domain.Profissao;
import br.com.proger.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProfissaoBean {

	private Profissao profissaoCadastro;
	
	private List<Profissao> listaProfissoes;
	private List<Profissao> listaProfissoesFiltradas;
	
	private String acao;
	private Long codigo;
	
	public Profissao getProfissaoCadastro() {
		if(profissaoCadastro == null){
			profissaoCadastro = new Profissao();
		}
		return profissaoCadastro;
	}
	public void setProfissaoCadastro(Profissao profissaoCadastro) {
		this.profissaoCadastro = profissaoCadastro;
	}
	public List<Profissao> getListaProfissoes() {
		return listaProfissoes;
	}
	public void setListaProfissoes(List<Profissao> listaProfissoes) {
		this.listaProfissoes = listaProfissoes;
	}
	public List<Profissao> getListaProfissoesFiltradas() {
		return listaProfissoesFiltradas;
	}
	public void setListaProfissoesFiltradas(List<Profissao> listaProfissoesFiltradas) {
		this.listaProfissoesFiltradas = listaProfissoesFiltradas;
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
		profissaoCadastro = new Profissao();
	}
	
	public void salvar(){
		try{
			ProfissaoDAO pdao = new ProfissaoDAO();
			pdao.salvar(profissaoCadastro);
			
			FacesUtil.addMsgInfo("A profissão foi cadastrada com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar a profissão "+ ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			ProfissaoDAO pdao = new ProfissaoDAO();
			listaProfissoes = pdao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu ");
		}
	}
	
	public void carregarDados(){
		try{
			if(codigo != null){
				ProfissaoDAO pdao = new ProfissaoDAO();
				profissaoCadastro = pdao.buscarPorCodigo(codigo);
			}else{
				profissaoCadastro = new Profissao();
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar os dados " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			ProfissaoDAO pdao = new ProfissaoDAO();
			pdao.editar(profissaoCadastro);
			
			FacesUtil.addMsgInfo("A profissão foi editada com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar a profissão "+ ex.getMessage());
		}
	}
	
	public void excluir(){
		try{
			ProfissaoDAO pdao = new ProfissaoDAO();
			pdao.salvar(profissaoCadastro);
			
			FacesUtil.addMsgInfo("A profissão foi excluída com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir a profissão "+ ex.getMessage());
		}
	}
}
