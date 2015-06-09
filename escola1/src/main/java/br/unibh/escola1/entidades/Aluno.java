package br.unibh.escola1.entidades;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "matricula"), name = "TB_ALUNO")

@NamedQueries({
	@NamedQuery(name="Aluno.findByName", query="SELECT a FROM Aluno a WHERE a.nome LIKE :nome")
})
public class Aluno extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Long matricula;
	
	@NotNull
	@Temporal(value = TemporalType.DATE)
	@Column(name = "data_aniversario", nullable = false)
	private Date dataAniversario;
	
	@ManyToMany(mappedBy = "alunos")
	private List<Disciplina> disciplinas;
	
	public Aluno(){
		super(null, null, null);
	}
	
	

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Date getDataAniversario() {
		return dataAniversario;
	}
	
	public void setDataAniversario(Date dataAniversario) {
		this.dataAniversario = dataAniversario;
	}
	
	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
	
	public static boolean verificaMatricula(Long matricula) {
		if(matricula == null || matricula.toString().length() != 8)
			return false;
		
		return true;
	}



	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", dataAniversario="
				+ dataAniversario + ", toString()=" + super.toString() + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dataAniversario == null) ? 0 : dataAniversario.hashCode());
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
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
		Aluno other = (Aluno) obj;
		if (dataAniversario == null) {
			if (other.dataAniversario != null)
				return false;
		} else if (!dataAniversario.equals(other.dataAniversario))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}


	
}