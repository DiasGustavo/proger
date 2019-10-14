package br.com.proger.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "tbl_pessoa_fisica")
@NamedQueries({
	@NamedQuery(name = "PessoaFisica.listar", query = "SELECT pessoaFisica FROM PessoaFisica pessoaFisica"),
	@NamedQuery(name = "PessoaFisica.buscarPorCodigo", query = "SELECT pessoaFisica FROM PessoaFisica pessoaFisica WHERE pessoaFisica.id = :codigo")
})
public class PessoaFisica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_pessoa_fisica")
	private Long id;
	
	@NotEmpty(message = "o campo Nome é obrigatório")
	@Size(min = 1, max = 14, message= "Nome deve ter entre 1 e 14 caracteres")
	@Column(name = "nome", length = 14, nullable = false)
	private String nome;
	
	@NotEmpty(message = "o campo CPF é obrigatório")
	@CPF(message = "O CPF informado não é válido")
	@Column(name = "cpf", length = 14, nullable = false, unique = true)
	private String cpf;
	
	@NotEmpty(message = "o campo RG é obrigatório")
	@Size(min = 1, max = 14, message= "RG deve ter entre 1 e 14 caracteres")
	@Column(name = "rg", length = 14, nullable = false)
	private String rg;
	
	@Column(name = "pai", length = 50)
	private String pai;
	
	@Column(name = "mae", length = 50)
	private String mae;
	
	@Column(name = "telefone", length = 13)
	private String telefone;
	
	@Column(name = "celular", length = 15)
	private String celular;
	
	@NotEmpty(message = "o campo email é obrigatório")
	@Email(message = "Email informado não é válido")
	@Column(name = "email", length = 50)
	private String email;
	
	@NotEmpty(message = "o campo sexo é obrigatório")
	@Column(name = "sexo", length = 1)
	private String sexo;
	
	@Column(name = "observacao", length = 50)
	private String observacao;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "dt_nascimento")
	private Date dataNascimento;
	
	@NotNull(message = "o campo Endereço é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_endereco", referencedColumnName = "id_endereco", nullable = false)
	private Endereco endereco;
	
	@NotNull(message = "o campo Profissão é obrigatório")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_profissao", referencedColumnName = "id_profissao", nullable = false)
	private Profissao profissao;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}	

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	@Override
	public String toString() {
		return "PessoaFisica [id=" + id + ", cpf=" + cpf + ", rg=" + rg + ", pai=" + pai + ", mae=" + mae
				+ ", telefone=" + telefone + ", celular=" + celular + ", email=" + email + ", sexo=" + sexo
				+ ", observacao=" + observacao + ", dataNascimento=" + dataNascimento + "]";
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
		PessoaFisica other = (PessoaFisica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
