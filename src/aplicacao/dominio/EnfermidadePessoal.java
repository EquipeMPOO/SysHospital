package aplicacao.dominio;

import aplicacao.enums.*;

public class EnfermidadePessoal {
	
	private String comentario;
	private StatusDeDoenca statusdedoenca;
	private Enfermidade enfermidade;
	
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public StatusDeDoenca getStatusDeDoenca() {
		return statusdedoenca;
	}
	public void setStatusDeDoenca(StatusDeDoenca statusdedoenca) {
		this.statusdedoenca = statusdedoenca;
	}
	
	public Enfermidade getEnfermidade() {
		return enfermidade;
	}
	public void setEnfermidade(Enfermidade enfermidade) {
		this.enfermidade = enfermidade;
	}

}
