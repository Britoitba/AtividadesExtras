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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import projeto.ae.model.Requisicao;
import projeto.ae.service.ConectaAtividades;
import projeto.ae.view.App;

public class NotificacaoAlunoController implements Initializable{
	
	// INICIALIZANDO A JANELA ----------------------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		configuraColunas();
		carregaTabela();
		configuraBotoes();
		txtAtividade.setEditable(false);
		txtHoras.setEditable(false);
		txtHorasValidadas.setEditable(false);
		txtJustificativa.setEditable(false);
		
	}
	
	// INSTANCIANDO CLASSES ------------------------------------------------------
	
	Requisicao requisicao = new Requisicao();
	ConectaAtividades conexao = new ConectaAtividades();
	
	
	// FXML ---------------------------------------------------------------------
	
    @FXML
    private TableView<Requisicao> tblnotificacoes;

    @FXML
    private Label lblHorasValidadas;

    @FXML
    private Button btnAceitar;

    @FXML
    private Button btnAtualizar;
    
    @FXML
    private TextField txtHorasValidadas;

    @FXML
    private TableColumn<Requisicao, String> tcAtividade;

    @FXML
    private Pane paneNotificacoes;

    @FXML
    private Label lblJustificativa;

    @FXML
    private Button btnRecurso;
    
    @FXML
    private Button btnVoltar;

    @FXML
    private Label lblHoras;

    @FXML
    private Label lblAtividade;

    @FXML
    private TextField txtAtividade;

    @FXML
    private TextArea txtJustificativa;

    @FXML
    private TableColumn<Requisicao, String> tcProfessor;

    @FXML
    private TableColumn<Requisicao, Integer> tcId;

    @FXML
    private TextField txtHoras;
    
    // BOTÕES ---------------------------------------------------------------------

    @FXML
    void aceitar(ActionEvent event) {
    	try{
            if(tblnotificacoes.getSelectionModel().getSelectedItem().getId() != 0){
                conexao.salvarConfirmaAluno(4);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText(null);
                alert.setContentText("Requisição Aceita.");
                alert.showAndWait();
                limpar();
                carregaTabela();
            }
        }catch(Exception e){

        }

    }

    @FXML
    void recurso(ActionEvent event) {
    	try{
            if(tblnotificacoes.getSelectionModel().getSelectedItem().getId() != 0){
            	App.set_pane(12, 4); 
            }
        }catch(Exception e){

        }
    	limpar();
    	limparTabela();
    	
    }
    
    @FXML
    void voltar(ActionEvent event) {
    	App.set_pane(6, 2);
    	limpar();
    	carregaTabela();
    }
    
    @FXML
    void atualizar(ActionEvent event) {
    	limparTabela();
    	limpar();
    	tblnotificacoes.getItems().addAll(conexao.buscaRequisicoes1());
    }

    // OUTROS METODOS ---------------------------------------------------------------

    //EXIBE REQUISICAO SELECIONADA 
    private void exibeRequisicao(){
    	App.idRequisicao = Integer.valueOf(tblnotificacoes.getSelectionModel().getSelectedItem().getId());
    	if(conexao.CoordIsNull(App.idRequisicao)){
    		requisicao = conexao.ExibeRequisicaoAluno(App.idRequisicao, 1);
    		txtAtividade.setText(requisicao.getAtividade());
    		txtHoras.setText(Integer.toString(requisicao.getCargaHoraria()));
    		txtHorasValidadas.setText(Integer.toString(requisicao.getChValidada()));
    		txtJustificativa.setText(requisicao.getParecer());
    	}else{
    		requisicao = conexao.ExibeRequisicaoAluno(App.idRequisicao, 2);
    		txtAtividade.setText(requisicao.getAtividade());
    		txtHoras.setText(Integer.toString(requisicao.getCargaHoraria()));
    		txtHorasValidadas.setText(Integer.toString(requisicao.getChValidada()));
    		txtJustificativa.setText(requisicao.getParecer());
    	}
    }
    
    //LIMPAR TABELA 
    private void limparTabela(){
    	tblnotificacoes.getItems().clear();
    }
    
    // ORGANIZAR COLUNAS DA TABELA
    private void configuraColunas(){
    	tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	tcProfessor.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	tcAtividade.setCellValueFactory(new PropertyValueFactory<>("atividade"));
    }
    
    //CARREGAR DADOS DA TABELA
    private void carregaTabela(){
    	limparTabela();
    	tblnotificacoes.getItems().addAll(conexao.buscaRequisicoes1());
    	tcProfessor.setText("Nome do Professor");
    	tcAtividade.setText("Atividades");
    	
    }
    
    private void configuraBotoes(){   	
    	tblnotificacoes.getSelectionModel().selectedItemProperty().addListener((b, o, n) -> {
    		if(n != null){
    			exibeRequisicao();
    		}
    	});
    	BooleanBinding camposPreenchidos = txtAtividade.textProperty().isEmpty();
    	btnRecurso.disableProperty().bind(camposPreenchidos);
    	btnAceitar.disableProperty().bind(camposPreenchidos);
    }
    
    private void limpar(){
    	txtAtividade.setText("");
    	txtHoras.setText("");
    	txtHorasValidadas.setText("");
    	txtJustificativa.setText("");
    }


}
