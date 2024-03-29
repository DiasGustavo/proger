package br.com.proger.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_orgao")
@NamedQueries({
	@NamedQuery(name = "Orgao.listar", query = "SELECT orgao FROM Orgao orgao"),
	@NamedQuery(name = "Orgao.buscarPorCodigo", query = "SELECT orgao FROM Orgao orgao WHERE orgao.id = :codigo"),
	@NamedQuery(name = "Orgao.buscarPorRegistro", query = "SELECT orgao FROM Orgao orgao WHERE orgao.registro = :registro AND :dataTeste BETWEEN '1900-01-01' AND orgao.dataVigencia")
})
public class Orgao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_orgao")
	private Long id;
	
	@NotEmpty(message = "o campo nome � obrigat�rio")
	@Size(min = 1, max = 50, message= "Nome deve ter entre 1 e 50 caracteres")
	@Column(name="nome", length=50, nullable=false)
	private String nome;
		
	@NotEmpty(message = "o campo reposit�rio � obrigat�rio")
	@Size(min = 1, max = 200, message= "reposit�rio deve ter entre 1 e 200 caracteres")
	@Column(name="repositorio", length=200, nullable=false)
	private String repositorio;
	
	@NotNull(message = "o campo endere�o � obrigat�rio")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="endereco_fk", referencedColumnName="id_endereco", nullable = false)
	private Endereco endereco;
	
	@NotNull(message = "O campo Pessoa Jur�dica � obrigat�rio")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fk_pessoa_juridica", referencedColumnName="id_pessoa_juridica", nullable = false)
	private PessoaJuridica pessoaJuridica;
	
	@NotNull(message = "o campo data de vig�ncia � obrigat�rio")
	@Temporal(value =TemporalType.DATE)
	@Column(name = "dt_vigencia", nullable = false)
	private Date dataVigencia;
	
	@NotEmpty(message = "o campo Registro � obrigat�rio")
	@Size(min = 6, max = 32, message= "Registro deve ter no m�nimo 6 caracteres")
	@Column(name = "registro", length = 50, nullable = false)
	private String registro;
	
	@NotEmpty(message = "o campo status � obrigat�rio")
	@Size(min = 1, max = 1, message= "O status deve ter entre 1 caracteres")
	@Column(name="status", length=1, nullable=false)
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(String repositorio) {
		this.repositorio = repositorio;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Date getDataVigencia() {
		return dataVigencia;
	}

	public void setDataVigencia(Date dataVigencia) {
		this.dataVigencia = dataVigencia;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	@Override
	public String toString() {
		return "Orgao [id=" + id + ", nome=" + nome + ", repositorio=" + repositorio + ", brasao="
				+ ", endereco=" + endereco + "]";
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
		Orgao other = (Orgao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
