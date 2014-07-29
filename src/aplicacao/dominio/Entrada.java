package aplicacao.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Entrada {
 
	private int idEntrada;
	private String dataEntrada;
	private String dataSaida;
	private String statusdeentrada;
	private ArrayList<Atendimento> atendimentos;
	
	public int getIdEntrada() {
		return idEntrada;
	}
	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}
	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}
	public String getStatusdeentrada() {
		return statusdeentrada;
	}
	public void setStatusdeentrada(String statusdeentrada) {
		this.statusdeentrada = statusdeentrada;
	}
	public ArrayList<Atendimento> getAtendimentos() {
		return atendimentos;
	}
	public void setAtendimentos(ArrayList<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}
	
}
 
