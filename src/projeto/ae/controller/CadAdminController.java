package projeto.ae.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import projeto.ae.model.Administrador;
import projeto.ae.service.ConectaAdministrador;
import projeto.ae.service.ConectaDataBase;
import projeto.ae.service.ConectaUsuario;
import projeto.ae.view.App;

public class CadAdminController implements Initializable{
	
	//INSTANCIANDO CLASSES ----------------------------------------------------------
	ConectaAdministrador conexao = new ConectaAdministrador();
	ConectaUsuario conexaoUser = new ConectaUsuario();
	Administrador adm = new Administrador();
	
	//  FXML ------------------------------------------------------------------------
    
    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField pwdSenha;
    
    @FXML
    private Button btnCadastrar;
    
    
    // INICIALIZANDO A JANELA -------------------------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		configuraBotoes();
		txtLogin.setText("admin");
		txtLogin.setDisable(true);
		limpar();
	}
    
    
    // BOTÕES -----------------------------------------------------------------------
 
    @FXML
    public void onEnter(ActionEvent event){
       if(btnCadastrar.isDisabled() == false){
    	   cadastrar(event);
       }
    }
	
	
    @FXML
    void cadastrar(ActionEvent event) {
    	int id = 0;
    	
    	adm.setNome("Admin");	
    	adm.setEmail(txtLogin.getText());
    	adm.setSenha(pwdSenha.getText());
    	adm.setTipoUsuario("administrador");
    	adm.setAtivo(1);
    	conexao.salvaradm(adm);
    	id = conexao.BuscaUltimoAdm();
    	conexaoUser.salvarUsuarioAdmin(id, adm);    	   	
    	
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Success!");
    	alert.setHeaderText(null);
    	alert.setContentText("Cadastrado com Sucesso !");

    	alert.showAndWait();
    	App.set_pane(0, 4);
    }

    private void configuraBotoes(){
    	BooleanBinding camposPreenchidos = txtLogin.textProperty().isEmpty().or(pwdSenha.textProperty().isEmpty());
    	btnCadastrar.disableProperty().bind(camposPreenchidos);
    }
    
    private void limpar(){
    	txtLogin.setText("admin");
    	pwdSenha.setText("");
    }

}
