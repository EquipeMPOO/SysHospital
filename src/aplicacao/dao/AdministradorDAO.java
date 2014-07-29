package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dao.util.ComandoSQL;
import aplicacao.dominio.Administrador;
import aplicacao.dominio.Atendente;
import aplicacao.dominio.Funcionario;
import aplicacao.enums.StatusDeUsuario;

public class AdministradorDAO{
	
	private static final String SQL_PESQUISA = "SELECT * FROM administrador";
	
	public List<Funcionario> pesquisarTodos(Boolean pesquisarPessoa){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		Administrador adm = new Administrador();
        		
        		adm.setIdFuncionario(rs.getInt("idfuncionario"));
        		adm.setLogin(rs.getString("login"));
        		adm.setSenha(rs.getString("senha"));
        		adm.setStatusDeUsuario(rs.getString("statusdeusuario"));

    			if (pesquisarPessoa){
    				PessoaDAO a = new PessoaDAO();
    				adm.setPessoa(a.pesquisarporID(rs.getInt("idpessoa")));
    			}
    			
        		adm.setCargo(rs.getString("cargo"));
        		
        		funcionarios.add(adm);
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
        Administrador adm = new Administrador();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		adm.setLogin(rs.getString("login"));
        		adm.setSenha(rs.getString("senha"));
        		
        		
        		if (adm.getSenha().equals(parametro.getSenha()) & adm.getLogin().equals(parametro.getLogin())){
	        		adm.setIdFuncionario(rs.getInt("idfuncionario"));
	        		adm.setStatusDeUsuario(rs.getString("statusdeusuario"));
	        		adm.setCargo(rs.getString("cargo"));
	        		return adm;
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
        Administrador adm = new Administrador();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		PessoaDAO a = new PessoaDAO();
        		adm.setPessoa(a.pesquisarporID(rs.getInt("pessoa")));
        		
        		if (adm.getPessoa().getCpf().equals(parametro.getPessoa().getCpf())){
        			
        			adm.setLogin(rs.getString("login"));
        			adm.setSenha(rs.getString("senha"));
	        		adm.setIdFuncionario(rs.getInt("idfuncionario"));
	        		adm.setCargo(rs.getString("cargo"));
	        		return adm;
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
        		Administrador adm = new Administrador();
        		adm.setStatusDeUsuario(rs.getString("statusdeusuario"));
        		
        		if (adm.getStatusDeUsuario().equals(StatusDeUsuario.I.getStatus())){
	        		adm.setIdFuncionario(rs.getInt("idfuncionario"));
	        		adm.setLogin(rs.getString("login"));
	        		adm.setSenha(rs.getString("senha"));
	        		adm.setCargo(rs.getString("cargo"));        	

	        		
	    			PessoaDAO a = new PessoaDAO();
	    			adm.setPessoa(a.pesquisarporID(rs.getInt("idpessoa")));	    				        		
	        		funcionarios.add(adm);
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
		
		String comando = "INSERT INTO administrador(login, senha, statusdeusuario, pessoa) VALUES ("+
						     "'"+atendente.getLogin()+"'"+ ","+ 
							"'"+atendente.getSenha()+"'"+","+ 
							"'"+atendente.getStatusDeUsuario()+"'"+","+ 
							"'"+atendente.getPessoa().getIdPessoa()+"'"+ ")" ;
        
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
        
        String comando = "UPDATE administrador SET login = " +"'"+atendente.getLogin()+"'"+  
		        		", senha = " +"'"+atendente.getSenha()+"'"+
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
        
        String comando = "UPDATE administrador SET statusdeusuario = " + "'"+ StatusDeUsuario.IP.getStatus() + "'"+ 
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
