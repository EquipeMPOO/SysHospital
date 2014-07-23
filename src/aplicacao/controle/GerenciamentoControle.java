package aplicacao.controle;

import java.util.List;

import aplicacao.dao.AtendenteDAO;
import aplicacao.dao.EnfermeiroDAO;
import aplicacao.dao.MedicoDAO;
import aplicacao.dao.EnfermeiroDAO;
import aplicacao.dominio.Atendente;
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
		else if (funcionario instanceof Enfermeiro){
			
			EnfermeiroDAO database = new EnfermeiroDAO();
			database.alterar(funcionario, 0);
			
		}
		
		else if (funcionario instanceof Atendente){
			AtendenteDAO database = new AtendenteDAO();
			database.alterar(funcionario, 0);
			
		}
		return true;
		
		
	}
	
	public boolean Inativar(){
		
		if(funcionario instanceof Medico){
			
			MedicoDAO database = new MedicoDAO();
			database.excluir(funcionario);
			
		}
		
		else if (funcionario instanceof Enfermeiro){
			
			EnfermeiroDAO database = new EnfermeiroDAO();
			database.excluir(funcionario);
			
		}
		
		else if (funcionario instanceof Atendente){
			AtendenteDAO database = new AtendenteDAO();
			database.excluir(funcionario);
			
		}
		
		return true;
		
	}
	
	public boolean Cadastrar(){
		
		boolean resultado = false;
		
		if(funcionario instanceof Medico){
			
			MedicoDAO database = new MedicoDAO();
			List<Funcionario> funcionariosPesquisados = database.pesquisarFiltrando(funcionario,false);
			
			//caso ele percorra todo o banco de dados e n�o encontre nenhum, quer dizer que ele ainda n�o est� salvo no banco
			if (funcionariosPesquisados.size() == 0){ 
				database.cadastrar(funcionario);
				resultado = true;
			}
			
		}
		
		else if (funcionario instanceof Enfermeiro){
			
			EnfermeiroDAO database = new EnfermeiroDAO();
			List<Funcionario> funcionariosPesquisados = database.pesquisarFiltrando(funcionario,false);
			
			//caso ele percorra todo o banco de dados e n�o encontre nenhum, quer dizer que ele ainda n�o est� salvo no banco 
			if (funcionariosPesquisados.size() == 0){
				database.cadastrar(funcionario);
				resultado = true;
			}
			
			
		}
		
		else if (funcionario instanceof Atendente){
			
			AtendenteDAO database = new AtendenteDAO();
			List<Funcionario> funcionariosPesquisados = database.pesquisarFiltrando(funcionario,false);
			
			//caso ele percorra todo o banco de dados e n�o encontre nenhum, quer dizer que ele ainda n�o est� salvo no banco 
			if (funcionariosPesquisados.size() == 0){
				database.cadastrar(funcionario);
				resultado = true;
			}
			
		}
		return resultado;
				

	}

}
