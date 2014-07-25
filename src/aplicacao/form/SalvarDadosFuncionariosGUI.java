package aplicacao.form;

import java.awt.Color;
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

import aplicacao.controle.GerenciamentoControle;
import aplicacao.dominio.Atendente;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Medico;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Gerente;
import aplicacao.dominio.Pessoa;
import aplicacao.enums.TipoSanguineo;

import javax.swing.JTextPane;

/**
 * 
 * @author icaro
 * Classe que agrupa a tela responsável por todas as operações de inserção e modificação de médicos e enfermeiros no sistema
 * Pode ser instanciada por um administrador para gerenciar os demais funcionarios do software ou para adicionar um novo funcionario
 */

public class SalvarDadosFuncionariosGUI extends JFrame {
	
	/**
	 * 
	 */
	
	
	private Gerente usuario;
	private JTextField nomeField, cpfField, nRegistroField, loginField;
	private JLabel lblTitulo, lblNome, lblCpf, lblSexo, lblTipoSangueRh, lblNdeRegistro, lblLogin, lblSenha, lblEspecializacao;
	private JComboBox sexoBox, tipoSanguineoBox, rhBox, especializacaoBox;
	private JButton btnSalvar, btnCancelar;
	private JPasswordField senhaField;
	private Funcionario funcionario;
	private JTextPane txtStatus;
	
	/**
	 * Cria a janela e adocopma todos os componentes adequados para a operação realizada pela tela
	 */
	
	public SalvarDadosFuncionariosGUI(Gerente usuario,Funcionario funcionario) {
		
		this.funcionario = funcionario;
		this.usuario = usuario;
		getContentPane().setBackground(SystemColor.inactiveCaption);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 445);
		
		this.criarTextField();
		this.criarButton();
		this.criarComboBox();
		this.criarLabel();
		this.adicionarEventocancelar();
		this.adicionarEventoSalvarFuncionario();
		
		
	}
		
		
	
	
	/**
	 * Metodos para criar os componentes na tela.
	 * Caso esta tela esteja sendo utilizada para cadastro, os campos serão inicializados vazios
	 * Caso esta tela esteja sendo utilizada para atualização de dados, os campos serão inicializados com os valores informados no construtor da tela
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
		
		if (funcionario instanceof Medico || funcionario instanceof Enfermeiro){
			
			nRegistroField = new JTextField();
			nRegistroField.setColumns(10);
			nRegistroField.setBounds(10, 211, 119, 20);
			getContentPane().add(nRegistroField);
			
		}
		
		
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
			
			if(funcionario instanceof Enfermeiro){
			nRegistroField.setText(Integer.toString(((Enfermeiro) funcionario).getNumeroDeRegistro()));
			}
			else if(funcionario instanceof Medico){
				nRegistroField.setText(Integer.toString(((Medico) funcionario).getNumeroDeRegistro()));
			}
			
			loginField.setText(funcionario.getLogin());
			senhaField.setText(funcionario.getSenha());
		}
		
		
	}
	
	// OBSERVE: ALTERACAO FEITA AQUI!!!!
	/**
	 * Aplica os campos de texto fixos da tela. O título que aparecerá no topo da tela e alguns campos preenchidos dependem de qual classe o atributo funcionario pertence
	 */
	
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
		else if (funcionario instanceof Atendente){
			
			if (funcionario.getPessoa()==null){
				lblTitulo.setName("Cadastro de Atendente");
			}
			else{
				lblTitulo.setName("Atualizar dados de Atendente");
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
		
		if (funcionario instanceof Medico||funcionario instanceof Enfermeiro){
			
			lblNdeRegistro = new JLabel("N\u00BA de registro");
			lblNdeRegistro.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNdeRegistro.setBounds(10, 186, 83, 14);
			getContentPane().add(lblNdeRegistro);
			
		}
		
				
		lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLogin.setBounds(147, 130, 46, 14);
		getContentPane().add(lblLogin);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSenha.setBounds(147, 186, 46, 14);
		getContentPane().add(lblSenha);
		
		txtStatus = new JTextPane();
		txtStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtStatus.setText("Preencha todos os campos corretamente!");
		txtStatus.setBounds(0, 384, 326, 21);
		getContentPane().add(txtStatus);
		
		if (funcionario instanceof Medico){
			
			lblEspecializacao = new JLabel("Especializa\u00E7\u00E3o");
			lblEspecializacao.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblEspecializacao.setBounds(147, 242, 74, 14);
			getContentPane().add(lblEspecializacao);
			
		}
		
	}
	
	// OBSERVE: ALTERACAO FEITA AQUI!!!
	
	/**
	 * Adiciona todas as caixas de seleção necessarias para as operações socilitadas
	 * Caso o tipo de funcionario a ser cadastrado/alterado seja um medico, haverá uma caixa de selação extra para definir qual a sua especialidade 
	 */
	
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
	
	/**
	 * Adiciona os botoes necessarios para execucao da tela
	 */
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

	
	/**
	 * Metodo responsavel pela adicao de todos os eventos necessarios para cadastrar/atualizar dados de um funcionario no sistema
	 * A operação realizada ao se realizar o evento do clico dependerá da classe a qual o atributo funcionario pertence
	 * Os dados capturados pela tela serão salvos num objeto da classe de domínio que representará o funcionario que se deseja cadastrar/alterar
	 * Dentro desse metodo há um acesso a camada de negócio que fará as validações e verificações necessárias para efetuação do casdastro/alteração
	 */
	
	public void adicionarEventoSalvarFuncionario(){			
		
		//-- Classes Anonimas para capturar dados dos JComboBoxes				
		btnSalvar.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				
				//Se o funcionario ainda não tem uma pessoa, quer dizer que ele é novo no sistema
				//Neste primeiro caso ele foi criado sem nenhum atributo pela GerenteGUI
				//Caso contrario, quer dizer que ele já existe e foi passado pela GerenciarFuncionarioGUI e já tem todos os atributos instanciados
				if (funcionario.getPessoa() == null){
					
					Pessoa pessoa = new Pessoa();
					funcionario.setPessoa(pessoa);
					auxFuncionario();
					
			
				}
				
				else{					
					
					auxFuncionario();
					
				}
				
				
			}

			/**
			 * Define todas as informacoes de (Funcionario) funcionario a partir dos dados provenientes dos componentes da interface gráfica.
			 * O tratamento destes já é diferenciado dentro da própria classe. Como exemplo, a definicao de especialidade para (Medico) funcionario
			 * a partir do (JComboBox) especializacaoBox, que evidentemente cabe apenas ao funcionario pertencente a classe Medico. 
			 * @param funcionario
			 */
			@SuppressWarnings("deprecation")
			public void auxFuncionario(){
				
				funcionario.getPessoa().setNome(nomeField.getText());	
				funcionario.getPessoa().setCpf(cpfField.getText());
				funcionario.getPessoa().setSexo((String) sexoBox.getSelectedItem());
				funcionario.setLogin(loginField.getText());	
				funcionario.setSenha(senhaField.getText());					
				adicionarTipoSanguineo(); //o Java interpreta isso como sendo this.adicionarTipoSanguineo()					
				
				// Condicional que seta a especializacao se o funcionario pertencer a classe Medico
				if (funcionario instanceof Medico){
					//Cast duplo para garantir que o funcionario é um medico e que o SelectedItem da especializacaoBox retornara uma String
					((Medico)funcionario).setEspecialidade((String) especializacaoBox.getSelectedItem());
					((Medico)funcionario).setNumeroDeRegistro(Integer.parseInt(nRegistroField.getText()));
				}
				else if (funcionario instanceof Enfermeiro){
					((Enfermeiro)funcionario).setNumeroDeRegistro(Integer.parseInt(nRegistroField.getText()));
				}
			    

			    					
				//Verifica se campos estão vazios e aciona camada de negócio, fazendo comparações necessárias
				if (verificarCamposVazios()){
					txtStatus.setForeground(Color.BLUE);
					txtStatus.setText("Processo realizado com sucesso!");
					GerenciamentoControle controleAtualizar = new GerenciamentoControle(funcionario);
					controleAtualizar.Cadastrar();
					
				}
				else{
					txtStatus.setForeground(Color.RED);
					txtStatus.setText("Erro! Preencha todos os campos corretamente!");
				}
				
			}
			
			
			
			
		});
		
	}	
	
	/**
	 * Metodo necessario por adicionar um evento de cancelar operação
	 * Retorna para a tela de gerente 
	 */
		
	public void adicionarEventocancelar(){
		
		btnCancelar.addActionListener(new ActionListener(){		
			
			public void actionPerformed(ActionEvent e) {
				
				GerenteGUI proximaTela = new GerenteGUI(usuario);
				proximaTela.setVisible(true);
				SalvarDadosFuncionariosGUI.this.dispose();
				
			}
		
		});
	
			
	}
	
	/**
	 * Metodo responsavel pela analise de qual tipo sanguineo foi selecionado pelo usuario e qual será devidamente cadastrado no banco de dados
	 * A operação depende da combinação das opções selecionadas entre as caixas de seleção de tipo sanguineo e fator RH
	 */
		
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
	
	public boolean verificarCamposVazios(){
		if (nomeField.getSelectedText()==""||cpfField.getSelectedText()==""||loginField.getSelectedText()==""||senhaField.getSelectedText()==""){
			return false;
		}
		else{
			return true;
		}
	}
			
	
}