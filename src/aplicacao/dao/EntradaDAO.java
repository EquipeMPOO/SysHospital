package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import aplicacao.dominio.Atendimento;
import aplicacao.dominio.Entrada;
import aplicacao.enums.StatusDePessoa;

/**
 * 
 * @author icaro
 * Classe que cria a conexão com a tabela entrada do banco de dados
 */

public class EntradaDAO {	
	
private static final String SQL_PESQUISA =	"SELECT * FROM entrada";
	
	/**
	 * Metodo que realiza a pesquisa dos dados de uma linha da tabela de Entradas de ID correspondente ao parametrizado
	 * @param ID - Parametro que servirá para comparação com os dados coletados do banco de dados
	 * @return Entrada - Instancia da classe entrada que possui os dados selecionados do banco
	 */

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
	
	
	public ArrayList<Entrada> pesquisarTodas(){		
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Entrada> entradas = new ArrayList<Entrada>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		
        		Entrada en = new Entrada();
        		en.setIdEntrada(rs.getInt("identrada"));
            	en.setDataEntrada(rs.getString("dataentrada"));
            	en.setDataSaida(rs.getString("datasaida"));
            	//en.setAtendimentos();
            	en.setStatusdeentrada(rs.getString("statusdeentrada"));            		
        		
            	entradas.add(en);
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
        
        return entradas;
	}
	
	/**
	 * Metodo que trata da inserção dos dados de um objeto da classe Entrada numa nova linha da Tabela Entrada
	 * @param en - Instancia da classe Entrada que conterá os dados que serão persistidos numa nova linha da tabela Entrada
	 */
	
	public int inserir(Entrada en){		
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null; 
        int idGerado = 0;
        
        try{
        	
        	String comando = "INSERT INTO entrada (dataentrada, statusdeentrada) VALUES ("+
        			          "'"+ en.getDataEntrada() +"'"+ ","+
        			          "'Atendendo'"+
        			          ")";
        	
        	ps = conecxao.prepareStatement(comando, PreparedStatement.RETURN_GENERATED_KEYS);
        	ps.executeUpdate();
        	rs = ps.getGeneratedKeys();        	
        	while(rs.next()){
				idGerado = rs.getInt(1);
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
        return idGerado;
        
	}
	
	/**
	 * Metodo que alterará o status de uma linha da tabela de Entrada para Finalizado
	 * @param en - Instancia da classe Entrada que terá o ID da linha que terá o status atualizado
	 */
	
	public void remover(Entrada en){		
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;         
                
        try{
        	
        	String comando = "UPDATE entrada SET statusdeentrada = " + "' Finalizado '"+
        					", datasaida = " + "'" + en.getDataSaida() + "'"+ 	
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
