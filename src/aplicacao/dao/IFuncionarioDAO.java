package aplicacao.dao;

import java.util.List;

import aplicacao.dominio.*;

public interface IFuncionarioDAO {
	
	List<Funcionario> pesquisarTodos(Boolean pesquisarPessoa);
	
	List<Funcionario> pesquisarFiltrando(Funcionario f, Boolean pesquisarPessoa);
	
	List<Funcionario> pesquisarAlgum(Funcionario f, Boolean pesquisarPessoa);
	
	void cadastrar(Funcionario f);
	
	void alterar(Funcionario f);
	
	void inativar (Funcionario f);
}
