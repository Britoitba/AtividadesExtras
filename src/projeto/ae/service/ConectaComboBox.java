package projeto.ae.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ConectaComboBox extends ConectaDataBase{
	
	// ALIMENTAR CURSOS ------------------------------------------------------
	public ArrayList cbCursos(ArrayList<String> arrayCursos){
		try{
			String sql = "SELECT nome FROM cursos ORDER BY nome ASC";
			Connection con = conexao();
			PreparedStatement buscar = con.prepareStatement(sql);
			ResultSet rs = buscar.executeQuery();
			while(rs.next()){
				arrayCursos.add(rs.getString("nome"));	
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO AO ALIMENTAR CURSOS");
			System.exit(0);
		}
		return arrayCursos;		
	}
	
	// ALIMENTAR ATIVIDADES EXTRAS -------------------------------------------
	public ArrayList cbAtividades(ArrayList<String> arrayAtividades){
		try{
			String sql = "SELECT nome FROM atividades ORDER BY nome ASC";
			Connection con = conexao();
			PreparedStatement buscar = con.prepareStatement(sql);
			ResultSet rs = buscar.executeQuery();
			while(rs.next()){
				arrayAtividades.add(rs.getString("nome"));	
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO AO ALIMENTAR ATIVIDADES");
			System.exit(0);
		}
		return arrayAtividades;		
	}
	
}
