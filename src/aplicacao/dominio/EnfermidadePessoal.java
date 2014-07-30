package aplicacao.dominio;

public class EnfermidadePessoal {
	
	private int idEnfermidadePessoal;
	private String comentario;
	private String statusDeDoenca;
	private Enfermidade enfermidade;
	
	public int getIdEnfermidadePessoal() {
		return idEnfermidadePessoal;
	}
	public void setIdEnfermidadePessoal(int idEnfermidadePessoal) {
		this.idEnfermidadePessoal = idEnfermidadePessoal;
	}
	
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public String getStatusDeDoenca() {
		return statusDeDoenca;
	}
	public void setStatusDeDoenca(String statusDeDoenca) {
		this.statusDeDoenca = statusDeDoenca;
	}
	
	public Enfermidade getEnfermidade() {
		return enfermidade;
	}
	public void setEnfermidade(Enfermidade enfermidade) {
		this.enfermidade = enfermidade;
	}

}
