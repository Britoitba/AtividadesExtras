package projeto.ae.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import projeto.ae.view.App;

public class VerificaRequisicaoRecursoController {
	// FXML -------------------------------------------------
	

    @FXML
    private Pane pane;
	
    @FXML
    private Button btnRecusar;

    @FXML
    private TextField txtCurso;

    @FXML
    private Label lblHorasValidadas;

    @FXML
    private Button btnAceitar;

    @FXML
    private TextField txtHorasValidadas;

    @FXML
    private Label lblJustificativa;

    @FXML
    private Label lblHoras;

    @FXML
    private Label lblCurso;

    @FXML
    private Label lblAtividade;

    @FXML
    private Label lblRelatorioAluno;

    @FXML
    private TextField txtAtividade;

    @FXML
    private TextArea txtJustificativa;

    @FXML
    private TextField txtAluno;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtHoras;

    @FXML
    private Button btnArquivo;

    @FXML
    private Label lblNome;

    @FXML
    private TextArea txtRelatorio;

    // BOTÕES -------------------------------------------------------------------------------
    @FXML
    void recusar(ActionEvent event) {
    	App.set_pane(App.get_pane(), 4);
    }

    @FXML
    void aceitar(ActionEvent event) {
    	App.set_pane(App.get_pane(), 4);
    }

    @FXML
    void cancelar(ActionEvent event) {
    	App.set_pane(App.get_pane(), 4);
    }

    @FXML
    void baixar(ActionEvent event) {

    }
}
