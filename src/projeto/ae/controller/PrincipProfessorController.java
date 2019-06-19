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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import projeto.ae.model.Aluno;
import projeto.ae.model.Professor;
import projeto.ae.service.ConectaAluno;
import projeto.ae.service.ConectaProfessor;
import projeto.ae.view.App;

public class PrincipProfessorController implements Initializable{
	ConectaProfessor conecta = new ConectaProfessor();
	Professor professor = new Professor();
	
	// INICIALIZAÇÃO DA JANELA ------------------------------------
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configuraBotoes();
		
	}
	
	// FXML ---------------------------------------------------------------

    @FXML
    private Label lblSetNome;
    
    @FXML
    private Label lblNome;
    
    @FXML
    private Button btnSair;

    @FXML
    private Button btnAtivar;

    @FXML
    private Button btnRequisições;
    
    // INICIALIZAÇÃO DA JANELA ------------------------------------------------------------
    

    
    // BOTÕES -----------------------------------------------------------------
    
    @FXML
    void ativarUsuario(ActionEvent event) {
    	App.set_pane(9, 0);
    }

    @FXML
    void verificarRequisicao(ActionEvent event) {
    	App.set_pane(16, 0);
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
    
    private void BuscaProfessor(){
    	   
    	professor= conecta.BuscaProfessorPorId(App.idUser);
    	exibeNomes(professor.getNome());
    }
    
    private void exibeNomes(String nome){
    	lblSetNome.setText(nome);
    }
    
    private void exibeNomes(){
    	lblSetNome.setText("Professor");
    }
    
    private void configuraBotoes(){
    	btnRequisições.focusedProperty().addListener(new ChangeListener<Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				BuscaProfessor();
			}
		});
    }
}
