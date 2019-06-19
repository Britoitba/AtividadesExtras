package projeto.ae.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConectaDuplicidade extends ConectaDataBase{

	// DUPLICIDADE CPF E RA
	public boolean VerificaDuplicidade(String txt){
		boolean existe = false;
		try{
			String sql = "SELECT * FROM alunos WHERE ra ='"+ txt + "'";
			Connection con = conexao();
			PreparedStatement verificar = con.prepareStatement(sql);
			ResultSet rs = verificar.executeQuery();
			while(rs.next()){
				existe = true;
			}
			String sql2 = "SELECT * FROM professores WHERE cpf ='"+ txt + "'";

			PreparedStatement verificar2 = con.prepareStatement(sql2);
			ResultSet rs2 = verificar2.executeQuery();
			while(rs2.next()){
				existe = true;
			}
			String sql3 = "SELECT * FROM coordenadores WHERE cpf ='"+ txt + "'";
			PreparedStatement verificar3 = con.prepareStatement(sql3);
			ResultSet rs3 = verificar3.executeQuery();
			while(rs3.next()){
				existe = true;
			}	
			con.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO AO VERIFICAR CPF OU RA DUPLICADO");
			System.exit(0);
		}
		
		return existe;
	}
	
	// DUPLICIDADE USUARIO
	public boolean VerificaDuplicidadeUsuario(String txt){
		boolean existe = false;
		try{
			String sql = "SELECT * FROM usuarios WHERE email ='"+ txt + "'";
			Connection con = conexao();
			PreparedStatement verificar = con.prepareStatement(sql);
			ResultSet rs = verificar.executeQuery();
			while(rs.next()){
				existe = true;
			}

			con.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO AO VERIFICAR EMAIL DUPLICADO");
			System.exit(0);
		}
		
		return existe;
	}

	// DUPLICIDADE CURSO
	public boolean VerificaDuplicidadeCurso(String txt) {
		boolean existe = false;
		try{
			String sql = "SELECT * FROM cursos WHERE nome ='"+ txt + "'";
			Connection con = conexao();
			PreparedStatement verificar = con.prepareStatement(sql);
			ResultSet rs = verificar.executeQuery();
			while(rs.next()){
				existe = true;
			}

			con.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO AO VERIFICAR CURSO DUPLICADO");
			System.exit(0);
		}
		
		return existe;
	}
}
