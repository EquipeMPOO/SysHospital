package aplicacao;

import aplicacao.dominio.Atendente;
import aplicacao.dominio.Gerente;
import aplicacao.form.GerenciarFuncionarioGUI;
import aplicacao.form.LoginGUI;

public class iniciar {
		
	public static void main(String[]args){
		LoginGUI telaDeLogin = new LoginGUI();
		telaDeLogin.setVisible(true);
	}
}
