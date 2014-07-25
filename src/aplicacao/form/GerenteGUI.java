package aplicacao.form;


import aplicacao.dominio.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;

/**
 * 
 * @author ICARO
 * Classe GerenteGUI que herda a classe JFrame
 * É instanciada após o Login ou ao se cancelar/finalizar uma operação de outra tela
 *
 */

public class GerenteGUI extends JFrame {

	private Gerente usuario;
	private Medico medico;
	private Enfermeiro enfermeiro;
	private Atendente atendente;
	private JPanel contentPane;
	private JButton btnCadastrarPaciente, btnSair,btnCadastrarMedico,btnCadastrarEnfermeiro,btnInternarPaciente,btnLiberarPaciente, btnGerenciarFuncinario, btnCadastrarAtendente;


	/**
	 * Construtor da classe. Assiona os metodos que adicionam os componentes e eventos da tela
	 * O tipo da instancia usuario define qual será o tamanho da tela e quais são seus componentes
	 * @param user - Administrador ou Atendente passado na tela anterior
	 * @return void
	 */
	public GerenteGUI(Gerente user) {
		
		this.usuario = user;
		setTitle("SysHospital");		
		
		//espaco para configurar a janela e seu contentPane
		setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (usuario instanceof Administrador){
			setBounds(100, 100, 228, 445);
		}
		else{
			setBounds(50, 100, 228, 251);
		}

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);
		
		this.criarBotoes(); //cria os botoes
		this.adicionarEventos(); //adiciona eventos neles
		
	}
	
	/**
	 * Metodo que adiciona os atributos da classe JButton
	 * As configurações de localização e composição dependem da classe do atributo usuario
	 * @return void
	 */
	
	public void criarBotoes(){
		
		//metodo que cria todos os botoes necessarios para o GerenteGUI
		
		//this.botaoCadastrarPaciente
		btnCadastrarPaciente = new JButton("Cadastrar paciente");
		btnCadastrarPaciente.setForeground(SystemColor.controlText);
		btnCadastrarPaciente.setBackground(SystemColor.controlHighlight);
		btnCadastrarPaciente.setFont(new Font("Georgia", Font.ITALIC, 16));	
		btnCadastrarPaciente.setBounds(10, 11, 193, 38);
		contentPane.add(btnCadastrarPaciente);
		
		btnInternarPaciente = new JButton("Internar paciente");
		btnInternarPaciente.setFont(new Font("Georgia", Font.ITALIC, 16));
		btnInternarPaciente.setBackground(SystemColor.controlHighlight);
		btnInternarPaciente.setBounds(10, 60, 193, 38);
		contentPane.add(btnInternarPaciente);
		
		btnLiberarPaciente = new JButton("Liberar paciente");
		btnLiberarPaciente.setFont(new Font("Georgia", Font.ITALIC, 16));
		btnLiberarPaciente.setBackground(SystemColor.controlHighlight);
		btnLiberarPaciente.setBounds(10, 109, 193, 38);
		contentPane.add(btnLiberarPaciente);

		
		//this.botaoSair
		btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Georgia", Font.ITALIC, 11));
		btnSair.setBackground(SystemColor.controlHighlight);
		btnSair.setBounds(10, 175, 89, 23);
		contentPane.add(btnSair);
		
		if (usuario instanceof Administrador){
			
			btnCadastrarMedico = new JButton("Cadastrar medico");
			btnCadastrarMedico.setFont(new Font("Georgia", Font.ITALIC, 16));
			btnCadastrarMedico.setBackground(SystemColor.controlHighlight);
			btnCadastrarMedico.setBounds(10, 207, 193, 38);
			contentPane.add(btnCadastrarMedico);
			
			btnCadastrarEnfermeiro = new JButton("Cadastrar enfermeiro");
			btnCadastrarEnfermeiro.setFont(new Font("Georgia", Font.ITALIC, 16));
			btnCadastrarEnfermeiro.setBackground(SystemColor.controlHighlight);
			btnCadastrarEnfermeiro.setBounds(10, 158, 193, 38);
			contentPane.add(btnCadastrarEnfermeiro);
			
			btnGerenciarFuncinario = new JButton("Gerenciar funcionario");
			btnGerenciarFuncinario.setFont(new Font("Georgia", Font.ITALIC, 16));
			btnGerenciarFuncinario.setBackground(SystemColor.controlHighlight);
			btnGerenciarFuncinario.setBounds(10, 305, 193, 38);
			contentPane.add(btnGerenciarFuncinario);
			
			btnCadastrarAtendente = new JButton("Cadastrar Atendente");
			btnCadastrarAtendente.setFont(new Font("Georgia", Font.ITALIC, 16));
			btnCadastrarAtendente.setBackground(SystemColor.controlHighlight);
			btnCadastrarAtendente.setBounds(10, 256, 193, 38);
			contentPane.add(btnCadastrarAtendente);
			
			btnSair.setBounds(10, 371, 89, 23);
			
	
		
		}

	}

	
	public ActionListener[] criarClassesEvento(){ //metodo que cria as classes internas que farão o tratamento de eventos
		
		class EventoCadastrarPaciente implements ActionListener { //criação da classe interna 
			
			public void actionPerformed(ActionEvent e) { //Espaço para sobrescrever o que deve ser feito ao pressionaro o botao de Cadastro de Paciente
				
				JFrame proximaTela = new CadastroPacienteGUI();
				GerenteGUI.this.dispose();
				proximaTela.setVisible(true);
			
			}
		}
		
		class EventoCadastrarMedico implements ActionListener { //criação da classe interna 
			
			public void actionPerformed(ActionEvent e) { //Espaço para sobrescrever o que deve ser feito ao pressionaro o botao de Cadastro de Medico
				
				medico = new Medico();
				JFrame proximaTela = new SalvarDadosFuncionariosGUI(usuario, medico);
				GerenteGUI.this.dispose();
				proximaTela.setVisible(true);
			
			}
		}
		
		class EventoCadastrarEnfermeiro implements ActionListener { //criação da classe interna 
			
			public void actionPerformed(ActionEvent e) { //Espaço para sobrescrever o que deve ser feito ao pressionaro o botao de Cadastro de Enfermeiro
				
				enfermeiro = new Enfermeiro();
				JFrame proximaTela = new SalvarDadosFuncionariosGUI(usuario,enfermeiro);
				GerenteGUI.this.dispose(); 
				proximaTela.setVisible(true);
				
			}
		}
		
		class EventoCadastrarAtendente implements ActionListener { //criação da classe interna 
			
			public void actionPerformed(ActionEvent e) { //Espaço para sobrescrever o que deve ser feito ao pressionaro o botao de Cadastro de Atendente
				
				atendente = new Atendente();
				JFrame proximaTela = new SalvarDadosFuncionariosGUI(usuario,atendente);
				GerenteGUI.this.dispose(); 
				proximaTela.setVisible(true);
				
			}
		}
		
		
		class EventoInternar implements ActionListener { //criação da classe interna 
			
			public void actionPerformed(ActionEvent e) { //Espaço para sobrescrever o que deve ser feito ao pressionaro o botao de Internar o Paciente
				
				
			}
		}
		
		class EventoGerenciar implements ActionListener { //criação da classe interna 
			
			public void actionPerformed(ActionEvent e) { //Espaço para sobrescrever o que deve ser feito ao pressionar o botao de Sair do Sistema
			
				JFrame proximaTela = new GerenciarFuncionarioGUI(usuario);
				GerenteGUI.this.dispose();
				proximaTela.setVisible(true);
				
			}
		}

		class EventoLiberar implements ActionListener { //criação da classe interna 
			
			public void actionPerformed(ActionEvent e) { //Espaço para sobrescrever o que deve ser feito ao pressionar o botao de Liberar o Paciente
			}
		}
		
		class EventoSair implements ActionListener { //criação da classe interna 
			
			public void actionPerformed(ActionEvent e) { //Espaço para sobrescrever o que deve ser feito ao pressionar o botao de Sair do Sistema
			
				JFrame proximaTela = new LoginGUI();
				GerenteGUI.this.dispose();
				proximaTela.setVisible(true);
				
			}
		}

		//criacao dos objetos que farao o tratamento de eventos
		EventoCadastrarPaciente eventoCadastroP = new EventoCadastrarPaciente();
		EventoCadastrarMedico eventoCadastroM = new EventoCadastrarMedico();
		EventoCadastrarEnfermeiro eventoCadastroE = new EventoCadastrarEnfermeiro();
		EventoCadastrarAtendente eventoCadastroA = new EventoCadastrarAtendente();
		EventoGerenciar eventoG = new EventoGerenciar();
		EventoInternar eventoInternar = new EventoInternar();
		EventoLiberar eventoLiberar = new EventoLiberar();
		EventoSair eventoSair = new EventoSair();
		
		ActionListener[] lista = {eventoCadastroP,eventoCadastroM,eventoCadastroE,eventoG,eventoInternar,eventoLiberar,eventoSair,eventoCadastroA}; //adicionando todos os objetos criados acima
		return lista;
		
	}
	
	public void adicionarEventos(){ //metodo chamado no construtor para adicionar os eventos nos botoes criados pelo metodo adicionarBotoes()
		
		//o metodo addActionListener é nativo do JButton e tem como parametro um objeto da classe ActionListener
		//ActionListener é uma interface do Java que implementa o metodo actionPerfomed		
				
		ActionListener[] lista = this.criarClassesEvento();	//captura a lista retornada pelo metodo criarClassesEvento() e adiciona numa variavel lista do tipo ActionListener Array
		
		btnCadastrarPaciente.addActionListener(lista[0]);
		
		if(usuario instanceof Administrador){
			btnCadastrarMedico.addActionListener(lista[1]);
			btnCadastrarEnfermeiro.addActionListener(lista[2]);
			btnCadastrarAtendente.addActionListener(lista[7]);
			btnGerenciarFuncinario.addActionListener(lista[3]);
		}
		
		btnInternarPaciente.addActionListener(lista[4]);
		btnLiberarPaciente.addActionListener(lista[5]);
		btnSair.addActionListener(lista[6]); 
		
	}
}