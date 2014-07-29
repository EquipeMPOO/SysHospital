package aplicacao.dominio;

import java.util.ArrayList;
import java.util.Date;

public class Atendimento {
	
	private int idAtentimento;
	private String comentarioEnfermeiro;
	private String comentarioMedico;
	private double peso;
	private double altura;
	private String data;
	private ArrayList<EnfermidadePessoal> doencas;
	private Medico medico;
	private Enfermeiro enfermeiro;
	
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public int getIdAtentimento() {
		return idAtentimento;
	}
	public void setIdAtentimento(int idAtentimento) {
		this.idAtentimento = idAtentimento;
	}
	public String getComentarioEnfermeiro() {
		return comentarioEnfermeiro;
	}
	public void setComentarioEnfermeiro(String comentarioEnfermeiro) {
		this.comentarioEnfermeiro = comentarioEnfermeiro;
	}
	public String getComentarioMedico() {
		return comentarioMedico;
	}
	public void setComentarioMedico(String comentarioMedico) {
		this.comentarioMedico = comentarioMedico;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public ArrayList<EnfermidadePessoal> getDoencas() {
		return doencas;
	}
	public void setDoencas(ArrayList<EnfermidadePessoal> doencas) {
		this.doencas = doencas;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Enfermeiro getEnfermeiro() {
		return enfermeiro;
	}
	public void setEnfermeiro(Enfermeiro enfermeiro) {
		this.enfermeiro = enfermeiro;
	}
	
	

}
