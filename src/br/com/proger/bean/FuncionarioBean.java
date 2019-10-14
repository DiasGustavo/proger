package br.com.proger.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.proger.dao.FuncionarioDAO;
import br.com.proger.dao.OrgaoDAO;
import br.com.proger.dao.PessoaFisicaDAO;
import br.com.proger.domain.Funcionario;
import br.com.proger.domain.Orgao;
import br.com.proger.domain.PessoaFisica;
import br.com.proger.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {

	private Funcionario funcionarioCadastro;
	
	private List<Funcionario> listaFuncionarios;
	private List<Funcionario> listaFuncionariosFiltrados;
	private List<PessoaFisica> listaPessoasFisicas;
	private List<Orgao> listaOrgaos;
	
	private String acao;
	private Long codigo;
	public Funcionario getFuncionarioCadastro() {
		return funcionarioCadastro;
	}
	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}
	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}
	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}
	public List<Funcionario> getListaFuncionariosFiltrados() {
		return listaFuncionariosFiltrados;
	}
	public void setListaFuncionariosFiltrados(List<Funcionario> listaFuncionariosFiltrados) {
		this.listaFuncionariosFiltrados = listaFuncionariosFiltrados;
	}
	public List<PessoaFisica> getListaPessoasFisicas() {
		return listaPessoasFisicas;
	}
	public void setListaPessoasFisicas(List<PessoaFisica> listaPessoasFisicas) {
		this.listaPessoasFisicas = listaPessoasFisicas;
	}	
	public List<Orgao> getListaOrgaos() {
		return listaOrgaos;
	}
	public void setListaOrgaos(List<Orgao> listaOrgaos) {
		this.listaOrgaos = listaOrgaos;
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
		funcionarioCadastro = new Funcionario();
	}
	
	public void salvar(){
		try{
			FuncionarioDAO fdao = new FuncionarioDAO();
			funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha()));
			fdao.salvar(funcionarioCadastro);
			
			FacesUtil.addMsgInfo("O Funcionario foi cadastrado com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar o funcionário " +ex.getMessage());
		}
	}
	
	public void listar(){
		try{
			FuncionarioDAO fdao = new FuncionarioDAO();
			listaFuncionarios = fdao.listar();
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao listar os funcionários");
		}
	}
	
	public void carregarDados(){
		try{
			if(codigo != null){
				FuncionarioDAO fdao = new FuncionarioDAO();
				funcionarioCadastro = fdao.buscarPorCodigo(codigo);
			}else{
				funcionarioCadastro = new Funcionario();
			}
			PessoaFisicaDAO pfdao = new PessoaFisicaDAO();
			listaPessoasFisicas = pfdao.listar();
			
			OrgaoDAO odao = new OrgaoDAO();
			listaOrgaos = odao.listar();
			
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar o funcionário " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			FuncionarioDAO fdao = new FuncionarioDAO();
			funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha()));
			fdao.editar(funcionarioCadastro);
			
			FacesUtil.addMsgInfo("O Funcionario foi editado com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o funcionário " +ex.getMessage());
		}
	}
	
	public void excluir(){
		try{
			FuncionarioDAO fdao = new FuncionarioDAO();
			fdao.excluir(funcionarioCadastro);
			
			FacesUtil.addMsgInfo("O Funcionario foi excluído com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o funcionário " +ex.getMessage());
		}
	}
}
