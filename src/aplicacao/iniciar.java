package aplicacao;

import java.awt.EventQueue;

import javax.swing.JFrame;

import aplicacao.dominio.Administrador;
import aplicacao.dominio.Atendente;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Gerente;
import aplicacao.dominio.Medico;
import aplicacao.dominio.Pessoa;
import aplicacao.dao.AdministradorDAO;
import aplicacao.dao.AtendenteDAO;
import aplicacao.form.SalvarDadosFuncionariosGUI;
import aplicacao.dao.EnfermeiroDAO;
import aplicacao.form.GerenciarFuncionarioGUI;
import aplicacao.form.LoginGUI;
import aplicacao.dao.MedicoDAO;

public class iniciar {
		
	public static void main(String[] args) {
		
		JFrame primeiraTela = new LoginGUI();
		primeiraTela.setVisible(true);

		Administrador x = new Administrador();
		JFrame a = new GerenciarFuncionarioGUI(x);
		a.setVisible(true);
	}
}
