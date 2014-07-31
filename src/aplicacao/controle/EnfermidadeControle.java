package aplicacao.controle;

import java.util.ArrayList;
import java.util.List;

import aplicacao.dao.EnfermidadeDAO;
import aplicacao.dominio.Enfermidade;
import aplicacao.enums.TipoEnfermidade;

public class EnfermidadeControle {
	public List<Enfermidade>getAll(){
		EnfermidadeDAO db = new EnfermidadeDAO ();
		List<Enfermidade> enfermidades = db.pesquisarTodos();
		return enfermidades;
		
	}
	public ArrayList<Enfermidade> getDeficiencias(){
		EnfermidadeDAO db = new EnfermidadeDAO ();
		ArrayList<Enfermidade> deficiencia = new ArrayList<Enfermidade>();
		List<Enfermidade> enfermidades = db.pesquisarTodos();
		for(Enfermidade enfermidade : enfermidades){
			if(enfermidade.getTipo().equals(TipoEnfermidade.D.getTipoEnfermidade())){
				deficiencia.add(enfermidade);
				
			}
		}
		return deficiencia;

		
	}	
	public ArrayList<Enfermidade> getAnomalias(){
		EnfermidadeDAO db = new EnfermidadeDAO ();
		ArrayList<Enfermidade> anomalias = new ArrayList<Enfermidade>();
		List<Enfermidade> enfermidades = db.pesquisarTodos();
		for(Enfermidade enfermidade : enfermidades){
			if(enfermidade.getTipo().equals(TipoEnfermidade.A.getTipoEnfermidade())){
				anomalias.add(enfermidade);
				
			}
		}
		return anomalias;

		
	}
	public ArrayList<Enfermidade> getAlergias(){
		EnfermidadeDAO db = new EnfermidadeDAO ();
		ArrayList<Enfermidade> alergias = new ArrayList<Enfermidade>();
		List<Enfermidade> enfermidades = db.pesquisarTodos();
		for(Enfermidade enfermidade : enfermidades){
			if(enfermidade.getTipo().equals(TipoEnfermidade.AL.getTipoEnfermidade())){
				alergias.add(enfermidade);
				
			}
		}
		return alergias;

		
	}
	public ArrayList<Enfermidade> getCronicas(){
		EnfermidadeDAO db = new EnfermidadeDAO ();
		ArrayList<Enfermidade> cronicas = new ArrayList<Enfermidade>();
		List<Enfermidade> enfermidades = db.pesquisarTodos();
		for(Enfermidade enfermidade : enfermidades){
			if(enfermidade.getTipo().equals(TipoEnfermidade.C.getTipoEnfermidade())){
				cronicas.add(enfermidade);
				
			}
		}
		return cronicas;

		
	}public ArrayList<Enfermidade> getComuns(){
		EnfermidadeDAO db = new EnfermidadeDAO ();
		ArrayList<Enfermidade> comuns= new ArrayList<Enfermidade>();
		List<Enfermidade> enfermidades = db.pesquisarTodos();
		for(Enfermidade enfermidade : enfermidades){
			if(enfermidade.getTipo().equals(TipoEnfermidade.N.getTipoEnfermidade())){
				comuns.add(enfermidade);
				
			}
		}
		return comuns;

		
	}	
	


}

