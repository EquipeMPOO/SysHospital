package aplicacao.testes;

import java.util.ArrayList;

import aplicacao.dao.AdministradorDAO;
import aplicacao.dao.AtendenteDAO;
import aplicacao.dao.EnfermeiroDAO;
import aplicacao.dao.MedicoDAO;
import aplicacao.dao.util.ComandoSQL;
import aplicacao.dominio.Administrador;
import aplicacao.dominio.Atendente;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Medico;
import aplicacao.dominio.Pessoa;

public class Testes {
	public static void main(String[] args) {
		
		ImpressaoDeTestes idt = new ImpressaoDeTestes();
		ArrayList<Funcionario> medicos = new ArrayList<>();
		
		AdministradorDAO administrador = new AdministradorDAO();
		AtendenteDAO atendente = new AtendenteDAO();
		EnfermeiroDAO enfermeiro = new EnfermeiroDAO();
		MedicoDAO medico = new MedicoDAO();
		
		Pessoa p0 = new Pessoa();
		
		Administrador a0 = new Administrador();
		a0.setIdFuncionario(-1);
		a0.setLogin("");
		a0.setSenha("");
		a0.setIdentificadorInterno("");
		a0.setStatusDeUsuario("Inati p");
		a0.setPessoa(null);
		a0.setCargo("");
		
		Atendente a1 = new Atendente();
		a1.setIdFuncionario(-1);
		a1.setLogin("");
		a1.setSenha("65456");
		a1.setIdentificadorInterno("");
		a1.setStatusDeUsuario("");
		a1.setPessoa(null);
		a1.setCargo("");
		
		Enfermeiro e0 = new Enfermeiro();
		e0.setIdFuncionario(-1);
		e0.setLogin("");
		e0.setSenha("");
		e0.setIdentificadorInterno("Dai-50-002");
		e0.setStatusDeUsuario("");
		e0.setPessoa(null);
		e0.setNumeroDeRegistro(-1);
		
		Medico m0 = new Medico();
		m0.setIdFuncionario(1);
		m0.setLogin("Abc");
		m0.setSenha("123456");
		m0.setIdentificadorInterno("");
		m0.setStatusDeUsuario("");
		m0.setPessoa(null);
		m0.setNumeroDeRegistro(-1);
		m0.setEspecialidade("");
		
		/*
		// Teste dos métodos de Administrador:
		idt.imprimirAdministrador( (ArrayList<Funcionario>) administrador.pesquisarTodos(false) );
		idt.imprimirAdministrador( (ArrayList<Funcionario>) administrador.pesquisarFiltrando(a0, false) );
		idt.imprimirAdministrador( (ArrayList<Funcionario>) administrador.pesquisarAlgum(a0, false) );
		
		// Teste dos métodos de Atendente:
		idt.imprimirAtendente( (ArrayList<Funcionario>) atendente.pesquisarTodos(false) );
		idt.imprimirAtendente( (ArrayList<Funcionario>) atendente.pesquisarFiltrando(a1, false) );
		idt.imprimirAtendente( (ArrayList<Funcionario>) atendente.pesquisarAlgum(a1, false) );
		
		// Teste dos métodos de Enfermeiro:
		idt.imprimirEnfermeiro( (ArrayList<Funcionario>) enfermeiro.pesquisarTodos(false) );
		idt.imprimirEnfermeiro( (ArrayList<Funcionario>) enfermeiro.pesquisarFiltrando(e0, false) );
		idt.imprimirEnfermeiro( (ArrayList<Funcionario>) enfermeiro.pesquisarAlgum(e0, false) );
		
		// Teste dos métodos de Medico:
		idt.imprimirMedicos( (ArrayList<Funcionario>) medico.pesquisarTodos(false) );
		idt.imprimirMedicos( (ArrayList<Funcionario>) medico.pesquisarFiltrando(m0, false) );
		idt.imprimirMedicos( (ArrayList<Funcionario>) medico.pesquisarAlgum(m0, false) );*/
		/*
		ComandoSQL codigo = new ComandoSQL();
    	String comandoSQL = codigo.gerarAtualizacao(m0, 1, 1);
    	*/
    	
		//idt.imprimirMedicos( (ArrayList<Funcionario>) medico.pesquisarTodos(false) );
		//medico.alterar(m0);
		
	}
}
