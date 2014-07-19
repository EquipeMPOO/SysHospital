package aplicacao.dominio;

import java.util.Date;
import java.util.List;

import aplicacao.enums.StatusDeEntrada;

public class Entrada {
 
	private int codigo;
	private String descricao;
	private List<Date> data;
	private Paciente paciente;
	private StatusDeEntrada statusdeentrada;
	private Prontuario prontuario;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<Date> getData() {
		return data;
	}
	public void setData(List<Date> data) {
		this.data = data;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public StatusDeEntrada getStatusDeEntrada() {
		return statusdeentrada;
	}
	public void setStatusDeEntrada(StatusDeEntrada statusdeentrada) {
		this.statusdeentrada = statusdeentrada;
	}
	
	public Prontuario getProntuario() {
		return prontuario;
	}
	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}
}
 
