package aplicacao.dominio;

import java.util.List;

public class Prontuario {
 
	private int numero;
	private List<SituacaoPaciente> condicaodepaciente;
	private List<AvaliacaoMedica> avaliacaomedica;
	private String comentario;
	private Entrada entrada;
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public List<SituacaoPaciente> getCondicaoDePaciente() {
		return condicaodepaciente;
	}
	public void setCondicaoDePaciente(List<SituacaoPaciente> condicaodepaciente) {
		this.condicaodepaciente = condicaodepaciente;
	}
	
	public List<AvaliacaoMedica> getAvaliacaoMedica() {
		return avaliacaomedica;
	}
	public void setAvaliacaoMedica(List<AvaliacaoMedica> avaliacaomedica) {
		this.avaliacaomedica = avaliacaomedica;
	}
	
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public Entrada getEntrada() {
		return entrada;
	}
	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}
}
 
