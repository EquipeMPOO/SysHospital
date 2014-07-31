package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import aplicacao.dominio.Atendimento;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Enfermidade;
import aplicacao.dominio.EnfermidadePessoal;
import aplicacao.dominio.Medico;
import aplicacao.dominio.Paciente;

public class EnfermidadePessoalDAO {
	
	public ArrayList<EnfermidadePessoal> pesquisarGeraisId(Atendimento atendimento) {
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<EnfermidadePessoal> enfermidadesPesquisadas = new ArrayList<EnfermidadePessoal>();
        EnfermidadePessoal enfPessoal = new EnfermidadePessoal();
        
        try{
        	ps = conecxao.prepareStatement("SELECT * FROM enfermidadepessoal WHERE atendimento =" + atendimento.getIdAtentimento());
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		
        		enfPessoal.setComentario(rs.getString("comentario"));  
            	enfPessoal.setIdEnfermidadePessoal(rs.getInt("idenfermidadepessoal"));
            	enfPessoal.setStatusDeDoenca(rs.getString("statusdedoenca"));
            	
            	EnfermidadeDAO databaseEnfermidades = new EnfermidadeDAO();
            	Enfermidade dadosEnfermidade = databaseEnfermidades.pesquisarPorID(rs.getInt("enfermidade"));
            	
            	enfPessoal.setEnfermidade(dadosEnfermidade);
        		
            	enfermidadesPesquisadas.add(enfPessoal);
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
        
        return enfermidadesPesquisadas;
	}
	
	public ArrayList<EnfermidadePessoal> pesquisarCronicasId(Paciente paciente){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<EnfermidadePessoal> enfermidadesPesquisadas = new ArrayList<EnfermidadePessoal>();
        EnfermidadePessoal enfPessoal = new EnfermidadePessoal();
        
        try{
        	ps = conecxao.prepareStatement("SELECT * FROM enfermidadepessoal WHERE paciente =" + paciente.getIdPaciente());
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		
        		enfPessoal.setComentario(rs.getString("comentario"));  
            	enfPessoal.setIdEnfermidadePessoal(rs.getInt("idenfermidadepessoal"));
            	enfPessoal.setStatusDeDoenca(rs.getString("statusdedoenca"));
            	
            	EnfermidadeDAO databaseEnfermidades = new EnfermidadeDAO();
            	Enfermidade dadosEnfermidade = databaseEnfermidades.pesquisarPorID(rs.getInt("enfermidade"));
            	
            	enfPessoal.setEnfermidade(dadosEnfermidade);
        		
            	enfermidadesPesquisadas.add(enfPessoal);
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
        
        return enfermidadesPesquisadas;
	}
	
	public EnfermidadePessoal inserirEnfermidadeCronica(EnfermidadePessoal enfPessoal, int idPaciente){
		
		Connection conexao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        	
        	System.out.println(enfPessoal.getComentario());
        	System.out.println(enfPessoal.getStatusDeDoenca());
        	System.out.println(enfPessoal.getEnfermidade().getIdEnfermidade());
        			
        	String comando = "INSERT INTO enfermidadepessoal(comentario, statusdeenfermidade , enfermidade, paciente) VALUES ("+
					        "'"+enfPessoal.getComentario() + "'"+ ","+ 
						    "'"+enfPessoal.getStatusDeDoenca()+"'"+","+ 
						    "'"+enfPessoal.getEnfermidade().getIdEnfermidade() +"'"+ ","+
						    "'" + idPaciente + "'" + ")" ;
        	
        	ps = conexao.prepareStatement(comando, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			while(rs.next()){
				enfPessoal.setIdEnfermidadePessoal(rs.getInt(1));
			}
			
						        	
        }
        catch(SQLException e){
        	try{
        		if(conexao != null){
        			conexao.rollback();
        		}
        	}
        	catch(SQLException e1){
        		e1.printStackTrace();
        	}
        	finally{
        		ConexaoDAO.close(conexao, ps, rs);
        	}
        	e.printStackTrace();
        }
		
		return enfPessoal;
		
	}
	
	public EnfermidadePessoal inserirEnfermidadeGeral(EnfermidadePessoal enfPessoal, int idAtendimento){
		
		Connection conexao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        	
        	String comando = "INSERT INTO enfermidadepessoal(comentario, statusdeenfermidade , enfermidade, atendimento) VALUES ("+
					        "'"+enfPessoal.getComentario() + "'"+ ","+ 
						    "'"+enfPessoal.getStatusDeDoenca()+"'"+","+ 
						    "'"+enfPessoal.getEnfermidade().getIdEnfermidade() +"'"+ ","+
						    "'" + idAtendimento + "'" + ")" ;
        	
        	ps = conexao.prepareStatement(comando, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			while(rs.next()){
				enfPessoal.setIdEnfermidadePessoal(rs.getInt(1));
			}
			
						        	
        }
        catch(SQLException e){
        	try{
        		if(conexao != null){
        			conexao.rollback();
        		}
        	}
        	catch(SQLException e1){
        		e1.printStackTrace();
        	}
        	finally{
        		ConexaoDAO.close(conexao, ps, rs);
        	}
        	e.printStackTrace();
        }
		
		return enfPessoal;
		
	}
	
}
