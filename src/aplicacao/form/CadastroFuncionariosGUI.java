package aplicacao.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import aplicacao.controle.GerenciamentoFuncionarioControle;
import aplicacao.dao.AdministradorDAO;
import aplicacao.dao.AtendenteDAO;
import aplicacao.dao.EnfermeiroDAO;
import aplicacao.dao.MedicoDAO;
import aplicacao.dominio.Administrador;
import aplicacao.dominio.Atendente;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Medico;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Gerente;
import aplicacao.dominio.Pessoa;
import aplicacao.enums.TipoSanguineo;

public class CadastroFuncionariosGUI extends JFrame {
	
	private Gerente usuario;
	private JTextField nomeField, cpfField, nRegistroField, loginField;
	private JLabel lblTitulo, lblNome, lblCpf, lblSexo, lblTipoSangueRh, lblNdeRegistro, lblLogin, lblSenha, lblEspecializacao;
	private JComboBox sexoBox, tipoSanguineoBox, rhBox, especializacaoBox;
	private JButton btnSalvar, btnCancelar;
	private JPasswordField senhaField;
	private Funcionario funcionario;
	

	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CadastroFuncionariosGUI frame = new CadastroFuncionariosGUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	
	/**
	 * Cria a janela.
	 */
	
	public CadastroFuncionariosGUI(Gerente usuario,Funcionario funcionario) {
		
		this.funcionario = funcionario;
		this.usuario = usuario;
		getContentPane().setBackground(SystemColor.inactiveCaption);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 423);
		
		this.criarTextField();
		this.criarButton();
		this.criarComboBox();
		this.criarLabel();
		this.adicionarEventocancelar();
		
		if (funcionario instanceof Medico){
			this.adicionarEventoSalvarMedico();
			
		}
		else{
			this.adicionarEventoSalvarEnfermeiro();
		}
		
		
		this.setVisible(true);
	}
		
		
	
	
	/**
	 * Mehtodos para criar os componentes na tela.
	 */
	
	public void criarTextField(){
		
		nomeField = new JTextField();
		nomeField.setToolTipText("");
		nomeField.setColumns(10);
		nomeField.setBounds(10, 99, 249, 20);
		getContentPane().add(nomeField);
		
		cpfField = new JTextField();
		cpfField.setColumns(10);
		cpfField.setBounds(10, 155, 119, 20);
		getContentPane().add(cpfField);
		
		nRegistroField = new JTextField();
		nRegistroField.setColumns(10);
		nRegistroField.setBounds(10, 211, 119, 20);
		getContentPane().add(nRegistroField);
		
		loginField = new JTextField();
		loginField.setColumns(10);
		loginField.setBounds(147, 155, 169, 20);
		getContentPane().add(loginField);
		
		// A pesar de ser um campo de password, o JPasswordField senhaField eh aqui criado
		
		senhaField = new JPasswordField();
		senhaField.setBounds(147, 211, 169, 20);
		getContentPane().add(senhaField);

		//Se o funcionario ainda não tem uma pessoa, quer dizer que ele é novo no sistema
		//Neste primeiro caso ele foi criado sem nenhum atributo pela GerenteGUI
		//Caso contrario, quer dizer que ele já existe e foi passado pela GerenciarFuncionarioGUI e já tem todos os atributos instanciados
		
		
		if (!(funcionario.getPessoa() == null)){ //Se funcionario.getPessoa() for diferente de nulo, ele fará o que está abaixo
			nomeField.setText(funcionario.getPessoa().getNome());
			cpfField.setText(funcionario.getPessoa().getCpf());
			nRegistroField.setText(Integer.toString(funcionario.getNumeroDeRegistro()));
			loginField.setText(funcionario.getSenha());
			senhaField.setText(funcionario.getSenha());
		}
		
		
	}
	
	// OBSERVE: ALTERACAO FEITA AQUI!!!!
	public void criarLabel(){
		
		lblTitulo = new JLabel();
		// Determina o nome do JLabel lblTitulo de acordo com o tipo de cadastro/atualização (Medico ou Enfermeiro)
		
		if (funcionario instanceof Medico){
			if (funcionario.getPessoa() == null){
				lblTitulo.setName("Cadastro de Médico");
			}
			else{
				lblTitulo.setName("Atualizar dados de Médico");
			}
			
		}
		else if (funcionario instanceof Enfermeiro){
			
			if (funcionario.getPessoa() == null){
				lblTitulo.setName("Cadastro de Enfermeiro");
			}
			else{
				lblTitulo.setName("Atualizar dados de Enfemeiro");
			}
		}
		
		
		
		
		lblTitulo.setFont(new Font("Georgia", Font.ITALIC, 25));
		lblTitulo.setBounds(10, 11, 227, 33);
		getContentPane().add(lblTitulo);
		
		lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 74, 46, 14);
		getContentPane().add(lblNome);
		
		lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpf.setBounds(10, 130, 46, 14);
		getContentPane().add(lblCpf);		
		
		lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSexo.setBounds(280, 74, 46, 14);
		getContentPane().add(lblSexo);
		
		lblTipoSangueRh = new JLabel("Tipo Sangu\u00EDneo / RH");
		lblTipoSangueRh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipoSangueRh.setBounds(10, 242, 124, 14);
		getContentPane().add(lblTipoSangueRh);
		
		lblNdeRegistro = new JLabel("N\u00BA de registro");
		lblNdeRegistro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNdeRegistro.setBounds(10, 186, 83, 14);
		getContentPane().add(lblNdeRegistro);
				
		lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLogin.setBounds(147, 130, 46, 14);
		getContentPane().add(lblLogin);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSenha.setBounds(147, 186, 46, 14);
		getContentPane().add(lblSenha);
		
		if (funcionario instanceof Medico){
			
			JLabel lblEspecializacao = new JLabel("Especializa\u00E7\u00E3o");
			lblEspecializacao.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblEspecializacao.setBounds(147, 242, 74, 14);
			getContentPane().add(lblEspecializacao);
			
		}
		
	}
	
	// OBSERVE: ALTERACAO FEITA AQUI!!!
	public void criarComboBox(){
		
		sexoBox = new JComboBox();
		sexoBox.setModel(new DefaultComboBoxModel(new String[] {"F", "M"}));
		sexoBox.setBounds(280, 99, 33, 20);
		getContentPane().add(sexoBox);
			
		tipoSanguineoBox = new JComboBox();
		tipoSanguineoBox.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "O", "AB"}));
		tipoSanguineoBox.setBounds(10, 267, 38, 20);
		getContentPane().add(tipoSanguineoBox);
		
		rhBox = new JComboBox();
		rhBox.setModel(new DefaultComboBoxModel(new String[] {"+", "-"}));
		rhBox.setBounds(55, 267, 38, 20);
		getContentPane().add(rhBox);
		
		// Cria o JComboBox especializacaoBox apenas se o Funcionario funcionario pertencer a classe Medico
		if (funcionario instanceof Medico){
			
			String[] opcoes = {"Neurologista", "Ortopedista", "Hepatologista", "Pneumatologista", "Endocrinologista", "Nefrologista", "Dermatologista", "Cardiologista", "Clínico Geral", "Mastologista", "Pediatra", "Oftomologista "," Obstetra"," Otorrino","Ginecologista","Geriatra"};
			especializacaoBox = new JComboBox(opcoes);
			especializacaoBox.setMaximumRowCount(4);
			especializacaoBox.setBounds(147, 267, 170, 20);
			getContentPane().add(especializacaoBox);
			
		}
			
		
	}
	
	
	public void criarButton(){
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Georgia", Font.ITALIC, 12));
		btnSalvar.setBounds(10, 346, 100, 27);
		getContentPane().add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Georgia", Font.ITALIC, 12));
		btnCancelar.setBounds(216, 346, 100, 27);
		getContentPane().add(btnCancelar);
		
	}

	
	public void adicionarEventoSalvarMedico(){			
		
		//-- Classes Anonimas para capturar dados dos JComboBoxes				
		btnSalvar.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				
				//Se o funcionario ainda não tem uma pessoa, quer dizer que ele é novo no sistema
				//Neste primeiro caso ele foi criado sem nenhum atributo pela GerenteGUI
				//Caso contrario, quer dizer que ele já existe e foi passado pela GerenciarFuncionarioGUI e já tem todos os atributos instanciados
				if (funcionario.getPessoa().equals(null)){
					
					Pessoa pessoa = new Pessoa();
					funcionario.setPessoa(pessoa);
					
					funcionario.getPessoa().setNome(nomeField.getText());	
					funcionario.getPessoa().setCpf(cpfField.getText());
					funcionario.setLogin(loginField.getText());	
					funcionario.setSenha(senhaField.getText());					
					funcionario.setNumeroDeRegistro(Integer.parseInt(nRegistroField.getText()));
					adicionarTipoSanguineo(); //o Java interpreta isso como sendo this.adicionarTipoSanguineo()
					
					//Cast duplo para garantir que o funcionario é um medico e que o SelectedItem da especializacaoBox retornara uma String
				    ((Medico)funcionario).setEspecialidade((String) especializacaoBox.getSelectedItem());

				    
					
					//Aciona camada de Negócio do sistema para ela fazer as validaçoes necessarias
					GerenciamentoFuncionarioControle controleAtualizar = new GerenciamentoFuncionarioControle(funcionario);
					controleAtualizar.AlterarDados();
			
				}
				
				else{					
					
					funcionario.getPessoa().setNome(nomeField.getText());	
					funcionario.getPessoa().setCpf(cpfField.getText());
					funcionario.setLogin(loginField.getText());	
					funcionario.setSenha(senhaField.getText());					
					funcionario.setNumeroDeRegistro(Integer.parseInt(nRegistroField.getText()));
					adicionarTipoSanguineo(); //o Java interpreta isso como sendo this.adicionarTipoSanguineo()
					
					//Aciona camada de Negócio do sistema para ela fazer as validaçoes necessarias
					GerenciamentoFuncionarioControle controleSalvar = new GerenciamentoFuncionarioControle(funcionario);
					controleSalvar.AlterarDados();
					
				}
				
				
			}
		});
		
	}	
		
		
		
	public void adicionarEventoSalvarEnfermeiro(){
		
		//-- Classe Anonima para capturar dados dos JComboBoxes				
		btnSalvar.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				
				//Se o funcionario ainda não tem uma pessoa, quer dizer que ele é novo no sistema
				//Neste primeiro caso ele foi criado sem nenhum atributo pela GerenteGUI
				//Caso contrario, quer dizer que ele já existe e foi passado pela GerenciarFuncionarioGUI e já tem todos os atributos instanciados
				if (funcionario.getPessoa().equals(null)){
					
					Pessoa pessoa = new Pessoa();
					funcionario.setPessoa(pessoa);
					
					funcionario.getPessoa().setNome(nomeField.getText());	
					funcionario.getPessoa().setCpf(cpfField.getText());
					funcionario.setLogin(loginField.getText());	
					funcionario.setSenha(senhaField.getText());					
					funcionario.setNumeroDeRegistro(Integer.parseInt(nRegistroField.getText()));
					adicionarTipoSanguineo(); //o Java interpreta isso como sendo this.adicionarTipoSanguineo()
					
					//Aciona camada de Negócio do sistema para ela fazer as validaçoes necessarias
					GerenciamentoFuncionarioControle controleAtualizar = new GerenciamentoFuncionarioControle(funcionario);
					controleAtualizar.AlterarDados();
			
				}
				
				else{					
					
					funcionario.getPessoa().setNome(nomeField.getText());	
					funcionario.getPessoa().setCpf(cpfField.getText());
					funcionario.setLogin(loginField.getText());	
					funcionario.setSenha(senhaField.getText());					
					funcionario.setNumeroDeRegistro(Integer.parseInt(nRegistroField.getText()));
					adicionarTipoSanguineo(); //o Java interpreta isso como sendo this.adicionarTipoSanguineo()
					
					//Aciona camada de Negócio do sistema para ela fazer as validaçoes necessarias
					GerenciamentoFuncionarioControle controleSalvar = new GerenciamentoFuncionarioControle(funcionario);
					controleSalvar.AlterarDados();
					
				}
				
				
			}
		});
	}
		
	public void adicionarEventocancelar(){
		
		btnCancelar.addActionListener(new ActionListener(){		
			
			public void actionPerformed(ActionEvent e) {
				
				GerenteGUI proximaTela = new GerenteGUI(usuario);
				proximaTela.setVisible(true);
				CadastroFuncionariosGUI.this.dispose();
				
			}
		
		});
	
			
	}
		
		
	public void adicionarTipoSanguineo(){
		
		String tipo = (String) tipoSanguineoBox.getSelectedItem();
		String rh = (String) rhBox.getSelectedItem();
		
		if (tipo == "A"){
			if (rh== "+"){
				funcionario.getPessoa().setTipoSanguineo(TipoSanguineo.AP.getTipoSanguineo());
			}
			else if (rh=="-"){
				funcionario.getPessoa().setTipoSanguineo(TipoSanguineo.AN.getTipoSanguineo());
			}
		}
					
		
		else if(tipo == "B"){
			if (rh == "+"){
				funcionario.getPessoa().setTipoSanguineo(TipoSanguineo.BP.getTipoSanguineo());
			}
			
			else if (rh == "-"){
				funcionario.getPessoa().setTipoSanguineo(TipoSanguineo.BN.getTipoSanguineo());
			}
			
		}
		
		
		else if(tipo == "O"){
			if (rh == "+"){
				funcionario.getPessoa().setTipoSanguineo(TipoSanguineo.OP.getTipoSanguineo());
			}
			
			else if (rh=="-"){							
				funcionario.getPessoa().setTipoSanguineo(TipoSanguineo.ON.getTipoSanguineo());
			}
			
			
		}
		
		else if(tipo == "AB"){
			if (rh == "+"){
				funcionario.getPessoa().setTipoSanguineo(TipoSanguineo.ABP.getTipoSanguineo());
			}
			
			else if (rh=="-"){
				funcionario.getPessoa().setTipoSanguineo(TipoSanguineo.ABN.getTipoSanguineo());
			}			
			
		}	
	
	}
	
	
	///*
	
	// Para testes com a tela.
	
	
	private static final long serialVersionUID = 1L;

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

					
					JFrame frame1 = new CadastroFuncionariosGUI(a1,m0);
					JFrame frame2 = new CadastroFuncionariosGUI(a1,m1);
					JFrame frame3 = new CadastroFuncionariosGUI(a1,e0);
					JFrame frame4 = new CadastroFuncionariosGUI(a1,e1);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
		
	
}

