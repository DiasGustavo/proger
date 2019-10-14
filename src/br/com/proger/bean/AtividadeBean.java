package br.com.proger.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.proger.dao.AcaoDAO;
import br.com.proger.dao.AtividadeDAO;
import br.com.proger.domain.Acao;
import br.com.proger.domain.Atividade;
import br.com.proger.util.FacesUtil;

@ManagedBean
@ViewScoped
public class AtividadeBean {

	private Atividade atividadeCadastro;
	
	private List<Atividade> listaAtividades;
	private List<Atividade> listaAtividadesFiltradas;
	private List<Acao> listaAcoes;
	
	private String acao;
	private Long codigo;
	public Atividade getAtividadeCadastro() {
		return atividadeCadastro;
	}
	public void setAtividadeCadastro(Atividade atividadeCadastro) {
		if(atividadeCadastro == null){
			atividadeCadastro = new Atividade();
		}
		this.atividadeCadastro = atividadeCadastro;
	}
	public List<Atividade> getListaAtividades() {
		return listaAtividades;
	}
	public void setListaAtividades(List<Atividade> listaAtividades) {
		this.listaAtividades = listaAtividades;
	}
	public List<Atividade> getListaAtividadesFiltradas() {
		return listaAtividadesFiltradas;
	}
	public void setListaAtividadesFiltradas(List<Atividade> listaAtividadesFiltradas) {
		this.listaAtividadesFiltradas = listaAtividadesFiltradas;
	}
	public List<Acao> getListaAcoes() {
		return listaAcoes;
	}
	public void setListaAcoes(List<Acao> listaAcoes) {
		this.listaAcoes = listaAcoes;
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
		atividadeCadastro = new Atividade();
	}
	
	public void salvar(){
		try{
			AtividadeDAO adao = new AtividadeDAO();
			adao.salvar(atividadeCadastro);
			
			FacesUtil.addMsgInfo("Atividade cadastrada com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar a atividade "+ ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			AtividadeDAO adao = new AtividadeDAO();
			listaAtividades = adao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao listar as atividades");
		}
	}
	
	public void carregarDados(){
		try{
			if(codigo != null){
				AtividadeDAO adao = new AtividadeDAO();
				atividadeCadastro = adao.buscarPorCodigo(codigo);
			}else{
				atividadeCadastro = new Atividade();
			}
			AcaoDAO acdao = new AcaoDAO();
			listaAcoes = acdao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar os dados da atividade " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			AtividadeDAO adao = new AtividadeDAO();
			adao.editar(atividadeCadastro);
			
			FacesUtil.addMsgInfo("Atividade editada com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar a atividade "+ ex.getMessage());
		}
	}
	
	public void excluir(){
		try{
			AtividadeDAO adao = new AtividadeDAO();
			adao.excluir(atividadeCadastro);
			
			FacesUtil.addMsgInfo("Atividade excluída com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir a atividade "+ ex.getMessage());
		}
	}
}
