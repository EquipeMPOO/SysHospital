package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dao.util.ComandoSQL;
import aplicacao.dao.util.Comparacao;
import aplicacao.dominio.Enfermeiro;
import aplicacao.dominio.Funcionario;
import aplicacao.enums.StatusDeUsuario;

public class EnfermeiroDAO implements IFuncionarioDAO {
	
	private static final String SQL_PESQUISA =
		"SELECT * FROM enfermeiro";
	
	// ----x> temporário:
	int a = 1;
	
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
        		//enfermeiro.setPessoa(pessoa);
        		enfermeiro.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
        		
        		funcionarios.add(enfermeiro);
        		
        		// ----x>  Apenas de teste;
        		//System.out.println(atendente.getIdFuncionario());
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
		
		Enfermeiro enfermeiroDeParametro = (Enfermeiro) f;
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		
        		Enfermeiro enfermeiroBD = new Enfermeiro();
        		
        		enfermeiroBD.setIdFuncionario(rs.getInt("idfuncionario"));
        		enfermeiroBD.setLogin(rs.getString("login"));
        		enfermeiroBD.setSenha(rs.getString("senha"));
        		enfermeiroBD.setIdentificadorInterno(rs.getString("identificadorinterno"));
        		enfermeiroBD.setStatusDeUsuario(rs.getString("statusdeusuario"));
        		// ----0> m.setPessoa(pessoa);
        		enfermeiroBD.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
        		
        		Comparacao comparacao = new Comparacao();
        		if ( comparacao.eFiltro(enfermeiroDeParametro, enfermeiroBD) ){
	        		funcionarios.add(enfermeiroBD);
        		}
        		else{
        			enfermeiroBD = null;
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
        	String comandoSQL = codigo.gerarPesquisa(f, 1);
        	
    		// ----x>  Apenas de teste;
    		//System.out.println(comandoSQL);
        	
        	ps = conecxao.prepareStatement(comandoSQL);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		Enfermeiro enfermeiro = new Enfermeiro();
        		
        		enfermeiro.setIdFuncionario(rs.getInt("idfuncionario"));
        		enfermeiro.setLogin(rs.getString("login"));
        		enfermeiro.setSenha(rs.getString("senha"));
        		enfermeiro.setIdentificadorInterno(rs.getString("identificadorinterno"));
        		enfermeiro.setStatusDeUsuario(rs.getString("statusdeusuario"));
        		//m.setPessoa(pessoa);
        		enfermeiro.setNumeroDeRegistro(rs.getInt("numeroderegistro"));
        		
        		funcionarios.add(enfermeiro);
        		
        		// ----x>  Apenas de teste;
        		//System.out.println(medico.getNumeroDeRegistro());
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
		return a;
		
	}
	
	public Funcionario alterar(Funcionario f, int iap){
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        Enfermeiro enfermeiro = (Enfermeiro) f;
        
    	ComandoSQL codigo = new ComandoSQL();
    	String comandoSQL = codigo.gerarAtualizacao(enfermeiro, iap, 1);
    	
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
		
		enfermeiro.setIdFuncionario(resultado);
		enfermeiro.setLogin("");
		enfermeiro.setSenha("");
		enfermeiro.setIdentificadorInterno("");
		enfermeiro.setStatusDeUsuario("");
		enfermeiro.setPessoa(null);
		enfermeiro.setNumeroDeRegistro(-1);
		
		ArrayList<Funcionario> f1 = (ArrayList<Funcionario>) this.pesquisarAlgum(enfermeiro, false);
		ArrayList<Funcionario> vazio = new ArrayList<>();
		if (f1 == vazio){
			return null;
		} 
		else{
			return (Funcionario) f1.get(0);
		}
	}
	
	public Funcionario excluir(Funcionario f){
		Enfermeiro f1 = (Enfermeiro) f;
		f1.setStatusDeUsuario(StatusDeUsuario.MP.getStatus());
		Enfermeiro f2 = (Enfermeiro) this.alterar(f1, 1);
		return f2;
	}
	
	public Funcionario logar(Funcionario f){
		Enfermeiro f1 = (Enfermeiro) f;
		f1.setStatusDeUsuario(StatusDeUsuario.A.getStatus());
		Enfermeiro f2 = (Enfermeiro) this.alterar(f1, 1);
		return f2;
	}
	
	public int deslogar(Funcionario funcionario){
		return a;
	}
}
