package aplicacao.testes;

import java.util.ArrayList;

import javax.swing.JFrame;

import aplicacao.dao.AdministradorDAO;
import aplicacao.dao.AtendenteDAO;
import aplicacao.dao.AtendimentoDAO;
import aplicacao.dao.EnfermeiroDAO;
import aplicacao.dao.EnfermidadeDAO;
import aplicacao.dao.EntradaDAO;
import aplicacao.dao.MedicoDAO;
import aplicacao.dao.PacienteDAO;
import aplicacao.dominio.Administrador;
import aplicacao.dominio.Atendente;
import aplicacao.dominio.Atendimento;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Enfermidade;
import aplicacao.dominio.Entrada;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Medico;
import aplicacao.dominio.Paciente;
import aplicacao.dominio.Pessoa;
import aplicacao.enums.Sexo;
import aplicacao.enums.StatusDePessoa;
import aplicacao.enums.TipoSanguineo;
import aplicacao.form.GerenciarFuncionarioGUI;
import aplicacao.form.SalvarDadosFuncionariosGUI;

public class Testes {
	public static void main(String[] args) {
		
//		Paciente paciente = new Paciente();
//		Pessoa p0 = new Pessoa();
//		p0.setCpf("123456");
//		p0.setNome("Icaro");
//		p0.setStatusDePessoa(StatusDePessoa.V.getStatus());
//		p0.setSexo("M");
//		p0.setTipoSanguineo(TipoSanguineo.ABN.getTipoSanguineo());
//		p0.setIdade(18);
		
//		paciente.setPessoa(p0);
		EntradaDAO db = new EntradaDAO();
//		db.cadastrar(paciente);
		
		Entrada e = new Entrada();
		e.setIdEntrada(3);
		
		for (Atendimento at : db.procurarAtendimentos(e)) {
			System.out.println(at.getAltura());
			System.out.println(at.getIdAtentimento());
			System.out.println(at.getEnfermeiro().getPessoa().getNome());
			System.out.println(at.getMedico().getIdFuncionario());
		}
		
		
//		db.liberar(p);
		
//		
//		
//		//---- Para Testes com o GerenciarFuncionarioGUI
//		Pessoa x = new Pessoa();
//		x.setNome("fulano de tal");
//		x.setCpf("0000000");
//		Pessoa y = new Pessoa();
//		y.setNome("beltrano");
//		y.setCpf("16120");
//		Pessoa z = new Pessoa();
//		z.setNome("ciclano");
//		z.setCpf("414.465");
//		Funcionario a = new Medico();
//		a.setPessoa(x);
//		Funcionario b = new Medico();
//		b.setPessoa(y);
//		Funcionario c = new Enfermeiro();
//		c.setPessoa(z);
//		
//		final ArrayList<Funcionario> funcionariosPesquisados = new ArrayList<Funcionario>();
//		funcionariosPesquisados.add(a);
//		funcionariosPesquisados.add(b);
//		funcionariosPesquisados.add(c);
//		ArrayList<Funcionario> medicos = new ArrayList<>();
//		
//		
//		//--- Para testes de pesquisas com o DAO
//		AdministradorDAO administrador = new AdministradorDAO();
//		AtendenteDAO atendente = new AtendenteDAO();
//		EnfermeiroDAO enfermeiro = new EnfermeiroDAO();
//		MedicoDAO medico = new MedicoDAO();
//		
//		Pessoa p0 = new Pessoa();
//		p0.setCpf("123456");
//		p0.setNome("Icaro");
//		
//		Pessoa p1 = new Pessoa();
//		p1.setCpf("3490384");
//		p1.setNome("Felipe");
//		
//		Atendente a1 = new Atendente();
//		a1.setIdFuncionario(-1);
//		a1.setLogin("");
//		a1.setSenha("65456");
//		a1.setStatusDeUsuario("");
//		a1.setPessoa(null);
//		a1.setCargo("");
//		
//		Enfermeiro e0 = new Enfermeiro();
//		e0.setIdFuncionario(8);
//		e0.setLogin("Fulano");
//		e0.setSenha("123");
//		e0.setStatusDeUsuario("Ativo");
//		e0.setPessoa(null);
//		e0.setNumeroDeRegistro(987);
//
//		Enfermeiro e1 = new Enfermeiro();
//		e1.setIdFuncionario(22999);
//		e1.setLogin("Beltrano");
//		e1.setSenha("487498");
//		e1.setStatusDeUsuario("Inativo");
//		e1.setPessoa(p1);
//		e1.setNumeroDeRegistro(0);
//
//		Medico m1 = new Medico();
//		m1.setIdFuncionario(1);
//		m1.setLogin("Abc");
//		m1.setSenha("123456");
//		m1.setStatusDeUsuario("");
//		m1.setPessoa(null);
//		m1.setNumeroDeRegistro(-1);
//		m1.setEspecialidade("");
//		
//		Medico m0 = new Medico();
//		m0.setIdFuncionario(10);
//		m0.setLogin("Abc");
//		m0.setSenha("123456");
//		m0.setStatusDeUsuario("");
//		m0.setPessoa(p0);
//		m0.setNumeroDeRegistro(-1);
//		m0.setEspecialidade("");
//
//		//--- Para testes do SalvarDadosFunconarioGUI
//		JFrame frame1 = new GerenciarFuncionarioGUI(a1);
//		
//		
//		/*
//		// Teste dos m�todos de Administrador:
//		idt.imprimirAdministrador( (ArrayList<Funcionario>) administrador.pesquisarTodos(false) );
//		idt.imprimirAdministrador( (ArrayList<Funcionario>) administrador.pesquisarFiltrando(a0, false) );
//		idt.imprimirAdministrador( (ArrayList<Funcionario>) administrador.pesquisarAlgum(a0, false) );
//		
//		// Teste dos m�todos de Atendente:
//		idt.imprimirAtendente( (ArrayList<Funcionario>) atendente.pesquisarTodos(false) );
//		idt.imprimirAtendente( (ArrayList<Funcionario>) atendente.pesquisarFiltrando(a1, false) );
//		idt.imprimirAtendente( (ArrayList<Funcionario>) atendente.pesquisarAlgum(a1, false) );
//		
//		// Teste dos m�todos de Enfermeiro:
//		idt.imprimirEnfermeiro( (ArrayList<Funcionario>) enfermeiro.pesquisarTodos(false) );
//		idt.imprimirEnfermeiro( (ArrayList<Funcionario>) enfermeiro.pesquisarFiltrando(e0, false) );
//		idt.imprimirEnfermeiro( (ArrayList<Funcionario>) enfermeiro.pesquisarAlgum(e0, false) );
//		
//		// Teste dos m�todos de Medico:
//		idt.imprimirMedicos( (ArrayList<Funcionario>) medico.pesquisarTodos(false) );
//		idt.imprimirMedicos( (ArrayList<Funcionario>) medico.pesquisarFiltrando(m0, false) );
//		idt.imprimirMedicos( (ArrayList<Funcionario>) medico.pesquisarAlgum(m0, false) );*/
//		/*
//		ComandoSQL codigo = new ComandoSQL();
//    	String comandoSQL = codigo.gerarAtualizacao(m0, 1, 1);
//    	*/
//    	
//		//idt.imprimirMedicos( (ArrayList<Funcionario>) medico.pesquisarTodos(false) );
//		//medico.alterar(m0)
		

	}
}
