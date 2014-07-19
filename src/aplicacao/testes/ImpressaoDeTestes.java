package aplicacao.testes;

import java.util.ArrayList;

import aplicacao.dominio.Administrador;
import aplicacao.dominio.Atendente;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Medico;

public class ImpressaoDeTestes {
	
	int vezesImpressasDeAdm = 0;
	int vezesImpressasDeAte = 0;
	int vezesImpressasDeEnf = 0;
	int vezesImpressasDeMed = 0;
	
	public void imprimirAdministrador(ArrayList<Funcionario> administradores){
		
		vezesImpressasDeAdm = vezesImpressasDeAdm + 1;
		System.out.println("Vezes impressas de administrador: " + vezesImpressasDeAdm + " ------------\n");
		for (Funcionario f : administradores) {
			Administrador a1 = new Administrador();
			a1 = (Administrador) f;
			
			System.out.println(
				a1 + "\n" + 
				"Id: " + a1.getIdFuncionario() + "\n" + 
				"Usuario: " + a1.getLogin() + "\n" + 
				"Senha: " + a1.getSenha() + "\n" + 
				"Identificador interno: " + a1.getIdentificadorInterno() + "\n" + 
				"Status de usuario: " + a1.getStatusDeUsuario() + "\n" + 
	    		//m.getPessoa();
	    		"Cargo: " + a1.getCargo() + "\n"
			);
		}
		System.out.println("");
	}
	
	public void imprimirAtendente(ArrayList<Funcionario> atendentes){
		
		vezesImpressasDeAte = vezesImpressasDeAte + 1;
		System.out.println("Vezes impressas de atendente: " + vezesImpressasDeAte + " ------------\n");
		for (Funcionario f : atendentes) {
			Atendente a1 = new Atendente();
			a1 = (Atendente) f;
			
			System.out.println(
				a1 + "\n" + 
				"Id: " + a1.getIdFuncionario() + "\n" + 
				"Usuario: " + a1.getLogin() + "\n" + 
				"Senha: " + a1.getSenha() + "\n" + 
				"Identificador interno: " + a1.getIdentificadorInterno() + "\n" + 
				"Status de usuario: " + a1.getStatusDeUsuario() + "\n" + 
	    		//m.getPessoa();
	    		"Cargo: " + a1.getCargo() + "\n"
			);
		}
		System.out.println("");
	}
	
	public void imprimirEnfermeiro(ArrayList<Funcionario> enfermeiro){
		
		vezesImpressasDeEnf = vezesImpressasDeEnf + 1;
		System.out.println("Vezes impressas de enfermeiro: " + vezesImpressasDeEnf + " ------------\n");
		for (Funcionario f : enfermeiro) {
			Enfermeiro m1 = new Enfermeiro();
			m1 = (Enfermeiro) f;
			
			System.out.println(
				m1 + "\n" + 
				"Id: " + m1.getIdFuncionario() + "\n" + 
				"Usuario: " + m1.getLogin() + "\n" + 
				"Senha: " + m1.getSenha() + "\n" + 
				"Identificador interno: " + m1.getIdentificadorInterno() + "\n" + 
				"Status de usuario: " + m1.getStatusDeUsuario() + "\n" + 
	    		//m.getPessoa();
	    		"Numero de registro: " + m1.getNumeroDeRegistro() + "\n"
			);
		}
		System.out.println("");
	}
	
	public void imprimirMedicos(ArrayList<Funcionario> medicos){
		
		vezesImpressasDeMed = vezesImpressasDeMed + 1;
		System.out.println("Vezes impressas de medico: " + vezesImpressasDeMed + " ------------\n");
		for (Funcionario f : medicos) {
			Medico m1 = new Medico();
			m1 = (Medico) f;
			
			System.out.println(
				m1 + "\n" + 
				"Id: " + m1.getIdFuncionario() + "\n" + 
				"Usuario: " + m1.getLogin() + "\n" + 
				"Senha: " + m1.getSenha() + "\n" + 
				"Identificador interno: " + m1.getIdentificadorInterno() + "\n" + 
				"Status de usuario: " + m1.getStatusDeUsuario() + "\n" + 
	    		//m.getPessoa();
	    		"Numero de registro: " + m1.getNumeroDeRegistro() + "\n" + 
	    		"Especialidade: " + m1.getEspecialidade() + "\n"
			);
		}
		System.out.println("");
	}
}
