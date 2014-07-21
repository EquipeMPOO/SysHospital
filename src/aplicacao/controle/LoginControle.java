package aplicacao.controle;

import java.util.List;

import aplicacao.dao.AdministradorDAO;
import aplicacao.dao.AtendenteDAO;
import aplicacao.dao.EnfermeiroDAO;
import aplicacao.dao.IFuncionarioDAO;
import aplicacao.dao.MedicoDAO;
import aplicacao.dominio.Administrador;
import aplicacao.dominio.Atendente;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Medico;
import aplicacao.enums.StatusDeUsuario;

public class LoginControle {
	private IFuncionarioDAO funcionario;
	
	public LoginControle (Administrador f){
			this.funcionario = new AdministradorDAO();
	}
	public LoginControle (Atendente f){
			this.funcionario = new AtendenteDAO();
	}
	public LoginControle (Enfermeiro f){
			this.funcionario = new EnfermeiroDAO();
	}
	public LoginControle (Medico f){
			this.funcionario = new MedicoDAO();
	}
	
	public List<Funcionario> pesquisarTodos(Boolean pesquisarPessoa){
		return ((IFuncionarioDAO) funcionario).pesquisarTodos(pesquisarPessoa);
	}
	
	public List<Funcionario> pesquisarFiltrando(Funcionario f, Boolean pesquisarPessoa){
		return ((IFuncionarioDAO) funcionario).pesquisarFiltrando(f, pesquisarPessoa);
	}
	
	public List<Funcionario> pesquisarAlgum(Funcionario f, Boolean pesquisarPessoa){
		return ((IFuncionarioDAO) funcionario).pesquisarAlgum(f, pesquisarPessoa);
	}
	
	public int cadastrar(Funcionario f){
		return ((IFuncionarioDAO) funcionario).cadastrar(f);
	}
	
	public Funcionario alterar(Funcionario f, int intAtributoDePesquisa){
		return ((IFuncionarioDAO) funcionario).alterar(f, intAtributoDePesquisa);
	}
	
	public Funcionario excluir(Funcionario f){
		return ((IFuncionarioDAO) funcionario).excluir(f);
	}
	
	public Funcionario logar(Funcionario f){
		if (f.getStatusDeUsuario().equals( StatusDeUsuario.A.getStatus() )){
			return null;
		}else
		if (f.getStatusDeUsuario().equals( StatusDeUsuario.IP.getStatus() )){
			return null;
		}else
		if (f.getStatusDeUsuario().equals( StatusDeUsuario.MP.getStatus() )){
			return null;
		}
		return ((IFuncionarioDAO) funcionario).logar(f);
	}
	
	public int deslogar(Funcionario f){
		return ((IFuncionarioDAO) funcionario).deslogar(f);
	}
}
