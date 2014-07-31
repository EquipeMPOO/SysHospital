package aplicacao.dao.util.geral;



import java.lang.reflect.*;
import java.util.ArrayList;

import aplicacao.dao.util.geral.AtributoUsavel;
import aplicacao.dominio.Funcionario;

public class ComandoSQL {
	
	AtributoUsavel atributoUsavel = new AtributoUsavel();
	
	public String gerarFiltragem(Object f, int superclassesDiretas) {
		
		atributoUsavel.limpar();
		
		Class<?> c1 = f.getClass();
		
		if (superclassesDiretas > 0){
			ArrayList<Object> classes = new ArrayList<>();
			classes = getSuperclasses(superclassesDiretas, c1);
			
			int todaClasse = classes.size();
			for (int i = 0; i < todaClasse; i++) {
				int iTodaClasse = todaClasse - i - 1;
				Class<?> c = (Class<?>) classes.get(iTodaClasse);
				analiseDeClasse(c, f);
			}
		}
		
		analiseDeClasse(c1, f);
		ArrayList<String> colunas = new ArrayList<String>();
		colunas = atributoUsavel.getTodosEmOrdemDeAdd();
		String codigoSQL = "";
		
		// ----x>  Apenas de teste;
		//System.out.println("--------------------------------------------------------\n" + atributoUsavel.getTodosEmOrdemDeAdd());
		
		if (!colunas.isEmpty()){
			
			String tabela = f.getClass().getSimpleName().toLowerCase();
			
			codigoSQL = "SELECT * FROM " + tabela + " WHERE ";
			for (int i = 0; i < colunas.size(); i++) {
				String coluna = colunas.get(i).toLowerCase();
				
				String primeiraLetra = colunas.get(i).substring(0,1);
				String semPrimeiraLetra = colunas.get(i).substring(1);
				String nomeDeMetodo = "get" + primeiraLetra.toUpperCase() + semPrimeiraLetra;
				Object valorParaPesquisar = invocarUmGet(nomeDeMetodo, f);
				
				boolean tipoString = valorParaPesquisar instanceof String;
				boolean tipoInt = valorParaPesquisar instanceof Number;
				boolean tipoObject = valorParaPesquisar instanceof Object;
				
				if (tipoString){
					valorParaPesquisar = "'" + ((String) valorParaPesquisar) + "'";
				}else
				
				if (tipoInt){
				}else
				
				if (tipoObject){
					String nomeDaClasseContida = valorParaPesquisar.getClass().getSimpleName();
					
					try{
						nomeDeMetodo = "getId" + nomeDaClasseContida;
						valorParaPesquisar = invocarUmGet(nomeDeMetodo, valorParaPesquisar);
					}
					catch (Throwable e) {
						System.err.println(e + "/n" +
										   "A classe " + nomeDaClasseContida + "não possui um getId" + nomeDaClasseContida +
										   "() desta forma gerarAtualizacao() não tem como permitir a atualização de " + i +
										   ", mas o programa seguirá normalmente (sem fazer a adição)."
										  );
						return codigoSQL ="";
					}
				}
				
				if (i == 0){
					codigoSQL = codigoSQL + coluna + "=" + valorParaPesquisar;
				}
				else {
					codigoSQL = codigoSQL + " AND " + coluna + "=" + valorParaPesquisar;
				}
			}
		}
		
		// ----x>  Apenas de teste;
		//System.out.println("\nCódigo: " + codigoSQL);
		return codigoSQL;
	}
	
	public String gerarPesquisa(Object f, int superclassesDiretas) {
		
		atributoUsavel.limpar();
		
		Class<?> c1 = f.getClass();
		
		if (superclassesDiretas > 0){
			ArrayList<Object> classes = new ArrayList<>();
			classes = getSuperclasses(superclassesDiretas, c1);
			
			int todaClasse = classes.size();
			for (int i = 0; i < todaClasse; i++) {
				int iTodaClasse = todaClasse - i - 1;
				Class<?> c = (Class<?>) classes.get(iTodaClasse);
				analiseDeClasse(c, f);
			}
		}
		
		analiseDeClasse(c1, f);
		ArrayList<String> colunas = new ArrayList<String>();
		colunas = atributoUsavel.getTodosEmOrdemDeAdd();
		String codigoSQL = "";
		
		// ----x>  Apenas de teste;
		//System.out.println("--------------------------------------------------------\n" + atributoUsavel.getTodosEmOrdemDeAdd());
		
		if (!colunas.isEmpty()){
			
			String tabela = f.getClass().getSimpleName().toLowerCase();
			
			codigoSQL = "SELECT * FROM " + tabela + " WHERE ";
			for (int i = 0; i < colunas.size(); i++) {
				String coluna = colunas.get(i).toLowerCase();
				
				String primeiraLetra = colunas.get(i).substring(0,1);
				String semPrimeiraLetra = colunas.get(i).substring(1);
				String nomeDeMetodo = "get" + primeiraLetra.toUpperCase() + semPrimeiraLetra;
				
				Object valorParaPesquisar = invocarUmGet(nomeDeMetodo, f);
				
				boolean tipoString = valorParaPesquisar instanceof String;
				boolean tipoInt = valorParaPesquisar instanceof Number;
				boolean tipoObject = valorParaPesquisar instanceof Object;
				
				if (tipoString){
					valorParaPesquisar = ((String) valorParaPesquisar).replace(" ", "%");
				}else
					
				if (tipoInt){
				}else
				
				if (tipoObject){
					String nomeDaClasseContida = valorParaPesquisar.getClass().getSimpleName();
					
					try{
						nomeDeMetodo = "getId" + nomeDaClasseContida;
						valorParaPesquisar = invocarUmGet(nomeDeMetodo, valorParaPesquisar);
					}
					catch (Throwable e) {
						System.err.println(e + "/n" +
										   "A classe " + nomeDaClasseContida + "não possui um getId" + nomeDaClasseContida +
										   "() desta forma gerarAtualizacao() não tem como permitir a atualização de " + i +
										   ", mas o programa seguirá normalmente (sem fazer a adição)."
										  );
						return codigoSQL ="";
					}
				}
				
				if (i == 0){
					codigoSQL = codigoSQL + coluna + " Like '%" + valorParaPesquisar + "%'";
				}
				else {
					codigoSQL = codigoSQL + " AND " + coluna + " Like '%" + valorParaPesquisar + "%'";
				}
			}
		}
		
		// ----x>  Apenas de teste;
		//System.out.println("\nCódigo: " + codigoSQL);
		return codigoSQL;
	}
	
	public String gerarCadastro(Object f, int superclassesDiretas) {
		
		atributoUsavel.limpar();
		
		Class<?> c1 = f.getClass();
		
		if (superclassesDiretas > 0){
			ArrayList<Object> classes = new ArrayList<>();
			classes = getSuperclasses(superclassesDiretas, c1);
			
			int todaClasse = classes.size();
			for (int i = 0; i < todaClasse; i++) {
				int iTodaClasse = todaClasse - i - 1;
				Class<?> c = (Class<?>) classes.get(iTodaClasse);
				analiseDeClasse(c, f);
			}
		}
		
		analiseDeClasse(c1, f);
		ArrayList<String> colunas = new ArrayList<String>();
		colunas = atributoUsavel.getTodosEmOrdemDeAdd();
		String codigoSQL = "";
		
		if (!colunas.isEmpty()){
			
			String tabela = f.getClass().getSimpleName().toLowerCase();
			
			codigoSQL = "INSERT INTO " + tabela + " WHERE ";
			for (String i : colunas){
				
				String primeiraLetra = i.substring(0,1);
				String semPrimeiraLetra = i.substring(1);
				String nomeDeMetodo = "get" + primeiraLetra.toUpperCase() + semPrimeiraLetra;
				
				Object valorParaInserir = invocarUmGet(nomeDeMetodo, f);
				
				boolean tipoString = valorParaInserir instanceof String;
				boolean tipoInt = valorParaInserir instanceof Number;
				boolean tipoObject = valorParaInserir instanceof Object;
				
				if (tipoString){
					valorParaInserir = "'" + valorParaInserir + "'";
				}
				else if (tipoInt == true){
					continue;
				}
				else if (tipoObject){
					String nomeDaClasseContida = valorParaInserir.getClass().getSimpleName();
					
					try{
						nomeDeMetodo = "getId" + nomeDaClasseContida;
						valorParaInserir = invocarUmGet(nomeDeMetodo, valorParaInserir);
					}
					catch (Throwable e) {
						System.err.println(e + "/n" +
										   "A classe " + nomeDaClasseContida + "não possui um getId" + nomeDaClasseContida +
										   "() desta forma gerarAtualizacao() não tem como permitir a atualização de " + i +
										   ", mas o programa seguirá normalmente (sem fazer a adição)."
										  );
						return codigoSQL ="";
					}
				}
				
				codigoSQL = codigoSQL + i.toLowerCase() + "=" + valorParaInserir + ", ";
			}
		}
		
		int retirarCaracteresExtras = codigoSQL.length() - 2;
		codigoSQL = codigoSQL.substring(0, retirarCaracteresExtras);
		// ----x>  Apenas de teste;
		//System.out.println(codigoSQL);
		return codigoSQL;
	}
	
	public String gerarAtualizacao(Object f, String strAtributoDePesquisa, int superclassesDiretas) {
		
		atributoUsavel.limpar();
		
		Class<?> c1 = f.getClass();
		ArrayList<String> toodosAtributos = new ArrayList<>();
		
		if (superclassesDiretas > 0){
			ArrayList<Object> classes = new ArrayList<>();
			classes = getSuperclasses(superclassesDiretas, c1);
			
			int todaClasse = classes.size();
			for (int i = 0; i < todaClasse; i++) {
				int iTodaClasse = todaClasse - i - 1;
				Class<?> c = (Class<?>) classes.get(iTodaClasse);
				ArrayList<String> atributosDaClasse = analiseDeClasse(c, f);
				for (String i1 : atributosDaClasse){
					toodosAtributos.add(i1);
				}
			}
		}
		
		ArrayList<String> atributosDaClasse = analiseDeClasse(c1, f);
		ArrayList<String> colunas = new ArrayList<String>();
		colunas = atributoUsavel.getTodosEmOrdemDeAdd();
		String codigoSQL = "";
		
		if (!colunas.isEmpty()){
			
			for (String i : atributosDaClasse){
				toodosAtributos.add(i);
			}
			boolean validadeAtributoDePesquisa = colunas.remove(strAtributoDePesquisa);
			
			if (validadeAtributoDePesquisa == true){
				String tabela = f.getClass().getSimpleName().toLowerCase();
				
				codigoSQL = "UPDATE " + tabela + " SET ";
				
				for (String i : colunas) {
					
					String primeiraLetra = i.substring(0,1);
					String semPrimeiraLetra = i.substring(1);
					String nomeDeMetodo = "get" + primeiraLetra.toUpperCase() + semPrimeiraLetra;
					
					Object valorParaAtualizar = invocarUmGet(nomeDeMetodo, f);
					
					boolean tipoString = valorParaAtualizar instanceof String;
					boolean tipoInt = valorParaAtualizar instanceof Number;
					boolean tipoObject = valorParaAtualizar instanceof Object;
					
					if (tipoString){
						valorParaAtualizar = "'" + valorParaAtualizar + "'";
					}else
					
					if (tipoInt){
					}else
						
					if (tipoObject){
						String nomeDaClasseContida = valorParaAtualizar.getClass().getSimpleName();
						
						try{
							nomeDeMetodo = "getId" + nomeDaClasseContida;
							valorParaAtualizar = invocarUmGet(nomeDeMetodo, valorParaAtualizar);
						}
						catch (Throwable e) {
							System.err.println(e + "/n" +
											   "A classe " + nomeDaClasseContida + "não possui um getId" + nomeDaClasseContida +
											   "() desta forma gerarAtualizacao() não tem como permitir a atualização de " + i +
											   ", mas o programa seguirá normalmente (sem fazer a atualização)."
											  );
							return codigoSQL ="";
						}
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
		}
		
		// ----x>  Apenas de teste;
		//System.out.println(codigoSQL);
		return codigoSQL;
	}
	
	
// ---------------------------------------------------------------- Métodos internos ----------------------------------------------------------------
	
	
	private ArrayList<Object> getSuperclasses(int superclassesDiretas, Class<?> c1){
		ArrayList<Object> classes = new ArrayList<>();
		Class<?> ci = c1.getSuperclass();
		for (int i = 0; i < superclassesDiretas; i++) {
			
			classes.add(ci);
			
			ci = ci.getSuperclass();
		}
		return classes;
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
	
	private ArrayList<String> analiseDeClasse(Class<?> c, Object f) {
		ArrayList<String> todosAtributos = new ArrayList<>();
		
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
				todosAtributos.add(nomeDeAtributo);
				
				boolean tipoString = tipoDeAtributo.equals("String");
				boolean tipoInt = tipoDeAtributo.equals("int");
				boolean tipoObject = retornoDeMetodo instanceof Object;
				
				if (tipoString && !(retornoDeMetodo.equals(""))){
					this.atributoUsavel.addString(nomeDeAtributo);
				} else
					
				if (tipoInt){
					Integer retornoDeMetodoInteiro = (Integer)retornoDeMetodo;
					if (!(retornoDeMetodoInteiro <= 0)){
						this.atributoUsavel.addInteiro(nomeDeAtributo);
					}
				} else
					
				if ( !tipoString && tipoObject && (retornoDeMetodo != null) ){
					this.atributoUsavel.addObjeto(nomeDeAtributo);
				}
			
			}
		}
		catch (Throwable e) {
			System.err.println(e);
		}
		
		return todosAtributos;
	}
}
