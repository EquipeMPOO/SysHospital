package aplicacao.dao;

import java.util.List;

import aplicacao.dominio.Funcionario;

public interface IFuncionarioDAO {
	
	List<Funcionario> pesquisarTodos(Boolean pesquisarPessoa);
	
	List<Funcionario> pesquisarFiltrando(Funcionario f, Boolean pesquisarPessoa);
	
	List<Funcionario> pesquisarAlgum(Funcionario f, Boolean pesquisarPessoa);
	
	int cadastrar(Funcionario f);
	
	Funcionario alterar(Funcionario f, int intAtributoDePesquisa);
	
	Funcionario excluir(Funcionario f);
	
	Funcionario logar(Funcionario f);
	
	int deslogar(Funcionario f);
}
