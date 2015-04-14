package br.unibh.escola.entidades;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_PROFESSOR")
public class Professor extends Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotNull
	@DecimalMin(value = "500.00")
	@DecimalMax(value = "50000.00")
	@Column(nullable = false)
	private BigDecimal salario;
	
	public static final Double BONUS = 0.1D;
	
	public Professor() {
		
	}
	
	public Professor(Long id, String nome, String cpf) {
		super(id, nome, cpf);
	}

	public Professor(Long id, String nome, String cpf, BigDecimal salario) {
		super(id, nome, cpf);
		this.salario = salario;
	}
	
	public BigDecimal getSalario() {
		return salario;
	}
	
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return super.toString() + "\nProfessor [salario=" + salario + "]";
	}
	
}