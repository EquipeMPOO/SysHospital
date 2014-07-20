package aplicacao;

import java.awt.EventQueue;

import javax.swing.JFrame;

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
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

					
					JFrame frame1 = new SalvarDadosFuncionariosGUI(a1,m0);
					JFrame frame2 = new SalvarDadosFuncionariosGUI(a1,m1);
					JFrame frame3 = new SalvarDadosFuncionariosGUI(a1,e0);
					JFrame frame4 = new SalvarDadosFuncionariosGUI(a1,e1);
					frame3.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
