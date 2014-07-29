package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dao.util.ComandoSQL;
import aplicacao.dominio.Atendente;
import aplicacao.dominio.Funcionario;
import aplicacao.enums.StatusDePessoa;
import aplicacao.enums.StatusDeUsuario;

public class AtendenteDAO{
	
	private static final String SQL_PESQUISA =	"SELECT * FROM atendente";
	
	public List<Funcionario> pesquisarTodos(Boolean pesquisarPessoa){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		Atendente atendenteBD = new Atendente();
        		
        		atendenteBD.setIdFuncionario(rs.getInt("idfuncionario"));
        		atendenteBD.setLogin(rs.getString("login"));
        		atendenteBD.setSenha(rs.getString("senha"));
        		atendenteBD.setStatusDeUsuario(rs.getString("statusdeusuario"));

    			if (pesquisarPessoa){
    				PessoaDAO a = new PessoaDAO();
    				atendenteBD.setPessoa(a.pesquisarporID(rs.getInt("pessoa")));
    			}
    			
        		atendenteBD.setCargo(rs.getString("cargo"));
        		
        		funcionarios.add(atendenteBD);
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
        Atendente atendente = new Atendente();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		atendente.setLogin(rs.getString("login"));
        		atendente.setSenha(rs.getString("senha"));
        		
        		if (atendente.getSenha().equals(parametro.getSenha()) & atendente.getLogin().equals(parametro.getLogin())){
	        		atendente.setIdFuncionario(rs.getInt("idfuncionario"));
	        		atendente.setStatusDeUsuario(rs.getString("statusdeusuario"));
	        		atendente.setCargo(rs.getString("cargo"));
	        		return atendente;
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
        Atendente atendente = new Atendente();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		PessoaDAO dbPessoa = new PessoaDAO();
        		atendente.setPessoa(dbPessoa.pesquisarporID(rs.getInt("pessoa")));
        		
        		if (atendente.getPessoa().getCpf().equals(parametro.getPessoa().getCpf())){
        			
        			atendente.setLogin(rs.getString("login"));
        			atendente.setSenha(rs.getString("senha"));
	        		atendente.setIdFuncionario(rs.getInt("idfuncionario"));
	        		atendente.setCargo(rs.getString("cargo"));
	        		return atendente;
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
	public List<Funcionario> pesquisarInativos(){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		Atendente atendente = new Atendente();
        		atendente.setStatusDeUsuario(rs.getString("statusdeusuario"));
        		
        		if (atendente.getStatusDeUsuario().equals("Inativo")){
	        		atendente.setIdFuncionario(rs.getInt("idfuncionario"));
	        		atendente.setLogin(rs.getString("login"));
	        		atendente.setSenha(rs.getString("senha"));
	        		atendente.setCargo(rs.getString("cargo"));        	

	        		
	    			PessoaDAO a = new PessoaDAO();
	    			atendente.setPessoa(a.pesquisarporID(rs.getInt("pessoa")));	    				        		
	        		funcionarios.add(atendente);
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
	
	
	public void cadastrar(Funcionario atendente) {
		
		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		
		PessoaDAO db = new PessoaDAO();
		db.cadastrar(atendente.getPessoa());
		
		String comando = "INSERT INTO atendente(login, senha, statusdeusuario, pessoa, cargo) VALUES ("+
						     "'"+atendente.getLogin()+"'"+ ","+ 
							"'"+atendente.getSenha()+"'"+","+ 
							"'"+ "Inativo" +"'"+","+ 
							"'"+db.procurarId(atendente.getPessoa())+"'" +","+ 
							"'"+ "atendente" +"'" + ")" ;
        
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
		
        String comando = "UPDATE atendente SET login = " +"'"+atendente.getLogin()+"'"+ 
		        		", senha = " +"'"+atendente.getSenha()+"'" +
		        		", statusdeusuario = " +"'"+atendente.getStatusDeUsuario()+"'"+
		        		", pessoa = " +"'"+atendente.getPessoa().getIdPessoa()+"'"+ 
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
        
        String comando = "UPDATE atendente SET statusdeusuario = " + "'"+ "IP" + "'"+ 
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
