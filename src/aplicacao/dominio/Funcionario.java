package aplicacao.dominio;

public abstract class Funcionario {
	
	private int numeroDeRegistro;
	private int idFuncionario;
	private String login;
	private String senha;
	private String identificadorInterno;
	private String statusDeUsuario;
	private Pessoa pessoa;
	
	
	public int getNumeroDeRegistro() {
		return numeroDeRegistro;
	}
	public void setNumeroDeRegistro(int numeroDeRegistro) {
		this.numeroDeRegistro = numeroDeRegistro;
	}
	public int getIdFuncionario() {
		return idFuncionario;
	}
	
	public void setIdFuncionario(int idfuncionario) {
		this.idFuncionario = idfuncionario;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String usuario) {
		this.login = usuario;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getIdentificadorInterno() {
		return identificadorInterno;
	}
	public void setIdentificadorInterno(String identificadorinterno) {
		this.identificadorInterno = identificadorinterno;
	}
	
	public String getStatusDeUsuario() {
		return statusDeUsuario;
	}
	public void setStatusDeUsuario(String statusdeusuario) {
		this.statusDeUsuario = statusdeusuario;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
 
