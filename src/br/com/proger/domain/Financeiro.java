package br.com.proger.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_financeiro")
public class Financeiro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_financeiro")
	private Long id;
	
	@NotEmpty(message = "o campo Entidade é obrigatório")
	@Size(min = 5, max = 50, message= "A Entidade deve ter entre 5 e 50 caracteres")
	@Column(name = "entidade", length = 50, nullable = false)
	private String entidade;
	
	@NotNull(message="o campo valor é obrigatorio.")
	@DecimalMin(value="0.00", message="o campo valor deve ser maior do que 0.00")
	@Digits(integer = 5, fraction = 2, message = "coloque um valor válido")
	@Column(name = "valor", precision = 7, scale = 2, nullable = false)
	private BigDecimal valor;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEntidade() {
		return entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	@Override
	public String toString() {
		return "Financeiro [id=" + id + ", entidade=" + entidade + ", valor=" + valor + "]";
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
		Financeiro other = (Financeiro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
