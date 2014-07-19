package aplicacao.dominio;

import java.util.Date;
import java.util.List;

public class AvaliacaoMedica {
 
	private String descricao;
	private Date data;
	private List<Medico> medico;
	private Prontuario prontuario;
	private Doenca doenca;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	
	public Prontuario getProntuario() {
		return prontuario;
	}
	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}
	
	public Doenca getDoenca() {
		return doenca;
	}
	public void setDoenca(Doenca doenca) {
		this.doenca = doenca;
	}
	
	
	
}
 
