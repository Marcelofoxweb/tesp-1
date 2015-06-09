package br.unibh.escola1.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TB_DISCIPLINA")
@NamedQueries({
	@NamedQuery(name="Disciplina.findByNomeECurso", query="SELECT d FROM Disciplina d WHERE d.nome LIKE :nome or d.curso LIKE :curso")
})
public class Disciplina implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 100)
	@Pattern(regexp = "[A-Za-zÀ-ú ]*")
	@Column(length = 100, nullable = false)
	private String nome;

	

	@NotNull
	@Max(160)
	@Column(nullable = false)
	private int cargaHoraria;

	@NotNull
	@Min(1)
	@Max(3)
	@Column(nullable = false)
	private int tipo;
	
	@NotBlank
	@Size(max = 100)
	@Column(length = 100, nullable = false)
	private String curso;

	@NotBlank
	@Size(max = 4000)
	@Column(length = 4000, nullable = false)
	private String ementa;

	@NotBlank
	@Size(max = 4000)
	@Column(length = 4000, nullable = false)
	private String bibliografia;
	
	@NotBlank
	@Size(max = 4000)
	@Column(length = 4000, nullable =  false)
	private String distribuicaoAvaliacao;

	@Size(max = 4000)
	@Column(length = 4000, nullable = true)
	private String observacao;

	@NotNull
	@ManyToOne
	private Sala sala;

	@NotNull
	@ManyToOne
	private Professor professor;

	@NotNull
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TB_DISCIPLINA_ALUNO", joinColumns = {
			@JoinColumn(name = "DISCIPLINA_ID", nullable = false,
					updatable = false) },
				inverseJoinColumns = { @JoinColumn(name = "ALUNO_ID",
				nullable = false, updatable = false) })
	private List<Aluno> alunos;
	
	
	
	@Version
	private int versao;

	public Disciplina() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public int getTipo() {
		return tipo;
	}
	
	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getEmenta() {
		return ementa;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	public String getBibliografia() {
		return bibliografia;
	}

	public void setBibliografia(String bibliografia) {
		this.bibliografia = bibliografia;
	}

	public String getDistribuicaoAvaliacao() {
		return distribuicaoAvaliacao;
	}

	public void setDistribuicaoAvaliacao(String distribuicaoAvaliacao) {
		this.distribuicaoAvaliacao = distribuicaoAvaliacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Disciplina) {
			return this.getId() == ((Disciplina)obj).id;
		}
		return false;
	}
}