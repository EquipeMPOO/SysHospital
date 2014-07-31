// Mudar ArrayList (...).get(-1) para get(isso.size -1)


package aplicacao.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.ListModel;

import aplicacao.controle.PacienteControle;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Entrada;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Gerente;
import aplicacao.dominio.Medico;
import aplicacao.dominio.Paciente;
import aplicacao.dominio.Pessoa;
import aplicacao.enums.StatusDeEntrada;
import aplicacao.enums.StatusDePessoa;

import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

public class GerenciarPacienteGUI extends JFrame {
	
	private JLabel lblTitulo;
	private JList<String> listaPacientes;
	private JTextPane txtDadosPacientes, txtStatus;
	private JButton btnSair, btnCriarProntuario, btnVisualizarUltimoPronturio;
	private Funcionario usuario;
	private int aux;
	private Paciente pacienteSelecionado;
	private PacienteControle pacientePesquisa;
	private List<Paciente> pacientesPesquisados;
		

	
	/**
	 * Cria a janela.
	 */
	public GerenciarPacienteGUI(Funcionario user, int aux) {
		
		this.usuario = user;
		this.aux = aux;
		getContentPane().setBackground(SystemColor.inactiveCaption);
		getContentPane().setLayout(null);
		
		// - Defining frame and contentPane settings. 
		setTitle("SysHospital");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 369);
		
				
		
		// Calling the method to create all components on the screen.
		this.criarComponentes();
		
		// Calling the method to add all events to the components on the screen and set up the JList listaPacientes.
		this.addEventos();
		
	
		
	}
	
	
	/**
	 * Cria todos os componentes na tela.
	 * @return void
	 * @author Guilherme
	 * @param null
	 */
	public void criarComponentes(){
		
		lblTitulo = new JLabel("Gerenciar paciente");
		lblTitulo.setFont(new Font("Georgia", Font.ITALIC, 25));
		lblTitulo.setBounds(142, 11, 226, 33);
		getContentPane().add(lblTitulo);
		
		listaPacientes = new JList<String>();
		listaPacientes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		listaPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaPacientes.setVisibleRowCount(5);
		listaPacientes.setBounds(10, 55, 471, 91);
		getContentPane().add(listaPacientes);
		
		txtDadosPacientes = new JTextPane();
		txtDadosPacientes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDadosPacientes.setBounds(10, 157, 471, 88);
		
		
		
		txtStatus = new JTextPane();
		txtStatus.setBounds(0, 310, 492, 20);
		getContentPane().add(txtStatus);
		
		btnSair = new JButton();
		btnSair.setFont(new Font("Georgia", Font.ITALIC, 11));
		btnSair.setBackground(SystemColor.activeCaption);
		btnSair.setBounds(10, 276, 89, 23);
		if (usuario instanceof Gerente){
			btnSair.setText("Cancelar");
		}
		else{
			btnSair.setText("Sair");
		}
		
		getContentPane().add(btnSair);
		
		// --- Resets the settings of the buttons according to the type of access (Medico or Enfermeiro)
		btnCriarProntuario = new JButton();
		btnVisualizarUltimoPronturio = new JButton();
		if (usuario instanceof Enfermeiro){
			btnCriarProntuario.setText("Criar prontu\u00E1rio");
			btnCriarProntuario.setFont(new Font("Georgia", Font.ITALIC, 11));
			btnCriarProntuario.setBackground(SystemColor.activeCaption);
			btnCriarProntuario.setBounds(360, 276, 121, 23);
			
			
			
		}
		
		else if (usuario instanceof Medico){
			btnCriarProntuario.setText("Preencher novo prontu\u00E1rio");
			btnCriarProntuario.setFont(new Font("Georgia", Font.ITALIC, 11));
			btnCriarProntuario.setBackground(SystemColor.activeCaption);
			btnCriarProntuario.setBounds(300, 276, 181, 23);
			
			
			btnVisualizarUltimoPronturio.setText("\u00DAltimo prontu\u00E1rio");
			btnVisualizarUltimoPronturio.setFont(new Font("Georgia", Font.ITALIC, 11));
			btnVisualizarUltimoPronturio.setBackground(SystemColor.activeCaption);
			btnVisualizarUltimoPronturio.setBounds(159, 276, 131, 23);
			
		}
		else if (usuario instanceof Gerente){
			
			// The (int) aux defines if it works as an inpatient method (internar) or as an discharging method (liberar). 
			if (aux==1){
				btnCriarProntuario.setText("Internar paciente");
				btnCriarProntuario.setFont(new Font("Georgia", Font.ITALIC, 11));
				btnCriarProntuario.setBackground(SystemColor.activeCaption);
				btnCriarProntuario.setBounds(300, 276, 181, 23);
			}
			else if (aux==2){
				btnCriarProntuario.setText("Liberar paciente");
				btnCriarProntuario.setFont(new Font("Georgia", Font.ITALIC, 11));
				btnCriarProntuario.setBackground(SystemColor.activeCaption);
				btnCriarProntuario.setBounds(300, 276, 181, 23);
			}
			
			
			
		}
		
		
		
		
	}
	
	
	/**
	 * Chama todos os métodos responsáveis pela adição de eventos a os componentes da tela, bem como o metodo que ajusta o JList listaPacientes.
	 * @author Guilherme
	 * @return void
	 * @param null
	 */
	public void addEventos(){
		this.configurarListaPacientes();
		this.eventoListaPacientes();
		this.eventoCriarProntuario();
		this.eventoSair();
		this.eventoVisualizarUltimoProntuario();
	}
	
	
	/**
	 * Configura o JList listaPacientes
	 */
	// ADAPTAR ESTA PARTE DO CODIGO AOS METODOS DE CONTROLE DE PacienteControle() (Rodolfo implementando tais)
	public void configurarListaPacientes(){
			pacientePesquisa = new PacienteControle();
			pacientesPesquisados = new ArrayList<Paciente>();  //------
			if (usuario instanceof Gerente){
				// The (int) aux defines if it works as an inpatient method (internar) or as an discharging method (liberar).
				
				if(aux==1){
					pacientesPesquisados.addAll(pacientePesquisa.pesquisarNaoInternados());
					
					
				}
				else if (aux==2){
					pacientesPesquisados.addAll(pacientePesquisa.pesquisarInternados()); 
				}
			}
			
			else if (usuario instanceof Medico || usuario instanceof Enfermeiro){
				pacientesPesquisados.addAll(pacientePesquisa.pesquisarInternados()); 

			}
				
			
			//DefaultListModel where the values to be set in the JList are assigned. PS.: This works as a List, in a way.
			DefaultListModel<String> nomesPacientes = new DefaultListModel<String>();
			
			for(Paciente nome:pacientesPesquisados){
				nomesPacientes.addElement(nome.getPessoa().getNome()); //Method to add an element to a DefaultListModel
			}
			
			listaPacientes.setModel(nomesPacientes);
			
	}
	
	
	/**
	 * Adiciona um evento a o elemento focalizado na JList; Configura e adiciona alguns componentes ao painel.
	 * @author Guilherme
	 * @return void
	 * @param null
	 */
	public void eventoListaPacientes(){
		
		listaPacientes.addFocusListener(new FocusListener() { 

			@Override
			public void focusGained(FocusEvent e){
				

				for (Paciente paciente:pacientesPesquisados){
					// Checking equality between the object name and the focused element in the JList.
					if (paciente.getPessoa().getNome()==listaPacientes.getSelectedValue()){
						
						pacienteSelecionado = paciente; 
						Pessoa selecao = paciente.getPessoa();
						
						String texto = "Nome: " + selecao.getNome()	+ "\nSexo" + selecao.getSexo() + "\nIdade: " + selecao.getIdade()
								+ "\nGrupo Sanguíneo" + selecao.getTipoSanguineo() + "\nStatus: " + selecao.getStatusDePessoa();
						
						txtDadosPacientes.setText(texto);
						break;
						
					}
				}
				
				getContentPane().add(txtDadosPacientes);
				getContentPane().add(btnCriarProntuario);
				if (usuario instanceof Medico){
					getContentPane().add(btnVisualizarUltimoPronturio);
				}
				
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				for (Component comp: getContentPane().getComponents()){
					if (comp.equals(txtDadosPacientes) || comp.equals(btnCriarProntuario)||comp.equals(btnVisualizarUltimoPronturio)){
						getContentPane().remove(comp);
					}
				}
			}
		
			});
	}
	
	
	/**
	 * Adiciona o evento ao botão btnSair, voltando para a tela LoginGUI ou GerenteGUI, dependendo do usuário.
	 * @author Guilherme
	 * @return void
	 * @param null
	 */
	public void eventoSair(){
			btnSair.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			
				if (usuario instanceof Gerente){
					JFrame novaTela = new GerenteGUI((Gerente) usuario);
					novaTela.setVisible(true);
					GerenciarPacienteGUI.this.dispose();
				}
				else{
					JFrame novaTela = new LoginGUI();
					novaTela.setVisible(true);
					GerenciarPacienteGUI.this.dispose();
				}
									 
								
			}
			
		
			});
	
	};
	
	
	/**
	 * Adiciona o evento ao botão criarProntuario, indo para a tela ProntuarioGUI ou fazendo com que o paciente em questão seja
	 * internado ou liberado, dependendo do usuario.
	 * @author Guilherme
	 * @return void
	 * @param null
	 */
	
	public void eventoCriarProntuario(){
		
		btnCriarProntuario.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				PacienteControle controle = new PacienteControle();
				
				if (usuario instanceof Gerente){
					// se estiver internando
					if(aux==1){
						
					
						//Metodo que salva no banco as informacoes do paciente interno
						controle.internar(pacienteSelecionado);
						
						
						
					}

					// se estiver liberando
					else if (aux==2){
						
						//Metodo que torna a entrada do paciente finalizada e salva no banco
						controle.liberar(pacienteSelecionado);
						
						
												
					}
					
					
				}
				
				else if (usuario instanceof Medico || usuario instanceof Enfermeiro){
					ProntuarioGUI novatela = new ProntuarioGUI(usuario,pacienteSelecionado,true);
					novatela.setVisible(true);
					GerenciarPacienteGUI.this.dispose();
				}
				mensagemStatus(true);
				
			}
			
		});		
		
	};
	
	
	/**
	 * Adiciona o evento ao botão btnvisualizarUltimoProntuario, indo para a tela ProntuarioGUI.
	 * @author Guilherme
	 * @return void
	 * @param null
	 */
	public void eventoVisualizarUltimoProntuario(){
	
		btnVisualizarUltimoPronturio.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (usuario instanceof Medico){
					ProntuarioGUI novatela = new ProntuarioGUI(usuario,pacienteSelecionado,false);
					novatela.setVisible(true);
					GerenciarPacienteGUI.this.dispose();
				}
				else{
					getContentPane().remove(btnVisualizarUltimoPronturio);
				}
				
				
			}
			
		});
		
	}
	
	public void mensagemStatus(boolean novo){
		if (novo){
			txtStatus.setForeground(Color.BLUE);
			txtStatus.setText("Processo realizado com sucesso!");
			
		}
		else{
			txtStatus.setForeground(Color.RED);
			txtStatus.setText("Não foi possível concluir a operacao!");
		}
	}
}
