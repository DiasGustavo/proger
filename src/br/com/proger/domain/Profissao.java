package br.com.proger.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_profissao")
@NamedQueries({
	@NamedQuery(name = "Profissao.listar", query = "SELECT profissao FROM Profissao profissao"),
	@NamedQuery(name = "Profissao.buscarPorCodigo", query = "SELECT profissao FROM Profissao profissao WHERE profissao.id =:codigo")
})
public class Profissao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_profissao")
	private Long id;
	
	@NotEmpty(message = "o campo Nome é obrigatório")
	@Size(min = 1, max = 30, message= "Nome deve ter entre 1 e 30 caracteres")
	@Column(name = "nome", length = 30, nullable = false)
	private String nome;
	
	@Column(name = "registro", length = 50)
	private String registro;
	
	@Column(name = "especialidade", length = 50)
	private String especialidade;
	
	@Column(name = "observacao", length = 200)
	private String observacao;

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

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return  nome +", " + especialidade;
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
		Profissao other = (Profissao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
