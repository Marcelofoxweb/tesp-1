package br.unibh.escola1.entidades;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@PrimaryKeyJoinColumn
@Table(name = "TB_ALUNO", uniqueConstraints = @UniqueConstraint(columnNames = "matricula"))
@NamedQueries({ @NamedQuery(name="Aluno.findByName", query = "select a from Aluno a where a.nome like :nome")
})

public class Aluno extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(nullable = false)
	private Long matricula;
	
	@NotNull
	@Temporal(value = TemporalType.DATE)
	@Column(name = "data_aniversario", nullable = false)
	private Date dataAniversario;
	
	public Aluno() {
		
	}
	
	public Aluno(Long id, Long matricula, String nome) {
		super(id, nome, null);
		this.matricula = matricula;
	}

	public Aluno(Long id, Long matricula, String nome, String cpf) {
		super(id, nome, cpf);
		this.matricula = matricula;
	}
	
	public Aluno(Long id, Long matricula, String nome, String cpf, Date dataAniversario) {
		super(id, nome, cpf);
		this.matricula = matricula;
		this.dataAniversario = dataAniversario;
	}

	public Long getMatricula() {
		return matricula;
	}
	
	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
	
	public Date getDataAniversario() {
		return dataAniversario;
	}
	
	public void setDataAniversario(Date dataAniversario) {
		this.dataAniversario = dataAniversario;
	}
	
	public static boolean verificaMatricula(Long matricula) {
		if(matricula == null || matricula.toString().length() != 8)
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "Aluno [dataAniversario=" + dataAniversario + ", matricula="
				+ matricula + "]"
				+ super.toString();
	}
	

}