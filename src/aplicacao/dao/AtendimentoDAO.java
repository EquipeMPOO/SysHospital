package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dominio.Atendimento;
import aplicacao.dominio.Enfermidade;
import aplicacao.dominio.Medico;

/**
 * @author icaro
 * Classe que trata de conexao do sistema com a tabela de atendimentos do banco de dados
 * É instanciado na classe PacienteControle
 */


public class AtendimentoDAO {
	
	private static final String SQL_PESQUISA =	"SELECT * FROM atendimento";
	
	/**
	 * Metodo que forma um objeto de ID selecionado
	 * @param ID - Inteiro que possui o numero indexado que servirá como parametro de pesquisa
	 * @return Atendimento - Instancia da classe Atendimento que contém os dados capturados pela tabela correspondente no banco
	 */
	
	public Atendimento pesquisarPorId(int ID){		
	
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Atendimento at = new Atendimento();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		
        		at.setIdAtentimento(rs.getInt("idatendimento"));
        		        		
        		if(at.getIdAtentimento() == ID){
            		at.setComentarioEnfermeiro(rs.getString("comentarioenfermeiro"));
            		at.setComentarioMedico(rs.getString("comentariomedico"));
            		at.setData(rs.getString("data"));            		
            		//espaco para setar doencas
            		//at.setEnfermeiro(enfermeiro);
            		//at.setMedico(medico);
            		at.setPeso(rs.getFloat("peso"));
        			at.setAltura(rs.getFloat("altura"));
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
        
        return at;
	}
	
	/**
	 * Metodo que captura dados de um objeto parametrizado e os insere numa nova linha do banco de dados
	 * @param at - Instancia da classe atendimento que contem os dados que serão persistidos no banco de dados
	 */
	
	public void inserir(Atendimento at){		
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;         
        
        try{
        	
        	String comando = "INSERT INTO atendimento (comentarioenfermeiro, comentariomedico, peso, altura, data, enfermeiro, medico) VALUES ("+
        			          "'"+ at.getComentarioEnfermeiro() +"'"+ ","+
        			          "'"+ at.getComentarioMedico() +"'"+ ","+ 
        			          "'"+ at.getPeso() +"'"+ ","+ 
        			          "'"+ at.getAltura() +"'"+ ","+ 
        			          "'"+ at.getData() +"'"+ ","+
        			          "'"+ at.getEnfermeiro().getIdFuncionario() +"'"+ ","+ 
        			          "'"+ at.getMedico().getIdFuncionario() +"'"+ 
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
	
	
	
}
