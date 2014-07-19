package aplicacao.dao.util;



import java.lang.reflect.*;
import java.util.ArrayList;

import aplicacao.dao.util.AtributoUsavel;
import aplicacao.dominio.Funcionario;

public class ComandoSQL {
	
	AtributoUsavel atributoUsavel = new AtributoUsavel();
	AtributoUsavel todosAtributo = new AtributoUsavel();
	
	public String gerarPesquisa(Funcionario f, int superclassesDiretas) {
		
		atributoUsavel.limpar();
		
		Class<?> c1 = (Class<?>) invocarUmGet("getClass", f);
		
		if (superclassesDiretas > 0){
			ArrayList<Object> classes = new ArrayList<>();
			Class<?> ci = (Class<?>) invocarUmGet("getSuperclass", c1);
			for (int i = 0; i < superclassesDiretas; i++) {
				
				classes.add(ci);
				
				ci = (Class<?>) invocarUmGet("getSuperclass", ci);
			}
			
			int todaClasse = classes.size();
			for (int i = 0; i < todaClasse; i++) {
				int classe = todaClasse - i - 1;
				Class<?> c = (Class<?>) classes.get(classe);
				analiseDeClasse(c, f);
			}
		}
		
		analiseDeClasse(c1, f);
		
		// ----x>  Apenas de teste;
		//System.out.println("--------------------------------------------------------\n" + atributoUsavel.getTodosEmOrdemDeAdd());
		
		ArrayList<String> atributos = new ArrayList<String>();
		atributos = atributoUsavel.getTodosEmOrdemDeAdd();
		
		String tabela = f.getClass().getSimpleName().toLowerCase();
		
		String codigoSQL = "SELECT * FROM " + tabela + " WHERE ";
		for (int i = 0; i < atributos.size(); i++) {
			String coluna = atributos.get(i).toLowerCase();
			
			String primeiraLetra = atributos.get(i).substring(0,1);
			String semPrimeiraLetra = atributos.get(i).substring(1);
			String nomeDeMetodo = "get" + primeiraLetra.toUpperCase() + semPrimeiraLetra;
			
			Object valorParaPesquisar = invocarUmGet(nomeDeMetodo, f);
			if (valorParaPesquisar instanceof String){
				valorParaPesquisar = ((String) valorParaPesquisar).replace(" ", "%");
			}
			
			if (i == 0){
				codigoSQL = codigoSQL + coluna + " Like '%" + valorParaPesquisar + "%'";
			}
			else {
				codigoSQL = codigoSQL + "AND " + coluna + " Like '%" + valorParaPesquisar + "%'";
			}
		}
		
		// ----x>  Apenas de teste;
		//System.out.println("\nCódigo: " + codigoSQL);
		
		return codigoSQL;
	}
	
	public String gerarAtualizacao(Funcionario f, int intAtributoDePesquisa, int superclassesDiretas) {
		intAtributoDePesquisa = intAtributoDePesquisa - 1;
		String strAtributoDePesquisa;
		
		atributoUsavel.limpar();
		
		Class<?> c1 = (Class<?>) invocarUmGet("getClass", f);
		ArrayList<String> toodosAtributos = new ArrayList<>();
		
		if (superclassesDiretas > 0){
			ArrayList<Object> classes = new ArrayList<>();
			Class<?> ci = (Class<?>) invocarUmGet("getSuperclass", c1);
			for (int i = 0; i < superclassesDiretas; i++) {
				
				classes.add(ci);
				
				ci = (Class<?>) invocarUmGet("getSuperclass", ci);
			}
			
			int todaClasse = classes.size();
			for (int i = 0; i < todaClasse; i++) {
				int classe = todaClasse - i - 1;
				Class<?> c = (Class<?>) classes.get(classe);
				ArrayList<String> a = analiseDeClasse(c, f);
				for (String i1 : a){
					toodosAtributos.add(i1);
				}
			}
		}
		
		ArrayList<String> a = analiseDeClasse(c1, f);
		for (String i : a){
			toodosAtributos.add(i);
		}
		strAtributoDePesquisa = toodosAtributos.get(intAtributoDePesquisa);
		
		ArrayList<String> atributos = new ArrayList<String>();
		atributos = atributoUsavel.getTodosEmOrdemDeAdd();
		
		intAtributoDePesquisa = atributos.indexOf(strAtributoDePesquisa);
		boolean validadeAtributoDePesquisa = atributos.remove(strAtributoDePesquisa);
		String codigoSQL = "";
		ArrayList<String> vazio = new ArrayList<String>();
		
		if (validadeAtributoDePesquisa == true && !(atributos.equals(vazio))){
			String tabela = f.getClass().getSimpleName().toLowerCase();
			
			codigoSQL = "UPDATE " + tabela + " SET ";
			
			for (String i : atributos) {
				
				String primeiraLetra = i.substring(0,1);
				String semPrimeiraLetra = i.substring(1);
				String nomeDeMetodo = "get" + primeiraLetra.toUpperCase() + semPrimeiraLetra;
				
				Object valorParaAtualizar = invocarUmGet(nomeDeMetodo, f);
				if (valorParaAtualizar instanceof String){
					valorParaAtualizar = "'" + valorParaAtualizar + "'";
				}
				
				codigoSQL = codigoSQL + i.toLowerCase() + "=" + valorParaAtualizar + ", ";
			}
			
			String primeiraLetra = strAtributoDePesquisa.substring(0,1);
			String semPrimeiraLetra = strAtributoDePesquisa.substring(1);
			String nomeDeMetodo = "get" + primeiraLetra.toUpperCase() + semPrimeiraLetra;
			Object valorParaAtualizar = invocarUmGet(nomeDeMetodo, f);
			if (valorParaAtualizar instanceof String){
				valorParaAtualizar = "'" + valorParaAtualizar + "'";
			}
			
			codigoSQL = codigoSQL.substring(0, (codigoSQL.length() - 2) );
			codigoSQL = codigoSQL + " WHERE " + strAtributoDePesquisa.toLowerCase() + "=" + valorParaAtualizar;
		}
		
		// ----x>  Apenas de teste;
		//System.out.println(codigoSQL);
		return codigoSQL;
	}
	
	private Object invocarUmGet(String nomeDeMetodo, Object o) {
		Object retornoDeMetodo = null;
		
		try {
			Class<?> c = o.getClass();
			
			Class<?> tipoDeParametro[] = new Class[0];
			
			Object parametroDeMetodo[] = new Object[0];
			
			Method metodo = c.getMethod(nomeDeMetodo, tipoDeParametro);
			retornoDeMetodo = metodo.invoke(o, parametroDeMetodo);
			
			// ----x>  Apenas de teste;
			//System.out.println("valor de atributo: " + retornoDeMetodo + "\n" +
			//		   		   "metodo: " + metodo);
		}
		catch (Throwable e) {
			System.err.println(e);
		}
		return retornoDeMetodo;
	}
	
	private ArrayList<String> analiseDeClasse(Class<?> c, Funcionario f) {
		ArrayList<String> toodosAtributos = new ArrayList<>();
		
		try {
			Field informacoesDaClasse[] = c.getDeclaredFields();
			for (int i = 0; i < informacoesDaClasse.length; i++) {
				Field informacao = informacoesDaClasse[i];
				String tipoDeAtributo = informacao.getType().getSimpleName();
				String nomeDeAtributo = informacao.getName();
				
				String primeiraLetra = nomeDeAtributo.substring(0,1);
				String semPrimeiraLetra = nomeDeAtributo.substring(1);
				String nomeDeMetodo = "get" + primeiraLetra.toUpperCase() + semPrimeiraLetra;
				Object retornoDeMetodo = invocarUmGet(nomeDeMetodo, f);
				
				// ----x>  Apenas de teste;
				//System.out.println("atributo: " + nomeDeAtributo);
				toodosAtributos.add(nomeDeAtributo.toString());
				
				if (tipoDeAtributo.equals("String") && !(retornoDeMetodo.equals(""))){
					this.atributoUsavel.addString(nomeDeAtributo);
				} else
					
				if (tipoDeAtributo.equals("int")){
					Integer retornoDeMetodoInteiro = (Integer)retornoDeMetodo;
					if (!(retornoDeMetodoInteiro <= 0)){
						this.atributoUsavel.addInteiro(nomeDeAtributo);
					}
				} else
					
				if ( tipoDeAtributo.equals("Pessoa") && (retornoDeMetodo != null) ){
					this.atributoUsavel.addPessoa(nomeDeAtributo);
				}
				
				// ----x>  Apenas de teste;
				//System.out.println("Todos: " + atributoUsavel.getTodos() + "\n");
			}
		}
		catch (Throwable e) {
			System.err.println(e);
		}
		
		return toodosAtributos;
	}
}
