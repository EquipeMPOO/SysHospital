package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import aplicacao.dominio.Atendimento;
import aplicacao.dominio.Entrada;
import aplicacao.dominio.Paciente;
import aplicacao.enums.StatusDePessoa;

/**
 * 
 * @author icaro
 * Classe que cria a conex�o com a tabela entrada do banco de dados
 */

public class EntradaDAO {	
	
private static final String SQL_PESQUISA =	"SELECT * FROM entrada";
	
	/**
	 * Metodo que realiza a pesquisa dos dados de uma linha da tabela de Entradas de ID correspondente ao parametrizado
	 * @param ID - Parametro que servir� para compara��o com os dados coletados do banco de dados
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
            		
            		ArrayList<Atendimento> atendimentos = this.procurarAtendimentos(en);            		
            		en.setAtendimentos(atendimentos);
            		
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
            	
        		ArrayList<Atendimento> atendimentos = this.procurarAtendimentos(en);            		
        		en.setAtendimentos(atendimentos);
        		
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
	 * Metodo que trata da inser��o dos dados de um objeto da classe Entrada numa nova linha da Tabela Entrada
	 * @param en - Instancia da classe Entrada que conter� os dados que ser�o persistidos numa nova linha da tabela Entrada
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
	 * Metodo que alterar� o status de uma linha da tabela de Entrada para Finalizado
	 * @param en - Instancia da classe Entrada que ter� o ID da linha que ter� o status atualizado
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
	
	public ArrayList<Atendimento> procurarAtendimentos(Entrada entrada){
		
		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		
		ArrayList<Atendimento> atendimentosPesquisados = new ArrayList<Atendimento>();
		
		//antes de mais nada ele ir� criar o comando para pegar dentro da tabela situacaodepaciente, todas as linhas onde a coluna "entrada" for igual ao id informado em entrada.getIdEntrada()
		String comando = "SELECT * FROM situacaodepaciente WHERE entrada = " + entrada.getIdEntrada();	
		
        try {
			ps = conexao.prepareStatement(comando);
			ResultSet rs = ps.executeQuery();
			
			AtendimentoDAO databaseAtendimento = new AtendimentoDAO();
			
			//enquanto houverem querys com a coluna entrada igual ao id do objeto passado ele far� uma busca 
			while(rs.next()){
				
				//em seguida ele ir� consultar a tabela de ATENDIMENTOS e ir� fazer uma pesquisa pelo ID que consta na tabela de situacaodepaciente
				Atendimento atend = databaseAtendimento.pesquisarPorId(rs.getInt("atendimento"));	
				atendimentosPesquisados.add(atend); //Por fim ele ir� adicionar o atendimento selecionado na lista de Atendimentos
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConexaoDAO.close(conexao, ps, null);
		}
        
        return atendimentosPesquisados;
		
	}
	
	public void adicionarAtendimento(Entrada entrada){
		
		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		
		AtendimentoDAO databaseAtendimento = new AtendimentoDAO();
		
		int tamanho = entrada.getAtendimentos().size(); //recupera a quantidade de elementos da Array		
		Atendimento atendimento = entrada.getAtendimentos().get(tamanho-1); //recupera o ultimo objeto atendimento da lista
				
		String comando = "INSERT INTO situacaodepaciente (entrada, atendimento) VALUES ('" + entrada.getIdEntrada() + "'" + "," + "'" + databaseAtendimento.inserir(atendimento) + "')";	
		
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
