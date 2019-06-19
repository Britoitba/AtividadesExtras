package projeto.ae.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import projeto.ae.model.Administrador;
import projeto.ae.service.ConectaAdministrador;
import projeto.ae.service.ConectaCurso;
import projeto.ae.view.App;

public class PrincipAdminController implements Initializable{
	
	// INSTANCIANDO CLASSES -------------------------------------------
	ConectaCurso verificaCurso = new ConectaCurso();
	ConectaAdministrador conectaAdmin = new ConectaAdministrador();
	Administrador admin = new Administrador();
	
	// INICIALIZAR A JANELA ---------------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		configuraBotoes();
		
	}
	
	// FXML -------------------------------------------------------------
    @FXML
    private Label lblSetNome;
    
    @FXML
    private Button btnSair;
    
    @FXML
    private Button btnCadastrarProf;
    
    @FXML
    private Button btnCadastrarCoord;
    
    @FXML
    private Button btnCadastrarCurso;
    
    // BOTÕES ------------------------------------------------------------

    @FXML
    void sair(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Sair");
    	alert.setHeaderText("Tem Certeza que Deseja Sair?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		App.set_pane(0, 3);
    	} else {
    	  	
    	}
    	
    }
    
    @FXML
    void cadastrarProf(ActionEvent event) {
    	App.set_pane(3, 0);
    }

    @FXML
    void cadastrarCoord(ActionEvent event) {
    	App.set_pane(4, 0);
    }
    
    @FXML
    void cadastrarCurso(ActionEvent event) {
    	App.set_pane(17, 0);
    }
    
    // OUTROS MÉTODOS ----------------------------------------------------------------------
    
    private void BuscaAdmin(){
    	admin = conectaAdmin.BuscaAdmPorID(App.idUser);
    	exibeNomes(admin.getNome());
    }
      
    private void exibeNomes(String nome){
    	lblSetNome.setText(nome);
    }
    
    private void configuraBotoes(){
    	btnCadastrarCurso.focusedProperty().addListener(new ChangeListener<Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				BuscaAdmin();
				iniciaBotoes();
			}
		});
    }
    
	// VERIFICAÇÃO PARA ATIVAR BOTÕES ----------------------------------------------------------------
	public void iniciaBotoes(){
		try{
			if(verificaCurso.VerificaBanco()){
				btnCadastrarCoord.setDisable(false);
				btnCadastrarProf.setDisable(false);
			}else{
				btnCadastrarCoord.setDisable(true);
				btnCadastrarProf.setDisable(true);
			}
		}catch(Exception e){
			
		}

	}

}
