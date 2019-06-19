package projeto.ae.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import projeto.ae.model.Requisicao;
import projeto.ae.service.ConectaAtividades;
import projeto.ae.view.App;

public class HistoricoController implements Initializable{
	
	// INICIANDO A JANELA ---------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		configuraColunas();
		carregaTabela();	
		
	}
	
	// INSTANCIANDO CLASSES -------------------------------------
	ConectaAtividades conexao = new ConectaAtividades();

	// FXML -----------------------------------------------------
    @FXML
    private TableView<Requisicao> tblHistorico;

    @FXML
    private TableColumn<Requisicao, Integer> tcHoras;

    @FXML
    private TableColumn<Requisicao, Integer> tcAno;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<Requisicao, Integer> tcSemestre;

    @FXML
    private TableColumn<Requisicao, String> tcAtividade;

    @FXML
    private TableColumn<Requisicao, Integer> tcID;

    @FXML
    private Label lblHistorico;
    
    @FXML
    private Button btnAtualizar;
    
    // BOTÕES --------------------------------------------------------

    @FXML
    void voltar(ActionEvent event) {
    	
    	limparTabela();
    	
    	App.set_pane(App.get_pane(), 2);
    }
    
    @FXML
    void atualizar(ActionEvent event) {
    	limparTabela();
    	tblHistorico.getItems().addAll(conexao.buscaRequisicoesHistorico());
    }
    
    // OUTOS METODOS -----------------------------------------------------
    
    //LIMPAR TABELA 
    private void limparTabela(){
    	tblHistorico.getItems().clear();
    }
    
    // ORGANIZAR COLUNAS DA TABELA
    private void configuraColunas(){
    	tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
    	tcAtividade.setCellValueFactory(new PropertyValueFactory<>("atividade"));
    	tcHoras.setCellValueFactory(new PropertyValueFactory<>("chValidada"));
       	tcSemestre.setCellValueFactory(new PropertyValueFactory<>("semestre"));
       	tcAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
    }
    
    //CARREGAR DADOS DA TABELA
    private void carregaTabela(){
    	limparTabela();
    	tblHistorico.getItems().addAll(conexao.buscaRequisicoesHistorico());
    	tcID.setText("ID");
    	tcAtividade.setText("Atividade");
    	tcHoras.setText("Horas Validadas");
       	tcSemestre.setText("Semestre");
       	tcAno.setText("Ano");
    	
    }


    
    
    
}
