package aplicacao.dominio;


public class UsuarioAtivo {
	
	private int idUsuarioAtivo;
	private Funcionario funcionario;
	

	public int getIdUsuarioAtivo() {
		return idUsuarioAtivo;
	}
	public void setIdUsuarioAtivo(int idUsuarioAtivo) {
		this.idUsuarioAtivo = idUsuarioAtivo;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}
