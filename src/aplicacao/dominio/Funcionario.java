package aplicacao.dominio;

/**
 * 
 * @author icaro
 * Classe abastrata que agrupa todos os funcionarios do sistema
 * Possui todos os dados comuns a todos os trabalhadores do software, além de um atributo da classe pessoa
 */

public abstract class Funcionario {
	
	private int idFuncionario;
	private String login;
	private String senha;
	private String statusDeUsuario;
	private Pessoa pessoa;
	
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
 
