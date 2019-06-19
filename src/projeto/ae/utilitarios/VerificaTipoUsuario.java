package projeto.ae.utilitarios;

import projeto.ae.model.Usuario;
import projeto.ae.service.ConectaUsuario;

public class VerificaTipoUsuario {
	// INSTANCIANDO CLASSES -------------------------------------------
	Usuario user = new Usuario();
	ConectaUsuario conexao = new ConectaUsuario();
	
	//VERIFICAR TIPO DE USUARIO POR LOGIN E SENHA
	public Usuario verificar(String email, String senha){
		return user = conexao.verificarLogin(email, senha);
	}
	
}
