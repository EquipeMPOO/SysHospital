package aplicacao.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

import aplicacao.dao.MedicoDAO;
import aplicacao.dao.PessoaDAO;
import aplicacao.dominio.*;
import aplicacao.enums.*;

public class CadastroMedicoGUI extends JFrame {

	private Gerente usuario;
	private JPanel contentPane;
	private JTextField textFieldNome, textFieldCpf, textFieldLogin, textFieldNdeRegistro;
	private JPasswordField textFieldSenha;
	private JComboBox SexoBox, tipoSangueBox, rhBox, especializacaoBox;
	private JButton salvarBotao, cancelarBotao;
	private JLabel lblCadastroDeMdico, lblNome, lblSexo, lblCpf, lblTipoSangue, lblLogin, lblSenha, lblRh, lblNdeRegistro, lblEspecializao;
	private Medico novoMedico;
	
	/**
	 * Create the frame.
	 */
	public CadastroMedicoGUI(Gerente usuario) {
		this.usuario = usuario;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(SystemColor.inactiveCaption);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		this.criarButton();
		this.criarComboBox();
		this.criarLabel();
		this.criarPasswordField();
		this.criarTextField();
		
		this.criarEventos();
		
		
		this.setVisible(true);
		
	}
		
	
	public void criarTextField(){
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(68, 79, 249, 20);
		contentPane.add(textFieldNome);
		
		textFieldCpf = new JTextField();
		textFieldCpf.setColumns(10);
		textFieldCpf.setBounds(68, 110, 119, 20);
		contentPane.add(textFieldCpf);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setColumns(10);
		textFieldLogin.setBounds(68, 211, 336, 20);
		contentPane.add(textFieldLogin);
		
		textFieldNdeRegistro = new JTextField();
		textFieldNdeRegistro.setColumns(10);
		textFieldNdeRegistro.setBounds(290, 110, 114, 20);
		contentPane.add(textFieldNdeRegistro);
		
	}
	
	
	
	
	public void criarComboBox(){
		
		
		SexoBox = new JComboBox();
		SexoBox.setModel(new DefaultComboBoxModel(new String[] {" ","F", "M"}));
		SexoBox.setBounds(371, 79, 33, 20);
		contentPane.add(SexoBox);
		
		tipoSangueBox = new JComboBox();
		tipoSangueBox.setModel(new DefaultComboBoxModel(new String[] {" ","A", "B", "O", "AB"}));
		tipoSangueBox.setBounds(260, 169, 38, 20);
		contentPane.add(tipoSangueBox);
		
		rhBox = new JComboBox();
		rhBox.setModel(new DefaultComboBoxModel(new String[] {" ","+", "-"}));
		rhBox.setBounds(366, 169, 38, 20);
		contentPane.add(rhBox);
		
		especializacaoBox = new JComboBox();
		especializacaoBox.setMaximumRowCount(4);
		especializacaoBox.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma opcao", "Neurologia", "Cardeologia", "Oftalmologia", "Otorrinolaringologia"}));
		especializacaoBox.setBounds(68, 169, 168, 20);
		contentPane.add(especializacaoBox);
		
	}
	
	
	public void criarButton(){
		
		
		salvarBotao = new JButton("Salvar");
		salvarBotao.setFont(new Font("Georgia", Font.ITALIC, 12));
		salvarBotao.setBounds(10, 301, 100, 27);
		contentPane.add(salvarBotao);
		
		cancelarBotao = new JButton("Cancelar");
		cancelarBotao.setFont(new Font("Georgia", Font.ITALIC, 12));
		cancelarBotao.setBounds(320, 301, 100, 27);
		contentPane.add(cancelarBotao);
		
	}
	
	
	public void criarLabel(){
		
		
		lblCadastroDeMdico = new JLabel("Cadastro de m\u00E9dico");
		lblCadastroDeMdico.setFont(new Font("Georgia", Font.ITALIC, 25));
		lblCadastroDeMdico.setBounds(107, 11, 266, 33);
		contentPane.add(lblCadastroDeMdico);
		
		lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 81, 46, 14);
		contentPane.add(lblNome);
		
		lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSexo.setBounds(327, 81, 46, 14);
		contentPane.add(lblSexo);
		
		lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpf.setBounds(10, 112, 46, 14);
		contentPane.add(lblCpf);
		
		lblTipoSangue = new JLabel("Tipo Sangu\u00EDneo");
		lblTipoSangue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipoSangue.setBounds(260, 144, 85, 14);
		contentPane.add(lblTipoSangue);
		
		lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLogin.setBounds(10, 213, 46, 14);
		contentPane.add(lblLogin);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSenha.setBounds(10, 244, 46, 14);
		contentPane.add(lblSenha);
		
		lblRh = new JLabel("RH");
		lblRh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRh.setBounds(366, 144, 29, 14);
		contentPane.add(lblRh);
		
		lblNdeRegistro = new JLabel("N\u00BA de registro");
		lblNdeRegistro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNdeRegistro.setBounds(197, 112, 83, 14);
		contentPane.add(lblNdeRegistro);
		
		lblEspecializao = new JLabel("Especializa\u00E7\u00E3o");
		lblEspecializao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEspecializao.setBounds(68, 144, 74, 14);
		contentPane.add(lblEspecializao);
		
	}	
	
	public void criarPasswordField(){
		
		
		textFieldSenha = new JPasswordField();
		textFieldSenha.setBounds(68, 242, 336, 20);
		contentPane.add(textFieldSenha);
		
	}
	
	public void criarEventos(){
		
		Pessoa pessoa = new Pessoa();
		novoMedico = new Medico();
		novoMedico.setPessoa(pessoa);
				
		
		//-- Classes Internas para capturar dados dos JComboBoxes
				
		class EventoEspecializacao implements ItemListener{

			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED){
					String opcao = (String) especializacaoBox.getSelectedItem();
					novoMedico.setEspecialidade(opcao);
				}
			}

		}
		
		
		//-- Classes Internas para capturar cliques nos botoes
		class EventoSalvar implements ActionListener,ItemListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				novoMedico.getPessoa().setNome(textFieldNome.getText());	
				novoMedico.getPessoa().setCpf(textFieldNome.getText());
				novoMedico.setLogin(textFieldLogin.getText());	
				novoMedico.setSenha(textFieldSenha.getText());
				
				int numero = Integer.parseInt(textFieldNdeRegistro.getText());
				
				novoMedico.setNumeroDeRegistro(numero);
				
				// ESTE METODO DEPENDE DO BANCO DE DADOS FORMADO PARA SER COMPLETAMENTE IMPLEMENTADO
				 PessoaDAO databasePessoa = new PessoaDAO();
				 int idPessoa = databasePessoa.cadastrar(novoMedico.getPessoa());
				 novoMedico.getPessoa().setIdPessoa(idPessoa);
				 MedicoDAO databaseMedico = new MedicoDAO();
				 databaseMedico.cadastrar(novoMedico);
				
			}
			
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED){
					String tipo = (String) tipoSangueBox.getSelectedItem();
					String rh = (String) rhBox.getSelectedItem();
					
					if (tipo == "A"){
						if (rh== "+"){
							novoMedico.getPessoa().setTipoSanguineo(TipoSanguineo.AP.getTipoSanguineo());
						}
						else if (rh=="-"){
							novoMedico.getPessoa().setTipoSanguineo(TipoSanguineo.AN.getTipoSanguineo());
						}
					}
								
					
					else if(tipo == "B"){
						if (rh == "+"){
							novoMedico.getPessoa().setTipoSanguineo(TipoSanguineo.BP.getTipoSanguineo());
						}
						
						else if (rh == "-"){
							novoMedico.getPessoa().setTipoSanguineo(TipoSanguineo.BN.getTipoSanguineo());
						}
						
					}
					
					
					else if(tipo == "O"){
						if (rh == "+"){
							novoMedico.getPessoa().setTipoSanguineo(TipoSanguineo.OP.getTipoSanguineo());
						}
						
						else if (rh=="-"){							
							novoMedico.getPessoa().setTipoSanguineo(TipoSanguineo.ON.getTipoSanguineo());
						}
						
						
					}
					
					else if(tipo == "AB"){
						if (rh == "+"){
							novoMedico.getPessoa().setTipoSanguineo(TipoSanguineo.ABP.getTipoSanguineo());
						}
						
						else if (rh=="-"){
							novoMedico.getPessoa().setTipoSanguineo(TipoSanguineo.ABN.getTipoSanguineo());
						}
						
						
					}
					
					
				
				}
					
			}	
		}

		
		
		class EventoCancelar implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				GerenteGUI proximaTela = new GerenteGUI(usuario);
				proximaTela.setVisible(true);
				CadastroMedicoGUI.this.dispose();
				
			}	
			
			
		}
		
		
		
		
		EventoEspecializacao eventoEsp = new EventoEspecializacao();
		especializacaoBox.addItemListener(eventoEsp);
		EventoSalvar eventoSalvar = new EventoSalvar();
		salvarBotao.addActionListener(eventoSalvar);
		EventoCancelar eventoCancelar = new EventoCancelar();
		cancelarBotao.addActionListener(eventoCancelar);		
		
		
		
	}
	
	///*
	
	// Para testes com a tela.
	
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Atendente at = new Atendente();
					CadastroMedicoGUI frame = new CadastroMedicoGUI(at);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 //*/
	
	
}

