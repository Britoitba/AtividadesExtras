package projeto.ae.controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import projeto.ae.model.Usuario;
import projeto.ae.service.ConectaCurso;
import projeto.ae.utilitarios.VerificaTipoUsuario;
import projeto.ae.view.App;


public class LoginController {	
	
	
	// INSTANCIANDO CLASSES ----------------------------------------
	VerificaTipoUsuario verificacao = new VerificaTipoUsuario();
	Usuario user = new Usuario();
	ConectaCurso verificaCurso = new ConectaCurso();
	
	GerarRelatorio g = new GerarRelatorio();
	
	// FXML ------------------------------------------
	
    @FXML
    private PasswordField pwdSenha;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label lblEmail;

    @FXML
    private Button btnEntrar;
    
    @FXML
    private Button bntCadastrar;
    
    
    // BOTOES -------------------------------------------------
    @FXML
    public void onEnter(ActionEvent event){
       if(txtEmail.getText().equals("") || pwdSenha.getText().equals("")){  
       }else{
    	   entrar(event);
       }
    }
    
    @FXML
    void entrar(ActionEvent event) {
    	
    	user = verificacao.verificar(txtEmail.getText(), pwdSenha.getText());
    	
    	if(user.getTipoUsuario() == null){
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("E-Mail e Senha Inválidos !");
    		alert.setContentText("Por favor, Verifique os Campos Novamente.");
    		alert.showAndWait();
    	}else{
    		if(user.getTipoUsuario().equals("administrador")){
        		App.idUser = user.getIdPessoa();
        		App.TipoUser = user.getTipoUsuario();
        		limpar();
        		App.set_pane(5, 1);    		
        	}else if(user.getTipoUsuario().equals("aluno")){
        		
        		App.idUser = user.getIdPessoa();
        		App.TipoUser = user.getTipoUsuario();
        		if(user.getAtivo() == 1){
        			limpar();
            		App.set_pane(6, 1);  
        		}else if(user.getAtivo() == 0){
            		Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setHeaderText("Usuario Inativo");
            		alert.setContentText("Usuario Não Ativo. Aguarde ativação do Professor Responsável!");
            		alert.showAndWait();
            	}else{
            		Alert alert = new Alert(AlertType.ERROR);
            		alert.setHeaderText("Usuário Recusado !");
            		alert.setContentText("Por favor, Refaça seu Cadastro.");
            		alert.showAndWait();
            	}
    		  
        	}else if(user.getTipoUsuario().equals("professor")){
        		App.idUser = user.getIdPessoa();
        		App.TipoUser = user.getTipoUsuario();
        		limpar();
        		App.set_pane(7, 1);    
        	}else if(user.getTipoUsuario().equals("coordenador")){
        		App.idUser = user.getIdPessoa();
        		App.TipoUser = user.getTipoUsuario();
        		limpar();
        		App.set_pane(8, 1);    
        	}else{
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setHeaderText("E-Mail e Senha Inválidos !");
        		alert.setContentText("Por favor, Verifique os Campos Novamente.");
        		alert.showAndWait();
        	}
    	}
    	    	
    }
    
    @FXML
    void cadastrar(ActionEvent event) {
    	App.set_pane(2, 1);
    }
    
    private void limpar(){
    	txtEmail.setText("");
    	pwdSenha.setText("");
    }
    
    // OUTROS METODOS -------------------------------------
	// VERIFICAÇÃO PARA ATIVAR BOTÕES ----------------------------------------------------------------
	public void iniciaBotoes(){
		try{
			if(verificaCurso.VerificaBanco()){
				bntCadastrar.setDisable(false);
			}else{
				bntCadastrar.setDisable(true);

			}
		}catch(Exception e){
			
		}
	}
	
    private void configuraBotoes(){
    	txtEmail.focusedProperty().addListener(new ChangeListener<Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				iniciaBotoes();
			}
		});
    }


	
}
