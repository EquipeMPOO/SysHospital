package aplicacao.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;

import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JCheckBox;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import aplicacao.controle.EnfermidadeControle;
import aplicacao.controle.PacienteControle;
import aplicacao.dao.PacienteDAO;
import aplicacao.dominio.Enfermidade;
import aplicacao.dominio.EnfermidadePessoal;
import aplicacao.dominio.Entrada;
import aplicacao.dominio.Gerente;
import aplicacao.dominio.Paciente;
import aplicacao.dominio.Pessoa;
import aplicacao.enums.StatusDePessoa;
import aplicacao.enums.TipoEnfermidade;
import aplicacao.enums.TipoSanguineo;
import javax.swing.JScrollBar;


public class CadastroPacienteGUI extends JFrame {

	private JPanel contentPane;
	private JTextField nomeField, cpfField;
	
	private JButton btnSalvar, btnCancelar;
	private JComboBox tipoSangueBox, sexoBox, rhBox; 
	private JList anomaliaList, doencaList, deficienciaList, alergiaList;
	private JScrollPane scrollPaneDoenca, scrollPaneAnomalia, scrollPaneDeficiencia, scrollPaneAlergia;
	private JTextField idadeField;
	private JTextPane textStatus;
	private ArrayList<String> nomesDeficiencia,nomesAnomalia,nomesDoencaCronica,nomesAlergia;
	private ArrayList<Enfermidade> enfermidades;
	private Pessoa pessoa;
	private Gerente usuario;
	private Paciente paciente;
	private Entrada entrada;
	
	/**
	 * Cria a janela.
	 */
	public CadastroPacienteGUI(Gerente usuario) {
		
		this.usuario = usuario;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(SystemColor.inactiveCaption);
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		
		this.criarBoxes();
		this.criarBotoes();
		this.criarLabels();
		this.criarFields();
		this.criarListas();

		this.criarEventos();
		
		this.setVisible(true);
		
	}

	
	/**
	 * @author Guilherme
	 * Cria todos os JButtons na janela.
	 */
	public void criarBotoes(){
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Georgia", Font.ITALIC, 12));
		btnSalvar.setBounds(10, 346, 100, 27);
		contentPane.add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Georgia", Font.ITALIC, 12));
		btnCancelar.setBounds(433, 346, 100, 27);
		contentPane.add(btnCancelar);

	}
	
	
	/**
	 * @author Guilherme
	 * Cria todos os JComboBoxes na janela.
	 */
	public void criarBoxes(){
		
		tipoSangueBox = new JComboBox();
		tipoSangueBox.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "O", "AB"}));
		tipoSangueBox.setBounds(360, 110, 38, 20);
		contentPane.add(tipoSangueBox);
		
		sexoBox = new JComboBox();
		sexoBox.setModel(new DefaultComboBoxModel(new String[] {"F", "M"}));
		sexoBox.setBounds(445, 79, 38, 20);
		contentPane.add(sexoBox);
		
		rhBox = new JComboBox();
		rhBox.setModel(new DefaultComboBoxModel(new String[] {"+", "-"}));
		rhBox.setBounds(445, 110, 38, 20);
		contentPane.add(rhBox);
		
		
		
	}
	
	
	/**
	 * @author Guilherme
	 */
	public void criarLabels(){
		
		JLabel lblCadastroDePaciente = new JLabel("Cadastro de paciente");
		lblCadastroDePaciente.setFont(new Font("Georgia", Font.ITALIC, 25));
		lblCadastroDePaciente.setBounds(154, 11, 244, 33);
		contentPane.add(lblCadastroDePaciente);
		
		JLabel label_1 = new JLabel("Nome");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(10, 81, 46, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Sexo");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(408, 81, 46, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("CPF");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(10, 112, 38, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Tipo Sangu\u00EDneo");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(265, 112, 85, 14);
		contentPane.add(label_4);
		
		JLabel lblAlergias = new JLabel("Alergias");
		lblAlergias.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAlergias.setBounds(31, 186, 46, 14);
		contentPane.add(lblAlergias);
		
		JLabel lblAnomalias = new JLabel("Anomalias");
		lblAnomalias.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAnomalias.setBounds(314, 186, 58, 14);
		contentPane.add(lblAnomalias);
		
		JLabel lblDeficincias = new JLabel("Defici\u00EAncias");
		lblDeficincias.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDeficincias.setBounds(15, 268, 62, 14);
		contentPane.add(lblDeficincias);
		
		JLabel lblDoenasCrnicas = new JLabel("Doen\u00E7as Cr\u00F4nicas");
		lblDoenasCrnicas.setVerticalAlignment(SwingConstants.TOP);
		lblDoenasCrnicas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDoenasCrnicas.setBounds(272, 268, 100, 13);
		contentPane.add(lblDoenasCrnicas);
		
		JLabel label_7 = new JLabel("RH");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_7.setBounds(408, 112, 27, 14);
		contentPane.add(label_7);
		
		JLabel lblIdade = new JLabel("Idade");
		lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdade.setBounds(314, 82, 38, 14);
		contentPane.add(lblIdade);
		
	}
	
	
	
	public void criarFields(){
		
		
		nomeField = new JTextField();
		nomeField.setColumns(10);
		nomeField.setBounds(53, 79, 244, 20);
		contentPane.add(nomeField);
		
		cpfField = new JTextField();
		cpfField.setColumns(10);
		cpfField.setBounds(53, 110, 202, 20);
		contentPane.add(cpfField);
		
		idadeField = new JTextField();
		idadeField.setColumns(10);
		idadeField.setBounds(360, 79, 38, 20);
		contentPane.add(idadeField);
		
		
	}
	
	
	public void criarListas(){
		
		EnfermidadeControle pesquisaEnfermidade = new EnfermidadeControle();      // CLASSE PARA METODOS DE RELACAO ENFERMIDADE - DAO
		enfermidades = new ArrayList<Enfermidade>();
		nomesDeficiencia = new ArrayList<String>();
		nomesAlergia = new ArrayList<String>();
		nomesAnomalia = new ArrayList<String>();
		nomesDoencaCronica = new ArrayList<String>();
		
		scrollPaneDeficiencia = new JScrollPane();
		scrollPaneDeficiencia.setBounds(92, 243, 151, 69);
		contentPane.add(scrollPaneDeficiencia);
		
		deficienciaList = new JList();
		enfermidades.addAll(pesquisaEnfermidade.getDeficiencias());  //METODO PARA ADICONAR TODAS AS ENFERMIDADES DO TIPO DEFICIENCIA AO ArrayList enfermidades
		for (Enfermidade enf:enfermidades){
			if (enf.getTipo().equals(TipoEnfermidade.D.getTipoEnfermidade())){
				nomesDeficiencia.add(enf.getNome());
				
			}
			
		}
		String[] stringNomesDeficiencia = new String[nomesDeficiencia.size()];
		stringNomesDeficiencia = (String[]) nomesDeficiencia.toArray(stringNomesDeficiencia);
		scrollPaneDeficiencia.setViewportView(deficienciaList);
		deficienciaList.setVisibleRowCount(3);
		deficienciaList.setModel(new DefaultComboBoxModel(stringNomesDeficiencia));
		
		JScrollBar scrollBar_2 = new JScrollBar();
		scrollPaneDeficiencia.setRowHeaderView(scrollBar_2);
		
		scrollPaneAnomalia = new JScrollPane();
		scrollPaneAnomalia.setBounds(382, 158, 151, 69);
		contentPane.add(scrollPaneAnomalia);
		
		anomaliaList = new JList();
		enfermidades.addAll(pesquisaEnfermidade.getAnomalias());     // METODO PARA ADICONAR TODAS AS ENFERMIDADES DO TIPO ANOMALIA AO ArrayList enfermidades
		for (Enfermidade enf : enfermidades){
			if (enf.getTipo().equals(TipoEnfermidade.A.getTipoEnfermidade())){
				nomesAnomalia.add(enf.getNome());
			}	
		}
		String[] stringNomesAnomalia = new String[nomesAnomalia.size()];
		stringNomesAnomalia = (String[]) nomesAnomalia.toArray(stringNomesAnomalia);
		scrollPaneAnomalia.setViewportView(anomaliaList);
		anomaliaList.setModel(new DefaultComboBoxModel(stringNomesAnomalia));
		
		JScrollBar scrollBar_1 = new JScrollBar();
		scrollPaneAnomalia.setRowHeaderView(scrollBar_1);
		
		scrollPaneDoenca = new JScrollPane();
		scrollPaneDoenca.setBounds(382, 243, 151, 69);
		contentPane.add(scrollPaneDoenca);
		
		doencaList = new JList();
		enfermidades.addAll(pesquisaEnfermidade.getCronicas());     // METODO ADICIONAR TODAS AS ENFERMIDADES DO TIPO DOENCA CRONICA AO ArrayList enfermidades
		for (Enfermidade enf : enfermidades){
			if (enf.getTipo().equals(TipoEnfermidade.C.getTipoEnfermidade())){
				nomesDoencaCronica.add(enf.getNome());
			}
		}
		String[] stringNomesDoencaCronica = new String[nomesDoencaCronica.size()];
		stringNomesDoencaCronica = (String[]) nomesDoencaCronica.toArray(stringNomesDoencaCronica);
		scrollPaneDoenca.setViewportView(doencaList);
		doencaList.setModel(new DefaultComboBoxModel(stringNomesDoencaCronica));
		
		JScrollBar scrollBar_3 = new JScrollBar();
		scrollPaneDoenca.setRowHeaderView(scrollBar_3);
		
		scrollPaneAlergia = new JScrollPane();
		scrollPaneAlergia.setBounds(92, 158, 151, 69);
		contentPane.add(scrollPaneAlergia);
		
		alergiaList = new JList();
		enfermidades.addAll(pesquisaEnfermidade.getAlergias());    //  METODO ADICIONAR TODAS AS ENFERMIDADES DO TIPO ALERGIA AO ArrayList enfermidades
		for (Enfermidade enf : enfermidades){
			if (enf.getTipo().equals(TipoEnfermidade.AL.getTipoEnfermidade())){
				nomesAlergia.add(enf.getNome());
			}
		}
		String[] stringNomesAlergia = new String[nomesAlergia.size()];
		stringNomesAlergia = (String[]) nomesAlergia.toArray(stringNomesAlergia);
		scrollPaneAlergia.setViewportView(alergiaList);
		alergiaList.setModel(new DefaultComboBoxModel(stringNomesAlergia));
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPaneAlergia.setRowHeaderView(scrollBar);
		
		
		textStatus = new JTextPane();
		textStatus.setBounds(0, 384, 543, 20);
		contentPane.add(textStatus);
		
	}
	
	
	public void criarEventos(){
		
		btnCancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame novaTela = new GerenteGUI(usuario);
				novaTela.setVisible(true);
				CadastroPacienteGUI.this.dispose();	
				
			}
			
		});
		
		btnSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PacienteControle controle = new PacienteControle(); 
				
				pessoa = new Pessoa();
				paciente = new Paciente();
				
				List<Object> enfSelecionadas = new ArrayList<Object>(); //Lista de Strings com o nome das enfermidades (nao funciona com <String>?)
				ArrayList<Enfermidade> listaEnfermidadesNova = new ArrayList<Enfermidade>();
				
				enfSelecionadas.addAll(deficienciaList.getSelectedValuesList());
				enfSelecionadas.addAll(anomaliaList.getSelectedValuesList());
				enfSelecionadas.addAll(doencaList.getSelectedValuesList());
				enfSelecionadas.addAll(alergiaList.getSelectedValuesList());
				
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
				
				for (Enfermidade enfermidade : listaEnfermidadesNova) {
					ep.setEnfermidade(enfermidade);
					listaEP.add(ep);
				}
				
				
				pessoa.setNome(nomeField.getText());
				pessoa.setCpf(cpfField.getText());
				pessoa.setIdade(Integer.parseInt(idadeField.getText()));
				pessoa.setSexo((String) sexoBox.getSelectedItem());
				pessoa.setStatusDePessoa(StatusDePessoa.V.getStatus());
				adicionarTipoSanguineo();
				paciente.setPessoa(pessoa);
				paciente.setDoenca(listaEP);
				
				
				controle.cadastrar(paciente);
				mensagemStatus(true);

		
			}
		});
		
		
		
		
	}
	
	
	
	public void adicionarTipoSanguineo(){
		
		if (tipoSangueBox.getSelectedItem().equals("A")){
			if (rhBox.getSelectedItem().equals("+")){
				pessoa.setTipoSanguineo(TipoSanguineo.AP.getTipoSanguineo());;
			}
			else if (rhBox.getSelectedItem().equals("-")){
				pessoa.setTipoSanguineo(TipoSanguineo.AN.getTipoSanguineo());
			}
		}
					
		
		else if(tipoSangueBox.getSelectedItem().equals("B")){
			if (rhBox.getSelectedItem().equals("+")){
				pessoa.setTipoSanguineo(TipoSanguineo.BP.getTipoSanguineo());
			}
			
			else if (rhBox.getSelectedItem().equals("-")){
				pessoa.setTipoSanguineo(TipoSanguineo.BN.getTipoSanguineo());
			}
			
		}
		
		
		else if(tipoSangueBox.getSelectedItem().equals("O")){
			if (rhBox.getSelectedItem().equals("+")){
				pessoa.setTipoSanguineo(TipoSanguineo.OP.getTipoSanguineo());
			}
			
			else if (rhBox.getSelectedItem().equals("-")){							
				pessoa.setTipoSanguineo(TipoSanguineo.ON.getTipoSanguineo());
			}
			
			
		}
		
		else if(tipoSangueBox.getSelectedItem().equals("AB")){
			if (rhBox.getSelectedItem().equals("+")){
				pessoa.setTipoSanguineo(TipoSanguineo.ABP.getTipoSanguineo());
			}
			
			else if (rhBox.getSelectedItem().equals("-")){
				pessoa.setTipoSanguineo(TipoSanguineo.ABN.getTipoSanguineo());
			}			
			
		}
		
	}
	
	public void mensagemStatus(boolean novo){
		if (novo){
			textStatus.setForeground(Color.BLUE);
			textStatus.setText("Processo realizado com sucesso!");
			
			JFrame novaTela = new GerenteGUI(usuario);
			novaTela.setVisible(true);
			CadastroPacienteGUI.this.dispose();
		}
		else{
			textStatus.setForeground(Color.RED);
			textStatus.setText("Não foi possível concluir a operacao!");
		}
	}
}
