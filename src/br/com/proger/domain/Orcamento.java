package br.com.proger.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_orcamento")
public class Orcamento {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	@Column(name = "id_orcamento")
	private Long id;
	
	@NotNull(message = "o campo data de cadastro é obrigatório")
	@Temporal(value = TemporalType.DATE)
	@Column(name = "dt_cadastro", nullable = false)
	private Date dataCadastro;
	
	@NotNull(message = "o campo data de modificação é obrigatório")
	@Temporal(value = TemporalType.DATE)
	@Column(name = "dt_modificacao", nullable = false)
	private Date dataModificacao;
	
	@NotNull(message="o campo valor total é obrigatorio.")
	@DecimalMin(value="0.00", message="o campo valor total deve ser maior do que 0.00")
	@Digits(integer = 5, fraction = 2, message = "coloque um valor válido")
	@Column(name = "valor_total", precision = 7, scale = 2, nullable = false)
	private BigDecimal valorTotal;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "financeiro_fk", referencedColumnName = "id_financeiro")
	private Financeiro financeiro;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "materiais_fk", referencedColumnName = "id_materiais")
	private Materiais materiais;
	
	@NotNull(message = "O campo ação é obrigatório")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "acao_fk", referencedColumnName = "id_acao", nullable = false)
	private Acao acao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Financeiro getFinanceiro() {
		return financeiro;
	}

	public void setFinanceiro(Financeiro financeiro) {
		this.financeiro = financeiro;
	}

	public Materiais getMateriais() {
		return materiais;
	}

	public void setMateriais(Materiais materiais) {
		this.materiais = materiais;
	}

	@Override
	public String toString() {
		return "Orcamento [id=" + id + ", dataCadastro=" + dataCadastro + ", dataModificacao=" + dataModificacao
				+ ", valorTotal=" + valorTotal + ", acao=" + acao + "]";
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
		Orcamento other = (Orcamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
