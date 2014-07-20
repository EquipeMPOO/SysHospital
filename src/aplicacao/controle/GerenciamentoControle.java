package aplicacao.controle;

import java.util.List;

import aplicacao.dao.EnfermeiroDAO;
import aplicacao.dao.MedicoDAO;
import aplicacao.dao.EnfermeiroDAO;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Medico;

public class GerenciamentoControle {
	
	private Funcionario funcionario;
	
	public GerenciamentoControle(Funcionario funcionario){		
		this.funcionario = funcionario;
	}
	
	
	public boolean AlterarDados(){
		
		if (funcionario instanceof Medico){
			
			MedicoDAO database = new MedicoDAO();
			database.alterar(funcionario, 0);
			
		}
		else{
			
			EnfermeiroDAO database = new EnfermeiroDAO();
			database.alterar(funcionario, 0);
			
			
		}
		
		return true;
		
		
	}
	
	public boolean Inativar(){
		
		if(funcionario instanceof Medico){
			
			MedicoDAO database = new MedicoDAO();
			database.excluir(funcionario);
			
		}
		
		else{
			
			EnfermeiroDAO database = new EnfermeiroDAO();
			database.excluir(funcionario);
			
		}
		
		return true;
		
	}
	
	public boolean Cadastrar(){
		
		boolean resultado = false;
		
		if(funcionario instanceof Medico){
			
			MedicoDAO database = new MedicoDAO();
			List<Funcionario> funcionariosPesquisados = database.pesquisarFiltrando(funcionario,false);
			
			//caso ele percorra todo o banco de dados e não encontre nenhum, quer dizer que ele ainda não está salvo no banco
			if (funcionariosPesquisados.size() == 0){ 
				database.cadastrar(funcionario);
				resultado = true;
			}
			
		}
		
		else{
			
			EnfermeiroDAO database = new EnfermeiroDAO();
			List<Funcionario> funcionariosPesquisados = database.pesquisarFiltrando(funcionario,false);
			
			//caso ele percorra todo o banco de dados e não encontre nenhum, quer dizer que ele ainda não está salvo no banco 
			if (funcionariosPesquisados.size() == 0){
				database.cadastrar(funcionario);
				resultado = true;
			}
			
			
		}
		return resultado;
				

	}

}
