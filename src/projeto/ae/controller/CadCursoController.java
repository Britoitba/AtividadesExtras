package projeto.ae.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import projeto.ae.model.Curso;
import projeto.ae.service.ConectaCurso;
import projeto.ae.utilitarios.ValidaCampos;
import projeto.ae.view.App;

public class CadCursoController implements Initializable{
	
	//INICIALIZANDO JANELA -------------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		configuraBotoes();
		
	}
	
	//INSTANCIANDO CLASSES --------------------------------------------
	Curso curso = new Curso();
	ConectaCurso conexao = new ConectaCurso();
	ValidaCampos validador = new ValidaCampos();

	//FXML -----------------------------------------------------------
	
    @FXML
    private TextField txtCurso;

    @FXML
    private Button btnCadastrar;
    
    @FXML
    private Button btnCancelar;

    // BOTÕES ----------------------------------------------------
    @FXML
    public void onEnter(ActionEvent event){
       if(btnCadastrar.isDisabled() == false){
    	   cadastrar(event);
       }
    }
    
    @FXML
    void cadastrar(ActionEvent event) {
    	if(validador.verificaCurso(txtCurso.getText()) == false){
    		curso.setNome(txtCurso.getText());
        	conexao.salvarCurso(curso);	
        	
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Success!");
        	alert.setHeaderText(null);
        	alert.setContentText("Cadastrado com Sucesso !");
        	alert.showAndWait();
        	limpar();
        	App.set_pane(App.get_pane(), 2);
    	}else{
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Error!");
        	alert.setHeaderText("Curso Existente!");
        	alert.setContentText("Curso já Cadastrado !");
        	alert.showAndWait();
    	}
    	
    }
    
    @FXML
    void cancelar(ActionEvent event) {
    	App.set_pane(App.get_pane(), 2);
    }

    // OUTROS MÉTODOS
    private void configuraBotoes(){
    	BooleanBinding camposPreenchidos = txtCurso.textProperty().isEmpty();
    	btnCadastrar.disableProperty().bind(camposPreenchidos);
    }
    
    private void limpar(){
    	txtCurso.setText("");
    }
    
}
