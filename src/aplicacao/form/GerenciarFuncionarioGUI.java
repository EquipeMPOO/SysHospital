package aplicacao.form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import aplicacao.controle.PesquisarFuncionarioControle;
import aplicacao.dao.EnfermeiroDAO;
import aplicacao.dao.MedicoDAO;
import aplicacao.dominio.*;
import aplicacao.enums.StatusDeUsuario;

public class GerenciarFuncionarioGUI extends JFrame {
	
	private Gerente usuario;
	private JPanel contentPane;
	private JComboBox funcionarioBox;
	private JButton btnInativar, btnAlterarDados, btnVoltar;
	private JTextPane textPane;
	private JLabel lblGerenciarFuncionrio;

	/**
	 * Create the frame.
	 */
	public GerenciarFuncionarioGUI(Gerente user) {
		
		this.usuario = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 345);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Georgia", Font.ITALIC, 11));
		btnVoltar.setBackground(SystemColor.activeCaption);
		btnVoltar.setBounds(10, 272, 89, 23);
		contentPane.add(btnVoltar);
			
		lblGerenciarFuncionrio = new JLabel("Gerenciar funcion\u00E1rio");
		lblGerenciarFuncionrio.setFont(new Font("Georgia", Font.ITALIC, 25));
		lblGerenciarFuncionrio.setBounds(127, 11, 256, 33);
		contentPane.add(lblGerenciarFuncionrio);
		
		btnInativar = new JButton("Inativar");
		btnInativar.setFont(new Font("Georgia", Font.ITALIC, 11));
		btnInativar.setBounds(365, 272, 117, 23);
		
		btnAlterarDados = new JButton("Alterar dados");
		btnAlterarDados.setFont(new Font("Georgia", Font.ITALIC, 11));
		btnAlterarDados.setBounds(365, 238, 117, 23);
		
		textPane = new JTextPane();
		textPane.setBackground(SystemColor.inactiveCaptionBorder);
		textPane.setBounds(10, 92, 472, 135);
		
		this.configurarJComboBox();
		
		this.setVisible(true);
	}
	
	public void configurarJComboBox(){
		
		//Cria um objeto da classe PesquisarFuncionarioControle e em seguida utiliza o metodo pesquisar para retornar uma lista de todos os usuarios pesquisados
//		PesquisarFuncionarioControle controlePesquisa = new PesquisarFuncionarioControle();
//		final List<Funcionario> funcionariosPesquisados = controlePesquisa.pesquisar();
//		
		
		
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

		Vector listaComboBox = new Vector();
		listaComboBox.add("Pesquisar Funcionario...");
		
		for (Funcionario object : funcionariosPesquisados) {
			listaComboBox.add(object.getPessoa().getNome());
		}
			
		funcionarioBox = new JComboBox(listaComboBox);
		funcionarioBox.addActionListener(new ActionListener(){	

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (funcionarioBox.getSelectedIndex() == 0){
					for (Component item : contentPane.getComponents()) {
						if (item == textPane || item == btnAlterarDados || item == btnInativar){
							contentPane.remove(item);
						}
					}
				}
				
				if (funcionarioBox.getSelectedIndex() != 0){
					
					contentPane.add(btnAlterarDados);
					contentPane.add(btnInativar);
					
					String texto = "Cargo: " + funcionariosPesquisados.get(funcionarioBox.getSelectedIndex()-1).getClass() + "\n"
							+ "Nome: " +funcionariosPesquisados.get(funcionarioBox.getSelectedIndex()-1).getPessoa().getNome() + "\n"
							+ "CPF: " + funcionariosPesquisados.get(funcionarioBox.getSelectedIndex()-1).getPessoa().getCpf() + "\n"
							+ "Identificador Interno: " + funcionariosPesquisados.get(funcionarioBox.getSelectedIndex()-1).getIdentificadorInterno();						
					
					textPane.setText(texto);
					contentPane.add(textPane);
				}				
				
			}

		});
	
		funcionarioBox.setBounds(50, 61, 392, 20);
		contentPane.add(funcionarioBox);
		
	}
	

}
