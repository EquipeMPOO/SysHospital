package aplicacao.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;

import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JCheckBox;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class CadastroPacienteGUI extends JFrame {

	private JPanel contentPane;
	private JTextField nomeField, cpfField;
	
	private JButton salvarBotao, cancelarBotao;
	private JComboBox tipoSangueBox, sexoBox, rhBox; 
	private JList anomaliaList, doencaList, deficienciaList, alergiaList;
	private JScrollPane scrollPaneDoenca, scrollPaneAnomalia, scrollPaneDeficiencia, scrollPaneAlergia;
	
	/**
	 * Create the frame.
	 */
	public CadastroPacienteGUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 416);
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
		
		this.adicionarEventos();
		
		this.setVisible(true);
		
	}


	public void criarBotoes(){
		
		salvarBotao = new JButton("Salvar");
		salvarBotao.setFont(new Font("Georgia", Font.ITALIC, 12));
		salvarBotao.setBounds(10, 340, 100, 27);
		contentPane.add(salvarBotao);
		
		cancelarBotao = new JButton("Cancelar");
		cancelarBotao.setFont(new Font("Georgia", Font.ITALIC, 12));
		cancelarBotao.setBounds(433, 340, 100, 27);
		contentPane.add(cancelarBotao);

	}
	
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
		
	}
	
	
	
	public void criarFields(){
		
		
		nomeField = new JTextField();
		nomeField.setColumns(10);
		nomeField.setBounds(53, 79, 345, 20);
		contentPane.add(nomeField);
		
		cpfField = new JTextField();
		cpfField.setColumns(10);
		cpfField.setBounds(53, 110, 202, 20);
		contentPane.add(cpfField);
		
		
	}
	
	
	public void criarListas(){
		
		scrollPaneDeficiencia = new JScrollPane();
		scrollPaneDeficiencia.setBounds(92, 243, 151, 69);
		contentPane.add(scrollPaneDeficiencia);
		
		deficienciaList = new JList();
		scrollPaneDeficiencia.setViewportView(deficienciaList);
		deficienciaList.setVisibleRowCount(3);
		deficienciaList.setModel(new DefaultComboBoxModel(new String[] {"Deficiencia 1","Deficiencia 2","Deficiencia 3","Deficiencia 4","Deficiencia 5","Deficiencia 6","Deficiencia 7"}));
		
		scrollPaneAnomalia = new JScrollPane();
		scrollPaneAnomalia.setBounds(382, 158, 151, 69);
		contentPane.add(scrollPaneAnomalia);
		
		anomaliaList = new JList();
		scrollPaneAnomalia.setViewportView(anomaliaList);
		anomaliaList.setModel(new DefaultComboBoxModel(new String[] {"Anomalia 1","Anomalia 2","Anomalia 3","Anomalia 4","Anomalia 5","Anomalia","Anomalia 7"}));
		
		scrollPaneDoenca = new JScrollPane();
		scrollPaneDoenca.setBounds(382, 243, 151, 69);
		contentPane.add(scrollPaneDoenca);
		
		doencaList = new JList();
		scrollPaneDoenca.setViewportView(doencaList);
		doencaList.setModel(new DefaultComboBoxModel(new String[] {"Doenca 1", "Doenca 2", "Doenca 3","Doenca 4", "Doenca 5", "Doenca 6", "Doenca 7"}));
		
		scrollPaneAlergia = new JScrollPane();
		scrollPaneAlergia.setBounds(92, 158, 151, 69);
		contentPane.add(scrollPaneAlergia);
		
		alergiaList = new JList();
		scrollPaneAlergia.setViewportView(alergiaList);
		alergiaList.setModel(new DefaultComboBoxModel(new String[] {"Alergia 1", "Alergia 2", "Alergia 3", "Alergia 4", "Alergia 5", "Alergia 6", "Alergia 7"}));
		
	}
	
	
	public void criarEventos(){
		
		
		
		
	}
	
	
	private void adicionarEventos() {
		
	}
}
