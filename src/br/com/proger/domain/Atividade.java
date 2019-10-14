package br.com.proger.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_atividade", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id_atividade")})
@NamedQueries({
	@NamedQuery(name = "Atividade.listar", query = "SELECT atividade FROM Atividade atividade"),
	@NamedQuery(name = "Atividade.buscarPorCodigo", query = "SELECT atividade FROM Atividade atividade WHERE atividade.id =:codigo"),
	@NamedQuery(name = "Atividade.buscarAtividades", query = "SELECT atividade FROM Atividade atividade  JOIN atividade.acoes acao WHERE acao.id =:codigo")
})
public class Atividade implements Serializable{
	
	private static final long serialVersionUID = -6890693372846798580L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_atividade")
	private Long id;
	
	@NotEmpty(message = "o campo Descriçaõ é obrigatório")
	@Size(min = 5, max = 1024, message= "A descrição deve ter entre 5 e 1024 caracteres")
	@Column(name = "descricao", length = 1024, nullable = false)
	private String descricao;
	
	@NotNull(message = "o campo data de início é obrigatório")
	@Temporal(value = TemporalType.DATE)
	@Column(name = "dt_inicio", nullable = false)
	private Date dataInicio;	
	
	@NotNull(message = "o campo data de fim é obrigatório")
	@Temporal(value = TemporalType.DATE)
	@Column(name = "dt_fim", nullable = false)
	private Date dataFim;	
	
	@NotEmpty(message = "o campo status é obrigatório")
	@Size(min = 1, max = 20, message= "O status deve ter entre 1 caracteres")
	@Column(name="status", length=1, nullable=false)
	private String status;
	
	@ManyToMany(mappedBy="atividades")
    private Set<Acao> acoes;

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

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<Acao> getAcoes() {
		return acoes;
	}

	public void setAcoes(Set<Acao> acoes) {
		this.acoes = acoes;
	}

	@Override
	public String toString() {
		return "Atividade [id=" + id + ", descricao=" + descricao + ", dataInicio=" + dataInicio + ", dataFim="
				+ dataFim + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		Atividade other = (Atividade) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
}
