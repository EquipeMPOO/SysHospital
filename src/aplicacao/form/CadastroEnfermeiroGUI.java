package aplicacao.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import aplicacao.dao.EnfermeiroDAO;
import aplicacao.dao.PessoaDAO;
import aplicacao.dominio.*;
import aplicacao.enums.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.util.List;

import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CadastroEnfermeiroGUI extends JFrame {
	
	private Gerente usuario;
	private JPanel contentPane;
	private JTextField textFieldNome, textFieldCpf, textFieldLogin, textFieldNdeResgistro;
	private JComboBox sexoBox, tipoSangueBox, rhBox;
	private JButton btnSalvar, btnCancelar;
	private JLabel lblCadastro, lblNewLabel, lblSexo, lblCpf, lblTipoSanguneo, lblLogin, lblSenha, lblRh, lblNDeRegistro;
	private JPasswordField passwordField;
	private Enfermeiro novoEnfermeiro;


	/**
	 * Create the frame.
	 */
	public CadastroEnfermeiroGUI(Gerente user) {
		
		this.usuario = user;
		setTitle("SysHospital");
		
		
		// -- Configurando frame e contentPane
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 331);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);
		
		// -- Mehtodo para criar TextFields
		this.criarTextField();
		
		// -- Mehtodo para criar ComboBoxs
		this.criarComboBox();
		
		// -- Mehtodo para criar Buttons
		this.criarButton();
		
		// -- Mehtodo para criar Labels (textos estahticos)
		this.criarLabel();
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(68, 203, 336, 20);
		contentPane.add(passwordField);
		
		this.criarEventos();
		
	}
	
	public void criarTextField(){
		
		//this.textFieldNome
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(68, 79, 336, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		//this.textFieldCpf
		
		textFieldCpf = new JTextField();
		textFieldCpf.setColumns(10);
		textFieldCpf.setBounds(68, 110, 119, 20);
		contentPane.add(textFieldCpf);
		
		//this.textFieldLogin
		
		textFieldLogin = new JTextField();
		textFieldLogin.setColumns(10);
		textFieldLogin.setBounds(68, 172, 336, 20);
		contentPane.add(textFieldLogin);
		
		textFieldNdeResgistro = new JTextField();
		textFieldNdeResgistro.setColumns(10);
		textFieldNdeResgistro.setBounds(290, 110, 114, 20);
		contentPane.add(textFieldNdeResgistro);
	}
	
	public void criarComboBox(){
		
		//this.sexoBox
		
		sexoBox = new JComboBox();
		sexoBox.setModel(new DefaultComboBoxModel(new String[] {" ","M", "F"}));
		sexoBox.setBounds(68, 141, 38, 20);
		contentPane.add(sexoBox);
		
		//this.tipoSangueBox
		
		tipoSangueBox = new JComboBox();
		tipoSangueBox.setModel(new DefaultComboBoxModel(new String[] {" ","A", "B", "O", "AB"}));
		tipoSangueBox.setBounds(206, 141, 45, 20);
		contentPane.add(tipoSangueBox);
		
		//this.rhBox
		
		rhBox = new JComboBox();
		rhBox.setModel(new DefaultComboBoxModel(new String[] {" ","+", "-"}));
		rhBox.setBounds(290, 141, 33, 20);
		contentPane.add(rhBox);
		
	}
	
	
	public void criarButton(){
		
		//this.salvarBotao
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Georgia", Font.ITALIC, 12));
		btnSalvar.setBounds(10, 254, 100, 27);
		contentPane.add(btnSalvar);
		
		//this.cancelarBotao
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCancelar.setFont(new Font("Georgia", Font.ITALIC, 12));
		btnCancelar.setBounds(320, 254, 100, 27);
		contentPane.add(btnCancelar);
		
	}
	
	
	// -- cria os textos estahticos
	
	public void criarLabel(){
		
		lblCadastro = new JLabel("Cadastro de enfermeiro");
		lblCadastro.setFont(new Font("Georgia", Font.ITALIC, 25));
		lblCadastro.setBounds(91, 11, 266, 33);
		contentPane.add(lblCadastro);
		
		lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 81, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSexo.setBounds(10, 143, 46, 14);
		contentPane.add(lblSexo);
		
		lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpf.setBounds(10, 112, 46, 14);
		contentPane.add(lblCpf);
		
		lblTipoSanguneo = new JLabel("Tipo Sangu\u00EDneo");
		lblTipoSanguneo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipoSanguneo.setBounds(111, 141, 85, 14);
		contentPane.add(lblTipoSanguneo);
		
		lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLogin.setBounds(10, 174, 46, 14);
		contentPane.add(lblLogin);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSenha.setBounds(10, 205, 46, 14);
		contentPane.add(lblSenha);
			
		lblRh = new JLabel("RH");
		lblRh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRh.setBounds(261, 143, 29, 14);
		contentPane.add(lblRh);
		
		lblNDeRegistro = new JLabel("N\u00BA de registro");
		lblNDeRegistro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNDeRegistro.setBounds(197, 112, 83, 14);
		contentPane.add(lblNDeRegistro);
		
		
		
	}
	
	
	public void criarEventos(){
		
		Pessoa pessoa = new Pessoa();
		novoEnfermeiro = new Enfermeiro();
		novoEnfermeiro.setPessoa(pessoa);
				
		
		//-- Classes Internas para capturar dados dos JComboBoxes
				
		
		//-- Classes Internas para capturar cliques nos botoes
		class EventoSalvar implements ActionListener,ItemListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				novoEnfermeiro.getPessoa().setNome(textFieldNome.getText());;	
				novoEnfermeiro.getPessoa().setCpf(textFieldCpf.getText());;	
				novoEnfermeiro.setLogin(textFieldLogin.getText());	
				novoEnfermeiro.setSenha(passwordField.getText());
				
				int numero = Integer.parseInt(textFieldNdeResgistro.getText());
				
				novoEnfermeiro.setNumeroDeRegistro(numero);

				// ESTE METODO DEPENDE DO BANCO DE DADOS FORMADO PARA SER COMPLETAMENTE IMPLEMENTADO
				 PessoaDAO databasePessoa = new PessoaDAO();
				 int idPessoa = databasePessoa.cadastrar(novoEnfermeiro.getPessoa());
				 novoEnfermeiro.getPessoa().setIdPessoa(idPessoa);
				 EnfermeiroDAO databaseEnfermeiro = new EnfermeiroDAO();
				 databaseEnfermeiro.cadastrar(novoEnfermeiro);
				
			}
			
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED){
					String tipo = (String) tipoSangueBox.getSelectedItem();
					String rh = (String) rhBox.getSelectedItem();
					
					if (tipo == "A"){
						if (rh== "+"){
							novoEnfermeiro.getPessoa().setTipoSanguineo(TipoSanguineo.AP.getTipoSanguineo());
						}
						else if (rh=="-"){
							novoEnfermeiro.getPessoa().setTipoSanguineo(TipoSanguineo.AN.getTipoSanguineo());
						}
					}
								
					
					else if(tipo == "B"){
						if (rh == "+"){
							novoEnfermeiro.getPessoa().setTipoSanguineo(TipoSanguineo.BP.getTipoSanguineo());
						}
						
						else if (rh == "-"){
							novoEnfermeiro.getPessoa().setTipoSanguineo(TipoSanguineo.BN.getTipoSanguineo());
						}
						
					}
					
					
					else if(tipo == "O"){
						if (rh == "+"){
							novoEnfermeiro.getPessoa().setTipoSanguineo(TipoSanguineo.OP.getTipoSanguineo());
						}
						
						else if (rh=="-"){							
							novoEnfermeiro.getPessoa().setTipoSanguineo(TipoSanguineo.ON.getTipoSanguineo());
						}
						
						
					}
					
					else if(tipo == "AB"){
						if (rh == "+"){
							novoEnfermeiro.getPessoa().setTipoSanguineo(TipoSanguineo.ABP.getTipoSanguineo());
						}
						
						else if (rh=="-"){
							novoEnfermeiro.getPessoa().setTipoSanguineo(TipoSanguineo.ABN.getTipoSanguineo());
						}
						
						
					}
					
					
				
				}
					
			}	
		}

		
		
		class EventoCancelar implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFrame proximaTela = new GerenteGUI(usuario);
				proximaTela.setVisible(true);
				CadastroEnfermeiroGUI.this.dispose();
				
			}	
			
			
		}
		
		
		
		
		EventoSalvar eventoSalvar = new EventoSalvar();
		btnSalvar.addActionListener(eventoSalvar);
		EventoCancelar eventoCancelar = new EventoCancelar();
		btnCancelar.addActionListener(eventoCancelar);		
		
		
		
	}
	
	
	
	
	/*
	// Para testes com a tela.
	
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Atendente at = new Atendente();
					CadastroEnfermeiroGUI frame = new CadastroEnfermeiroGUI(at);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
}
