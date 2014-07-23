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
	private Paciente paciente;
	private String statusDePessoa;
	private FuncionariosDePessoa funcionariosDePessoa;
	
	/*
	 * O sistema tem quatro tipos de funcionarios. Cada pessoa pode ter 0 ou 1 deles. São eles:
	 * Administrador, Atendente, Enfermeiro e Medico.
	 */
	private Administrador administrador;
	private Atendente atendente;
	private Enfermeiro enfermeiro;
	private Medico medico;
	
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
	
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public String getStatusDePessoa() {
		return statusDePessoa;
	}
	public void setStatusDePessoa(String statusdepessoa) {
		this.statusDePessoa = statusdepessoa;
	}
	
	public Administrador getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
	
	public Atendente getAtendente() {
		return atendente;
	}
	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}
	
	public Enfermeiro getEnfermeiro() {
		return enfermeiro;
	}
	public void setEnfermeiro(Enfermeiro enfermeiro) {
		this.enfermeiro = enfermeiro;
	}
	
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	public FuncionariosDePessoa getFuncionariosDePessoa() {
		return funcionariosDePessoa;
	}
	public void setFuncionariosDePessoa(FuncionariosDePessoa funcionariosDePessoa) {
		this.funcionariosDePessoa = funcionariosDePessoa;
	}
	
}
 
