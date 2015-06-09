package br.unibh.escola1.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TB_SALA")
@NamedQueries({
	@NamedQuery(name="Sala.findByCapacidade", query="SELECT s FROM Sala s WHERE s.capacidade >= :capacidade")
})
public class Sala implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@NotNull
	@Size(max = 10)
	@Column(length = 10, nullable = false, columnDefinition = "VARCHAR(10)")
	private String codigo;

	@NotNull
	@Column(nullable = false)
	private int capacidade;
	
	@Column(nullable = true)
	private boolean possuiQuadroBranco;
	
	@Column(nullable = true)
	private boolean possuiDataShow;
	
	@Column(nullable = true)
	private boolean possuiComputador;
	
	@Size(max = 255)
	@Column(length = 255, nullable = true, columnDefinition = "VARCHAR(255)")
	private String observacao;
	
	@Max(value = 3)
	@Min(value = 1)
	@Column
	private int status;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "data_termino_manutencao", nullable = true)
	private Date dataTerminoManutencao;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="sala")
	private List<Disciplina> disciplinas;
	
	public Sala() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public boolean isPossuiQuadroBranco() {
		return possuiQuadroBranco;
	}

	public void setPossuiQuadroBranco(boolean possuiQuadroBranco) {
		this.possuiQuadroBranco = possuiQuadroBranco;
	}

	public boolean isPossuiDataShow() {
		return possuiDataShow;
	}

	public void setPossuiDataShow(boolean possuiDataShow) {
		this.possuiDataShow = possuiDataShow;
	}

	public boolean isPossuiComputador() {
		return possuiComputador;
	}

	public void setPossuiComputador(boolean possuiComputador) {
		this.possuiComputador = possuiComputador;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getDataTerminoManutencao() {
		return dataTerminoManutencao;
	}

	public void setDataTerminoManutencao(Date dataTerminoManutencao) {
		this.dataTerminoManutencao = dataTerminoManutencao;
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", codigo=" + codigo + ", capacidade="
				+ capacidade + ", possuiQuadroBranco=" + possuiQuadroBranco
				+ ", possuiDataShow=" + possuiDataShow + ", possuiComputador="
				+ possuiComputador + ", observacao=" + observacao + ", status="
				+ status + ", dataTerminoManutencao=" + dataTerminoManutencao
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacidade;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime
				* result
				+ ((dataTerminoManutencao == null) ? 0 : dataTerminoManutencao
						.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + (possuiComputador ? 1231 : 1237);
		result = prime * result + (possuiDataShow ? 1231 : 1237);
		result = prime * result + (possuiQuadroBranco ? 1231 : 1237);
		result = prime * result + status;
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
		Sala other = (Sala) obj;
		if (capacidade != other.capacidade)
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dataTerminoManutencao == null) {
			if (other.dataTerminoManutencao != null)
				return false;
		} else if (!dataTerminoManutencao.equals(other.dataTerminoManutencao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (possuiComputador != other.possuiComputador)
			return false;
		if (possuiDataShow != other.possuiDataShow)
			return false;
		if (possuiQuadroBranco != other.possuiQuadroBranco)
			return false;
		if (status != other.status)
			return false;
		return true;
	}



}