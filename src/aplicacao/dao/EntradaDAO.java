package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aplicacao.dominio.Atendimento;
import aplicacao.dominio.Entrada;
import aplicacao.enums.StatusDePessoa;

public class EntradaDAO {	
	
private static final String SQL_PESQUISA =	"SELECT * FROM entrada";
	
	public Entrada pesquisarPorId(int ID){		
	
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Entrada en = new Entrada();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		
        		en.setIdEntrada(rs.getInt("identrada"));
        		        		
        		if(en.getIdEntrada() == ID){
            		en.setDataEntrada(rs.getString("dataentrada"));
            		en.setDataSaida(rs.getString("datasaida"));
            		//en.setAtendimentos();
            		en.setStatusdeentrada(rs.getString("statusdeentrada"));            		
        		}
        		
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
        
        return en;
	}
	
	public void inserir(Entrada en){		
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;         
        
        try{
        	
        	String comando = "INSERT INTO entrada (dataentrada, datasaida, statusdeentrada) VALUES ("+
        			          "'"+ en.getDataEntrada() +"'"+ ","+
        			          "'"+ en.getDataSaida() +"'"+ ","+ 
        			          "'Funcionando'"+
        			          ")";
        	
        	ps = conecxao.prepareStatement(comando);
        	ps.executeUpdate();
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
        
	}
	
	public void remover(Entrada en){		
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;         
        
        try{
        	
        	String comando = "UPDATE entrada SET statusdeentrada = " + "' Finalizado '"+ 
		          "WHERE identrada = " + "'" + en.getIdEntrada() + "'" ;
        	
        	ps = conecxao.prepareStatement(comando);
        	ps.executeUpdate();
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
        
	}

}
