package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aplicacao.dao.ConexaoDAO;
import aplicacao.dao.util.ComandoSQL;
import aplicacao.dao.util.Comparacao;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Medico;
import aplicacao.dominio.Pessoa;
import aplicacao.enums.StatusDeUsuario;

public class MedicoDAO implements IFuncionarioDAO {
	
	private static final String SQL_PESQUISA =	"SELECT * FROM medico";
	
	public List<Funcionario> pesquisarTodos(Boolean pesquisarPessoa){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		Medico medico = new Medico();
        		
        		medico.setIdFuncionario(rs.getInt("idfuncionario"));
        		medico.setLogin(rs.getString("login"));
        		medico.setSenha(rs.getString("senha"));
        		medico.setIdentificadorInterno(rs.getString("identificadorinterno"));
        		medico.setStatusDeUsuario(rs.getString("statusdeusuario"));
        		medico.setNumeroDeRegistro(rs.getInt("numeroderegistro"));

    			if (pesquisarPessoa){
    				PessoaDAO a = new PessoaDAO();
    				medico.setPessoa(a.pesquisarporID(rs.getInt("idpessoa")));
    			}
    			        		
        		funcionarios.add(medico);
        		
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
        Medico medico = new Medico();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		medico.setLogin(rs.getString("login"));
        		medico.setSenha(rs.getString("senha"));
        		
        		if (medico.getSenha().equals(parametro.getSenha()) & medico.getLogin().equals(parametro.getLogin())){
	        		medico.setIdFuncionario(rs.getInt("idfuncionario"));
	        		medico.setStatusDeUsuario(rs.getString("statusdeusuario"));
	        		medico.setIdentificadorInterno(rs.getString("identificadorinterno"));
	        		medico.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
	        		return medico;
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
        Medico medico = new Medico();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		PessoaDAO dbPessoa = new PessoaDAO();
        		medico.setPessoa(dbPessoa.pesquisarporID(rs.getInt("pessoa")));
        		
        		if (medico.getPessoa().getCpf().equals(parametro.getPessoa().getCpf())){
        			
        			medico.setLogin(rs.getString("login"));
        			medico.setSenha(rs.getString("senha"));
	        		medico.setIdFuncionario(rs.getInt("idfuncionario"));
	        		medico.setIdentificadorInterno(rs.getString("identificadorinterno"));
	        		medico.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
	        		return medico;
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
        		Medico medico = new Medico();
        		medico.setStatusDeUsuario(rs.getString("statusdeusuario"));
        		
        		if (medico.getStatusDeUsuario().equals("Inativo")){
	        		medico.setIdFuncionario(rs.getInt("idfuncionario"));
	        		medico.setLogin(rs.getString("login"));
	        		medico.setSenha(rs.getString("senha"));
	        		medico.setIdentificadorInterno(rs.getString("identificadorinterno"));
	        		medico.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
	        		
	    			PessoaDAO a = new PessoaDAO();
	    			medico.setPessoa(a.pesquisarporID(rs.getInt("pessoa")));	    				        		
	        		funcionarios.add(medico);
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
	
	
	public void cadastrar(Funcionario medico) {
		
		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		
		PessoaDAO db = new PessoaDAO();
		db.cadastrar(medico.getPessoa());
		
		String comando = "INSERT INTO medico(login, senha, identificadorinterno, statusdeusuario, pessoa, numeroderegistro, especialidade) VALUES ("+
						     "'"+medico.getLogin()+"'"+ ","+ 
							"'"+medico.getSenha()+"'"+","+ 
							"'"+medico.getIdentificadorInterno()+"'"+","+ 
							"'"+ "Inativo" +"'"+","+ 
							"'"+ db.procurarId(medico.getPessoa()) + "'"+ ","+ 
							"'"+((Medico) medico).getNumeroDeRegistro() +"'"+","+
							"'"+((Medico) medico).getEspecialidade()+"'" +")" ;
        
        try {
			
			ps = conexao.prepareStatement(comando);
			ps.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConexaoDAO.close(conexao, ps, null);
		}


	}
	
	public void alterar(Funcionario medico){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
		PessoaDAO db = new PessoaDAO();
		db.alterar(medico.getPessoa());
        
        String comando = "UPDATE medico SET login = " +"'"+medico.getLogin()+"'"+
		        		", senha = " +"'"+medico.getSenha()+"'"+
		        		", identificadorinterno = " +"'"+medico.getIdentificadorInterno()+"'"+ 
		        		", statusdeusuario = " +"'"+medico.getStatusDeUsuario()+"'"+
		        		", pessoa = " +"'"+ db.procurarId(medico.getPessoa()) +"'"+ 
		        		"WHERE idfuncionario = " + "'" + medico.getIdFuncionario() + "'";
		                
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
        
        String comando = "UPDATE medico SET statusdeusuario = " + "'"+ "IP" + "'"+ 
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
