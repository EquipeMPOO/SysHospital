package aplicacao.controle;

import java.util.List;

import aplicacao.dao.AtendenteDAO;
import aplicacao.dao.EnfermeiroDAO;
import aplicacao.dao.MedicoDAO;
import aplicacao.dao.EnfermeiroDAO;
import aplicacao.dao.PessoaDAO;
import aplicacao.dominio.Atendente;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Medico;

/** 
 * @author icaro
 * Classe responsável pelo controle das ações de alteração, inserção e remoção de funcionarios do sistema
 */

public class GerenciamentoControle {
	
	private Funcionario funcionario;
	
	public GerenciamentoControle(Funcionario funcionario){		
		this.funcionario = funcionario;
	}
	
	
	/**
	 * Metodo que verifica qual a classe do atributo funcionario e decide qual a classe de persistência adequada para operar a mudança de dados
	 * @return boolean - Explica se a operação solicitada foi concluida
	 */
	
	public boolean AlterarDados(){
		
		if (funcionario instanceof Medico){
			MedicoDAO database = new MedicoDAO();
			database.alterar(funcionario);
			
		}
		else if (funcionario instanceof Enfermeiro){
			
			EnfermeiroDAO database = new EnfermeiroDAO();
			database.alterar(funcionario);
			
		}
		
		else if (funcionario instanceof Atendente){
			AtendenteDAO database = new AtendenteDAO();
			database.alterar(funcionario);
			
		}
		return true;
		
		
	}
	
	/**
	 * Metodo que verifica qual a classe do atributo funcionario e decide qual a classe de persistência adequada para operar a inativação do funcionario no sistema
	 * @return boolean - Explica se a operação solicitada foi concluida
	 */
	
	public boolean Inativar(){
		
		if(funcionario instanceof Medico){
			
			MedicoDAO database = new MedicoDAO();
			database.inativar(funcionario);
			
		}
		
		else if (funcionario instanceof Enfermeiro){
			
			EnfermeiroDAO database = new EnfermeiroDAO();
			database.inativar(funcionario);
			
		}
		
		else if (funcionario instanceof Atendente){
			AtendenteDAO database = new AtendenteDAO();
			database.inativar(funcionario);
			
		}
		
		return true;
		
	}
	
	
	/**
	 * Metodo que verifica qual a classe do atributo funcionario e decide qual a classe de persistência adequada para operar o cadastro
	 * Antes de relizar o cadastro há uma verificação para conferir se já há um funcionario com os dados informados no banco de dados
	 * @return boolean - Explica se a operação solicitada foi concluida
	 */
	
	public boolean Cadastrar(){
		
		boolean resultado = false;
		
		if(funcionario instanceof Medico){
			
			MedicoDAO database = new MedicoDAO();
			Funcionario funcionariosPesquisados = database.pesquisarCpf(funcionario);
			
			//caso ele percorra todo o banco de dados e não encontre nenhum, quer dizer que ele ainda não está salvo no banco
			if (funcionariosPesquisados == null){ 
				database.cadastrar(funcionario);
				resultado = true;
			}
			
		}
		
		else if (funcionario instanceof Enfermeiro){
			
			EnfermeiroDAO database = new EnfermeiroDAO();
			Funcionario funcionariosPesquisados = database.pesquisarCpf(funcionario);
			
			//caso ele percorra todo o banco de dados e não encontre nenhum, quer dizer que ele ainda não está salvo no banco 
			if (funcionariosPesquisados == null){
				
				database.cadastrar(funcionario);
				resultado = true;
			}
			
			
		}
		
		else if (funcionario instanceof Atendente){
			
			AtendenteDAO database = new AtendenteDAO();
			Funcionario funcionariosPesquisados = database.pesquisarCpf(funcionario);
			
			//caso ele percorra todo o banco de dados e não encontre nenhum, quer dizer que ele ainda não está salvo no banco 
			if (funcionariosPesquisados == null){
				database.cadastrar(funcionario);
				resultado = true;
			}
			
		}
		return resultado;
				

	}

}
