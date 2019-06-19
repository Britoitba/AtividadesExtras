package projeto.ae.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import projeto.ae.service.ConectaAtividades;
import projeto.ae.view.App;

public class SolicitaRecursoController implements Initializable {
	
	//INSTANCIANDO CLASSES------------------------------------------------------
	ConectaAtividades conexao = new ConectaAtividades();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		limpar();		
	}
	
	// FXML ----------------------------------------------------------------------
    @FXML
    private Label lblJustificativaRecurso;

    @FXML
    private Button btnEnviar;

    @FXML
    private Button btnCancear;

    @FXML
    private Pane paneRecurso;

    @FXML
    private Label lblRecurso;

    @FXML
    private TextArea txtRecurso;
    
    // BOTÕES -------------------------------------------------------------------------

    @FXML
    void enviar(ActionEvent event) {
    	conexao.salvarRecurso(txtRecurso.getText());	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Success!");
    	alert.setHeaderText(null);
    	alert.setContentText("Recurso enviado.");
    	alert.showAndWait();
    	App.set_pane(App.get_pane(), 4);
    	limpar();
    }

    @FXML
    void cancelar(ActionEvent event) {
    	App.set_pane(App.get_pane(), 4);
    	limpar();
    }
	
    
    
    //OUTROS MÉTODOS-------------------------------------------------------------
    private void limpar(){
    	txtRecurso.setText("");
    }

	
    
}
