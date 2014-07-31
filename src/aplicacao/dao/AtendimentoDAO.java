package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dominio.Atendimento;
import aplicacao.dominio.Enfermeiro;
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
	
	public ArrayList<Atendimento> pesquisarPorId(int ID){		
	
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Atendimento> atendimentos = new ArrayList<Atendimento>();
        Atendimento at = new Atendimento();
        
        try{
        	ps = conecxao.prepareStatement("SELECT * FROM atendimento WHERE entrada =" + ID);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		
        		at.setIdAtentimento(rs.getInt("idatendimento"));
        		        		
            	at.setComentarioEnfermeiro(rs.getString("comentarioenfermeiro"));
            	at.setComentarioMedico(rs.getString("comentariomedico"));
            	at.setData(rs.getString("data"));      
            		
            	//espaco para setar doencas
            		
            	//Adicionar Medico
            	MedicoDAO dbMedico = new MedicoDAO();            		
            	Medico medico = dbMedico.pesquisarPorID(rs.getInt("medico"));
            	at.setMedico(medico);
            		
            	//Adicionar Enfermeiro
            	EnfermeiroDAO dbEnfermeiro = new EnfermeiroDAO();            		
            	Enfermeiro enf = dbEnfermeiro.pesquisarPorID(rs.getInt("enfermeiro"));
            	at.setEnfermeiro(enf);          		
            		
            		
            	at.setPeso(rs.getFloat("peso"));
        		at.setAltura(rs.getFloat("altura"));
        		
        		atendimentos.add(at);
        		
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
        
        return atendimentos;
	}
	
	/**
	 * Metodo que captura dados de um objeto parametrizado e os insere numa nova linha do banco de dados
	 * @param at - Instancia da classe atendimento que contem os dados que serão persistidos no banco de dados
	 * @param idEntrada 
	 */
	
	public Atendimento inserir(Atendimento at, int idEntrada){		
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;         
        
        try{
        	
        	String comando = "INSERT INTO atendimento (comentarioenfermeiro, peso, altura, data,  enfermeiro, entrada) VALUES ("+
        			          "'"+ at.getComentarioEnfermeiro() +"'"+ ","+
        			          "'"+ at.getPeso() +"'"+ ","+ 
        			          "'"+ at.getAltura() +"'"+ ","+ 
        			          "'"+ at.getData() +"'"+ ","+
        			          "'"+ at.getEnfermeiro().getIdFuncionario() +"'"+ ","+ 
        			          "'" + idEntrada + "'" +
        			          ")";
        	
        	ps = conecxao.prepareStatement(comando, PreparedStatement.RETURN_GENERATED_KEYS);
        	ps.executeUpdate();
        	
        	rs = ps.getGeneratedKeys();
        	while(rs.next()){
				at.setIdAtentimento(rs.getInt(1));
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
	
	public Atendimento inserirDadosMedico(Atendimento at){		
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;         
        
        try{        	
        	String comando = "UPDATE atendimento SET comentariomedico = "+  "'" + at.getComentarioMedico() +"'"+
        			          ", medico ='"+ at.getMedico().getIdFuncionario() +"'"+
        			          "WHERE idatendimento = " + "'" + at.getIdAtentimento() + "'";
        	
        	System.out.println(comando);
        	
        	ps = conecxao.prepareStatement(comando, PreparedStatement.RETURN_GENERATED_KEYS);
        	ps.executeUpdate();
        	
        	//TODO criar algoritmo pra inserir uma enfermidade pessoal
        	//EnfermidadePessoalDAO databaseEP = new EnfermidadePessoalDAO()
        	//databaseEP.inserir(at);
        	
        	rs = ps.getGeneratedKeys();
        	while(rs.next()){
				at.setIdAtentimento(rs.getInt(1));
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
	 
	
	
}
