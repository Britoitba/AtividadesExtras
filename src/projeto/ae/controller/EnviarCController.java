package projeto.ae.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileStore;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileStoreAttributeView;
import java.util.ResourceBundle;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import projeto.ae.model.Requisicao;
import projeto.ae.service.ConectaAtividades;
import projeto.ae.service.ConectaComboBox;
import projeto.ae.utilitarios.CarregaComboBox;
import projeto.ae.utilitarios.ValidaCampos;
import projeto.ae.view.App;

public class EnviarCController implements Initializable{
	
	String nome = "";
	String nomeArquivo = "";
	File arquivo;
	
    InputStream is;
    byte[] bytes;

	
	// INICIALIZANDO JANELA ------------------------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtCategoria.setEditable(false);
		cbAtividades.getItems().addAll(atividades.cbAtividades());
		configuraBotoes();
		lblCH.setVisible(false);
		lblAno.setVisible(false);
		lblSemestre.setVisible(false);
		imgDone.setVisible(false);
		lblError.setVisible(false);
		
	}
	
	// INSTANCIANDO CLASSES -----------------------------------------------------------
	CarregaComboBox atividades = new CarregaComboBox();
	ConectaAtividades conexao = new ConectaAtividades();
	ValidaCampos validador = new ValidaCampos();
	Requisicao requisicao = new Requisicao();
	
	
	// FXML ---------------------------------------------------------------------------
	
    @FXML
    private TextField txtAno;

    @FXML
    private TextField txtLocal;

    @FXML
    private Button btnEnviar;

    @FXML
    private TextField txtSemestre;

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextField txtCargaHoraria;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnAnexar;

    @FXML
    private TextArea txtRelatorio;
    
    @FXML
    private Label lblError;
    
    @FXML
    private Label lblAno;
    
    @FXML
    private Label lblSemestre;
    
    @FXML
    private Label lblCH;

    @FXML
    private ImageView imgDone;
    
    @FXML
    private ImageView imgAlert;    

    @FXML
    private ComboBox<String> cbAtividades;
    
    // BOTÕES -------------------------------------------------------------------

    @FXML
    void enviar(ActionEvent event) {
    	int ch = 0;
    	int semestre = 0;
    	int ano = 0;
    	
    	if(validador.verificaNumeros(txtCargaHoraria.getText())){
       		ch =0;
    		lblCH.setVisible(false);
    	}else{
    		ch = 1;
    		lblCH.setVisible(true);
    		lblError.setVisible(true);
    	}
    	
    	if(validador.verificaNumeros(txtSemestre.getText())){
       		semestre =0;
    		lblSemestre.setVisible(false);
    	}else{
    		semestre = 1;
    		lblSemestre.setVisible(true);
    		lblError.setVisible(true);
    	}
    	
    	if(validador.verificaNumeros(txtAno.getText())){
       		ano =0;
    		lblAno.setVisible(false);
    	}else{
    		ano = 1;
    		lblAno.setVisible(true);
    		lblError.setVisible(true);
    	}
    		
    	if(ch == 0 && semestre == 0 && ano == 0){
    		lblError.setVisible(false);
    		if(imgDone.isVisible()){
    			requisicao.setAtividade(cbAtividades.getSelectionModel().getSelectedItem().toString());
    			requisicao.setModalidade(txtCategoria.getText());
    			requisicao.setCargaHoraria(Integer.parseInt(txtCargaHoraria.getText()));
    			requisicao.setLocal(txtLocal.getText());
    			requisicao.setComprovante(bytes);
    			requisicao.setComprovanteName(nomeArquivo);
    			requisicao.setSemestre(Integer.parseInt(txtSemestre.getText()));
    			requisicao.setAno(Integer.parseInt(txtAno.getText()));
    			requisicao.setRelatorio(txtRelatorio.getText());
    			requisicao.setIdAluno(App.idUser);
    			
    			conexao.salvarRequisicao(requisicao);  
    			
        		Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle("Success!");
            	alert.setHeaderText(null);
            	alert.setContentText("Enviado com Sucesso !");
            	alert.showAndWait();
    			
    			limpar();
            	App.set_pane(App.get_pane(), 2);
    		}else{
    			Alert alert = new Alert(AlertType.WARNING);
            	alert.setTitle("Atenção!");
            	alert.setHeaderText(null);
            	alert.setContentText("Anexo invalido ou inexistente !");
            	alert.showAndWait();
    		}
        	
    	}

    }

    @FXML
    void cancelar(ActionEvent event) {
    	limpar();
    	App.set_pane(App.get_pane(), 2);
    }
    
    @FXML
    void PegaTexto(ActionEvent event) {
    	nome = cbAtividades.getSelectionModel().getSelectedItem().toString();
    	txtCategoria.setText(conexao.buscaModalidade(nome));
    }
    
    @FXML
    void anexar(ActionEvent event) throws IOException {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Selecione um Arquivo");
    	fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
    	arquivo = fileChooser.showOpenDialog(null);
    	nomeArquivo = arquivo.getName();
    	    	
    	if(arquivo != null && validador.verificaExtensao(nomeArquivo)){
        	try {
    			is = new FileInputStream( arquivo );
    			bytes = new byte[(int)arquivo.length() ];
    	        int offset = 0;
    	        int numRead = 0;
    	        while (offset < bytes.length
    	               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
    	            offset += numRead;
    	        }
    	
    		} catch (FileNotFoundException e) {
    			System.out.println("ERRO CONVERTENDO ARQUIVO PRA BYTES");
    			e.printStackTrace();
    		}
	
    		imgDone.setVisible(true);
    		imgAlert.setVisible(false);
    	}else{
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("Formato Inválido !");
    		alert.setContentText("Por favor, Envie Somente Arquivo com Extensão PDF.");
    		alert.showAndWait();
    		imgDone.setVisible(false);
    		imgAlert.setVisible(true);
    	}
    }
    
    // OUTROS MÉTODOS ------------------------------------------------------------

    private void configuraBotoes(){
    	BooleanBinding camposPreenchidos = txtSemestre.textProperty().isEmpty().or(txtCategoria.textProperty().isEmpty()).or(txtCargaHoraria.textProperty().isEmpty()).or(txtRelatorio.textProperty().isEmpty());
    	btnEnviar.disableProperty().bind(camposPreenchidos);
    	
    }
    
    
    private void limpar(){
    	cbAtividades.setValue("Selecione Uma Atividade");
    	txtCategoria.setText("");
    	txtCargaHoraria.setText("");
    	txtRelatorio.setText("");
    	txtCategoria.setText("");
    	txtSemestre.setText("");
    	txtAno.setText("");		
    	txtLocal.setText("");
    	lblCH.setVisible(false);
		lblAno.setVisible(false);
		lblSemestre.setVisible(false);
		lblError.setVisible(false);
		imgDone.setVisible(false);
		imgAlert.setVisible(true);    	
    }

}
