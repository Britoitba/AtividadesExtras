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
import projeto.ae.model.Coordenador;
import projeto.ae.service.ConectaAtividades;
import projeto.ae.service.ConectaCoordenador;
import projeto.ae.view.App;

public class PrincipCoordenadorController implements Initializable{
	
	// INSTANCIANDO CLASSES -----------------------------------------------
	Coordenador coordenador = new Coordenador();
	ConectaCoordenador conectaCoord = new ConectaCoordenador();
	GerarRelatorio relatorio = new GerarRelatorio();
	ConectaAtividades conexao = new ConectaAtividades();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configuraBotoes();
		
	}

	//  FXML -----------------------------------------------------------
	
	@FXML
	private Label lblSetNome;
	
    @FXML
    private Label lblNome;
    
    @FXML
    private Button btnRequisicoes;

    @FXML
    private Button btnVerificaRecurso;

    @FXML
    private Button btnCadastrarProf;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnAtivar;
    
    @FXML
    private Button btnCadastrarCoord;

    @FXML
    private Button btnGerarRelatorio;
    
    // BOTÕES --------------------------------------------------------------

    @FXML
    void cadastrarCoord(ActionEvent event) {
    	App.set_pane(4, 0);
    }

    @FXML
    void cadastrarProf(ActionEvent event) {
    	App.set_pane(3, 0);
    }

    @FXML
    void verificarReq(ActionEvent event) {
    	App.set_pane(16, 0);
    }

    @FXML
    void verificarRecursos(ActionEvent event) {
    	App.set_pane(15, 0);
    }

    @FXML
    void AtivarAluno(ActionEvent event) {
    	App.set_pane(9, 0);
    }

    @FXML
    void relatorio(ActionEvent event) {
    	relatorio.GerarPDF();
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
    
    private void BuscaCoordenador(){
    	coordenador = conectaCoord.BuscaCoordenadorPorId(App.idUser);
    	exibeNomes(coordenador.getNome());
    }
    
    private void BotaoGerarRelatorio(){
    	if(conexao.VerificaBanco()){
    		btnGerarRelatorio.setDisable(false);
    	}else{
    		btnGerarRelatorio.setDisable(true);
    	}
    }
      
    private void exibeNomes(String nome){
    	lblSetNome.setText(nome);
    }
    
    private void configuraBotoes(){
    	btnCadastrarCoord.focusedProperty().addListener(new ChangeListener<Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				BuscaCoordenador();
				BotaoGerarRelatorio();
				
			}
		});
    }
    
}
