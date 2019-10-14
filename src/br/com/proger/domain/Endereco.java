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
@Table(name = "tbl_endereco")
@NamedQueries({ 
	@NamedQuery(name = "Endereco.listar", query = "SELECT endereco FROM Endereco endereco"),
	@NamedQuery(name = "Endereco.buscarPorCodigo", query = "SELECT endereco FROM Endereco endereco WHERE endereco.id = :codigo")
})
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_endereco")
	private Long id;

	@NotEmpty(message = "o campo rua é obrigatório")
	@Size(min = 5, max = 50, message= "Rua deve ter entre 5 e 50 caracteres")
	@Column(name = "rua", length = 50, nullable = false)
	private String logradouro;

	@NotEmpty(message = "o campo cidade é obrigatório")
	@Size(min = 3, max = 50, message= "Cidade deve ter entre 3 e 50 caracteres")
	@Column(name = "cidade", length = 50, nullable = false)
	private String cidade;

	@NotEmpty(message = "o campo estado é obrigatório")
	@Size(min = 3, max = 50, message= "Estado deve ter entre 3 e 50 caracteres")
	@Column(name = "estado", length = 50, nullable = false)
	private String estado;

	@NotEmpty(message = "o campo bairro é obrigatório")
	@Size(min = 3, max = 50, message= "Bairro deve ter entre 3 e 50 caracteres")
	@Column(name = "bairro", length = 50, nullable = false)
	private String bairro;

	@Column(name = "complemento", length = 50)
	private String complemento;

	@Column(name = "numero", length = 50)
	private String numero;

	//@NotEmpty(message = "o campo CEP é obrigatório")
	//@Size(min = 1, max = 9, message= "CEP deve ter entre 1 e 9 caracteres")
	@Column(name = "cep", length = 9)
	private String cep;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return  logradouro + ", " + numero + ", " +  bairro + ", " + cep ;
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
