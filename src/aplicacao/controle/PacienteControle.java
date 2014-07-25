package aplicacao.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import aplicacao.dao.EnfermeiroDAO;
import aplicacao.dao.MedicoDAO;
import aplicacao.dao.PacienteDAO;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Medico;
import aplicacao.dominio.Paciente;
import aplicacao.enums.StatusDeUsuario;

public class PacienteControle {	
	
	public List<Paciente> pesquisarNaoInternados(){
		
		PacienteDAO database = new PacienteDAO();
		return database.pesquisarTodos();
	}
	
}
