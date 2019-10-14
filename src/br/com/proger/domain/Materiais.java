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
@Table(name = "tbl_materiais")
public class Materiais {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	@Column(name = "id_materiais")
	private Long id;
	
	@NotEmpty(message = "o campo Descriçaõ é obrigatório")
	@Size(min = 5, max = 1024, message= "A descrição deve ter entre 5 e 1024 caracteres")
	@Column(name = "descricao", length = 1024, nullable = false)
	private String descricao;
	
	@NotNull(message="o campo quantidade é obrigatorio.")
	@DecimalMin(value="0.00", message="o campo quantidade deve ser maior do que 0.00")
	@Digits(integer = 5, fraction = 2, message = "coloque um quantidade")
	@Column(name = "quantidade", precision = 7, scale = 2, nullable = false)
	private BigDecimal quantidade;
	
	@NotNull(message="o campo valor unitário é obrigatorio.")
	@DecimalMin(value="0.00", message="o campo valor unitário deve ser maior do que 0.00")
	@Digits(integer = 5, fraction = 2, message = "coloque um valor válido")
	@Column(name = "valor_unitario", precision = 7, scale = 2, nullable = false)
	private BigDecimal valorUnitario;
		

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	
	@Override
	public String toString() {
		return "Materiais [id=" + id + ", descricao=" + descricao + ", quantidade=" + quantidade + ", valorUnitario="
				+ valorUnitario + "]";
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
		Materiais other = (Materiais) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
