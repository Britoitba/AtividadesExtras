package projeto.ae.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import projeto.ae.model.Curso;

public class ConectaCurso extends ConectaDataBase {

	// SALVAR CURSO
	public void salvarCurso(Curso curso){
		try{
			String sql = "INSERT INTO cursos (nome) VALUES (?)";
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(sql);
			salvar.setString(1, curso.getNome());
			salvar.executeUpdate();
			salvar.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO SALVANDO CURSO");
			System.exit(0);
		}
	}
	
	// BUSCAR PARA VER SE TEM CURSO CADASTRADO
	public boolean VerificaBanco(){
		boolean existe = false;
		int valor = 0;
		try{
			String sql = "SELECT nome FROM cursos WHERE id=1";
			Connection con = conexao();
			PreparedStatement verificar = con.prepareStatement(sql);
			ResultSet rs = verificar.executeQuery();
			while(rs.next()){
				valor = 1;
				existe = true;
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO AO VERIFICAR DADOS PARA VERIFICAR CURSOS");
			System.exit(0);
		}
		
		return existe;
	}
}
