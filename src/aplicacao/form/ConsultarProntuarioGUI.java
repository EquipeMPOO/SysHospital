package aplicacao.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class ConsultarProntuarioGUI extends JFrame {

	private JPanel contentPane;
	private JComboBox pesquisarPacienteBox;
	private JButton btnSalvar, btnCancelar;
	private JLabel lblConsultarPaciente, lblPesquisarPorPaciente;

	
	/**
	 * Create the frame.
	 */
	public ConsultarProntuarioGUI() {
		
		// - Configurando frame e contentPane
		
		setTitle("SysHospital");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 278);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// Mehtodo para criar textos estáticos
		this.criarLabel();
		
		// Mehtodo para criar botoes
		this.criarButton();
		
		// Mehtodo para criar comboBox
		this.criarComboBox();
		
	}
	
	

	
	
	// -- Cria ComboBox
	
	public void criarComboBox(){
		
		pesquisarPacienteBox = new JComboBox();
		pesquisarPacienteBox.setModel(new DefaultComboBoxModel(new String[] {"Selecionar paciente...", "Paciente 1", "Paciente 2", "Paciente 3", "Paciente 4"}));
		pesquisarPacienteBox.setMaximumRowCount(4);
		pesquisarPacienteBox.setBounds(140, 74, 315, 20);
		contentPane.add(pesquisarPacienteBox);
		
	}
	
	
	// -- Cria os texto estahticos
	
	public void criarLabel(){ 
		
		lblConsultarPaciente = new JLabel("Consultar prontu\u00E1rio");
		lblConsultarPaciente.setFont(new Font("Georgia", Font.ITALIC, 25));
		lblConsultarPaciente.setBounds(131, 11, 244, 33);
		contentPane.add(lblConsultarPaciente);
		
		lblPesquisarPorPaciente = new JLabel("Pesquisa de pacientes");
		lblPesquisarPorPaciente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPesquisarPorPaciente.setBounds(10, 76, 120, 14);
		contentPane.add(lblPesquisarPorPaciente);
				
	}
	
	
	// - Cria os botoes
	
	public void criarButton(){
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Georgia", Font.ITALIC, 12));
		btnSalvar.setBounds(10, 201, 100, 27);
		contentPane.add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Georgia", Font.ITALIC, 12));
		btnCancelar.setBounds(391, 201, 100, 27);
		contentPane.add(btnCancelar);
		
	}
}
