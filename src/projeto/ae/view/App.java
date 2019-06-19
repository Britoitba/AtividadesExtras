package projeto.ae.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import projeto.ae.service.ConectaPrimeiroAcesso;

public class App extends Application{

	static AnchorPane root;
	static List<GridPane> grid = new ArrayList<GridPane>();
	static int viewAnterior = 0;
	static int viewAtual = 0;
	static int principal = 0;
	public static int idUser = 0;
	public static String TipoUser = "";
	public static int idRequisicao = 0 ;
	
	
	//INSTANCIANDO CLASSE DE VERIFICAÇÃO DE INICIO DE SISTEMA --------------------------------
	ConectaPrimeiroAcesso inicio = new ConectaPrimeiroAcesso();
	
	
	// MAIN -------------------------------------------------------------------------------------
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try{
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("anchor.fxml"));
			
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("LoginView.fxml")));
			
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("CadAdminView.fxml")));			//1
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("CadAlunoView.fxml")));
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("CadProfessorView.fxml")));
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("CadCoordenadorView.fxml")));		//4
			
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("PrincipAdminView.fxml")));		//5
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("PrincipAlunoView.fxml")));
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("PrincipProfessorView.fxml")));
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("PrincipCoordenadorView.fxml")));	//8
			
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("AtivarAlunoView.fxml")));		//9
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("EnviarCView.fxml")));
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("NotificacaoAlunoView.fxml")));
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("SolicitaRecursoView.fxml")));
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("HistoricoView.fxml")));			//13
			
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("RecursosView.fxml")));	//14
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("RecursosView.fxml")));
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("RequisicoesView.fxml")));				//16
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("CadCursoView.fxml")));				//17
			
			iniciaSistema(primaryStage);
		}catch(Exception e){
			e.printStackTrace();
		}
			
	}
	
	
	// BUSCAR POSIÇÃO DA GRID ------------------------------------------------------------------
	public static int get_pane(){
		return viewAnterior;		
	}
	
	// REMOVER PANE ANTIGA E MUDAR PARA PANE DESEJADA ----------------------------------------------
	public static void set_pane(int index, int valor){
		if(valor == 0){ 						// ADICIONAR JANELA SECUNDARIA
			root.getChildren().add(grid.get(index));
			viewAnterior = viewAtual;
			viewAtual = index;
		}else if(valor == 2){ 					// CANCELAR E VOLTAR PARA A TELA PRINCIPAL
			root.getChildren().remove(grid.get(viewAtual));
		}else if(valor == 3){ 					// FECHAR JANELA PRINCIPAL -- BOTÃO SAIR
			root.getChildren().remove(grid.get(viewAtual));
			root.getChildren().remove(grid.get(principal));
			root.getChildren().add(grid.get(index));
			viewAnterior = viewAtual;
			viewAtual = index;
		}else if(valor == 4){					// FECHAR JANELA SECUNDARIA
			root.getChildren().remove(grid.get(viewAtual));
			root.getChildren().add(grid.get(index));
			viewAnterior = viewAtual;
			viewAtual = index;	
			
		}else{									// PRIMEIRA INICIALIZAÇÃO DA JANELA PRINCIPAL -- FAZER LOGIN
			principal = index;
			root.getChildren().remove(grid.get(viewAtual));
			root.getChildren().add(grid.get(index));
			viewAnterior = viewAtual;
			viewAtual = index;
		}

	}	
	
	// VERIFICAÇÃO PARA INICIAR SISTEMA ----------------------------------------------------------------
	public void iniciaSistema(Stage primaryStage){
		try{
			if(inicio.VerificaBanco()){
				root.getChildren().add(grid.get(0));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.show();
			}else{
				viewAtual = 1;
				root.getChildren().add(grid.get(1));
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.show();

			}
		}catch(Exception e){
			
		}

	}

}
