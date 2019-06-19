package projeto.ae.utilitarios;

import javafx.scene.control.TextField;
import projeto.ae.service.ConectaDuplicidade;

public class ValidaCampos {
	
	// INSTANCIANDO CLASSES -------------------------------------------------------
	ConectaDuplicidade conexao = new ConectaDuplicidade();

	//VERIFICAR SE POSSUI NUMEROS -------------------------------------------------
	public boolean verificaNumeros(String txt){
		boolean result = true;
		for (int i = 0; i < txt.length(); i++) {
			if(txt.charAt(i) != '0' && txt.charAt(i) != '1' && txt.charAt(i) != '2' && txt.charAt(i) != '3' && txt.charAt(i) != '4' && txt.charAt(i) != '5' && 
					txt.charAt(i) != '6' && txt.charAt(i) != '7' && txt.charAt(i) != '8' && txt.charAt(i) != '9' ){
				result = false;
				return result;
			}
		}
		
		return result;		
	}
	
	//VERIFICA EMAIL ------------------------------------------------------------
	public boolean verificaEmail(String txt){
		String valor = "@";
		boolean result = false;
		if(txt.contains(valor)){
			result = true;
			return result;
		}else{
			return result;
		}
		
	}
	
	//VERIFICAR DUPLICIDADE CPF
	public boolean verificacpf(String txt){
		return conexao.VerificaDuplicidade(txt);
	}
	
	//VERIFICAR DUPLICIDADE USUARIO
	public boolean verificaUsuario(String txt){
		return conexao.VerificaDuplicidadeUsuario(txt);
	}
	
	//VERIFICAR DUPLICIDADE CURSOS
	public boolean verificaCurso(String txt){
		return conexao.VerificaDuplicidadeCurso(txt);
	}
	
	//VERIFICAR EXTENSÃO DO ARQUIVO 
	public boolean verificaExtensao(String nome){
		boolean result = false;
		String extensao = null;
		for (int i = 0; i < nome.length(); i++) {
			if(nome.charAt(i) == '.'){
				extensao = "";
			}else{
				extensao += nome.charAt(i);
			}
		}
		if(extensao.equals("pdf")){
			result = true;
		}
		return result;
	}
	
}
