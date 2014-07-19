package aplicacao.dao;

import java.util.List;

import aplicacao.dominio.Pessoa;

public interface IPessoaDAO {


	List<Pessoa> pesquisarTodos(Boolean pesquisarAdministrador, Boolean pesquisarAtendente,
								Boolean pesquisarMedico, Boolean pesquisarEnfermeiro);
	
	List<Pessoa> pesquisarFiltrando(Pessoa pessoa, Boolean pesquisarAdministrador,
									Boolean pesquisarAtendente, Boolean pesquisarMedico,
									Boolean pesquisarEnfermeiro);
	
	int cadastrar(Pessoa pessoa);
	
	int alterar(Pessoa pessoa);
	
	int excluir(Pessoa pessoa);
}
