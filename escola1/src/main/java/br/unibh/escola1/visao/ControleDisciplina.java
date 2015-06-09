package br.unibh.escola1.visao;

import java.util.List; 
import java.util.logging.Logger;  

import javax.annotation.PostConstruct; 
import javax.faces.application.FacesMessage; 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped; 
import javax.faces.context.FacesContext; 
import javax.inject.Inject;  

import br.unibh.escola1.entidades.Aluno;
import br.unibh.escola1.entidades.Disciplina;
import br.unibh.escola1.entidades.Professor;
import br.unibh.escola1.entidades.Sala;
import br.unibh.escola1.negocio.ServicoAluno;  
import br.unibh.escola1.negocio.ServicoDisciplina;
import br.unibh.escola1.negocio.ServicoProfessor;
import br.unibh.escola1.negocio.ServicoSala;

@ManagedBean(name = "disciplinamb") 
@ViewScoped 
public class ControleDisciplina {  

	@Inject  
	private Logger log;  

	@Inject
	private ServicoDisciplina sd;
	
	@Inject
	private ServicoSala ss;
	
	@Inject
	private ServicoAluno sa;
	
	@Inject
	private ServicoProfessor sp;

	private Disciplina disciplina;
	private Long id; 

	private String nomeArg; 
	private String cursoArg; 

	private List<Disciplina> disciplinas;

	public Disciplina getDisciplina() {  
		return disciplina; 
	}  

	public String getNomeArg() {
		return nomeArg;
	}

	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}

	public String getCursoArg() {
		return cursoArg;
	}

	public void setCursoArg(String cursoArg) {
		this.cursoArg = cursoArg;
	}

	public Long getId() {  
		return id;
	}  

	public void setId(Long id) {  
		this.id = id; 
	}  

	public List<Disciplina> getDisciplinas() {  
		return disciplinas; 
	}

	public List<Sala> getSalas() throws Exception {
		return ss.findAll();
	}

	public List<Professor> getProfessores() throws Exception {
		return sp.findAll();
	}
	
	public List<Aluno> getAlunos() throws Exception {
		return sa.findAll();
	}

	@PostConstruct
	public void inicializaLista(){
		log.info("Executando o MB de Disciplinas");

		try {  
			disciplinas = sd.findAll(); 
		} 
		catch (Exception e) { 
			e.printStackTrace();  
		} 
	}  

	public void gravar() { 
		FacesMessage facesMsg; 

		try { 
			if (disciplina.getId() == null) { 
				disciplina = sd.insert(disciplina);
				disciplinas.add(disciplina);
			} else
			{ 
				disciplina = sd.update(disciplina);
				inicializaLista();
			} 
		} catch (Exception e){  
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: "+e.getMessage(), "");   
			FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);  
			return;  
		} 

		facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,  "Disciplina salva com sucesso!", "");  
		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg); 
	} 

	public void pesquisar() { 
		try { 
			disciplinas = sd.findByNomeECurso(nomeArg, cursoArg); 
		}
		catch (Exception e) {  
			e.printStackTrace();  
		}
	}

	public void novo(){ 
		disciplina = new Disciplina();  
	}

	public void cancelar(){ 
		disciplina = null; 
	} 

	public void editar(){ 
		try { 
			disciplina = sd.find(id); 
			return; 
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
		disciplina = null;  
	} 

	public void excluir(){ 
		try { 
			sd.delete(sd.find(id)); 
			inicializaLista();
		} 
		catch (Exception e) {   
			e.printStackTrace(); 
		}  
		disciplina = null; 
	}
	
	
	public String getTipo(int s) {
		switch(s) {
		case 1:
			return "Presencial";
		case 2:
			return "À distância";
		case 3:
			return "Presencial e à distância";
		default:
			return " ";
		}
	}
}