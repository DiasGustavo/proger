package br.com.proger.bean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.proger.dao.AcaoDAO;
import br.com.proger.dao.ArquivoDAO;
import br.com.proger.dao.AtividadeDAO;
import br.com.proger.dao.FuncionarioDAO;
import br.com.proger.dao.OrgaoDAO;
import br.com.proger.domain.Acao;
import br.com.proger.domain.Arquivo;
import br.com.proger.domain.Atividade;
import br.com.proger.domain.Funcionario;
import br.com.proger.domain.Orgao;
import br.com.proger.util.ArquivoUtil;
import br.com.proger.util.DownloadArquivoUtil;
import br.com.proger.util.FacesUtil;

@ManagedBean
@ViewScoped
public class AcaoBean {

	private Acao acaoCadastro;
	private File arquivoCarregado;
	private StreamedContent arquivoVisualizar;
	private Arquivo arquivoCadastro;
	private Atividade atividade;

	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	private List<Acao> listaAcoes;
	private List<Acao> listaAcoesFiltradas;
	private List<Atividade> listaAtividades;
	private Set<Atividade> atividades;
	private Set<Arquivo> arquivos;

	private String acao;
	private Long codigo;

	public Acao getAcaoCadastro() {
		if (acaoCadastro == null) {
			acaoCadastro = new Acao();
		}
		return acaoCadastro;
	}

	public Atividade getAtividade() {
		if (atividade == null) {
			atividade = new Atividade();
		}
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public void setAcaoCadastro(Acao acaoCadastro) {
		this.acaoCadastro = acaoCadastro;
	}

	public List<Acao> getListaAcoes() {
		return listaAcoes;
	}

	public void setListaAcoes(List<Acao> listaAcoes) {
		this.listaAcoes = listaAcoes;
	}

	public List<Acao> getListaAcoesFiltradas() {
		return listaAcoesFiltradas;
	}

	public void setListaAcoesFiltradas(List<Acao> listaAcoesFiltradas) {
		this.listaAcoesFiltradas = listaAcoesFiltradas;
	}

	public List<Atividade> getListaAtividades() {
		if (listaAtividades == null) {
			listaAtividades = new ArrayList<>();
		}
		return listaAtividades;
	}

	public Set<Atividade> getAtividades() {
		if (atividades == null) {
			atividades = new HashSet<Atividade>();
		}
		return atividades;
	}

	public Set<Arquivo> getArquivos() {
		if (arquivos == null) {
			arquivos = new HashSet<Arquivo>();
		}
		return arquivos;
	}

	public void setArquivos(Set<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}

	public void setAtividades(Set<Atividade> atividades) {
		this.atividades = atividades;
	}

	public void setListaAtividades(List<Atividade> listaAtividades) {
		this.listaAtividades = listaAtividades;
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

	public File getArquivoCarregado() {
		return arquivoCarregado;
	}

	public void setArquivoCarregado(File arquivoCarregado) {
		this.arquivoCarregado = arquivoCarregado;
	}

	public StreamedContent getArquivoVisualizar() {
		return arquivoVisualizar;
	}

	public void setArquivoVisualizar(StreamedContent arquivoVisualizar) {
		this.arquivoVisualizar = arquivoVisualizar;
	}

	public Arquivo getArquivoCadastro() {
		if (arquivoCadastro == null) {
			arquivoCadastro = new Arquivo();
		}
		return arquivoCadastro;
	}

	public void setArquivoCadastro(Arquivo arquivoCadastro) {
		this.arquivoCadastro = arquivoCadastro;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public void novo() {
		acaoCadastro = new Acao();
	}

	public void salvar() {
		try {
			AcaoDAO adao = new AcaoDAO();

			if (acaoCadastro != null) {
				// preenche a ação
				Set<Acao> acoes = new HashSet<Acao>();
				acoes.add(acaoCadastro);
				acaoCadastro.setAtividades(atividades);
				acaoCadastro.setArquivos(arquivos);
				acaoCadastro.setRegistro(DigestUtils.md5Hex(acaoCadastro.getTitulo() + acaoCadastro.getFuncionario()
						+ acaoCadastro.getDataCadastro() + acaoCadastro.getDataRealizacao()));

			}
			adao.salvar(acaoCadastro);
			FacesUtil.addMsgInfo("Ação cadastrada com sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao salvar a ação");
		}
	}

	public void listar() {
		try {
			AcaoDAO adao = new AcaoDAO();
			listaAcoes = adao.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao listar as ações");
		}
	}

	public void carregarDados() {
		try {
			if (codigo != null) {
				AcaoDAO adao = new AcaoDAO();
				ArquivoDAO ardao = new ArquivoDAO();
				AtividadeDAO atvdao = new AtividadeDAO();

				acaoCadastro = adao.buscarPorCodigo(codigo);
				arquivos = ardao.buscarListaSetPorCodigo(codigo);
				atividades = atvdao.buscarListaAtividades(codigo);

			} else {

				acaoCadastro = new Acao();
				atividade = new Atividade();
				arquivoCadastro = new Arquivo();

				FuncionarioDAO fdao = new FuncionarioDAO();
				Funcionario funcionario = fdao.buscarPorCodigo(autenticacaoBean.getFuncionarioLogado().getId());
				acaoCadastro.setFuncionario(funcionario);
				acaoCadastro.setDataCadastro(getPegaDataAtual());

				OrgaoDAO odao = new OrgaoDAO();
				Orgao orgao = odao.buscarPorCodigo(autenticacaoBean.getOrgaoLogado().getId());
				acaoCadastro.setOrgao(orgao);
			}
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao carregar os dados da Ação.");
		}
	}

	public void editar() {
		try {
			AcaoDAO adao = new AcaoDAO();

			if (acaoCadastro != null) {
				// preenche a ação
				Set<Acao> acoes = new HashSet<Acao>();
				acoes.add(acaoCadastro);
				acaoCadastro.setAtividades(atividades);
				acaoCadastro.setArquivos(arquivos);
				acaoCadastro.setRegistro(DigestUtils.md5Hex(acaoCadastro.getTitulo() + acaoCadastro.getFuncionario()
						+ acaoCadastro.getDataCadastro() + acaoCadastro.getDataRealizacao()));

			}
			adao.editar(acaoCadastro);
			FacesUtil.addMsgInfo("Ação editada com sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao editar a ação");
		}
	}

	public void excluir() {
		try {
			AcaoDAO adao = new AcaoDAO();
			adao.salvar(acaoCadastro);

			FacesUtil.addMsgInfo("Ação excluída com sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro ao excluir a ação");
		}
	}

	public void upload(FileUploadEvent event) {
		UploadedFile arquivo = event.getFile();
		ArquivoUtil carregamento = new ArquivoUtil();

		try {
			arquivoCarregado = carregamento.escrever(arquivo.getFileName(), arquivo.getContents());
			adicionarArquivo(arquivoCarregado);

		} catch (IOException ex) {
			FacesUtil.addMsgErro("Ocorreu um erro no upload dos arquivos " + ex.getMessage());
		}
	}

	public void adicionarArquivo(File arquivoTemp) {

		if (arquivoCadastro == null) {
			arquivoCadastro = new Arquivo();
		} else {
			arquivoCadastro.setNome(arquivoTemp.getName());
			arquivoCadastro.setCaminho(arquivoTemp.getPath());
		}
		if (arquivos == null) {
			arquivos = new HashSet<Arquivo>();
		}
		//arquivoCadastro.setId((long) arquivos.size() + 1);
		arquivos.add(arquivoCadastro);
		arquivoCadastro = new Arquivo();
	}

	public void removerArquivo(Arquivo arquivo) {
		for(Iterator<Arquivo> i = arquivos.iterator(); i.hasNext();){
			Arquivo arq = i.next();
			if(arq.equals(arquivo)){
				ArquivoDAO arqdao = new ArquivoDAO();
				arqdao.excluir(arquivo);
			}
		}
	}
	/**
	 * Realiza o Download do arquivo selecionado
	 * @param arquivoTemp
	 */
	public void visualizarArquivo(Arquivo arquivoTemp){
		for(Iterator<Arquivo> i = arquivos.iterator(); i.hasNext();){
			Arquivo arq = i.next();
			if(arq.equals(arquivoTemp)){
				DownloadArquivoUtil downloadArquivo = new DownloadArquivoUtil();
				arquivoVisualizar = downloadArquivo.visualizarArquivo(arquivoTemp.getCaminho(),
						"application/pdf");
			}
		}
	}

	public void adicionarAtividades() {

		Atividade atvTemp = new Atividade();
		atvTemp = atividade;
		if (atividades == null) {
			atividades = new HashSet<Atividade>();
		}
		if (atividade == null) {
			atividade = new Atividade();
		}
		//atvTemp.setId((long) atividades.size() + 1);
		atividades.add(atvTemp);
		atividade = new Atividade();
	}
	
	public void editarAtividades(Atividade atividadeTemp){		
		AtividadeDAO atvdao = new AtividadeDAO();
		for (Iterator<Atividade> i = atividades.iterator(); i.hasNext();) {
			Atividade tv = i.next();
			if (tv.equals(atividadeTemp)) {
				i.remove();				
				atvdao.editar(tv);
			}
		}
		
	}

	public void removerAtividades(Atividade atvtemp) {
		AtividadeDAO atvdao = new AtividadeDAO();
		for (Iterator<Atividade> i = atividades.iterator(); i.hasNext();) {
			Atividade tv = i.next();
			if (tv.equals(atvtemp)) {
				i.remove();				
				atvdao.excluir(tv);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public Date getPegaDataAtual() {
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);

		return date;
	}
}
