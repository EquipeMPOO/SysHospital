package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Paciente;

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
        		
        		PessoaDAO a = new PessoaDAO();
    			paciente.setPessoa(a.pesquisarporID(rs.getInt("pessoa")));
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
	
	public void cadastrar(Paciente paciente) {
		
		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		
		PessoaDAO db = new PessoaDAO();
		db.cadastrar(paciente.getPessoa());
		
		String comando = "INSERT INTO paciente(idpaciente, pessoa) VALUES ( '1', " + "'" +  db.procurarId(paciente.getPessoa())+"'"+ ")" ;
        
        try {
			ps = conexao.prepareStatement(comando);
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConexaoDAO.close(conexao, ps, null);
		}


	}
	
}
