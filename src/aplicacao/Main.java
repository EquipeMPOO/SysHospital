package aplicacao;

import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;

import com.mysql.jdbc.Connection;

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

public class Main {
		
	public static void main(String[] args) throws SQLException {
		
		JFrame primeiraTela = new LoginGUI();
		primeiraTela.setVisible(true);
		
	}
}
