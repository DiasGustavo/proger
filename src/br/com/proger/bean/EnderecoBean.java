package br.com.proger.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.proger.dao.EnderecoDAO;
import br.com.proger.domain.Endereco;
import br.com.proger.util.FacesUtil;

@ManagedBean
@ViewScoped
public class EnderecoBean {

	private Endereco enderecoCadastro;
	private List<Endereco> listaEnderecos;
	private List<Endereco> listaEnderecosFiltrados;
	
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

	public Endereco getEnderecoCadastro() {
		if (enderecoCadastro == null){
			enderecoCadastro = new Endereco();
		}
		return enderecoCadastro;
	}

	public void setenderecoCadastro(Endereco enderecoCadastro) {
		this.enderecoCadastro = enderecoCadastro;
	}

	public List<Endereco> getListaEnderecos() {
		return listaEnderecos;
	}

	public void setListaEnderecos(List<Endereco> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}

	public List<Endereco> getListaEnderecosFiltrados() {
		return listaEnderecosFiltrados;
	}

	public void setListaEnderecosFiltrados(List<Endereco> listaEnderecosFiltrados) {
		this.listaEnderecosFiltrados = listaEnderecosFiltrados;
	}

	public void novo() {
		enderecoCadastro = new Endereco();
	}

	public void salvar() {
		try {
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			enderecoDAO.salvar(enderecoCadastro);

			enderecoCadastro = new Endereco();

			FacesUtil.addMsgInfo("Endereco cadastrado com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar o endereco" + ex.getMessage());
		}
	}

	public void listar() {
		try {
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			listaEnderecos = enderecoDAO.listar();

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar os endereços " + ex.getMessage());
		}
	}

	public void carregarDados() {
		try {
			if (codigo != null) {
				EnderecoDAO edao = new EnderecoDAO();
				enderecoCadastro = edao.buscarPorCodigo(codigo);
			}else{
				enderecoCadastro = new Endereco();
			}

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar os enderecos" + ex.getMessage());
		}
	}

	public void editar() {
		try {
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			enderecoDAO.editar(enderecoCadastro);

			enderecoCadastro = new Endereco();

			FacesUtil.addMsgInfo("Endereco editado com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao editar o endereco" + ex.getMessage());
		}
	}
	
	public void excluir() {
		try {
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			enderecoDAO.excluir(enderecoCadastro);

			enderecoCadastro = new Endereco();

			FacesUtil.addMsgInfo("Endereco excluido com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir o endereco" + ex.getMessage());
		}
	}	
}
