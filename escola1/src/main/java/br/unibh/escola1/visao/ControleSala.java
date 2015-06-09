package br.unibh.escola1.visao;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.unibh.escola1.entidades.Sala;
import br.unibh.escola1.negocio.ServicoSala;

@ManagedBean(name = "salamb")
@ViewScoped
public class ControleSala {
	@Inject
	private Logger log;
	@Inject
	private ServicoSala sa;
	private Sala sala;
	private String nomeArg;
	private Long id;
	private List<Sala> salaes;

	public void pesquisar() {
		try {
			salaes = sa.findByCapacidade(Integer.parseInt(nomeArg));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void novo() {
		sala = new Sala();
	}

	public void cancelar() {
		sala = null;
	}

	public void editar() {
		try {
			sala = sa.find(id);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		sala = null;
	}

	public void excluir() {
		try {
			sa.delete(sa.find(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		sala = null;
	}

	public void gravar() {
		FacesMessage facesMsg;
		try {
			
			if (sala.getCodigo() == null) {
				sala = sa.insert(sala);
			} else {
				sala = sa.update(sala);
			}
		} catch (Exception e) {
			facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:"
					+ e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("messagePanel",
					facesMsg);
			return;
		}
		facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Sala gravado com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage("messagePanel", facesMsg);
	}

	@PostConstruct
	public void inicializaLista() {
		log.info("Executando o MB de Sala");
		try {
			salaes = sa.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Sala getSala() {
		return sala;
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

	public List<Sala> getSalaes() {
		return salaes;
	}
}