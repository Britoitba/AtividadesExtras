package projeto.ae.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import projeto.ae.model.Aluno;
import projeto.ae.model.Professor;

public class ConectaProfessor extends ConectaDataBase {
	// SALVAR PROFESSOR
		public void salvarProfessor(Professor prof){
			try{
				Connection con = conexao();
				PreparedStatement salvar = con.prepareStatement(INSERIRPROFESSOR);
				
				salvar.setString(1, prof.getNome());
				salvar.setString(2, prof.getCpf());
				salvar.setString(3, prof.getCampus());
				
				salvar.executeUpdate();
				salvar.close();
				
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("ERRO SALVANDO PROFESSOR");
				System.exit(0);
			}
		}
		
		// BUSCAR ULTIMO PROFESSOR
		public int BuscaUltimoProf(){
			int id = 0;
			try{
				String sql = "SELECT * from professores";
				Connection con = conexao();
				PreparedStatement buscar = con.prepareStatement(sql);
				ResultSet rs = buscar.executeQuery();
				while(rs.next()){
					id = id+1;
				}
				con.close();
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("ERRO AO BUSCAR ULTIMO PROFESSOR");
				System.exit(0);
			}
			
			return id;		
		}
		
		//BUSCA PROFESSOR POR ID-----------------------------------------------------------------
		public Professor BuscaProfessorPorId(int id){
			Professor professor = new Professor();
			try{
				String sql = "SELECT * from professores,usuarios where professores.id=usuarios.idProfessor and professores.id=" + id;
				Connection con = conexao();
				PreparedStatement buscar = con.prepareStatement(sql);
				ResultSet rs = buscar.executeQuery();
				while(rs.next()){
					professor.setId(rs.getInt(1));
					professor.setNome(rs.getString(2));
					professor.setCpf(rs.getString(4));
					professor.setCampus(rs.getString(5));
					professor.setEmail(rs.getString(7));
					professor.setAtivo(rs.getInt(9));
				}
				
				
				
				con.close();
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("ERRO AO BUSCAR PROFESSOR");
				System.exit(0);
			}
			
			return professor;		
		}

}
