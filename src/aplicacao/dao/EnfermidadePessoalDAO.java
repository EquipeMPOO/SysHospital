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

public class EnfermidadePessoalDAO {
	
	public ArrayList<EnfermidadePessoal> pesquisarId(EnfermidadePessoal enfPessoal){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<EnfermidadePessoal> enfermidadesPesquisadas = new ArrayList<EnfermidadePessoal>();
        
        try{
        	ps = conecxao.prepareStatement("SELECT * FROM enfermidadepessoal WHERE entrada =" + enfPessoal.getIdEnfermidadePessoal());
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
}
