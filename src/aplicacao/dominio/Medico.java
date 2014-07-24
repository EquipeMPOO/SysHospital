package aplicacao.dominio;

public class Medico extends Funcionario {
	
	private int numeroDeRegistro;
	private String especialidade;
	
	public int getNumeroDeRegistro() {
		return numeroDeRegistro;
	}
	public void setNumeroDeRegistro(int numeroderegistro) {
		this.numeroDeRegistro = numeroderegistro;
	}
	
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
}
 
