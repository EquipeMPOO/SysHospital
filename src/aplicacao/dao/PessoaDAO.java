package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplicacao.dao.util.Comparacao;
import aplicacao.dominio.Funcionario;
import aplicacao.dominio.Pessoa;
import aplicacao.enums.StatusDePessoa;

public class PessoaDAO{
	
	private static final String SQL_PESQUISA = "SELECT * FROM pessoa";
	
	private static final String SQL_INSERT=
			  "INSERT INTO pessoa(nome, cpf, idade, tiposanguineo, sexo, paciente, statusdepessoa, funcionarios)values(?,?,?,?,?,?,?,?)";

	public int cadastrar(Pessoa novaPessoa) {
		
		Connection conexao = ConexaoDAO.getConnection();
		PreparedStatement statement = null;
		int retorno = 0;

		 PreparedStatement statement1 = null;
         try{
        	 
        	 System.out.println(
        			 
                     novaPessoa.getNome() + "\n" +
                     novaPessoa.getCpf() + "\n" +
                     novaPessoa.getIdade() + "\n" +
                     novaPessoa.getTipoSanguineo() + "\n" +
                     novaPessoa.getSexo() + "\n" +
                     
                     novaPessoa.getStatusDePessoa()

             
        	 );
        	 
             statement1 = conexao.prepareStatement(SQL_INSERT);
             statement1.setString(1, novaPessoa.getNome());
             statement1.setString(2, novaPessoa.getCpf());
             statement1.setInt(3, novaPessoa.getIdade());
             statement1.setString(4, novaPessoa.getTipoSanguineo());
             statement1.setString(5, novaPessoa.getSexo());
             statement1.setInt(6, novaPessoa.getPaciente().getIdPaciente());
             statement1.setString(7, novaPessoa.getStatusDePessoa());
             statement1.setInt(7, novaPessoa.getFuncionariosDePessoa().getIdFuncionariosDePessoa());
             
             retorno = statement1.executeUpdate();
             
         }catch(SQLException e){
        	 try{
        		 if(conexao!=null){
        			
        			 conexao.rollback();
        		 }
        	 }catch(SQLException e1){
        		 
        		 e1.printStackTrace();
        	 }finally{
        		 ConexaoDAO.close(conexao, statement, null);
        	 }
        	 e.printStackTrace();
        	 
         }
		
		return retorno;
	}


	public int alterar(Pessoa pessoa) throws SQLException {		
		
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
                
        ps = conecxao.prepareStatement(comando);
        ps.executeUpdate();
        return 0;
	}

	
	public int excluir(Pessoa pessoa) throws SQLException {
		
		Connection conecxao = ConexaoDAO.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String comando = "UPDATE pessoa SET statusdepessoa = " + "'"+ StatusDePessoa.M.getStatus() + "'"+ 
		          "WHERE idpessoa = " + "'" + pessoa.getIdPessoa() + "'" ;
      
        ps = conecxao.prepareStatement(comando);
        ps.executeUpdate();

		return 0;
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
		
		// TODO Auto-generated method stub
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
	
	public static void main(String[] args) throws SQLException {
				
		PessoaDAO a = new PessoaDAO();
		
		Pessoa fulano = new Pessoa();
		fulano.setIdPessoa(1);
		
		a.excluir(fulano);
		
	}	

}
