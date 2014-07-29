package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Pessoa;
import aplicacao.enums.StatusDePessoa;

public class PessoaDAO{

	private static final String SQL_PESQUISA = "SELECT * FROM pessoa";


	public int procurarId(Pessoa pessoa){

		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;

		try {
			ps = conexao.prepareStatement("SELECT idpessoa FROM pessoa WHERE cpf = " + "'" + pessoa.getCpf() + "'");
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt("idpessoa");				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConexaoDAO.close(conexao, ps, rs);
		}

		return id;

	}

	public void cadastrar(Pessoa novaPessoa) {

		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

        String comando = "INSERT INTO pessoa(nome, cpf, idade, tiposanguineo, sexo, statusdepessoa) VALUES ("+
        				"'"+novaPessoa.getNome()+"'"+ ","+ 
        				"'"+novaPessoa.getCpf()+"'"+","+ 
        				"'"+novaPessoa.getIdade()+"'"+","+ 
        				"'"+novaPessoa.getTipoSanguineo()+"'"+","+ 
        				"'"+novaPessoa.getSexo() +"'"+","+ 
        				"'"+novaPessoa.getStatusDePessoa()+"'" + ")";
                
        try {
			ps = conexao.prepareStatement(comando);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{

			ConexaoDAO.close(conexao, ps, null);
		}
        
	}


	public void alterar(Pessoa pessoa) {		

		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String comando = "UPDATE pessoa SET nome = "+ "'" + pessoa.getNome() + "'" + 
        		          ", cpf = " + "'"+ pessoa.getCpf() + "'" +
        		          ", idade = " + "'"+ pessoa.getIdade() + "'" +
        				  ", tiposanguineo = "+ "'"+ pessoa.getTipoSanguineo() + "'"+ 
        		          ", sexo = " + "'"+ pessoa.getSexo() + "'"+
        				  ", statusdepessoa = " + "'"+ pessoa.getStatusDePessoa() + "'"+ 
        		          "WHERE idpessoa = " + "'" + pessoa.getIdPessoa() + "'" ;
                
        try {
			ps = conecxao.prepareStatement(comando);
			ps.executeUpdate();
        			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConexaoDAO.close(conecxao, ps, rs);
		}

 	}


	public void excluir(Pessoa pessoa){

		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String comando = "UPDATE pessoa SET statusdepessoa = " + "'"+ StatusDePessoa.M.getStatus() + "'"+ 
		          "WHERE idpessoa = " + "'" + pessoa.getIdPessoa() + "'" ;
      
        try {
 			ps = conecxao.prepareStatement(comando);
 			ps.executeUpdate();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}finally{
 			ConexaoDAO.close(conecxao, ps, rs);
 		}
	}

	public ArrayList<Pessoa> pesquisarTodos() {

		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		Pessoa pessoa = new Pessoa();
        		
        		pessoa.setIdPessoa(rs.getInt("idpessoa"));
        		pessoa.setNome(rs.getString("nome"));
        		pessoa.setCpf(rs.getString("cpf"));
        		pessoa.setIdade(rs.getInt("idade"));
        		pessoa.setTipoSanguineo(rs.getString("tiposanguineo"));
        		pessoa.setSexo(rs.getString("sexo"));
        		pessoa.setStatusDePessoa(rs.getString("statusdepessoa"));
        		
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

		return pessoas;

	}

	public Pessoa pesquisarporID(int idPessoa) {

		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Pessoa pessoa = new Pessoa();
        
        try{
        	ps = conecxao.prepareStatement(SQL_PESQUISA);
        	rs = ps.executeQuery();
        	
        	while(rs.next()) {
        		
        		pessoa.setIdPessoa(rs.getInt("idpessoa"));
        		
        		if (pessoa.getIdPessoa() == idPessoa ){
            		pessoa.setNome(rs.getString("nome"));
            		pessoa.setCpf(rs.getString("cpf"));
            		pessoa.setIdade(rs.getInt("idade"));
            		pessoa.setTipoSanguineo(rs.getString("tiposanguineo"));
            		pessoa.setSexo(rs.getString("sexo"));
            		pessoa.setStatusDePessoa(rs.getString("statusdepessoa"));
        			break; 
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

		return pessoa;
	}

}