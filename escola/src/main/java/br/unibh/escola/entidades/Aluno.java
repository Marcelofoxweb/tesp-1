package br.unibh.escola.entidades;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "matricula"), name = "TB_ALUNO")
public class Aluno extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(min = 8, max = 8)
	@Pattern(regexp = "[0-9]*", message = "Informar apenas números.")
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
		return super.toString() + "\nAluno [matricula=" + matricula + ", dataAniversario="
				+ dataAniversario + "]";
	}

}