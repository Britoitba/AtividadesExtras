package projeto.ae.utilitarios;

import java.util.ArrayList;

import projeto.ae.service.ConectaComboBox;

public class CarregaComboBox {

	// INSTANCIANDO CLASSES ---------------------------------------------------------------
	ConectaComboBox carregaComboBox = new ConectaComboBox();
	
	
	// CARREGAR COMBOBOX CURSOS ---------------------------------------------------
	public ArrayList cbCursos(){
		ArrayList<String> cursos = new ArrayList<String>();
		carregaComboBox.cbCursos(cursos);
		return cursos;
	}
	
	// CARREGAR COMBOBOX ATIVIDADES ---------------------------------------------------
	public ArrayList cbAtividades(){
		ArrayList<String> atividades = new ArrayList<String>();
		carregaComboBox.cbAtividades(atividades);
		return atividades;
	}
	
	
}
