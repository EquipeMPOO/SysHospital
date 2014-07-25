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

import aplicacao.controle.PesquisaControle;
import aplicacao.dominio.*;
import aplicacao.enums.StatusDeUsuario;

import java.awt.Label;
import java.awt.Insets;
import java.awt.SystemColor;
import java.util.ArrayList;

/**
 * 
 * @author Icaro
 * Tela inicial do programa. Um objeto desta classe é criado na classe Main
 */

public class LoginGUI extends JFrame {

	private JPanel panelGeral;
	private JPasswordField textSenha;
	private JTextField textLogin;
	private JLabel lblLogin, lblSenha, lblStatus;
	private JButton btnEntrar, btnApagar;
	private Panel panelDeStatus;
	private JSeparator separator;
	
	/**
	 * Construtor da tela de Login
	 * Adiciona as configurações básicas de tela e utiliza o metodo CriarEventos
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
	
	/**
	 * Cria os atributos da classe JPasswordField
	 * @return void
	 */
	
	public void criarPasswordField(){
		
		textSenha = new JPasswordField();
		textSenha.setBounds(120, 74, 250, 25);
		panelGeral.add(textSenha);
		
		
	}
	
	
	/**
	 * Cria os atributos da classe JTextField
	 * @return void
	 */
	public void criarTextFirld(){
		
		textLogin = new JTextField();
		textLogin.setBounds(120, 38, 250, 25);
		panelGeral.add(textLogin);
		textLogin.setColumns(10);
					
	}
	
	
	/**
	 * Cria os atributos da classe JButton
	 * @return void
	 */
	
	public void criarButton(){
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(new Font("Georgia", Font.ITALIC, 12));
		btnEntrar.setBounds(126, 134, 90, 23);
		panelGeral.add(btnEntrar);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Georgia", Font.ITALIC, 12));
		btnApagar.setBounds(275, 134, 90, 23);
		panelGeral.add(btnApagar);		
		
	}
	
	/**
	 * Cria os atributos da classe JLabel
	 * @return void
	 */
	
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
	
	/**
	 * Metodo que adiciona os eventos da classe LoginGUI
	 * Cria duas classes internas chamadas EventoEntrar e EventoApagar
	 * Instancia objetos dessas classes nos atributos btnApagar e btnEntrar
	 * 
	 *@return void
	 */
	
	public void adicionarEventos(){
		
		 /**
		 * Classe interna que implementa a interface ActionListener e sobrescreve o metodo actionPerformed
		 */
		class EventoEntrar implements ActionListener{			
		
			/**
			 * Inicialmente há uma verificação para confirmar se todos os campos foram preenchidos
			 * Caso não hajam impedimentos, serão criados objetos das subclasses de Funcionario: Enfermeiro, Administrador, Medico e Atendente
			 * Em seguida, cria-se quatro objetos da classe LoginControle com os objetos criados acima como parâmetro
			 * Em todos os objetos da classe LoginControle se utiliza-se o metodo pesquisarFiltrando que retorna uma lista de Funcionarios da mesma classe do parâmetro que tenham mesmo Login e senha
			 * Espera-se que nas tabelas não existam usuarios com informações duplicadas, logo, só haverá um ou nenhum elemento nas listas retornadas pelo metodo pesquisarFiltrando
			 * Cria-se um objeto das Classes GerenteGUI, MedicoGUI ou EnfermeiroGUI dependendo do tipo de usuário encontrando no banco de dados
			 * Por fim, caso não seja possível logar por algum motivo, o programa executará o metodo informarMotivoDeNaoLogar
			 * @return void
			 */
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				if (textLogin.getText().equals("") & textSenha.getText().equals("")){
					lblStatus.setForeground(Color.RED);
					lblStatus.setText("Por favor, insira o Login e a Senha.");
				}
				else if (textLogin.getText().equals("")){
					lblStatus.setForeground(Color.RED);
					lblStatus.setText("Por favor, insira o Login.");
				}
				else if (textSenha.getText().equals("")){
					lblStatus.setForeground(Color.RED);
					lblStatus.setText("Por favor, insira a Senha.");
				}
				
				else if ( !(textLogin.getText().equals("")) & !(textSenha.getText().equals("")) ) {
					
					
					PesquisaControle controleLogin = new PesquisaControle();
					
					Administrador administrador = new Administrador();
					administrador.setLogin(textLogin.getText());
					administrador.setSenha(textSenha.getText());				
					
					
					Atendente atendente = new Atendente();
					atendente.setLogin(textLogin.getText());
					atendente.setSenha(textSenha.getText());
					
					Enfermeiro enfermeiro = new Enfermeiro();
					enfermeiro.setLogin(textLogin.getText());
					enfermeiro.setSenha(textSenha.getText());					
										
					Medico medico = new Medico();
					medico.setLogin(textLogin.getText());
					medico.setSenha(textSenha.getText());
					
					Funcionario resultadoPesquisa = controleLogin.logar(administrador);
					
					if (controleLogin.logar(administrador) != null){
						
						lblStatus.setForeground(Color.BLUE);
						lblStatus.setText("Administrador encontrado! Fazendo Login.");
						GerenteGUI gerenteGUI = new GerenteGUI(administrador);
						LoginGUI.this.dispose();
						gerenteGUI.setVisible(true);

					
					}
					
					else if (controleLogin.logar(atendente) != null){
						
						lblStatus.setForeground(Color.BLUE);
						lblStatus.setText("Atendente encontrado! Fazendo Login.");
						GerenteGUI gerenteGUI = new GerenteGUI(atendente);
						LoginGUI.this.dispose();
						gerenteGUI.setVisible(true);
												
					}
				
					
							
					else if (controleLogin.logar(enfermeiro) != null){
						
						lblStatus.setForeground(Color.BLUE);
						lblStatus.setText("Enfermeiro encontrado! Fazendo Login.");
						NovaTela novaTela = new NovaTela();
						LoginGUI.this.dispose();
						novaTela.setVisible(true);
					} 
					else if (controleLogin.logar(medico) != null){
						
						lblStatus.setForeground(Color.BLUE);
						lblStatus.setText("Medico encontrado! Fazendo Login.");
						NovaTela novaTela = new NovaTela();
						LoginGUI.this.dispose();
						novaTela.setVisible(true);
						}
										
					else{
						lblStatus.setForeground(Color.RED);
						lblStatus.setText("Usuário não encontrado! Verifique se o Login e a Senha estão corretos...");
					}
				}
				
			}

			/**
			 * Verifica o atributo StatusDeUsuario do parametro f e exibe na label de Status o motivo de não ter sido possível efetuar login
			 * @param f - Funcionario passado pelo metodo actionPerformed
			 */

			private boolean informarMotivoDeNaoLogar(Funcionario f) {
				
				if ( f.getStatusDeUsuario().equals(StatusDeUsuario.A.getStatus()) ){
					lblStatus.setForeground(Color.RED);
					lblStatus.setText("Este usuário já está logado!");
					return true;
				}
				
				else if ( f.getStatusDeUsuario().equals(StatusDeUsuario.IP.getStatus()) ){
					lblStatus.setForeground(Color.RED);
					lblStatus.setText("Este usuário está impedido de fazer login!");
					return true;
					
				}else if ( f.getStatusDeUsuario().equals(StatusDeUsuario.MP.getStatus()) ){
					lblStatus.setForeground(Color.BLACK);
					lblStatus.setText("Este usuário não pode fazer login e será excluído do sistema!");
					return true;
				}
				
				return false;
			}
		}
		
		/**
		 * Classe interna que implementa a interface ActionListener e sobrescreve o metodo actionPerformed
		 */
		
		class EventoApagar implements ActionListener{
			
			/**
			 * Apaga os campos de Login ou Senha, ou seja, trocam seu conteudo por uma String vazia
			 * @return void
			 */
			public void actionPerformed(ActionEvent arg0) {
				textLogin.setText("");
				textSenha.setText("");
				lblStatus.setForeground(new Color(0, 128, 0));
				lblStatus.setText("Para logar preencha os campos com informação válida e click em Entar.");
			}

			
		}
		
		EventoEntrar eventoEntrar = new EventoEntrar();
		EventoApagar eventoApagar = new EventoApagar();
		btnEntrar.addActionListener(eventoEntrar);
		btnApagar.addActionListener(eventoApagar);

	}
	
}
