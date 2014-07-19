package aplicacao.dao.util;

import aplicacao.dominio.Administrador;
import aplicacao.dominio.Atendente;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Medico;

public class Comparacao {
	
	/**
	 * Este método dedica-se a testar se um obijeto possui os mesmos parâmetros de outro
	 * através de comparação feita pelos parâmetros encontrados neste, permitindo assim uma compação
	 * com conjunção de parâmetros (ou seja, um objeto filtrador).
	 * @param administradorDeParametro - um médico passado pelo usuário;
	 * @param administradorEmAnalise - um médico existente no banco de dados.
	 * @return boolean - quando verdadeiro indica que o objeto é compatível e quando falso incompatível.
	 */
	public boolean eFiltro(Administrador administradorDeParametro, Administrador administradorEmAnalise) {
		
		// 
		boolean testea = (administradorEmAnalise.getLogin().equals(administradorDeParametro.getLogin()));
		boolean testeb = (administradorEmAnalise.getSenha().equals(administradorDeParametro.getSenha()));
		boolean testec = (administradorEmAnalise.getIdentificadorInterno().equals(administradorDeParametro.getIdentificadorInterno()));
		boolean tested = (administradorEmAnalise.getStatusDeUsuario().equals(administradorDeParametro.getStatusDeUsuario()));
		// ----0> boolean testee - teste para pessoa
		boolean testef = (administradorEmAnalise.getCargo().equals(administradorDeParametro.getCargo()));
		
		// 
		boolean teste1 = administradorDeParametro.getLogin().equals(null) || administradorDeParametro.getLogin().equals("") || testea;
		boolean teste2 = administradorDeParametro.getSenha().equals(null) || administradorDeParametro.getSenha().equals("") || testeb;
		boolean teste3 = administradorDeParametro.getIdentificadorInterno() == null || administradorDeParametro.getIdentificadorInterno().equals("") || testec;
		boolean teste4 = administradorDeParametro.getStatusDeUsuario() == null || administradorDeParametro.getStatusDeUsuario().equals("") || tested;
		// ----0> boolean testee - teste para pessoa
		boolean teste6 = administradorDeParametro.getCargo() == null || administradorDeParametro.getCargo().equals("") || testef;
		
		boolean resultado = (teste1 & teste2 & teste3 & teste4 & teste6) && (testea || testeb || testec || tested || testef);
		
		/*----x>  Apenas para teste de valor de casa 'teste';
		System.out.println(
				"Os testes: " + "\n" + medicodeparametro.getUsuario() + medicobd.getUsuario()+
				"a: " + testea + "\n" + 
				"b: " + testeb + "\n" + 
				"c: " + testec + "\n" + 
				"d: " + tested + "\n" + 
				"f: " + testef + "\n" + 
				"1: " + teste1 + "\n" + 
				"2: " + teste2 + "\n" + 
				"3: " + teste3 + "\n" + 
				"4: " + teste4 + "\n" + 
				"6: " + teste6
		);
		boolean r0 = (teste1 & teste2 & teste3 & teste4 & teste6);
		boolean r1 = (testea || testeb || testec || tested || testef);
		System.out.println("Resultado da filtragem: " + resultado + " (" + r0 + " && " + r1 + ").\n");
		*/
		
		return resultado;
	}
	
	/**
	 * Este método dedica-se a testar se um obijeto possui os mesmos parâmetros de outro
	 * através de comparação feita pelos parâmetros encontrados neste, permitindo assim uma compação
	 * com conjunção de parâmetros (ou seja, um objeto filtrador).
	 * @param atendenteDeParametro - um médico passado pelo usuário;
	 * @param atendenteEmAnalise - um médico existente no banco de dados.
	 * @return boolean - quando verdadeiro indica que o objeto é compatível e quando falso incompatível.
	 */
	public boolean eFiltro(Atendente atendenteDeParametro, Atendente atendenteEmAnalise) {
		
		// 
		boolean testea = (atendenteEmAnalise.getLogin().equals(atendenteDeParametro.getLogin()));
		boolean testeb = (atendenteEmAnalise.getSenha().equals(atendenteDeParametro.getSenha()));
		boolean testec = (atendenteEmAnalise.getIdentificadorInterno().equals(atendenteDeParametro.getIdentificadorInterno()));
		boolean tested = (atendenteEmAnalise.getStatusDeUsuario().equals(atendenteDeParametro.getStatusDeUsuario()));
		// ----0> boolean testee - teste para pessoa
		boolean testef = (atendenteEmAnalise.getCargo().equals(atendenteDeParametro.getCargo()));
		
		// 
		boolean teste1 = atendenteDeParametro.getLogin() == null || atendenteDeParametro.getLogin().equals("") || testea;
		boolean teste2 = atendenteDeParametro.getSenha() == null || atendenteDeParametro.getSenha().equals("") || testeb;
		boolean teste3 = atendenteDeParametro.getIdentificadorInterno() == null || atendenteDeParametro.getIdentificadorInterno().equals("") || testec;
		boolean teste4 = atendenteDeParametro.getStatusDeUsuario() == null || atendenteDeParametro.getStatusDeUsuario().equals("") || tested;
		// ----0> boolean testee - teste para pessoa
		boolean teste6 = atendenteDeParametro.getCargo() == null || atendenteDeParametro.getCargo().equals("") || testef;
		
		boolean resultado = (teste1 & teste2 & teste3 & teste4 & teste6) && (testea || testeb || testec || tested || testef);
		
		/*// ----x>  Apenas para teste de valor de casa 'teste';
		System.out.println(
				"Os testes: " + "\n" + atendenteDeParametro.getLogin() + atendenteEmAnalise.getLogin() +
				"a: " + testea + "\n" + 
				"b: " + testeb + "\n" + 
				"c: " + testec + "\n" + 
				"d: " + tested + "\n" + 
				"f: " + testef + "\n" + 
				"1: " + teste1 + "\n" + 
				"2: " + teste2 + "\n" + 
				"3: " + teste3 + "\n" + 
				"4: " + teste4 + "\n" + 
				"6: " + teste6
		);
		boolean r0 = (teste1 & teste2 & teste3 & teste4 & teste6);
		boolean r1 = (testea || testeb || testec || tested || testef);
		System.out.println("Resultado da filtragem: " + resultado + " (" + r0 + " && " + r1 + ").\n");
		*/
		
		return resultado;
	}
	
	/**
	 * Este método dedica-se a testar se um obijeto possui os mesmos parâmetros de outro
	 * através de comparação feita pelos parâmetros encontrados neste, permitindo assim uma compação
	 * com conjunção de parâmetros (ou seja, um objeto filtrador).
	 * @param enfermeiroDeParametro - um médico passado pelo usuário;
	 * @param enfermeiroEmAnalise - um médico existente no banco de dados.
	 * @return boolean - quando verdadeiro indica que o objeto é compatível e quando falso incompatível.
	 */
	public boolean eFiltro(Enfermeiro enfermeiroDeParametro, Enfermeiro enfermeiroEmAnalise) {
		
		// 
		boolean testea = (enfermeiroEmAnalise.getLogin().equals(enfermeiroDeParametro.getLogin()));
		boolean testeb = (enfermeiroEmAnalise.getSenha().equals(enfermeiroDeParametro.getSenha()));
		boolean testec = (enfermeiroEmAnalise.getIdentificadorInterno().equals(enfermeiroDeParametro.getIdentificadorInterno()));
		boolean tested = (enfermeiroEmAnalise.getStatusDeUsuario().equals(enfermeiroDeParametro.getStatusDeUsuario()));
		// ----0> boolean testee - teste para pessoa
		boolean testef = (enfermeiroEmAnalise.getNumeroDeRegistro() == enfermeiroDeParametro.getNumeroDeRegistro());
		
		// 
		boolean teste1 = enfermeiroDeParametro.getLogin() == null || enfermeiroDeParametro.getLogin().equals("") || testea;
		boolean teste2 = enfermeiroDeParametro.getSenha() == null || enfermeiroDeParametro.getSenha().equals("") || testeb;
		boolean teste3 = enfermeiroDeParametro.getIdentificadorInterno() == null || enfermeiroDeParametro.getIdentificadorInterno().equals("") || testec;
		boolean teste4 = enfermeiroDeParametro.getStatusDeUsuario() == null || enfermeiroDeParametro.getStatusDeUsuario().equals("") || tested;
		// ----0> boolean testee - teste para pessoa
		boolean teste6 = !(enfermeiroDeParametro.getNumeroDeRegistro() > 0 ) || testef;
		
		boolean resultado = (teste1 & teste2 & teste3 & teste4 & teste6) && (testea || testeb || testec || tested || testef);
		
		/*----x>  Apenas para teste de valor de casa 'teste';
		System.out.println(
				"Os testes: " + "\n" + medicodeparametro.getUsuario() + medicobd.getUsuario()+
				"a: " + testea + "\n" + 
				"b: " + testeb + "\n" + 
				"c: " + testec + "\n" + 
				"d: " + tested + "\n" + 
				"f: " + testef + "\n" + 
				"g: " + testeg + "\n" + 
				"1: " + teste1 + "\n" + 
				"2: " + teste2 + "\n" + 
				"3: " + teste3 + "\n" + 
				"4: " + teste4 + "\n" + 
				"6: " + teste6 + "\n" + 
				"7: " + teste7
		);
		boolean r0 = (teste1 & teste2 & teste3 & teste4 & teste6 & teste7);
		boolean r1 = (testea || testeb || testec || tested || testef || testeg);
		System.out.println("Resultado da filtragem: " + resultado + " (" + r0 + " && " + r1 + ").\n");
		*/
		
		return resultado;
	}
	
	/**
	 * Este método dedica-se a testar se um obijeto possui os mesmos parâmetros de outro
	 * através de comparação feita pelos parâmetros encontrados neste, permitindo assim uma compação
	 * com conjunção de parâmetros (ou seja, um objeto filtrador).
	 * @param medicoDeParametro - um médico passado pelo usuário;
	 * @param medicoEmAnalise - um médico existente no banco de dados.
	 * @return boolean - quando verdadeiro indica que o objeto é compatível e quando falso incompatível.
	 */
	public boolean eFiltro(Medico medicoDeParametro, Medico medicoEmAnalise) {
		
		// Verificando a igualdade entre os parâmetros. O(Um dos) medico(s) procurado(s) tem ao menos um atributo igual a algum médico do banco.
		boolean testea = (medicoEmAnalise.getLogin().equals(medicoDeParametro.getLogin()));
		boolean testeb = (medicoEmAnalise.getSenha().equals(medicoDeParametro.getSenha()));
		boolean testec = (medicoEmAnalise.getIdentificadorInterno().equals(medicoDeParametro.getIdentificadorInterno()));
		boolean tested = (medicoEmAnalise.getStatusDeUsuario().equals(medicoDeParametro.getStatusDeUsuario()));
		// ----0> boolean testee - teste para pessoa
		boolean testef = (medicoEmAnalise.getNumeroDeRegistro() == medicoDeParametro.getNumeroDeRegistro());
		boolean testeg = (medicoEmAnalise.getEspecialidade().equals(medicoDeParametro.getEspecialidade()));
		
		// Verificando a validade de filtro de cada parâmetro. Campo cheio e atributo desigual => este não é o(um dos) medico(s) procurado(s).
		boolean teste1 = medicoDeParametro.getLogin() == null || medicoDeParametro.getLogin().equals("") || testea;
		boolean teste2 = medicoDeParametro.getSenha() == null || medicoDeParametro.getSenha().equals("") || testeb;
		boolean teste3 = medicoDeParametro.getIdentificadorInterno() == null || medicoDeParametro.getIdentificadorInterno().equals("") || testec;
		boolean teste4 = medicoDeParametro.getStatusDeUsuario() == null || medicoDeParametro.getStatusDeUsuario().equals("") || tested;
		// ----0> boolean testee - teste para pessoa
		boolean teste6 = !(medicoDeParametro.getNumeroDeRegistro() > 0 ) || testef;
		boolean teste7 = medicoDeParametro.getEspecialidade() == null || medicoDeParametro.getEspecialidade().equals("") || testeg;
		
		boolean resultado = (teste1 & teste2 & teste3 & teste4 & teste6 & teste7) && (testea || testeb || testec || tested || testef || testeg);
		
		/*----x>  Apenas para teste de valor de casa 'teste';
		System.out.println(
				"Os testes: " + "\n" + medicodeparametro.getUsuario() + medicobd.getUsuario()+
				"a: " + testea + "\n" + 
				"b: " + testeb + "\n" + 
				"c: " + testec + "\n" + 
				"d: " + tested + "\n" + 
				"f: " + testef + "\n" + 
				"g: " + testeg + "\n" + 
				"1: " + teste1 + "\n" + 
				"2: " + teste2 + "\n" + 
				"3: " + teste3 + "\n" + 
				"4: " + teste4 + "\n" + 
				"6: " + teste6 + "\n" + 
				"7: " + teste7
		);
		boolean r0 = (teste1 & teste2 & teste3 & teste4 & teste6 & teste7);
		boolean r1 = (testea || testeb || testec || tested || testef || testeg);
		System.out.println("Resultado da filtragem: " + resultado + " (" + r0 + " && " + r1 + ").\n");
		*/
		
		return resultado;
	}
	
}
