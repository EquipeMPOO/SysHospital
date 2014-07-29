package aplicacao.controle;

import java.util.ArrayList;
import java.util.List;

import aplicacao.dao.AdministradorDAO;
import aplicacao.dao.AtendenteDAO;
import aplicacao.dao.EnfermeiroDAO;
import aplicacao.dao.MedicoDAO;
import aplicacao.dominio.Administrador;
import aplicacao.dominio.Atendente;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Medico;
import aplicacao.enums.StatusDeUsuario;

/**
 * 
 * @author icaro
 * Classe que aplica as regras necessarias para efetivação dos atos de pesquisa
 */

public class PesquisaControle {
	
	public Funcionario logar(Funcionario funcionario){
				
		if(funcionario instanceof Medico){
			
			MedicoDAO database = new MedicoDAO();
			Funcionario funcionarioPesquisado = database.pesquisarLogin(funcionario);
			return funcionarioPesquisado;
			
		}
		
		else if (funcionario instanceof Enfermeiro){
			
			EnfermeiroDAO database = new EnfermeiroDAO();
			Funcionario funcionarioPesquisado =database.pesquisarLogin(funcionario);
			return funcionarioPesquisado;
		}
		
		else if (funcionario instanceof Atendente){
			AtendenteDAO database = new AtendenteDAO();
			Funcionario funcionarioPesquisado = database.pesquisarLogin(funcionario);
			return funcionarioPesquisado;
		}
		
		else{
			AdministradorDAO database = new AdministradorDAO();
			Funcionario funcionarioPesquisado = database.pesquisarLogin(funcionario);
			return funcionarioPesquisado;
		}

		
	}
	
	public int deslogar(Funcionario f){
		return 0;
	}
	
	public List<Funcionario> pesquisarInativos(){
		
		MedicoDAO dbMedico = new MedicoDAO();
		EnfermeiroDAO dbEnfermeiro = new EnfermeiroDAO();
		AtendenteDAO dbAtendente = new AtendenteDAO();
		
		ArrayList<Funcionario> listaPesquisados = new ArrayList<Funcionario>();
		listaPesquisados.addAll(dbMedico.pesquisarInativos());
		listaPesquisados.addAll(dbEnfermeiro.pesquisarInativos());
		listaPesquisados.addAll(dbAtendente.pesquisarInativos());
		
		return listaPesquisados;		
		
	}
	
}
