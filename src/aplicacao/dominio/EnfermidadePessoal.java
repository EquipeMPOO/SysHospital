package aplicacao.dominio;

import aplicacao.enums.*;

public class EnfermidadePessoal {
	
	private int idEnfermidade;
	private String comentario;
	private String statusdedoenca;
	private Enfermidade enfermidade;	
	
	public int getIdEnfermidade() {
		return idEnfermidade;
	}
	public void setIdEnfermidade(int idEnfermidade) {
		this.idEnfermidade = idEnfermidade;
	}
	public String getStatusdedoenca() {
		return statusdedoenca;
	}
	public void setStatusdedoenca(String statusdedoenca) {
		this.statusdedoenca = statusdedoenca;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public String getStatusDeDoenca() {
		return statusdedoenca;
	}
	public void setStatusDeDoenca(String statusdedoenca) {
		this.statusdedoenca = statusdedoenca;
	}
	
	public Enfermidade getEnfermidade() {
		return enfermidade;
	}
	public void setEnfermidade(Enfermidade enfermidade) {
		this.enfermidade = enfermidade;
	}

}
