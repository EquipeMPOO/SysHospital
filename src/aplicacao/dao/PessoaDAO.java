package aplicacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import aplicacao.dominio.Pessoa;

public class PessoaDAO implements IPessoaDAO{
	
	private static final String SQL_PESQUISA = "SELECT * FROM pessoa";
	
	private static final String SQL_INSERT=
			  "INSERT INTO pessoa(nome, cpf, idade, tiposanguineo, sexo, paciente, statusdepessoa, funcionarios)values(?,?,?,?,?,?,?,?)";

	@Override
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

	@Override
	public int alterar(Pessoa pessoa) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int excluir(Pessoa pessoa) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Pessoa> pesquisarTodos(Boolean pesquisarAdministrador,
			Boolean pesquisarAtendente, Boolean pesquisarMedico,
			Boolean pesquisarEnfermeiro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pessoa> pesquisarFiltrando(Pessoa pessoa,
			Boolean pesquisarAdministrador, Boolean pesquisarAtendente,
			Boolean pesquisarMedico, Boolean pesquisarEnfermeiro) {
		// TODO Auto-generated method stub
		return null;
	}

}
