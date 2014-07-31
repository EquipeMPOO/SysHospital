/* COMENTARIOS SOBRE O QUE ESTAH FALTANDO NESTA CLASSE:
 * 
 * 1- REVISAR CLASSE DOMINIO Atendimento.
 * 2- VERIFICAR FUNCIONAMENTO DE TODOS OS METODOS GET E SET DE Paciente E FILIADOS; E IMPLEMENTAR METODO 
 * 	  PARA RETORNAR UMA LISTA COM TODAS AS DOENCAS NAO CRONICAS REGISTRADAS NO BANCO 
 * 3- IMPLEMENTACAO DE ENFERMIDADE E FILIADOS
 * 4- OBS1 IMPORTANTE: EM ATENDIMENTO, DEVE HAVER Dates SEPARADOS PARA MEDICO E ENFERMEIRO (?)
 * 5- OBS2 IMPORTANTE: IMPLESMENTAR PARTE DE MEDICO DE VISUALIZAR ULTIMO PRONTUARIO PREENCHIDO!!!!!
 * 
 * !!!!!!!!!!!ATENCAO!!!!!!!!!!!!!!
 * !!!		NÃO TESTADO			!!!
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 */

package aplicacao.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;

import aplicacao.controle.EnfermidadeControle;
import aplicacao.controle.PacienteControle;
import aplicacao.dominio.Atendimento;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Enfermidade;
import aplicacao.dominio.EnfermidadePessoal;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Medico;
import aplicacao.dominio.Paciente;
import aplicacao.enums.StatusDePessoa;
import aplicacao.enums.TipoEnfermidade;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProntuarioGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnSalvar, btnCancelar;
	private JTextArea txtEnfCronicas,txtParecer,txtComentarioEnfermeiro;
	private JLabel lblGrupoSanguineo,lblM,lblKg,lblStatus,lblSexo,lblProntuario,lblPeso, lblAltura,lblComentarioEnfermeiro,lblNome,lblIdade, lblEnfermidadesCrnicas, lblEnfermidades, lblParecerMdico;;
	private JTextPane txtStatus;
	private JTextField idadeField,alturaField,pesoField,nomeField, sangueField, sexoField;
	private JList enfermidadesList;
	private JComboBox<String> statusPessoaBox;
	private Funcionario usuario;
	private Paciente paciente;
	private boolean novo;
	private ArrayList<Enfermidade> enfermidades;
	private String[] statusPessoa = {"Viva","Morta"};
	private Date data;
	
	

	/**
	 * Cria a janela.
	 */
	public ProntuarioGUI(Funcionario usuario, Paciente paciente, boolean novo) {
		// - Confirando o frame e o contentPane
		this.usuario = usuario;
		this.paciente = paciente;
		this.novo = novo;
		setTitle("SysHospital");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if (usuario instanceof Medico){
			setBounds(0, 0, 676, 522);
		}
		else{
			setBounds(0, 0, 676, 423);
		}
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
				
		//-- Chamando metodo para criar Labels (textos estáticos)
		this.criarLabel();
		
		// -- Chamando metodo para criar Buttons
		this.criarButton();
		
		// -- Chamando metodo para criar TextAreas
		this.criarTextArea();
		
		// -- Chamando metodo para criar TextFields
		this.criarTextField();
		
		// -- Chamando metodo para adicionar eventos aos componentes
		this.criarEventos();
				
			
				
	}
			
				
	/**
	 * Cria todos os JLabels na tela
	 * @author Guilherme
	 * @return void
	 * @param null
	 */
	public void criarLabel(){ 
		
		lblProntuario = new JLabel("Prontu\u00E1rio");
		lblProntuario.setFont(new Font("Georgia", Font.ITALIC, 25));
		lblProntuario.setBounds(285, 11, 143, 33);
		contentPane.add(lblProntuario);
		
		lblPeso = new JLabel("Peso:");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPeso.setBounds(348, 100, 36, 14);
		contentPane.add(lblPeso);
		
		lblAltura = new JLabel("Altura:");
		lblAltura.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAltura.setBounds(246, 100, 36, 14);
		contentPane.add(lblAltura);
		
		lblComentarioEnfermeiro = new JLabel("Coment\u00E1rio do enfermeiro:");
		lblComentarioEnfermeiro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblComentarioEnfermeiro.setBounds(10, 209, 155, 14);
		contentPane.add(lblComentarioEnfermeiro);
		
		lblNome = new JLabel("Nome: ");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(10, 75, 44, 14);
		contentPane.add(lblNome);
		
		lblIdade = new JLabel("Idade: ");
		lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdade.setBounds(163, 100, 39, 14);
		contentPane.add(lblIdade);
		
		lblSexo = new JLabel("Sexo: ");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSexo.setBounds(572, 75, 36, 14);
		contentPane.add(lblSexo);
		
		lblGrupoSanguineo = new JLabel("Grupo Sangu\u00EDneo: ");
		lblGrupoSanguineo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGrupoSanguineo.setBounds(10, 100, 102, 14);
		contentPane.add(lblGrupoSanguineo);
		
		lblM = new JLabel("m");
		lblM.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblM.setBounds(321, 100, 17, 14);
		contentPane.add(lblM);
		
		lblKg = new JLabel("kg");
		lblKg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKg.setBounds(438, 100, 17, 14);
		contentPane.add(lblKg);
		
		lblStatus = new JLabel("Status: ");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStatus.setBounds(465, 100, 44, 14);
		contentPane.add(lblStatus);
		
		lblEnfermidadesCrnicas = new JLabel("Enfermidades cr\u00F4nicas:");
		lblEnfermidadesCrnicas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEnfermidadesCrnicas.setBounds(10, 125, 130, 14);
		contentPane.add(lblEnfermidadesCrnicas);
		
		if (usuario instanceof Medico){
			lblEnfermidades = new JLabel("Enfermidades:");
			lblEnfermidades.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblEnfermidades.setBounds(348, 210, 82, 14);
			contentPane.add(lblEnfermidades);
			
			lblParecerMdico = new JLabel("Parecer M\u00E9dico:");
			lblParecerMdico.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblParecerMdico.setBounds(10, 312, 100, 14);
			contentPane.add(lblParecerMdico);
		}
		
	}
			
		
	/**
	 * Cria todos os JButtons na tela.
	 * @author Guilherme
	 * @return void
	 * @param null
	 */
	public void criarButton(){
		
		// Se for um novo prontuario a ser preenchido
		if(novo){
			btnSalvar = new JButton("Salvar");
			btnSalvar.setFont(new Font("Georgia", Font.ITALIC, 12));
			if (usuario instanceof Medico){
				btnSalvar.setBounds(10, 425, 100, 27);
			}
			else{
				btnSalvar.setBounds(10, 327, 100, 27);
			}
			contentPane.add(btnSalvar);
		}
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Georgia", Font.ITALIC, 12));
		if (usuario instanceof Medico){
			btnCancelar.setBounds(550, 425, 100, 27);
		}
		else{
			btnCancelar.setBounds(550, 327, 100, 27);
		}
		contentPane.add(btnCancelar);
		
		
	}
		
		
	/**
	 * Cria todos os JTextFields na tela, assim como cria o JComboBox statusPessoaBox.
	 */
	// CLASSE DE DOMINIO Atendimento NAO IMPLEMENTADA TOTALEMENTE. "IMPLEMENTEI" O QUE ME FOI NECESSARIO. CARECE REVISAO
	public void criarTextField(){
		idadeField = new JTextField();
		idadeField.setBounds(212, 100, 26, 16);
		idadeField.setText(Integer.toString(paciente.getPessoa().getIdade()));
		if (usuario instanceof Medico){
			idadeField.setBackground(SystemColor.inactiveCaption);
			idadeField.setEditable(false);
		}
		contentPane.add(idadeField);
		
		ArrayList<Atendimento>listaAtendimentos = paciente.getHistorico().get(paciente.getHistorico().size()-1).getAtendimentos();;
		
		/* 
		 * Recupera de paciente o ultimo elemento (Entrada) de (List) historico e, a partir deste,
		 * recupera o ultimo elemento (Atendimento) de (List) situacaoDePaciente, para so entao recuperar o
		 * (float) altura deste elemento, salvando-o assim em um variavel local denominada altura. 
		 */
		double altura;
		if (novo){ // Se for um novo prontuario
			altura = paciente.getHistorico().get(paciente.getHistorico().size()-1).getAtendimentos().get(listaAtendimentos.size()-1).getAltura();
		}
		else{
			altura = paciente.getHistorico().get(paciente.getHistorico().size()-1).getAtendimentos().get(paciente.getHistorico().size()-2).getAltura();
		}
		
		alturaField = new JTextField();
		alturaField.setBounds(292, 100, 30, 16);
		alturaField.setText(Double.toString(altura));  // Tornando o double um String
		if (usuario instanceof Medico){
			alturaField.setBackground(SystemColor.inactiveCaption);
			alturaField.setEditable(false);
		}
		contentPane.add(alturaField);

		/* 
		 * Recupera de paciente o ultimo elemento (Entrada) de (List) historico e, a partir deste,
		 * recupera o ultimo elemento (Atendimento) de (List) situacaoDePaciente, para so entao recuperar o
		 * (float) peso deste elemento, salvando-o assim em um variavel local denominada peso. 
		 */
		double peso;
		if(novo){ // Se for um novo prontuario
			peso = paciente.getHistorico().get(paciente.getHistorico().size()-1).getAtendimentos().get(listaAtendimentos.size()-1).getPeso();
		}
		else{
			peso = paciente.getHistorico().get(paciente.getHistorico().size()-1).getAtendimentos().get(listaAtendimentos.size()-2).getPeso();
		}
		
		pesoField = new JTextField();
		pesoField.setBounds(394, 100, 44, 16);
		pesoField.setText(Double.toString(peso));
		if (usuario instanceof Medico){
			pesoField.setBackground(SystemColor.inactiveCaption);
			pesoField.setEditable(false);
		}
		contentPane.add(pesoField);
		
		nomeField = new JTextField();
		nomeField.setEditable(false);
		nomeField.setBackground(SystemColor.inactiveCaption);
		nomeField.setBounds(64, 75, 498, 16);
		nomeField.setText(paciente.getPessoa().getNome());
		contentPane.add(nomeField);
		
		sangueField = new JTextField();
		sangueField.setEditable(false);
		sangueField.setBackground(SystemColor.inactiveCaption);
		sangueField.setBounds(122, 100, 31, 16);
		sangueField.setText(paciente.getPessoa().getTipoSanguineo());
		contentPane.add(sangueField);
		
		sexoField = new JTextField();
		sexoField.setEditable(false);
		sexoField.setBackground(SystemColor.inactiveCaption);
		sexoField.setText(paciente.getPessoa().getSexo());
		sexoField.setBounds(618, 75, 26, 16);
		contentPane.add(sexoField);
		
		// --- Cria comboBox de StatusPessoa
		statusPessoaBox = new JComboBox();
		if (novo){
			statusPessoaBox.setModel(new DefaultComboBoxModel(new String[] {"Viva", "Morta"}));
		}
		else{
			statusPessoaBox.setModel(new DefaultComboBoxModel(new String[] {paciente.getPessoa().getStatusDePessoa()}));
			statusPessoaBox.setEditable(false);
		}
		statusPessoaBox.setBounds(519, 99, 53, 18);
		contentPane.add(statusPessoaBox);
		
	}
		
	
	/**
	 * Cria todos os JTextAreas na tela, bem como cria o JList, cara usuario pertenca a classe Medico.
	 * @author Guilherme
	 * @return void
	 * @param null
	 */
	/*
	 * VERIFICAR FUNCIONAMENTO DE TODOS OS METODOS GET E SET DE Paciente E FILIADOS; E IMPLEMENTAR METODO
	 * PARA RETORNAR UMA LISTA COM TODAS AS DOENCAS NAO CRONICAS REGISTRADAS NO BANCO 
	 */
	// IMPLEMENTAR ENFERMIDADE E FILIADOS (AJUSTALOS)
	public void criarTextArea(){
		
		String nova = new String();
		nova = ""; // String vazio
		
		txtEnfCronicas = new JTextArea();
		txtEnfCronicas.setEditable(false);
		txtEnfCronicas.setBackground(SystemColor.inactiveCaption);
		txtEnfCronicas.setBounds(10, 150, 634, 48);
		ArrayList<Atendimento> listaAtendimentos = paciente.getHistorico().get(paciente.getHistorico().size()-1).getAtendimentos();
		
		/*Percorrendo a lista de enfermidades cronicas do paciente e concatenando seus nomes
		 * em um "grande" String, cada nome posto linha a linha. 
		 */
		for (EnfermidadePessoal enfermidade:paciente.getDoenca()){
			nova = nova + enfermidade.getEnfermidade().getNome() + "\n";
		}
		txtEnfCronicas.setText(nova);
		contentPane.add(txtEnfCronicas);
		
		
		
		txtStatus = new JTextPane();
		if (usuario instanceof Medico){
			txtStatus.setBounds(0, 463, 660, 20);
		}
		else{
			txtStatus.setBounds(0, 364, 660, 20);
		}
		contentPane.add(txtStatus);
		
		
				
		txtComentarioEnfermeiro = new JTextArea();
		if (usuario instanceof Medico){
			/* 
			 * Recupera de paciente o ultimo elemento (Entrada) de (List) historico e, a partir deste,
			 * recupera o ultimo elemento (Atendimento) de (List) situacaoDePaciente, para so entao recuperar o
			 * (String) comentarioEnfermeiro deste elemento, salvando-o assim em um variavel local denominada altura.
			 * Leva-se em consideracao que o element eh nao vazio, visto que o Medico apenas visita o paciente
			 * apos a visita previa de um Enfermeiro que, por sua vez, jah haverah comentado e facilidado o diagnostico do Medico. 
			 */
			String comentarioEnfermeiro = new String();
			comentarioEnfermeiro = paciente.getHistorico().get(paciente.getHistorico().size()-1).getAtendimentos().get(listaAtendimentos.size()-1).getComentarioEnfermeiro();
			txtComentarioEnfermeiro.setBounds(10, 234, 294, 67);
			txtComentarioEnfermeiro.setEditable(false);
			txtComentarioEnfermeiro.setBackground(SystemColor.inactiveCaption);
			txtComentarioEnfermeiro.setText(comentarioEnfermeiro);
		}
		else{
			txtComentarioEnfermeiro.setBounds(10, 234, 634, 67);
		}
		contentPane.add(txtComentarioEnfermeiro);
		
		
		
		if (usuario instanceof Medico){
							
			EnfermidadeControle pesquisaEnfermidade = new EnfermidadeControle();
			for (Enfermidade enf : pesquisaEnfermidade.getComuns()){
				enfermidades.add(enf);
				
			}
			
			
			enfermidadesList = new JList<String>();
			enfermidadesList.setBounds(348, 234, 294, 67);				
			
			DefaultListModel<String> nomesEnfermidades = new DefaultListModel<String>();
			for (Enfermidade enfermidade: enfermidades){
				nomesEnfermidades.addElement(enfermidade.getNome());
			}
			enfermidadesList.setModel(nomesEnfermidades);
			contentPane.add(enfermidadesList);
			
			
		
			txtParecer = new JTextArea();
			txtParecer.setBounds(10, 337, 632, 67);
			contentPane.add(txtParecer);
		}
		
	}


	/**
	 * Adiona os eventos dos JButton btnSalvar e btnCancelar
	 * @author Guilherme
	 * @return void
	 * @param null
	 */
	public void criarEventos(){
		
		
		
		btnCancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JFrame novaTela = new GerenciarPacienteGUI(usuario,0);
				novaTela.setVisible(true);
				ProntuarioGUI.this.dispose();
			}
			
		});
		
		btnSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// - Retorna a data e hora do sistema e transforma em String
				PacienteControle controle = new PacienteControle();
				
				
				
				if (usuario instanceof Enfermeiro){
									

					Paciente pacienteConfigurado = configurarPaciente();
					int indexUltimoAtendimento = pacienteConfigurado.getHistorico().size();
					controle.adicionarAtendimento(pacienteConfigurado.getHistorico().get(indexUltimoAtendimento-1));
					
					
					
				}
				
				else if(usuario instanceof Medico){
					
					Paciente pacienteConfiguradoEmMedico = configurarPacienteEmMedico();
					int indexUltimoAtendimento = pacienteConfiguradoEmMedico.getHistorico().size();
					controle.completarAtendimento(pacienteConfiguradoEmMedico.getHistorico().get(indexUltimoAtendimento-1));
					
					
					
					
				}
				mensagemStatus(true);
				
				
			}
		});
		
		
		
	}


	protected Paciente configurarPacienteEmMedico() {
		List<Object> enfSelecionadas = new ArrayList<Object>(); //Lista de Strings com o nome das enfermidades (nao funciona com <String>?
		List<Enfermidade> listaEnfermidadesNova = new ArrayList<Enfermidade>();
		enfSelecionadas.addAll(enfermidadesList.getSelectedValuesList());
		
		/*
		 * Percorre a lista de enfermidades selecionadas pelo medico, adiciona o elemento quando o acha a uma
		 * listaEnfermidadesNova e dah um break no for mais interno, passando assim para o proximo item a ser comparado, que foi
		 * selecionado pelo medico no JList de enfermidades
		 */
		for (Object nome:enfSelecionadas){
			for (Enfermidade efermidade: enfermidades){
				if (nome.equals(efermidade.getNome())){
					listaEnfermidadesNova.add(efermidade);
					break;
				}
			}
		}
		
		
		ArrayList<EnfermidadePessoal> listaEP = new ArrayList<EnfermidadePessoal>();
		
		EnfermidadePessoal ep = new EnfermidadePessoal();
		
		for (Enfermidade enfermidade : enfermidades) {
			ep.setEnfermidade(enfermidade);
			listaEP.add(ep);
		}
		
		
		
		int indexUltimoAtendimento = paciente.getHistorico().get(-1).getAtendimentos().size();
		int indexUltimaEntrada = paciente.getHistorico().size();
		paciente.getHistorico().get(indexUltimaEntrada-1).getAtendimentos().get(indexUltimoAtendimento-1).setDoencas(listaEP);
		paciente.getHistorico().get(indexUltimaEntrada-1).getAtendimentos().get(indexUltimoAtendimento-1).setComentarioMedico(txtParecer.getText());
		
		return paciente;
	}


	
	protected Paciente configurarPaciente() {
		data = new Date();
		String stringData = "" + data;
		
		Atendimento atendimento = new Atendimento();
		double altura = Double.parseDouble(alturaField.getText());
		atendimento.setAltura(altura);
		atendimento.setPeso(Double.parseDouble(pesoField.getText()));
		atendimento.setComentarioEnfermeiro(txtComentarioEnfermeiro.getText());
		atendimento.setData(stringData);
		atendimento.setEnfermeiro((Enfermeiro) usuario);
		paciente.getPessoa().setIdade(Integer.parseInt(idadeField.getText()));
		paciente.getPessoa().setStatusDePessoa((String) statusPessoaBox.getSelectedItem());
		paciente.getHistorico().get(-1).getAtendimentos().add(atendimento);
		
		return paciente;
	}
	
	
	public void mensagemStatus(boolean novo){
		if (novo){
			txtStatus.setForeground(Color.BLUE);
			txtStatus.setText("Processo realizado com sucesso!");
			
			JFrame novaTela = new GerenciarPacienteGUI(usuario, 0);
			novaTela.setVisible(true);
			ProntuarioGUI.this.dispose();
		}
		else{
			txtStatus.setForeground(Color.RED);
			txtStatus.setText("Não foi possível concluir a operacao!");
		}
	}
}
