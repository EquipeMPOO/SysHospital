package aplicacao.form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;

import javax.swing.JSeparator;

import aplicacao.controle.FuncionarioControle;
import aplicacao.dominio.*;
import aplicacao.enums.StatusDeUsuario;

import java.awt.Label;
import java.awt.Insets;
import java.awt.SystemColor;
import java.util.ArrayList;

public class LoginGUI extends JFrame {

	private JPanel panelGeral;
	private JPasswordField textSenha;
	private JTextField textLogin;
	private JLabel lblLogin, lblSenha, lblStatus;
	private JButton btnEntrar, btnCancelar, btnGerente;
	private Panel panelDeStatus;
	private JSeparator separator;
	
	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		
		setTitle("SysHospital");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		panelGeral = new JPanel();
		panelGeral.setBackground(SystemColor.activeCaption);
		panelGeral.setToolTipText("");
		panelGeral.setForeground(Color.WHITE);
		panelGeral.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelGeral);
		panelGeral.setLayout(null);
		
		
		// --- Separador --------------------------------------
		
		separator = new JSeparator();
		separator.setBackground(SystemColor.desktop);
		separator.setBounds(0, 190, 442, 2);
		panelGeral.add(separator);
		//-----------------------------------------------------
		
		
		
		// -- Novo painel (inferior) ------------------------------------
		
		panelDeStatus = new Panel();
		panelDeStatus.setBackground(Color.WHITE);
		panelDeStatus.setBounds(0, 191, 442, 25);
		panelGeral.add(panelDeStatus);
		panelDeStatus.setLayout(null);
		//------------------------------------------------------------------
		
		
		this.criarButton();
		this.criarLabel();
		this.criarPasswordField();
		this.criarTextFirld();
		this.adicionarEventos();
		
	}
	
	
	public void criarPasswordField(){
		
		textSenha = new JPasswordField();
		textSenha.setBounds(120, 74, 250, 25);
		panelGeral.add(textSenha);
		
		
	}
	
	
	
	public void criarTextFirld(){
		
		textLogin = new JTextField();
		textLogin.setBounds(120, 38, 250, 25);
		panelGeral.add(textLogin);
		textLogin.setColumns(10);
					
	}
	
	
	
	public void criarButton(){
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(new Font("Georgia", Font.ITALIC, 12));
		btnEntrar.setBounds(75, 134, 90, 23);
		panelGeral.add(btnEntrar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Georgia", Font.ITALIC, 12));
		btnCancelar.setBounds(175, 134, 90, 23);
		panelGeral.add(btnCancelar);
		
		btnGerente = new JButton("Novo Gerente");
		btnGerente.setFont(new Font("Georgia", Font.ITALIC, 12));
		btnGerente.setMargin(new Insets(2, 4, 2, 4));
		btnGerente.setBounds(275, 134, 90, 23);
		panelGeral.add(btnGerente);
		
		
		
	}
	
	
	
	public void criarLabel(){
		
		lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Georgia", Font.ITALIC, 15));
		lblLogin.setBounds(50, 37, 60, 25);
		panelGeral.add(lblLogin);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Georgia", Font.ITALIC, 15));
		lblSenha.setBounds(50, 73, 60, 25);
		panelGeral.add(lblSenha);
		
		lblStatus = new JLabel("");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setForeground(new Color(0, 128, 0));
		lblStatus.setText("Para logar preencha os campos com informação válida e click em Entar.");
		lblStatus.setBounds(10, 0, 422, 25);
		panelDeStatus.add(lblStatus);
	
	}
	
	public void adicionarEventos(){
		
		class EventoEntrar implements ActionListener{			
		
			public void actionPerformed(ActionEvent arg0) {
				
				if (textLogin.getText().equals("") & textSenha.getText().equals("")){
					lblStatus.setForeground(Color.RED);
					lblStatus.setText("Por favor, isira o Login e a Senha.");
				}
				else if (textLogin.getText().equals("")){
					lblStatus.setForeground(Color.RED);
					lblStatus.setText("Por favor, isira o Login.");
				}
				else if (textSenha.getText().equals("")){
					lblStatus.setForeground(Color.RED);
					lblStatus.setText("Por favor, isira a Senha.");
				}
				
				else if ( !(textLogin.getText().equals("")) & !(textSenha.getText().equals("")) ) {
					
					Administrador administrador = new Administrador();
					administrador.setLogin(textLogin.getText());
					administrador.setSenha(textSenha.getText());
					ArrayList<Funcionario> adm = new ArrayList();
					FuncionarioControle funcionarioAdministrador = new FuncionarioControle(administrador);
					adm = (ArrayList<Funcionario>)funcionarioAdministrador.pesquisarFiltrando(administrador, false);
					
					Atendente aterndente = new Atendente();
					aterndente.setLogin(textLogin.getText());
					aterndente.setSenha(textSenha.getText());
					ArrayList<Funcionario> ate = new ArrayList();
					FuncionarioControle funcionarioAtendente =
							new FuncionarioControle(aterndente);
					ate = (ArrayList<Funcionario>)
							funcionarioAtendente.pesquisarFiltrando(aterndente, false);
					
					Enfermeiro enfermeiro = new Enfermeiro();
					enfermeiro.setLogin(textLogin.getText());
					enfermeiro.setSenha(textSenha.getText());
					ArrayList<Funcionario> enf = new ArrayList();
					FuncionarioControle funcionarioEnfermeiro =
							new FuncionarioControle(enfermeiro);
					enf = (ArrayList<Funcionario>)
							funcionarioEnfermeiro.pesquisarFiltrando(enfermeiro, false);
					
					Medico medico = new Medico();
					medico.setLogin(textLogin.getText());
					medico.setSenha(textSenha.getText());
					ArrayList<Funcionario> med = new ArrayList();
					FuncionarioControle funcionarioMedico =
							new FuncionarioControle(medico);
					med = (ArrayList<Funcionario>)
							funcionarioMedico.pesquisarFiltrando(medico, false);
					
					ArrayList<Funcionario> vazio = new ArrayList();
					Funcionario resultodoDeLigin = null;
					if ( !(adm.equals(vazio)) ){
						lblStatus.setForeground(Color.BLUE);
						lblStatus.setText("Administrador encontrado! Fazendo Login.");
						resultodoDeLigin = funcionarioAdministrador.logar(adm.get(0));
						if (resultodoDeLigin == null){
							this.informarMotivoDeNaoLogar(adm.get(0));
						}
						else{
							GerenteGUI gerenteGUI = new GerenteGUI((Administrador) adm.get(0));
							LoginGUI.this.dispose();
							gerenteGUI.setVisible(true);
						}
					}else
					
					if ( !(ate.equals(vazio)) ){
						lblStatus.setForeground(Color.BLUE);
						lblStatus.setText("Atendente encontrado! Fazendo Login.");
						resultodoDeLigin = funcionarioAtendente.logar(ate.get(0));
						if (resultodoDeLigin == null){
							this.informarMotivoDeNaoLogar(ate.get(0));
						}
						else{
							GerenteGUI gerenteGUI = new GerenteGUI((Atendente) ate.get(0));
							LoginGUI.this.dispose();
							gerenteGUI.setVisible(true);
						}
					}else
					
					if ( !(enf.equals(vazio)) ){
						lblStatus.setForeground(Color.BLUE);
						lblStatus.setText("Enfermeiro encontrado! Fazendo Login.");
						resultodoDeLigin = funcionarioEnfermeiro.logar(enf.get(0));
						if (resultodoDeLigin == null){
							this.informarMotivoDeNaoLogar(enf.get(0));
						}
						else{
							NovaTela novaTela = new NovaTela();
							LoginGUI.this.dispose();
							novaTela.setVisible(true);
						}
					}else
					
					if ( !(med.equals(vazio)) ){
						lblStatus.setForeground(Color.BLUE);
						lblStatus.setText("Medico encontrado! Fazendo Login.");
						resultodoDeLigin = funcionarioMedico.logar(med.get(0));
						if (resultodoDeLigin == null){
							this.informarMotivoDeNaoLogar(med.get(0));
						}
						else{
							NovaTela novaTela = new NovaTela();
							LoginGUI.this.dispose();
							novaTela.setVisible(true);
						}
					}
					
					else{
						lblStatus.setForeground(Color.RED);
						lblStatus.setText("Usuário não encontrado! Verifique se o Login e a Senha estão corretos...");
					}
				}
			}

			private void informarMotivoDeNaoLogar(Funcionario f) {
				
				if ( f.getStatusDeUsuario().equals(StatusDeUsuario.A.getStatus()) ){ // Caso a ser pensado!!!!
					lblStatus.setForeground(Color.RED);
					lblStatus.setText("Este usuário já está logado!");
				}else
					
				if ( f.getStatusDeUsuario().equals(StatusDeUsuario.IP.getStatus()) ){
					lblStatus.setForeground(Color.RED);
					lblStatus.setText("Este usuário está impedido de fazer login!");
				}else
					
				if ( f.getStatusDeUsuario().equals(StatusDeUsuario.MP.getStatus()) ){
					lblStatus.setForeground(Color.BLACK);
					lblStatus.setText("Este usuário não pode fazer login e será excluído do sistema!");
				}
				
			}
		}
		
		
		class EventoCancelar implements ActionListener{
			
			public void actionPerformed(ActionEvent arg0) {
				textLogin.setText("");
				textSenha.setText("");
				lblStatus.setForeground(new Color(0, 128, 0));
				lblStatus.setText("Para logar preencha os campos com informação válida e click em Entar.");
			}
			
		}
		
		EventoEntrar eventoEntrar = new EventoEntrar();
		EventoCancelar eventoCancelar = new EventoCancelar();
		btnEntrar.addActionListener(eventoEntrar);
		btnCancelar.addActionListener(eventoCancelar);

	}
	
	
	
	/*
	
	// Para testes com a tela.
	
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 */
}
