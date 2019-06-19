package projeto.ae.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import projeto.ae.view.App;
import projeto.ae.model.Aluno;
import projeto.ae.service.ConectaAluno;

public class PrincipAlunoController implements Initializable{
	
	//INSTANCIANDO CLASSES-------------------------------------------------------
	ConectaAluno conecta = new ConectaAluno();
	Aluno aluno = new Aluno();
	
	
	// FXML ----------------------------------------------------------
    @FXML
    private Button btnEnviar;
    
    @FXML
    private Label lblSetNome;

    @FXML
    private Label lblCurso;

    @FXML
    private Button btnNotificacoes;

    @FXML
    private Button btnSair;

    @FXML
    private Label lblNome;

    @FXML
    private Button btnHistorico;
    
    // INICIALIZAÇÃO DA JANELA -------------------------------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		configuraBotoes();
		
	}
    
    // BOTÕES -----------------------------------------------------------------------------
	

    @FXML
    void enviarCertificado(ActionEvent event) {
    	App.set_pane(10, 0);
    }

    @FXML
    void notificacoes(ActionEvent event) {
    	App.set_pane(11, 0);
    }

    @FXML
    void historico(ActionEvent event) {
    	App.set_pane(13, 0);
    }

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
    
    // OUTROS MÉTODOS ----------------------------------------------------------------------
    
    private void BuscaAluno(){
   
    	aluno= conecta.buscaAlunoPorId(App.idUser);
    	exibeNomes(aluno.getNome());
    }
    
    private void exibeNomes(String nome){
    	lblSetNome.setText(nome);
    }
    
    private void configuraBotoes(){
    	btnEnviar.focusedProperty().addListener(new ChangeListener<Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				BuscaAluno();
			}
		});
    }

}
