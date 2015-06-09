package br.unibh.escola1.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_PROFESSOR")
@NamedQueries({
	@NamedQuery(name="Professor.findByName", query="SELECT p FROM Professor p WHERE p.nome LIKE :nome")
})
public class Professor extends Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotNull
	@DecimalMin(value = "500.00")
	@DecimalMax(value = "50000.00")
	@Column(nullable = false)
	private BigDecimal salario;
	
//	@OneToMany(mappedBy = "professor")
//	private List<Disciplina> disciplinas;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="professor")
	private List<Disciplina> disciplinas;
	
	
	public static final Double BONUS = 0.1D;
	
	public Professor() {
		super(null, null, null);
	}
	
	public BigDecimal getSalario() {
		return salario;
	}
	
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}


	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@Override
	public String toString() {
		return "Professor [salario=" + salario + ", disciplinas=" + disciplinas
				+ ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((salario == null) ? 0 : salario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		if (salario == null) {
			if (other.salario != null)
				return false;
		} else if (!salario.equals(other.salario))
			return false;
		return true;
	}

	
	
}