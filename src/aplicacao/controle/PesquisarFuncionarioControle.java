package aplicacao.controle;

import java.util.List;
import java.util.Vector;

import aplicacao.dao.EnfermeiroDAO;
import aplicacao.dao.MedicoDAO;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Medico;
import aplicacao.enums.StatusDeUsuario;

public class PesquisarFuncionarioControle {
	
	public List<Funcionario> pesquisar(){
		MedicoDAO databaseMedico = new MedicoDAO();
		Medico medicoParametro = new Medico();
		medicoParametro.setStatusDeUsuario(StatusDeUsuario.A.getStatus());
		
		final List<Funcionario> funcionariosPesquisados = databaseMedico.pesquisarFiltrando(medicoParametro, true);
	
		EnfermeiroDAO databaseEnfermeiro = new EnfermeiroDAO();
		Enfermeiro enfermeiroParametro = new Enfermeiro();
		enfermeiroParametro.setStatusDeUsuario(StatusDeUsuario.A.getStatus());
		
		funcionariosPesquisados.addAll(databaseEnfermeiro.pesquisarFiltrando(enfermeiroParametro, true));
		
		Vector listaFinal = new Vector();
		listaFinal.addAll(funcionariosPesquisados);	
		
		return listaFinal;
	}

}
