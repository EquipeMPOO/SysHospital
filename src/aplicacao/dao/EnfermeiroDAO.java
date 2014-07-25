package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dao.util.ComandoSQL;
import aplicacao.dao.util.Comparacao;
import aplicacao.dominio.Atendente;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Funcionario;
import aplicacao.enums.StatusDePessoa;
import aplicacao.enums.StatusDeUsuario;

public class EnfermeiroDAO implements IFuncionarioDAO {
	
	private static final String SQL_PESQUISA = "SELECT * FROM enfermeiro";
	
	public List<Funcionario> pesquisarTodos(Boolean pesquisarPessoa){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		Enfermeiro enfermeiro = new Enfermeiro();
        		
        		enfermeiro.setIdFuncionario(rs.getInt("idfuncionario"));
        		enfermeiro.setLogin(rs.getString("login"));
        		enfermeiro.setSenha(rs.getString("senha"));
        		enfermeiro.setIdentificadorInterno(rs.getString("identificadorinterno"));
        		enfermeiro.setStatusDeUsuario(rs.getString("statusdeusuario"));
        		enfermeiro.setNumeroDeRegistro(rs.getInt("numeroderegistro"));

    			if (pesquisarPessoa){
    				PessoaDAO a = new PessoaDAO();
    				enfermeiro.setPessoa(a.pesquisarporID(rs.getInt("pessoa")));
    			}
    			        		
        		funcionarios.add(enfermeiro);
        		
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
		
		return funcionarios;
	}
	
	
	public Funcionario pesquisarLogin(Funcionario parametro){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Enfermeiro enfermeiro = new Enfermeiro();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		enfermeiro.setLogin(rs.getString("login"));
        		enfermeiro.setSenha(rs.getString("senha"));
        		
        		if (enfermeiro.getSenha().equals(parametro.getSenha()) & enfermeiro.getLogin().equals(parametro.getLogin())){
	        		enfermeiro.setIdFuncionario(rs.getInt("idfuncionario"));
	        		enfermeiro.setStatusDeUsuario(rs.getString("statusdeusuario"));
	        		enfermeiro.setIdentificadorInterno(rs.getString("identificadorinterno"));
	        		enfermeiro.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
	        		System.out.println("aqui");
	        		return enfermeiro;
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
		
		return null;
	}
	
	public Funcionario pesquisarCpf(Funcionario parametro){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Enfermeiro enfermeiro = new Enfermeiro();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		PessoaDAO a = new PessoaDAO();
        		enfermeiro.setPessoa(a.pesquisarporID(rs.getInt("pessoa")));
        		
        		if (enfermeiro.getPessoa().getCpf().equals(parametro.getPessoa().getCpf())){
        			
        			enfermeiro.setLogin(rs.getString("login"));
        			enfermeiro.setSenha(rs.getString("senha"));
	        		enfermeiro.setIdFuncionario(rs.getInt("idfuncionario"));
	        		enfermeiro.setIdentificadorInterno(rs.getString("identificadorinterno"));
	        		enfermeiro.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
	        		return enfermeiro;
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
		
		return null;
	}
	
	public List<Funcionario> pesquisarFiltrando(Funcionario f, Boolean pesquisarPessoa){
		return null;
	}
	
	public List<Funcionario> pesquisarAlgum(Funcionario f, Boolean pesquisarPessoa){
		
		return null;
	}
	
	public List<Funcionario> pesquisarInativos(){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		Enfermeiro enfermeiro = new Enfermeiro();
        		enfermeiro.setStatusDeUsuario(rs.getString("statusdeusuario"));
        		
        		if (enfermeiro.getStatusDeUsuario().equals("Inativo")){
	        		enfermeiro.setIdFuncionario(rs.getInt("idfuncionario"));
	        		enfermeiro.setLogin(rs.getString("login"));
	        		enfermeiro.setSenha(rs.getString("senha"));
	        		enfermeiro.setIdentificadorInterno(rs.getString("identificadorinterno"));
	        		enfermeiro.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
	        		
	    			PessoaDAO a = new PessoaDAO();
	    			enfermeiro.setPessoa(a.pesquisarporID(rs.getInt("pessoa")));	    				        		
	        		funcionarios.add(enfermeiro);
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
		
		return funcionarios;
		
	}
	
	
	public void cadastrar(Funcionario enfermeiro) {
		
		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		
		PessoaDAO db = new PessoaDAO();
		db.cadastrar(enfermeiro.getPessoa());
		
		String comando = "INSERT INTO enfermeiro(login, senha, identificadorinterno, statusdeusuario, pessoa, numeroderegistro) VALUES ("+
						     "'"+enfermeiro.getLogin()+"'"+ ","+ 
							"'"+enfermeiro.getSenha()+"'"+","+ 
							"'"+enfermeiro.getIdentificadorInterno()+"'"+","+ 
							"'"+ "Inativo"+"'"+","+ 
							"'"+db.procurarId(enfermeiro.getPessoa())+"'"+ ","+
							"'"+((Enfermeiro) enfermeiro).getNumeroDeRegistro() +"'"+ ")" ;
        
        try {
			ps = conexao.prepareStatement(comando);
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConexaoDAO.close(conexao, ps, null);
		}


	}
	
	public void alterar(Funcionario atendente){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        PessoaDAO db = new PessoaDAO();
		db.alterar(atendente.getPessoa());
		
        String comando = "UPDATE enfermeiro SET login = " +"'"+atendente.getLogin()+"'"+ 
		        		", senha = " +"'"+atendente.getSenha()+"'"+
		        		", identificadorinterno = " +"'"+atendente.getIdentificadorInterno()+"'"+ 
		        		", statusdeusuario = " +"'"+atendente.getStatusDeUsuario()+"'"+
		        		", pessoa = " +"'"+db.procurarId(atendente.getPessoa())+"'"+ 
		        		"WHERE idfuncionario = " + "'" + atendente.getIdFuncionario() + "'";
		                
        try {
			ps = conecxao.prepareStatement(comando);
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConexaoDAO.close(conecxao, ps, rs);
		}
	}
	
	public void inativar(Funcionario atendente){		
	
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String comando = "UPDATE enfermeiro SET statusdeusuario = " + "'"+ "IP" + "'"+ 
        				 "WHERE idfuncionario = " + "'" + atendente.getIdFuncionario() + "'";
      
        try {
 			ps = conecxao.prepareStatement(comando);
 			ps.executeUpdate();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}finally{
 			ConexaoDAO.close(conecxao, ps, rs);
 		}
		
	}


}
