package projeto.ae.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.swing.filechooser.FileSystemView;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import projeto.ae.model.Aluno;
import projeto.ae.model.Requisicao;
import projeto.ae.service.ConectaAtividades;
import projeto.ae.utilitarios.ValidaCampos;
import projeto.ae.view.App;

public class RequisicoesController implements Initializable{
	// INICIALIZANDO JANELA ---------------------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		configuraBotoes();
		configuraColunas();
		carregaTabela();
		pane.setVisible(false);
    	txtAluno.setEditable(false);
    	txtCurso.setEditable(false);
    	txtAtividade.setEditable(false);
    	txtHoras.setEditable(false);
    	txtRelatorio.setEditable(false);
    	txtHorasValidadas.setPromptText("");
		
	}
	
	// INSTANCIANDO CLASSES ----------------------------------------------------
	ConectaAtividades conectaBanco = new ConectaAtividades();
	Requisicao requisicao = new Requisicao();
	ValidaCampos validador = new ValidaCampos();
	File file;
	
	
	// FXML ---------------------------------------------------------------------
   
    @FXML
    private TableView<Requisicao> tblRequisicoes;

    @FXML
    private TableColumn<Requisicao, String> tcCurso;

    @FXML
    private TableColumn<Requisicao, String> tcAluno;

    @FXML
    private TableColumn<Requisicao, String> tcAtividade;

    @FXML
    private Pane paneNotificacoes;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnDetalhar;

    @FXML
    private TableColumn<Requisicao, Integer> tcId;
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
   
    // BOTÕES -------------------------------------------------------------

    @FXML
    void detalhar(ActionEvent event) {    	
    	
    	requisicao = conectaBanco.buscaRequisicoesId(tblRequisicoes.getSelectionModel().getSelectedItem().getId());
    	
    	txtAluno.setText(requisicao.getNome());
    	txtCurso.setText(requisicao.getCurso());
    	txtAtividade.setText(requisicao.getAtividade());
    	txtHoras.setText(String.valueOf(requisicao.getCargaHoraria()));
    	txtRelatorio.setText(requisicao.getRelatorio());
    	    	
    	pane.setVisible(true);
    
    }

    @FXML
    void voltar(ActionEvent event) {
    	if(App.TipoUser.equals("coordenador")){
    		App.set_pane(8, 2);
    	}else{
    		App.set_pane(7, 2);
    	}
    }
    
    @FXML
    void atualizar(ActionEvent event) {
    	limparTabela();
    	tblRequisicoes.getItems().addAll(conectaBanco.buscaRequisicoes());
    }
    
  //BOTÔES SEGUNDA PANE---------------------------------------------------------------
    @FXML
    void recusar(ActionEvent event) {
    	
		if(validador.verificaNumeros(txtHorasValidadas.getText())){
			
	    	requisicao.setParecer(txtJustificativa.getText());
	    	requisicao.setStatus(4);
	    	requisicao.setChValidada(0);
	    	if(App.TipoUser.equals("professor")){
	    		requisicao.setIdProfessor(App.idUser);
			}else{
				requisicao.setIdCoordenador(App.idUser);
			}
			
			conectaBanco.salvarParecer(requisicao);
			
			Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Success!");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Requisição Recusada.");
	    	alert.showAndWait();
	    	
	    	carregaTabela();
	    	limparSegundaPane();
	    	pane.setVisible(false);
		}else{
			Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Atenção!");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Digite Somente Numeros no Campo: ' Horas Validadas ' ");
	    	alert.showAndWait();
		}
    }

    @FXML
    void aceitar(ActionEvent event) {
		
		if(validador.verificaNumeros(txtHorasValidadas.getText())){
			
	    	requisicao.setChValidada(Integer.parseInt(txtHorasValidadas.getText()));
	    	requisicao.setParecer(txtJustificativa.getText());
	    	requisicao.setStatus(1);	
	    	if(App.TipoUser.equals("professor")){
	    		requisicao.setIdProfessor(App.idUser);
			}else{
				requisicao.setIdCoordenador(App.idUser);
			}
			
			conectaBanco.salvarParecer(requisicao);
			
			Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Success!");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Requisição Aceita.");
	    	alert.showAndWait();
	    	
	    	carregaTabela();
	    	limparSegundaPane();
	    	pane.setVisible(false);
		}else{
			Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Atenção!");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Digite Somente Numeros no Campo: ' Horas Validadas ' ");
	    	alert.showAndWait();
		}
    }

    @FXML
    void cancelar(ActionEvent event) {
    	//App.set_pane(App.get_pane(), 4);
    	limparSegundaPane();
    	pane.setVisible(false);
    }

    @FXML
    void baixar(ActionEvent event) throws IOException {
	    try {
	    	requisicao = conectaBanco.DownloadCertificado(requisicao);
	    	FileSystemView desktop = FileSystemView.getFileSystemView();
	        file = new File( desktop.getHomeDirectory().getPath() + "\\" + requisicao.getComprovanteName());
	        FileOutputStream fos = new FileOutputStream(file);
	        fos.write( requisicao.getComprovante() );
	        fos.close();
        
			Desktop.getDesktop().open(new File(desktop.getHomeDirectory().getPath() + "\\" + requisicao.getComprovanteName()));
		} catch (IOException e) {
			System.out.println();
			e.printStackTrace();
		}
    	
    	
    }
    
    // OUTROS MÉTODOS ----------------------------------------------------------
    
    //LIMPAR TABELA 
    private void limparTabela(){
    	tblRequisicoes.getItems().clear();
    }
    
    //LIMPAR SEGUNDA PANE
    private void limparSegundaPane(){
    	txtAluno.setText("");
    	txtCurso.setText("");
    	txtAtividade.setText("");
    	txtHoras.setText("");
    	txtRelatorio.setText("");
    	txtJustificativa.setText("");
    	txtHorasValidadas.setText("");
    	
    }
    
    // ORGANIZAR COLUNAS DA TABELA
    private void configuraColunas(){
    	tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	tcAluno.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	tcAtividade.setCellValueFactory(new PropertyValueFactory<>("atividade"));
    	tcCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
    }
    
    //CARREGAR DADOS DA TABELA
    private void carregaTabela(){
    	limparTabela();
    	tblRequisicoes.getItems().addAll(conectaBanco.buscaRequisicoes());
    	tcAluno.setText("Nome do Aluno");
    	tcAtividade.setText("Atividades");
    	tcCurso.setText("Curso");
    	
    }
    
    private void configuraBotoes(){
    	BooleanBinding camposPreenchidos = txtJustificativa.textProperty().isEmpty().or(txtHorasValidadas.textProperty().isEmpty());
    	btnAceitar.disableProperty().bind(camposPreenchidos);
    	btnRecusar.disableProperty().bind(camposPreenchidos);
    	
    	BooleanBinding algoSelecionado = tblRequisicoes.getSelectionModel().selectedItemProperty().isNull();
    	
    	btnDetalhar.disableProperty().bind(algoSelecionado);
    }

    
}
