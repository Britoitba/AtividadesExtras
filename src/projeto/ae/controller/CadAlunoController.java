package projeto.ae.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import projeto.ae.model.Aluno;
import projeto.ae.service.ConectaAluno;
import projeto.ae.service.ConectaDuplicidade;
import projeto.ae.service.ConectaUsuario;
import projeto.ae.utilitarios.CarregaComboBox;
import projeto.ae.utilitarios.ValidaCampos;
import projeto.ae.view.App;

public class CadAlunoController implements Initializable{
	
	// INSTANCIANDO CLASSES ---------------------------------------------
	CarregaComboBox cursos = new CarregaComboBox();
	ValidaCampos validador = new ValidaCampos();
	Aluno aluno = new Aluno();
	ConectaAluno conexao = new ConectaAluno();
	ConectaUsuario conectaUser = new ConectaUsuario();
		
	
	// INICIALIZANDO JANELA -------------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbCurso.getItems().addAll(cursos.cbCursos());
		cbCurso.setValue("Selecione Seu Curso");
		configuraBotoes();
		lblError.setVisible(false);
		lblEmail.setVisible(false);
		lblCurso.setVisible(false);
		lblRA.setVisible(false);
		
	}
	
	// FXML --------------------------------------------------------
    @FXML
    private Label lblError;
    
    @FXML
    private Label lblEmail;
    
    @FXML
    private Label lblCurso;

    @FXML
    private Label lblRA;
    
    @FXML
    private PasswordField pwdSenha;

    @FXML
    private TextField txtRa;

    @FXML
    private ComboBox<String> cbCurso;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtCampus;
    
    @FXML
    private Button btnCadastrar;
    
    @FXML
    private Button btnCancelar;

    
    // BOTÕES --------------------------------------------------------
    @FXML
    public void onEnter(ActionEvent event){
       if(btnCadastrar.isDisabled() == false){
    	   cadastrar(event);
       }
    }
    
    public void carregarCursos(){
    	cbCurso.getItems().clear();
    	cbCurso.getItems().addAll(cursos.cbCursos());
    }
    
    @FXML
    void cadastrar(ActionEvent event) {
    	int email = 0;
    	int ra = 0;
    	int curso = 0;
    	int duplicidadeCpf = 0;
    	int duplicidadeUsuario = 0;
    	
    	if(validador.verificaNumeros(txtRa.getText()) && txtRa.getText().length() == 12){
       		ra =0;
    		lblRA.setVisible(false);
    	}else{
    		ra = 1;
    		lblRA.setVisible(true);
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
    	
    	if(cbCurso.getSelectionModel().getSelectedItem().toString().equals("Selecione Seu Curso")){
    		curso = 1;
    		lblCurso.setVisible(true);
    		lblError.setVisible(true);
    	}else{
    		curso = 0;    		
    		lblCurso.setVisible(false);
    	}
    	
    	if(validador.verificacpf(txtRa.getText())){
    		duplicidadeCpf = 1;
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Error!");
        	alert.setHeaderText("RA Existente!");
        	alert.setContentText("RA já Cadastrado !");
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
    	    	
    	
    	if(ra == 0 && email == 0 && curso == 0 && duplicidadeCpf == 0 && duplicidadeUsuario == 0){
    		int id = 0;
    		lblError.setVisible(false);
    		aluno.setNome(txtNome.getText());
    		aluno.setCurso(cbCurso.getSelectionModel().getSelectedItem());
    		aluno.setRa(txtRa.getText());
    		aluno.setCampus(txtCampus.getText());
    		aluno.setEmail(txtEmail.getText());
    		aluno.setSenha(pwdSenha.getText());
    		aluno.setTipoUsuario("aluno");
    		aluno.setAtivo(0);
    		conexao.salvarAluno(aluno);
    		id = conexao.buscaUltimoAluno();
    		conectaUser.salvarUsuarioAluno(id, aluno);  	
    
    
    		Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Success!");
        	alert.setHeaderText(null);
        	alert.setContentText("Cadastrado com Sucesso !");
        	alert.showAndWait();
        	limpar();
        	App.set_pane(0, 1);
    	}
    	
    }

    @FXML
    void cancelar(ActionEvent event) {
    	limpar();
    	App.set_pane(0, 1);
    }
    
    // OUTROS METODOS ---------------------------------------------------------
    
    private void configuraBotoes(){
    	BooleanBinding camposPreenchidos = txtEmail.textProperty().isEmpty().or(pwdSenha.textProperty().isEmpty()).or(txtRa.textProperty().isEmpty()).or(txtNome.textProperty().isEmpty())
    			.or(txtCampus.textProperty().isEmpty());
    	btnCadastrar.disableProperty().bind(camposPreenchidos);
    	
    	txtNome.focusedProperty().addListener(new ChangeListener<Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				carregarCursos();
			}
		});
    }
    
    private void limpar(){
    	txtNome.setText("");
    	cbCurso.setValue("Selecione Seu Curso");
    	txtRa.setText("");
    	txtCampus.setText("");
    	txtEmail.setText("");
    	pwdSenha.setText("");
		lblError.setVisible(false);
		lblEmail.setVisible(false);
		lblCurso.setVisible(false);
		lblRA.setVisible(false);
    }

}
