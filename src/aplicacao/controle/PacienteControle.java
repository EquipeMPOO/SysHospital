package aplicacao.controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import aplicacao.dao.EnfermeiroDAO;
import aplicacao.dao.EntradaDAO;
import aplicacao.dao.MedicoDAO;
import aplicacao.dao.PacienteDAO;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Entrada;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Medico;
import aplicacao.dominio.Paciente;
import aplicacao.enums.StatusDeEntrada;
import aplicacao.enums.StatusDeUsuario;

public class PacienteControle {	
	PacienteDAO db = new PacienteDAO();
	EntradaDAO entrada = new EntradaDAO();
	ArrayList<Paciente> todosPacientes = (ArrayList<Paciente>) db.pesquisarTodos();
	
	public ArrayList<Paciente> pesquisarInternados(){
		ArrayList<Paciente> internos = new ArrayList<Paciente>();
		
		for(Paciente p: todosPacientes){
			
			  int ultimoindex = p.getHistorico().size();
			  if(p.getHistorico().size() > 0){
				Entrada ultimaEntrada = p.getHistorico().get(ultimoindex-1);
				if (ultimaEntrada.getStatusdeentrada().equals(StatusDeEntrada.A.getStatus())){
					internos.add(p);
				}
				
			  }
			  
		}
		return internos;
	}
	
	public ArrayList<Paciente> pesquisarNaoInternados(){
		ArrayList<Paciente> naoInternos = new ArrayList<Paciente>();
		
		for(Paciente p:todosPacientes){
			
			int ultimoindex = p.getHistorico().size();
			if (p.getHistorico()!=null || p.getHistorico().size()== 0){
				
					naoInternos.add(p);
			}
			else if(p.getHistorico().size() > 0){
				Entrada ultimaEntrada = p.getHistorico().get(ultimoindex-1);
				if (ultimaEntrada.getStatusdeentrada().equals(StatusDeEntrada.F.getStatus())){
					naoInternos.add(p);
				}
				
			}	
				
			
		}
		return naoInternos;
		
		
	}
	
		

	public List<Paciente> pesquisarTodos(){
		
		List<Paciente>todosPacientes=db.pesquisarTodos();
		return todosPacientes;
		
	}
	public void internar(Paciente paciente){
		Entrada entrada = new Entrada();
		Date data = new Date();
		String dataEntrada = "" + data;
		entrada = new Entrada();
		entrada.setDataEntrada(dataEntrada);
		entrada.setStatusdeentrada(StatusDeEntrada.A.getStatus());
		paciente.getHistorico().add(entrada);
		db.internar(paciente);
		
	}
	public void cadastrar(Paciente paciente){
		db.cadastrar((paciente));
		  
		  
	}
	public void liberar(Paciente paciente){
		Date data = new Date();
		String dataSaida = "" + data;
		
		int ultimoindex = paciente.getHistorico().size()-1;
		paciente.getHistorico().get(ultimoindex).setDataSaida(dataSaida);
		paciente.getHistorico().get(ultimoindex).setStatusdeentrada(StatusDeEntrada.F.getStatus());
		db.liberar(paciente);
		
	}
	
	public void update(Paciente paciente){
		
		
		
		
		
	}
	
	
}
