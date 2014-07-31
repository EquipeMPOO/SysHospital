package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.EnfermidadePessoal;
import aplicacao.dominio.Entrada;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Paciente;
import aplicacao.dominio.Pessoa;

public class PacienteDAO {

	private static final String SQL_PESQUISA = "SELECT * FROM paciente";
	
	public List<Paciente> pesquisarTodos(){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Paciente> pacientes = new ArrayList<Paciente>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		Paciente paciente = new Paciente();
        		
        		paciente.setIdPaciente(rs.getInt("idpaciente"));
        		paciente.setHistorico(this.procurarEntradas(paciente));        		
        		PessoaDAO db = new PessoaDAO();
    			paciente.setPessoa(db.pesquisarporID(rs.getInt("pessoa")));   
    			
    			paciente.setDoenca(this.procurarEnfermidadesCronicas(paciente));
    			
    			pacientes.add(paciente);
    			
    			
    		}   
        }
        catch(SQLException e){
        	try{
        		if(conecxao != null){
        			conecxao.rollback();
        		}
        	}
        	catch(SQLException e1){
        		e1.printStackTrace();
        	}
        	finally{
        		ConexaoDAO.close(conecxao, ps, rs);
        	}
        	e.printStackTrace();
        }
		
		return pacientes;
	}
	
	public Paciente cadastrar(Paciente paciente) {
		
		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		PessoaDAO db = new PessoaDAO();
		Pessoa pessoaPaciente = db.cadastrar(paciente.getPessoa());
				
		String comando = "INSERT INTO paciente(pessoa) VALUES ('" +  pessoaPaciente.getIdPessoa() +"'"+ ")" ;
        
        try {
			ps = conexao.prepareStatement(comando, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
        	while(rs.next()){
				paciente.setIdPaciente(rs.getInt(1));
				
			}
        	this.adicionarEnfermidadesCronicas(paciente);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConexaoDAO.close(conexao, ps, null);
		}
        return paciente;


	}
	
	public Paciente alterar(Paciente paciente){
		
		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		PessoaDAO databasePessoa = new PessoaDAO();		
		Pessoa pessoaPaciente = paciente.getPessoa();
		
		paciente.setPessoa(databasePessoa.alterar(pessoaPaciente));
        
        return paciente;
		
	}
	
	
	public ArrayList<Entrada> procurarEntradas(Paciente paciente){
		
		ArrayList<Entrada> entradasPesquisadas = new ArrayList<Entrada>();
				
		EntradaDAO databaseEntrada = new EntradaDAO();
							
		entradasPesquisadas= databaseEntrada.pesquisarPorId(paciente.getIdPaciente());							
        return entradasPesquisadas;
		
	}
	
	public void internar(Paciente paciente){
		
		EntradaDAO databaseEntrada = new EntradaDAO();
		int ultimoindex = paciente.getHistorico().size()-1; //recupera a quantidade de elementos da Array
		Entrada entrada = paciente.getHistorico().get(ultimoindex); //recupera o ultimo objeto entrada da lista
				
		databaseEntrada.inserir(entrada, paciente.getIdPaciente());
	}
	
	public void liberar(Paciente paciente){
		
		EntradaDAO databaseEntrada = new EntradaDAO();
		int ultimoindex = paciente.getHistorico().size() -1; //recupera a quantidade de elementos da Array
		Entrada entrada = paciente.getHistorico().get(ultimoindex); //recupera o ultimo objeto entrada da lista
		
		databaseEntrada.remover(entrada);
		
	}
	
	public void adicionarEnfermidadesCronicas(Paciente paciente){
		
		EnfermidadePessoalDAO databaseEnfPessoal = new EnfermidadePessoalDAO();			
		for (EnfermidadePessoal enfPessoal : paciente.getDoenca()) {			
			databaseEnfPessoal.inserirEnfermidadeCronica(enfPessoal, paciente.getIdPaciente());
			
		}
		
	}
	
	public ArrayList<EnfermidadePessoal> procurarEnfermidadesCronicas(Paciente paciente){
		
		ArrayList<EnfermidadePessoal> enfermidadesPesquisadas = new ArrayList<EnfermidadePessoal>();
			
		EnfermidadePessoalDAO databaseEnfermidade = new EnfermidadePessoalDAO();
							
		enfermidadesPesquisadas= databaseEnfermidade.pesquisarCronicasId(paciente);							
        return enfermidadesPesquisadas;
		
	}
	
	
}
