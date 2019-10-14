package br.com.proger.bean;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.proger.dao.MateriaisDAO;
import br.com.proger.domain.Materiais;
import br.com.proger.util.FacesUtil;

@ManagedBean
@ViewScoped
public class MateriaisBean {

	private Materiais materiaisCadastro;
	
	private List<Materiais> listaMateriais;
	private List<Materiais> listaMateriaisFiltrados;
	
	private String acao;
	private Long codigo;
	public Materiais getMateriaisCadastro() {
		return materiaisCadastro;
	}
	public void setMateriaisCadastro(Materiais materiaisCadastro) {
		this.materiaisCadastro = materiaisCadastro;
	}
	public List<Materiais> getListaMateriais() {
		return listaMateriais;
	}
	public void setListaMateriais(List<Materiais> listaMateriais) {
		this.listaMateriais = listaMateriais;
	}
	public List<Materiais> getListaMateriaisFiltrados() {
		return listaMateriaisFiltrados;
	}
	public void setListaMateriaisFiltrados(List<Materiais> listaMateriaisFiltrados) {
		this.listaMateriaisFiltrados = listaMateriaisFiltrados;
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
		materiaisCadastro = new Materiais();
	}
	
	public void salvar(){
		try{
			MateriaisDAO mdao = new MateriaisDAO();
			mdao.salvar(materiaisCadastro);
			
			FacesUtil.addMsgInfo("Materiais cadastrado com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar o Material "+ ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			MateriaisDAO mdao = new MateriaisDAO();
			listaMateriais = mdao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao listar os materiais");
		}
	}
	
	public void carregarDados(){
		try{
			if(codigo != null){
				MateriaisDAO mdao = new MateriaisDAO();
				materiaisCadastro = mdao.buscarPorCodigo(codigo);
			}else{
				materiaisCadastro = new Materiais();
			}
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar o Material " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			MateriaisDAO mdao = new MateriaisDAO();
			mdao.editar(materiaisCadastro);
			
			FacesUtil.addMsgInfo("Materiais editado com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o Material "+ ex.getMessage());
		}
	}
	
	public void excluir(){
		try{
			MateriaisDAO mdao = new MateriaisDAO();
			mdao.excluir(materiaisCadastro);
			
			FacesUtil.addMsgInfo("Materiais excluído com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o Material "+ ex.getMessage());
		}
	}
}
