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
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert.AlertType;
import projeto.ae.model.Coordenador;
import projeto.ae.service.ConectaCoordenador;
import projeto.ae.service.ConectaUsuario;
import projeto.ae.utilitarios.CarregaComboBox;
import projeto.ae.utilitarios.ValidaCampos;
import projeto.ae.view.App;

public class CadCoordenadorController implements Initializable{
	
	// INSTANCIANDO MÉTODOS ---------------------------------------------
	Coordenador coord = new Coordenador();
	ConectaCoordenador conexao = new ConectaCoordenador();
	CarregaComboBox cursos = new CarregaComboBox();
	ValidaCampos validador = new ValidaCampos();
	ConectaUsuario conectaUser = new ConectaUsuario();
	

	// INICIALIZANDO A JANELA ------------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		configuraBotoes();
		cbCurso.getItems().addAll(cursos.cbCursos());
		cbCurso.setValue("Selecione Seu Curso");
		lblError.setVisible(false);
		lblEmail.setVisible(false);
		lblCurso.setVisible(false);
		lblCpf.setVisible(false);
		
	}
	
	// FXML ----------------------------------------------------
    @FXML
    private Label lblCpf;
    
    @FXML
    private Label lblCurso;
    
    @FXML
    private Label lblEmail;
    
    @FXML
    private Label lblError;
    
    @FXML
    private PasswordField pwdSenha;
    
    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtCampus;

    @FXML
    private ComboBox<String> cbCurso;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;
    
    @FXML
    private Button btnCadastrar;
    
    @FXML
    private Button btnCancelar;
    
    
    // BOTÕES -------------------------------------------------------
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
    	int cpf = 0;
    	int curso = 0;
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
    	
    	if(cbCurso.getSelectionModel().getSelectedItem().toString().equals("Selecione Seu Curso")){
    		curso = 1;
    		lblCurso.setVisible(true);
    		lblError.setVisible(true);
    	}else{
    		curso = 0;    		
    		lblCurso.setVisible(false);
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
    	    	
    	
    	if(cpf == 0 && email == 0 && curso == 0 && duplicidadeCpf == 0 && duplicidadeUsuario == 0){
    		int id = 0;
    		lblError.setVisible(false);
    		coord.setNome(txtNome.getText());
    		coord.setCurso(cbCurso.getSelectionModel().getSelectedItem());
    		coord.setCpf(txtCpf.getText());
    		coord.setCampus(txtCampus.getText());
    		coord.setEmail(txtEmail.getText());
    		coord.setSenha(pwdSenha.getText());
    		coord.setTipoUsuario("coordenador");
    		coord.setAtivo(1);
    		conexao.salvarCoordenador(coord);
    		id = conexao.BuscaUltimoCoor();
    		conectaUser.salvarUsuarioCoordenador(id, coord);  	
    
    
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
    
    @FXML
    void newButtonClick(ActionEvent event) {
		cbCurso.getItems().addAll(cursos.cbCursos());
    }
    
    // OUTROS METODOS ---------------------------------------------------------------

    private void configuraBotoes(){
    	BooleanBinding camposPreenchidos = txtEmail.textProperty().isEmpty().or(pwdSenha.textProperty().isEmpty()).or(txtCpf.textProperty().isEmpty()).or(txtNome.textProperty().isEmpty())
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
    	txtCpf.setText("");
    	txtCampus.setText("");
    	txtEmail.setText("");
    	pwdSenha.setText("");
		lblError.setVisible(false);
		lblEmail.setVisible(false);
		lblCurso.setVisible(false);
		lblCpf.setVisible(false);
    }

}
