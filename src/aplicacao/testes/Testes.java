package aplicacao.testes;

import java.util.ArrayList;

import javax.swing.JFrame;

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
import aplicacao.form.GerenciarFuncionarioGUI;
import aplicacao.form.SalvarDadosFuncionariosGUI;

public class Testes {
	public static void main(String[] args) {
		
		//---- Para Testes com o GerenciarFuncionarioGUI
		Pessoa x = new Pessoa();
		x.setNome("fulano de tal");
		x.setCpf("0000000");
		Pessoa y = new Pessoa();
		y.setNome("beltrano");
		y.setCpf("16120");
		Pessoa z = new Pessoa();
		z.setNome("ciclano");
		z.setCpf("414.465");
		Funcionario a = new Medico();
		a.setPessoa(x);
		a.setIdentificadorInterno("10000");
		Funcionario b = new Medico();
		b.setPessoa(y);
		b.setIdentificadorInterno("4000");
		Funcionario c = new Enfermeiro();
		c.setPessoa(z);
		c.setIdentificadorInterno("3333");
		
		final ArrayList<Funcionario> funcionariosPesquisados = new ArrayList<Funcionario>();
		funcionariosPesquisados.add(a);
		funcionariosPesquisados.add(b);
		funcionariosPesquisados.add(c);
		ImpressaoDeTestes idt = new ImpressaoDeTestes();
		ArrayList<Funcionario> medicos = new ArrayList<>();
		
		
		//--- Para testes de pesquisas com o DAO
		AdministradorDAO administrador = new AdministradorDAO();
		AtendenteDAO atendente = new AtendenteDAO();
		EnfermeiroDAO enfermeiro = new EnfermeiroDAO();
		MedicoDAO medico = new MedicoDAO();
		
		Pessoa p0 = new Pessoa();
		p0.setCpf("123456");
		p0.setNome("Icaro");
		
		Pessoa p1 = new Pessoa();
		p1.setCpf("3490384");
		p1.setNome("Felipe");
		
		Atendente a1 = new Atendente();
		a1.setIdFuncionario(-1);
		a1.setLogin("");
		a1.setSenha("65456");
		a1.setIdentificadorInterno("");
		a1.setStatusDeUsuario("");
		a1.setPessoa(null);
		a1.setCargo("");
		
		Enfermeiro e0 = new Enfermeiro();
		e0.setIdFuncionario(8);
		e0.setLogin("Fulano");
		e0.setSenha("123");
		e0.setIdentificadorInterno("Dai-50-002");
		e0.setStatusDeUsuario("Ativo");
		e0.setPessoa(null);
		e0.setNumeroDeRegistro(987);

		Enfermeiro e1 = new Enfermeiro();
		e1.setIdFuncionario(22999);
		e1.setLogin("Beltrano");
		e1.setSenha("487498");
		e1.setIdentificadorInterno("349832-50-32998439");
		e1.setStatusDeUsuario("Inativo");
		e1.setPessoa(p1);
		e1.setNumeroDeRegistro(0);

		Medico m1 = new Medico();
		m1.setIdFuncionario(1);
		m1.setLogin("Abc");
		m1.setSenha("123456");
		m1.setIdentificadorInterno("");
		m1.setStatusDeUsuario("");
		m1.setPessoa(null);
		m1.setNumeroDeRegistro(-1);
		m1.setEspecialidade("");
		
		Medico m0 = new Medico();
		m0.setIdFuncionario(10);
		m0.setLogin("Abc");
		m0.setSenha("123456");
		m0.setIdentificadorInterno("");
		m0.setStatusDeUsuario("");
		m0.setPessoa(p0);
		m0.setNumeroDeRegistro(-1);
		m0.setEspecialidade("");

		//--- Para testes do SalvarDadosFunconarioGUI
		JFrame frame1 = new GerenciarFuncionarioGUI(a1);
		
		
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
		//medico.alterar(m0)
		

	}
}
