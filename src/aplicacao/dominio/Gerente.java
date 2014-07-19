package aplicacao.dominio;

public abstract class Gerente extends Funcionario {
 
	private String cargo;
	
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}
 
