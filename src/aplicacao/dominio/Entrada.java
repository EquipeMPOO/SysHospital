package aplicacao.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import aplicacao.enums.StatusDeEntrada;

public class Entrada {
 
	private int codigo;
	private String descricao;
	private Date dataEntrada;
	private Date dataSaida;
	private Paciente paciente;
	private StatusDeEntrada statusdeentrada;
	private ArrayList<Atendimento> situacaoDePaciente;
	
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
	public Date getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public Date getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	public StatusDeEntrada getStatusdeentrada() {
		return statusdeentrada;
	}
	public void setStatusdeentrada(StatusDeEntrada statusdeentrada) {
		this.statusdeentrada = statusdeentrada;
	}
	public ArrayList<Atendimento> getSituacaoDePaciente() {
		return situacaoDePaciente;
	}
	public void setSituacaoDePaciente(ArrayList<Atendimento> situacaoDePaciente) {
		this.situacaoDePaciente = situacaoDePaciente;
	}
	
	
}
 
