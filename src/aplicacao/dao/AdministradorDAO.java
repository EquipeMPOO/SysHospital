package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dao.util.ComandoSQL;
import aplicacao.dao.util.Comparacao;
import aplicacao.dominio.Administrador;
import aplicacao.dominio.Funcionario;
import aplicacao.enums.StatusDeUsuario;

public class AdministradorDAO implements IFuncionarioDAO{
	

	private static final String SQL_PESQUISA =
		"SELECT * FROM administrador";
	
	public List<Funcionario> pesquisarTodos(Boolean pesquisarPessoa){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		
        		Administrador administrador = new Administrador();
        		
        		administrador.setIdFuncionario(rs.getInt("idfuncionario"));
        		administrador.setLogin(rs.getString("login"));
        		administrador.setSenha(rs.getString("senha"));
        		administrador.setIdentificadorInterno(rs.getString("identificadorinterno"));
        		administrador.setStatusDeUsuario(rs.getString("statusdeusuario"));

    			if (pesquisarPessoa){
    				PessoaDAO a = new PessoaDAO();
    				administrador.setPessoa(a.pesquisarporID(rs.getInt("idpessoa")));
    			}
    			
        		administrador.setCargo(rs.getString("cargo"));
        		
        		funcionarios.add(administrador);
       
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
	
	public List<Funcionario> pesquisarFiltrando(Funcionario f, Boolean pesquisarPessoa){
		
		Administrador administradorDeParametro = (Administrador) f;
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		
        		Administrador administradorBD = new Administrador();
        		
        		administradorBD.setIdFuncionario(rs.getInt("idfuncionario"));
        		administradorBD.setLogin(rs.getString("login"));
        		administradorBD.setSenha(rs.getString("senha"));
        		administradorBD.setIdentificadorInterno(rs.getString("identificadorinterno"));
        		administradorBD.setStatusDeUsuario(rs.getString("statusdeusuario"));

    			if (pesquisarPessoa){
    				PessoaDAO a = new PessoaDAO();
    				administradorBD.setPessoa(a.pesquisarporID(rs.getInt("idpessoa")));
    			}
    			
        		administradorBD.setCargo(rs.getString("cargo"));
        		
        		Comparacao comparacao = new Comparacao();
        		if ( comparacao.eFiltro(administradorDeParametro, administradorBD) ){
	        		funcionarios.add(administradorBD);
        		}
        		else{
        			administradorBD = null;
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
	
	public List<Funcionario> pesquisarAlgum(Funcionario f, Boolean pesquisarPessoa){
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try{
        	ComandoSQL codigo = new ComandoSQL();
        	String comandoSQL = codigo.gerarPesquisa(f, 2);
        	
    		// ----x>  Apenas de teste;
    		//System.out.println(comandoSQL);
        	
        	ps = conecxao.prepareStatement(comandoSQL);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		Administrador administradorBD = new Administrador();
        		
        		administradorBD.setIdFuncionario(rs.getInt("idfuncionario"));
        		administradorBD.setLogin(rs.getString("login"));
        		administradorBD.setSenha(rs.getString("senha"));
        		administradorBD.setIdentificadorInterno(rs.getString("identificadorinterno"));
        		administradorBD.setStatusDeUsuario(rs.getString("statusdeusuario"));

        		if (pesquisarPessoa){
        			
        			PessoaDAO databasePessoa = new PessoaDAO();
        			administradorBD.setPessoa(databasePessoa.pesquisarporID(rs.getInt("idpessoa")));
        			
        		}
        		
        		administradorBD.setCargo(rs.getString("cargo"));
        		
        		funcionarios.add(administradorBD);
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
	
	public int cadastrar(Funcionario funcionario) {
		return 0;
	}
	
	public Funcionario alterar(Funcionario f, int iap){
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        Administrador administrador = (Administrador) f;
        
    	ComandoSQL codigo = new ComandoSQL();
    	String comandoSQL = codigo.gerarAtualizacao(administrador, iap, 2);
        
    	int resultado = 0;
        try{
            ps = conecxao.prepareStatement(comandoSQL);
            resultado = ps.executeUpdate();
            
            }catch(SQLException e){
            	try{
            		if(conecxao!=null){
            			conecxao.rollback();
            		}
            	}catch(SQLException e1){
            		e1.printStackTrace();
            	}finally{
            		ConexaoDAO.close(conecxao, ps, null);
            	}
            	e.printStackTrace();
            }
		
		administrador.setIdFuncionario(resultado);
		administrador.setLogin("");
		administrador.setSenha("");
		administrador.setIdentificadorInterno("");
		administrador.setStatusDeUsuario("");
		administrador.setPessoa(null);
		administrador.setCargo("");
		
		ArrayList<Funcionario> f1 = (ArrayList<Funcionario>) this.pesquisarAlgum(administrador, false);
		ArrayList<Funcionario> vazio = new ArrayList<>();
		if (f1 == vazio){
			return null;
		} 
		else{
			return (Funcionario) f1.get(0);
		}
	}
	
	public Funcionario excluir(Funcionario f){
		Administrador f1 = (Administrador) f;
		f1.setStatusDeUsuario(StatusDeUsuario.MP.getStatus());
		Administrador f2 = (Administrador) this.alterar(f1, 1);
		return f2;
	}
	
	public Funcionario logar(Funcionario f){
		Administrador f1 = (Administrador) f;
		f1.setStatusDeUsuario(StatusDeUsuario.A.getStatus());
		Administrador f2 = (Administrador) this.alterar(f1, 1);
		return f2;
	}
	
	public int deslogar(Funcionario funcionario){
		return 0;
	}
	
}
