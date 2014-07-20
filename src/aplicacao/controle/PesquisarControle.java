package aplicacao.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import aplicacao.dao.EnfermeiroDAO;
import aplicacao.dao.MedicoDAO;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Medico;
import aplicacao.dominio.Paciente;
import aplicacao.enums.StatusDeUsuario;

public class PesquisarControle {
	
	
	
	public List<Funcionario> pesquisarFuncionarioEspecifico(Funcionario funcionarioParametro){
		
		List<Funcionario> funcionariosPesquisados = null;
		
		if (funcionarioParametro instanceof Medico){

		MedicoDAO databaseMedico = new MedicoDAO();				
		funcionariosPesquisados = databaseMedico.pesquisarFiltrando(funcionarioParametro, true);
		
		}
		
		else{
			
			EnfermeiroDAO databaseEnfermeiro = new EnfermeiroDAO();			
			funcionariosPesquisados = databaseEnfermeiro.pesquisarFiltrando(funcionarioParametro, true);
			
		}


		return funcionariosPesquisados;
	}
	
	
	public List<Funcionario> pesquisarTodosFuncionarios(){
				
		List<Funcionario> funcionariosPesquisados = null;

		MedicoDAO databaseMedico = new MedicoDAO();			
		funcionariosPesquisados.add((Funcionario) databaseMedico.pesquisarTodos(true));

		EnfermeiroDAO databaseEnfermeiro = new EnfermeiroDAO();			
		funcionariosPesquisados.add((Funcionario) databaseEnfermeiro.pesquisarTodos(true));
		
		return funcionariosPesquisados;
		
	}
	
	public List<Paciente> pesquisarPacienteEspecifico(Funcionario funcionarioParametro){
		
		return null;
	}
	
	
	public List<Paciente> pesquisarTodosPacientes(){
		
		return null;
		
	}
}
