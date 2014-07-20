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
import aplicacao.dominio.Atendente;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Medico;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Gerente;
import aplicacao.dominio.Pessoa;
import aplicacao.enums.TipoSanguineo;

public class SalvarDadosFuncionariosGUI extends JFrame {
	
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
	
	public SalvarDadosFuncionariosGUI(Gerente usuario,Funcionario funcionario) {
		
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
		this.adicionarEventoSalvarFuncionario();
		
		
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

		//Se o funcionario ainda n�o tem uma pessoa, quer dizer que ele � novo no sistema
		//Neste primeiro caso ele foi criado sem nenhum atributo pela GerenteGUI
		//Caso contrario, quer dizer que ele j� existe e foi passado pela GerenciarFuncionarioGUI e j� tem todos os atributos instanciados
		
		if (!(funcionario.getPessoa() == null)){ //Se funcionario.getPessoa() for diferente de nulo, ele far� o que est� abaixo
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
		// Determina o nome do JLabel lblTitulo de acordo com o tipo de cadastro/atualiza��o (Medico ou Enfermeiro)
		
		if (funcionario instanceof Medico){
			if (funcionario.getPessoa() == null){
				lblTitulo.setName("Cadastro de M�dico");
			}
			else{
				lblTitulo.setName("Atualizar dados de M�dico");
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
			
			String[] opcoes = {"Neurologista", "Ortopedista", "Hepatologista", "Pneumatologista", "Endocrinologista", "Nefrologista", "Dermatologista", "Cardiologista", "Cl�nico Geral", "Mastologista", "Pediatra", "Oftomologista "," Obstetra"," Otorrino","Ginecologista","Geriatra"};
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

	
	public void adicionarEventoSalvarFuncionario(){			
		
		//-- Classes Anonimas para capturar dados dos JComboBoxes				
		btnSalvar.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				
				//Se o funcionario ainda n�o tem uma pessoa, quer dizer que ele � novo no sistema
				//Neste primeiro caso ele foi criado sem nenhum atributo pela GerenteGUI
				//Caso contrario, quer dizer que ele j� existe e foi passado pela GerenciarFuncionarioGUI e j� tem todos os atributos instanciados
				if (funcionario.getPessoa().equals(null)){
					
					Pessoa pessoa = new Pessoa();
					funcionario.setPessoa(pessoa);
					
					funcionario.getPessoa().setNome(nomeField.getText());	
					funcionario.getPessoa().setCpf(cpfField.getText());
					funcionario.setLogin(loginField.getText());	
					funcionario.setSenha(senhaField.getText());					
					funcionario.setNumeroDeRegistro(Integer.parseInt(nRegistroField.getText()));
					adicionarTipoSanguineo(); //o Java interpreta isso como sendo this.adicionarTipoSanguineo()
					
					
					// Condicional que seta a especializacao se o funcionario pertencer a classe Medico
					if (funcionario instanceof Medico){
						//Cast duplo para garantir que o funcionario � um medico e que o SelectedItem da especializacaoBox retornara uma String
						((Medico)funcionario).setEspecialidade((String) especializacaoBox.getSelectedItem());
					}
				    

				    					
					//Aciona camada de Neg�cio do sistema para ela fazer as valida�oes necessarias
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
					
					//Aciona camada de Neg�cio do sistema para ela fazer as valida�oes necessarias
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
				SalvarDadosFuncionariosGUI.this.dispose();
				
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
			
	
}
