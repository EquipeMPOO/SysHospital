package aplicacao.dominio;

import java.util.Date;
import java.util.List;

public class SituacaoPaciente {
 
	private String estado;
	private Date data;
	private List<Medico> medico;
	private List<Enfermeiro> enfermeiro;
	private CondicaoFisica condicaofisica;
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public List<Medico> getMedico() {
		return medico;
	}
	public void setMedico(List<Medico> medico) {
		this.medico = medico;
	}
	
	public List<Enfermeiro> getEnfermeiro() {
		return enfermeiro;
	}
	public void setEnfermeiro(List<Enfermeiro> enfermeiro) {
		this.enfermeiro = enfermeiro;
	}
	
	public CondicaoFisica getCondicaoFisica() {
		return condicaofisica;
	}
	public void setCondicaoFisica(CondicaoFisica condicaofisica) {
		this.condicaofisica = condicaofisica;
	}
}
 
