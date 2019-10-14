package br.com.proger.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_acao", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id_acao"),
        @UniqueConstraint(columnNames = "registro")})
@NamedQueries({
	@NamedQuery(name = "Acao.listar", query = "SELECT acao FROM Acao acao"),
	@NamedQuery(name = "Acao.buscarPorCodigo", query = "SELECT acao FROM Acao acao WHERE acao.id =:codigo")	
})
public class Acao implements Serializable {

	private static final long serialVersionUID = -1798070786993154676L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_acao")
	private Long id;
	
	@NotEmpty(message = "o campo Código é obrigatório")
	@Size(min = 1, max = 50, message= "Código deve ter entre 1 e 50 caracteres")
	@Column(name = "registro", length = 50, nullable = false)
	private String registro;
	
	@NotEmpty(message = "o campo Título é obrigatório")
	@Size(min = 5, max = 50, message= "Título deve ter entre 5 e 50 caracteres")
	@Column(name = "titulo", length = 50, nullable = false)
	private String titulo;
	
	@NotNull(message = "o campo data de cadastro é obrigatório")
	@Temporal(value = TemporalType.DATE)
	@Column(name = "dt_cadastro", nullable = false)
	private Date dataCadastro;
	
	@NotNull(message = "o campo data de realização é obrigatório")
	@Temporal(value = TemporalType.DATE)
	@Column(name = "dt_realizacao", nullable = false)
	private Date dataRealizacao;	
	
	@NotEmpty(message = "o campo Objetivo de atuação é obrigatório")
	@Size(min = 5, max = 50, message= "Objetivo de atuação deve ter entre 5 e 50 caracteres")
	@Column(name = "obj_atuacao", length = 50, nullable = false)
	private String objetivoAtuacao;
	
	@NotEmpty(message = "o campo Objetivos é obrigatório")
	@Size(min = 5, max = 1024, message= "Objetivos deve ter entre 5 e 1024 caracteres")
	@Column(name = "objetivos", length = 1024, nullable = false)
	private String objetivos;	
	
	@Column(name = "avaliacao", length = 20)
	private String avaliacao;
	
	@Column(name = "observacao", length = 2048)
	private String observacao;
	
	@NotNull(message = "o campo Usuário é obrigatório")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_fk", referencedColumnName = "cod_fun", nullable = false)
	private Funcionario funcionario;
	
	@NotNull(message = "o campo Órgão é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orgao_fk", referencedColumnName = "cod_orgao", nullable = false)
	private Orgao orgao;
	
	@ManyToMany(cascade=CascadeType.REMOVE)
    @JoinTable(name="acao_arquivo", joinColumns={@JoinColumn(referencedColumnName="id_acao")}
                                        , inverseJoinColumns={@JoinColumn(referencedColumnName="id_arquivo")}) 
    private Set<Arquivo> arquivos;
	
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="acao_atividade", joinColumns={@JoinColumn(referencedColumnName="id_acao")}
                                        , inverseJoinColumns={@JoinColumn(referencedColumnName="id_atividade")}) 
    private Set<Atividade> atividades;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public String getObjetivoAtuacao() {
		return objetivoAtuacao;
	}

	public void setObjetivoAtuacao(String objetivoAtuacao) {
		this.objetivoAtuacao = objetivoAtuacao;
	}

	public String getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}

	public String getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}		

	public Set<Arquivo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(Set<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}
	
	public Set<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(Set<Atividade> atividades) {
		this.atividades = atividades;
	}

	@Override
	public String toString() {
		return "Acao [id=" + id + ", titulo=" + titulo + ", dataCadastro=" + dataCadastro + ", dataRealizacao="
				+ dataRealizacao + ", objetivoAtuacao=" + objetivoAtuacao + ", objetivos=" + objetivos + ", avaliacao="
				+ avaliacao + ", observacao=" + observacao + ", funcionario=" + funcionario + ", orgao=" + orgao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acao other = (Acao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
