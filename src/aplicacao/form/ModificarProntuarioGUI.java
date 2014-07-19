package aplicacao.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ModificarProntuarioGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome, textFieldFerimentos, textFieldAltura;
	private JButton btnSalvar, btnCancelar;
	private JTextArea textArea;
	private JLabel lblModificarProntuario,lblPeso, lblAltura, lblDoencas,lblFerimentos,lblDescricaoAtendimento;
	

	/**
	 * Create the frame.
	 */
	public ModificarProntuarioGUI() {
		
		// - Confirando o frame e o contentPane
		
		setTitle("SysHospital");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 481, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(SystemColor.inactiveCaption);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
				
		//-- Mehtodo para criar Labels (textos estáticos)
		this.criarLabel();
		
		// -- Mehtodo para criar TextFields
		this.criarTextField();
		
		// -- Mehtodo para criar Buttons
		this.criarButton();
		
		// -- Mehtodo para criar TextAreas
		this.criarTextArea();
		
	
		
	}
	
	
	
	
	
	
	// - Cria textos extahticos
	public void criarLabel(){ 
		
		lblModificarProntuario = new JLabel("Modificar prontuario");
		lblModificarProntuario.setFont(new Font("Georgia", Font.ITALIC, 25));
		lblModificarProntuario.setBounds(123, 11, 239, 33);
		contentPane.add(lblModificarProntuario);
		
		lblPeso = new JLabel("Peso");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPeso.setBounds(28, 77, 36, 14);
		contentPane.add(lblPeso);
		
		lblAltura = new JLabel("Altura");
		lblAltura.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAltura.setBounds(28, 108, 36, 14);
		contentPane.add(lblAltura);
		
		lblDoencas = new JLabel("Doenças");
		lblDoencas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDoencas.setBounds(170, 109, 66, 14);
		contentPane.add(lblDoencas);		
		
		lblFerimentos = new JLabel("Ferimentos");
		lblFerimentos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFerimentos.setBounds(170, 77, 66, 14);
		contentPane.add(lblFerimentos);
		
		lblDescricaoAtendimento = new JLabel("Descricao do atendimento");
		lblDescricaoAtendimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescricaoAtendimento.setBounds(28, 137, 143, 14);
		contentPane.add(lblDescricaoAtendimento);
		
	}
	
	
	
	// - Cria TextFields
	public void criarTextField(){
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(74, 75, 86, 20);
		contentPane.add(textFieldNome);
			
		textFieldFerimentos = new JTextField();
		textFieldFerimentos.setColumns(10);
		textFieldFerimentos.setBounds(246, 75, 198, 20);
		contentPane.add(textFieldFerimentos);
		
		textFieldAltura = new JTextField();
		textFieldAltura.setColumns(10);
		textFieldAltura.setBounds(74, 106, 86, 20);
		contentPane.add(textFieldAltura);
		
	}
	
	
	
	// - Cria Buttons (botoes)
	public void criarButton(){
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Georgia", Font.ITALIC, 12));
		btnSalvar.setBounds(10, 272, 100, 27);
		contentPane.add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Georgia", Font.ITALIC, 12));
		btnCancelar.setBounds(355, 272, 100, 27);
		contentPane.add(btnCancelar);
		
		
	}
	
	
	
	// - Cria caixas de texto (textArea)
	public void criarTextArea(){
		
		textArea = new JTextArea();
		textArea.setBounds(28, 162, 417, 86);
		contentPane.add(textArea);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Doenca 1", "Doenca 2", "Doenca 3"}));
		comboBox.setBounds(246, 106, 198, 20);
		contentPane.add(comboBox);
	}
}
