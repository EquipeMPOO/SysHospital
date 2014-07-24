package aplicacao.dominio;

/**
 * 
 * @author icaro
 * Classe que representa todas as pessoas físicas do sistema
 */

public class Pessoa {
	
	private int idPessoa;
	private String nome;
	private String cpf;
	private int idade;
	private String tipoSanguineo;
	private String sexo;
	private String statusDePessoa;

	/*
	 * O sistema tem quatro tipos de funcionarios. Cada pessoa pode ter 0 ou 1 deles. São eles:
	 * Administrador, Atendente, Enfermeiro e Medico.
	 */
	
	public int getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String getTipoSanguineo() {
		return tipoSanguineo;
	}
	public void setTipoSanguineo(String tiposanguineo) {
		this.tipoSanguineo = tiposanguineo;
	}
	
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getStatusDePessoa() {
		return statusDePessoa;
	}
	public void setStatusDePessoa(String statusdepessoa) {
		this.statusDePessoa = statusdepessoa;
	}
	
}
 
