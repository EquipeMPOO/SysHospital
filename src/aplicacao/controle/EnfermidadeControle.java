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
	public List<Enfermidade> getDeficiencia(){
		EnfermidadeDAO db = new EnfermidadeDAO ();
		List<Enfermidade> deficiencia = new ArrayList<Enfermidade>();
		List<Enfermidade> enfermidades = db.pesquisarTodos();
		for(Enfermidade enfermidade : enfermidades){
			if(enfermidade.getTipo().equals(TipoEnfermidade.D.getTipoEnfermidade())){
				deficiencia.add(enfermidade);
				
			}
		}
		return deficiencia;

		
	}	
	public List<Enfermidade> getAnomalias(){
		EnfermidadeDAO db = new EnfermidadeDAO ();
		List<Enfermidade> anomalias = new ArrayList<Enfermidade>();
		List<Enfermidade> enfermidades = db.pesquisarTodos();
		for(Enfermidade enfermidade : enfermidades){
			if(enfermidade.getTipo().equals(TipoEnfermidade.A.getTipoEnfermidade())){
				anomalias.add(enfermidade);
				
			}
		}
		return anomalias;

		
	}
	public List<Enfermidade> getAlergias(){
		EnfermidadeDAO db = new EnfermidadeDAO ();
		List<Enfermidade> alergias = new ArrayList<Enfermidade>();
		List<Enfermidade> enfermidades = db.pesquisarTodos();
		for(Enfermidade enfermidade : enfermidades){
			if(enfermidade.getTipo().equals(TipoEnfermidade.AL.getTipoEnfermidade())){
				alergias.add(enfermidade);
				
			}
		}
		return alergias;

		
	}
	public List<Enfermidade> getCronicas(){
		EnfermidadeDAO db = new EnfermidadeDAO ();
		List<Enfermidade> cronicas = new ArrayList<Enfermidade>();
		List<Enfermidade> enfermidades = db.pesquisarTodos();
		for(Enfermidade enfermidade : enfermidades){
			if(enfermidade.getTipo().equals(TipoEnfermidade.C.getTipoEnfermidade())){
				cronicas.add(enfermidade);
				
			}
		}
		return cronicas;

		
	}public List<Enfermidade> getComuns(){
		EnfermidadeDAO db = new EnfermidadeDAO ();
		List<Enfermidade> comuns= new ArrayList<Enfermidade>();
		List<Enfermidade> enfermidades = db.pesquisarTodos();
		for(Enfermidade enfermidade : enfermidades){
			if(enfermidade.getTipo().equals(TipoEnfermidade.N.getTipoEnfermidade())){
				comuns.add(enfermidade);
				
			}
		}
		return comuns;

		
	}	
	


}

