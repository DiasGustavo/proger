package br.com.proger.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.proger.dao.EnderecoDAO;
import br.com.proger.dao.OrgaoDAO;
import br.com.proger.dao.PessoaJuridicaDAO;
import br.com.proger.domain.Endereco;
import br.com.proger.domain.Orgao;
import br.com.proger.domain.PessoaJuridica;
import br.com.proger.util.FacesUtil;

@ManagedBean
@ViewScoped
public class OrgaoBean {

	private Orgao orgaoCadastro;
	
	private List<Orgao> listaOrgaos;
	private List<Orgao> listaOrgaosFiltrados;
	private List<Endereco> listaEnderecos;
	private List<PessoaJuridica> listaPjs;
	
	private String acao;
	private Long codigo;
	public Orgao getOrgaoCadastro() {
		return orgaoCadastro;
	}
	public void setOrgaoCadastro(Orgao orgaoCadastro) {
		this.orgaoCadastro = orgaoCadastro;
	}
	public List<Orgao> getListaOrgaos() {
		return listaOrgaos;
	}
	public void setListaOrgaos(List<Orgao> listaOrgaos) {
		this.listaOrgaos = listaOrgaos;
	}
	public List<Orgao> getListaOrgaosFiltrados() {
		return listaOrgaosFiltrados;
	}
	public void setListaOrgaosFiltrados(List<Orgao> listaOrgaosFiltrados) {
		this.listaOrgaosFiltrados = listaOrgaosFiltrados;
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
	
	public List<Endereco> getListaEnderecos() {
		return listaEnderecos;
	}
	public void setListaEnderecos(List<Endereco> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}	
	
	public List<PessoaJuridica> getListaPjs() {
		return listaPjs;
	}
	public void setListaPjs(List<PessoaJuridica> listaPjs) {
		this.listaPjs = listaPjs;
	}
	public void novo(){
		orgaoCadastro = new Orgao();
	}
	
	public void salvar(){
		try{
			OrgaoDAO odao = new OrgaoDAO();
			orgaoCadastro.setRegistro(DigestUtils.md5Hex(orgaoCadastro.getPessoaJuridica().getCnpj()+orgaoCadastro.getNome()));
			odao.salvar(orgaoCadastro);
			
			FacesUtil.addMsgInfo("O Órgão foi Cadastrado com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar o Órgão.");
		}
	}
	
	public void listar(){
		try{
			OrgaoDAO odao = new OrgaoDAO();
			listaOrgaos = odao.listar();
		
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao listar os órgãos.");
		}
	}
	
	public void carregarDados(){
		try{
			if(codigo != null){
				OrgaoDAO odao = new OrgaoDAO();
				orgaoCadastro = odao.buscarPorCodigo(codigo);
			}else{
				orgaoCadastro = new Orgao();
			}
			EnderecoDAO edao = new EnderecoDAO();
			listaEnderecos = edao.listar();
			
			PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();
			listaPjs = pjdao.listar();
			
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar o Órgão " + ex.getMessage());
		}
	}
	
	public void editar(){
		try{
			OrgaoDAO odao = new OrgaoDAO();
			orgaoCadastro.setRegistro(DigestUtils.md5Hex(orgaoCadastro.getPessoaJuridica().getCnpj()+orgaoCadastro.getNome()));
			odao.editar(orgaoCadastro);
			
			FacesUtil.addMsgInfo("O Órgão foi editado com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o Órgão.");
		}
	}
	
	public void excluir(){
		try{
			OrgaoDAO odao = new OrgaoDAO();
			odao.salvar(orgaoCadastro);
			
			FacesUtil.addMsgInfo("O Órgão foi excluído com sucesso!");
		}catch(RuntimeException ex){
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o Órgão.");
		}
	}
}
