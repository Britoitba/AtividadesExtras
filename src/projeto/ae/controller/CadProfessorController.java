package projeto.ae.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import projeto.ae.model.Administrador;
import projeto.ae.model.Professor;
import projeto.ae.service.ConectaAdministrador;
import projeto.ae.service.ConectaProfessor;
import projeto.ae.service.ConectaUsuario;
import projeto.ae.utilitarios.ValidaCampos;
import projeto.ae.view.App;

public class CadProfessorController implements Initializable {
	
	//INSTANCIANDO CLASSES ---------------------------------------------------
	ConectaProfessor conexao = new ConectaProfessor();
	ConectaUsuario conexaoUser = new ConectaUsuario();
	Professor prof = new Professor();
	ValidaCampos validador = new ValidaCampos();
	
	//metodo de inicializar janela--------------------------------------------
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configuraBotoes();
		lblError.setVisible(false);
		lblEmail.setVisible(false);
		lblCpf.setVisible(false);
	}
	
	// FXML ------------------------------------------------------------------
	@FXML
	private PasswordField pwdSenha;
    
    @FXML
    private TextField txtCpf;
    
    @FXML
    private Label lblError;
    
    @FXML
    private Label lblEmail;
    
    @FXML
    private Label lblCpf;

    @FXML
    private TextField txtCampus;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;
    
    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnCadastrar;

    // BOTÕES ----------------------------------------------------------  
    @FXML
    public void onEnter(ActionEvent event){
       if(btnCadastrar.isDisabled() == false){
    	   cadastrar(event);
       }
    }
    
    @FXML
    void cadastrar(ActionEvent event) {
     	int id = 0;
    	int email = 0;
    	int cpf = 0;
    	int duplicidadeCpf = 0;
    	int duplicidadeUsuario = 0;
    	
    	if(validador.verificaNumeros(txtCpf.getText()) && txtCpf.getText().length() == 11){
       		cpf =0;
    		lblCpf.setVisible(false);
    	}else{
    		cpf = 1;
    		lblCpf.setVisible(true);
    		lblError.setVisible(true);
    	}
    	
    	if(validador.verificaEmail(txtEmail.getText())){
    		email =0;
    		lblEmail.setVisible(false);
    	}else{
    		email =1;
    		lblEmail.setVisible(true);
    		lblError.setVisible(true);
    	}
    	
    	if(validador.verificacpf(txtCpf.getText())){
    		duplicidadeCpf = 1;
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Error!");
        	alert.setHeaderText("CPF Existente!");
        	alert.setContentText("CPF já Cadastrado !");
        	alert.showAndWait();
    	}else{
    		duplicidadeCpf = 0;
    	}
    	
    	if(validador.verificaUsuario(txtEmail.getText())){
    		duplicidadeUsuario = 1;
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Error!");
        	alert.setHeaderText("Email Existente!");
        	alert.setContentText("Email já Cadastrado !");
        	alert.showAndWait();
    	}else{
    		duplicidadeUsuario = 0;
    	}
    	
    	if(cpf == 0 && email == 0 && duplicidadeCpf == 0 && duplicidadeUsuario == 0) {
        	prof.setNome(txtNome.getText());
        	prof.setCpf(txtCpf.getText());
        	prof.setCampus(txtCampus.getText());
        	prof.setEmail(txtEmail.getText());
        	prof.setSenha(pwdSenha.getText());
        	prof.setTipoUsuario("professor");
        	prof.setAtivo(1);
        	conexao.salvarProfessor(prof);
        	id = conexao.BuscaUltimoProf();
        	conexaoUser.salvarUsuarioProfessor(id, prof);    	   	
        	
        	
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Success!");
        	alert.setHeaderText(null);
        	alert.setContentText("Cadastrado com Sucesso !");

        	alert.showAndWait();
        	limpar();
        	
        	App.set_pane(App.get_pane(), 2);
    	}
    }

    @FXML
    void cancelar(ActionEvent event) {
    	limpar();
    	App.set_pane(App.get_pane(), 2);
    }
    
    // OUTROS MÉTODOS -----------------------------------------------------
    
    private void limpar(){
    	txtNome.setText("");
    	txtCpf.setText("");
    	txtCampus.setText("");
    	txtEmail.setText("");
    	pwdSenha.setText("");
		lblError.setVisible(false);
		lblEmail.setVisible(false);
		lblCpf.setVisible(false);
    }
    
    private void configuraBotoes(){
    	BooleanBinding camposPreenchidos = (txtNome.textProperty().isEmpty()).or (txtCpf.textProperty().isEmpty()).or
    			(txtCampus.textProperty().isEmpty()).or (txtEmail.textProperty().isEmpty()).or(pwdSenha.textProperty().isEmpty())
                .or(txtCampus.textProperty().isEmpty());
        btnCadastrar.disableProperty().bind(camposPreenchidos);
    }
       
}

