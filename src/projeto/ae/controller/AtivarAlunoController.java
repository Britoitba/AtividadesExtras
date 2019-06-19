package projeto.ae.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import projeto.ae.model.Aluno;
import projeto.ae.service.ConectaAluno;
import projeto.ae.view.App;

public class AtivarAlunoController implements Initializable{
	
	// INICIALIZANDO A JANELA -----------------------------------------------
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		configuraColunas();
		carregaTabela();
		//configuraBotoes();
		
	}
	
	// INSTANCIANDO CLASSES --------------------------------------------------
	ConectaAluno conectaBanco = new ConectaAluno();
	
	
	// FXML -----------------------------------------------------------------
	
    @FXML
    private Button btnRecusar;
    
    @FXML
    private Button btnAtualizar;

    @FXML
    private TableColumn<Aluno, String> tcCurso;

    @FXML
    private TableColumn<Aluno, String> tcAluno;

    @FXML
    private TableColumn<Aluno, String> tcRa;
    
    @FXML
    private TableColumn<Aluno, Integer> tcId;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnAtivar;

    @FXML
    private TableView<Aluno> tblAlunos;	
	
	// BOTÕES -----------------------------------------------------------------
    @FXML
    void cancelar(ActionEvent event) {
    	App.set_pane(App.get_pane(), 2);
    }

    @FXML
    void recusar(ActionEvent event) {
    	try{
            int id = tblAlunos.getSelectionModel().getSelectedItem().getId();
            conectaBanco.recusarAluno(id);
            carregaTabela();
        }catch(Exception e){

        }
    }

    @FXML
    void ativar(ActionEvent event) {
    	try{
            int id = tblAlunos.getSelectionModel().getSelectedItem().getId();
            conectaBanco.ativarAluno(id);
            carregaTabela();
        }catch(Exception e){

        }
    }
    
    @FXML
    void atualizar(ActionEvent event) {
    	carregaTabela();
    }
    
    // OUTROS MÉTODOS -----------------------------------------------------------
    
    // ORGANIZAR COLUNAS DA TABELA
    private void configuraColunas(){
    	tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	tcAluno.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	tcRa.setCellValueFactory(new PropertyValueFactory<>("ra"));
    	tcCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
    }
    
    //CARREGAR DADOS DA TABELA
    private void carregaTabela(){
    	limparTabela();
    	tblAlunos.getItems().addAll(conectaBanco.buscaAlunos());
    	tcAluno.setText("Nome do Aluno");
    	tcRa.setText("RA");
    	tcCurso.setText("Curso");
    	
    }
    
    //LIMPAR TABELA 
    private void limparTabela(){
    	tblAlunos.getItems().clear();
    }
    
    //CONFIGURAR CARREGAMENTO DE TABELA "GAMBIARRA" ----------------
    private void configuraBotoes(){
    	
    }

}
