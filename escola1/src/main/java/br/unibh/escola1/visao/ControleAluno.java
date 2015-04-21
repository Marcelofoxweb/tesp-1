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
import br.unibh.escola1.negocio.ServicoAluno;

@ManagedBean(name = "alunomb")
@ViewScoped
public class ControleAluno {
	@Inject
	private Logger log;
	@Inject
	private ServicoAluno sa;
	private Aluno aluno;
	private String nomeArg;
	private Long id;
	private List<Aluno> alunoes;

	public Aluno getAluno() {
		return aluno;
	}

	public String getNomeArg() {
		return nomeArg;
	}

	public void setNomeArg(String nomeArg) {
		this.nomeArg = nomeArg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Aluno> getAlunoes() {
		return alunoes;
	}

	@PostConstruct
	public void inicializaLista() {
		log.info("Executando o MB de Aluno");
		try {
			alunoes = sa.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gravar() {
		FacesMessage facesMsg;
		try {
			if (aluno.getId() == null) {
				aluno = sa.insert(aluno);
			} else {
				aluno = sa.update(aluno);
			}
		} catch (Exception e) {
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: "
					+ e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("messagePanel",
					facesMsg);
			return;
		}
		facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Aluno gravado com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
	}

	public void pesquisar() {
		try {
			alunoes = sa.findByName(nomeArg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void novo() {
		aluno = new Aluno();
	}

	public void cancelar() {
		aluno = null;
	}

	public void editar() {
		try {
			aluno = sa.find(id);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		aluno = null;
	}

	public void excluir() {
		try {
			sa.delete(sa.find(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		aluno = null;
	}
}