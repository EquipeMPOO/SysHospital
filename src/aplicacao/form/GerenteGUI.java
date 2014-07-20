package aplicacao.form;

import java.awt.BorderLayout;
import aplicacao.dominio.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;

public class GerenteGUI extends JFrame {

	private Gerente usuario;
	private Medico medico;
	private Enfermeiro enfermeiro;
	private JPanel contentPane;
	private JButton btnCadastrarPaciente, btnSair,btnCadastrarMedico,btnCadastrarEnfermeiro,btnInternarPaciente,btnLiberarPaciente, btnGerenciarFuncinario;

	/**
	 * Cria a janela.
	 * 
	 */
	public GerenteGUI(Gerente user) {
		
		this.usuario = user;
		setTitle("SysHospital");		
		
		//espaco para configurar a janela e seu contentPane
		setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 228, 391);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);
		
		this.criarBotoes(); //cria os botoes
		this.adicionarEventos(); //adiciona eventos neles
		
	}
	
	public void criarBotoes(){
		
		//metodo que cria todos os botoes necessarios para o GerenteGUI
		
		//this.botaoCadastrarPaciente
		btnCadastrarPaciente = new JButton("Cadastrar paciente");
		btnCadastrarPaciente.setBackground(SystemColor.activeCaption);
		btnCadastrarPaciente.setFont(new Font("Georgia", Font.ITALIC, 16));	
		btnCadastrarPaciente.setBounds(10, 11, 193, 38);
		contentPane.add(btnCadastrarPaciente);
		
		//this.botaoSair
		btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Georgia", Font.ITALIC, 11));
		btnSair.setBackground(SystemColor.activeCaption);
		btnSair.setBounds(10, 318, 89, 23);
		contentPane.add(btnSair);
		
		btnCadastrarMedico = new JButton("Cadastrar medico");
		btnCadastrarMedico.setFont(new Font("Georgia", Font.ITALIC, 16));
		btnCadastrarMedico.setBackground(SystemColor.activeCaption);
		btnCadastrarMedico.setBounds(10, 60, 193, 38);
		contentPane.add(btnCadastrarMedico);
		
		btnCadastrarEnfermeiro = new JButton("Cadastrar enfermeiro");
		btnCadastrarEnfermeiro.setFont(new Font("Georgia", Font.ITALIC, 16));
		btnCadastrarEnfermeiro.setBackground(SystemColor.activeCaption);
		btnCadastrarEnfermeiro.setBounds(10, 109, 193, 38);
		contentPane.add(btnCadastrarEnfermeiro);
		
		btnInternarPaciente = new JButton("Internar paciente");
		btnInternarPaciente.setFont(new Font("Georgia", Font.ITALIC, 16));
		btnInternarPaciente.setBackground(SystemColor.activeCaption);
		btnInternarPaciente.setBounds(10, 256, 193, 38);
		contentPane.add(btnInternarPaciente);
		
		btnLiberarPaciente = new JButton("Liberar paciente");
		btnLiberarPaciente.setFont(new Font("Georgia", Font.ITALIC, 16));
		btnLiberarPaciente.setBackground(SystemColor.activeCaption);
		btnLiberarPaciente.setBounds(10, 207, 193, 38);
		contentPane.add(btnLiberarPaciente);
		
		btnGerenciarFuncinario = new JButton("Gerenciar funcin\u00E1rio");
		btnGerenciarFuncinario.setFont(new Font("Georgia", Font.ITALIC, 16));
		btnGerenciarFuncinario.setBackground(SystemColor.activeCaption);
		btnGerenciarFuncinario.setBounds(10, 158, 193, 38);
		contentPane.add(btnGerenciarFuncinario);
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
			
				JFrame proximaTela = new SalvarDadosFuncionariosGUI(usuario, medico);
				GerenteGUI.this.dispose();
				proximaTela.setVisible(true);
			
			}
		}
		
		class EventoCadastrarEnfermeiro implements ActionListener { //criação da classe interna 
			
			public void actionPerformed(ActionEvent e) { //Espaço para sobrescrever o que deve ser feito ao pressionaro o botao de Cadastro de Enfermeiro
			
				JFrame proximaTela = new SalvarDadosFuncionariosGUI(usuario,enfermeiro);
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
		EventoGerenciar eventoG = new EventoGerenciar();
		EventoInternar eventoInternar = new EventoInternar();
		EventoLiberar eventoLiberar = new EventoLiberar();
		EventoSair eventoSair = new EventoSair();
		
		ActionListener[] lista = {eventoCadastroP,eventoCadastroM,eventoCadastroE,eventoG,eventoInternar,eventoLiberar,eventoSair}; //adicionando todos os objetos criados acima
		return lista;
		
	}
	
	public void adicionarEventos(){ //metodo chamado no construtor para adicionar os eventos nos botoes criados pelo metodo adicionarBotoes()
		
		//o metodo addActionListener é nativo do JButton e tem como parametro um objeto da classe ActionListener
		//ActionListener é uma interface do Java que implementa o metodo actionPerfomed		
				
		ActionListener[] lista = this.criarClassesEvento();	//captura a lista retornada pelo metodo criarClassesEvento() e adiciona numa variavel lista do tipo ActionListener Array
		
		btnCadastrarPaciente.addActionListener(lista[0]);
		btnCadastrarMedico.addActionListener(lista[1]);
		btnCadastrarEnfermeiro.addActionListener(lista[2]);
		btnGerenciarFuncinario.addActionListener(lista[3]);
		btnInternarPaciente.addActionListener(lista[4]);
		btnLiberarPaciente.addActionListener(lista[5]);
		btnSair.addActionListener(lista[6]); 
		
	}
}