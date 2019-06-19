package projeto.ae.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import projeto.ae.model.Aluno;
import projeto.ae.model.Coordenador;

public class ConectaCoordenador extends ConectaDataBase {
	
	// SALVAR COORDENADOR
	public void salvarCoordenador(Coordenador coor){
		try{
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(INSERIRCOORDENADOR);
			
			salvar.setString(1, coor.getNome());
			salvar.setString(2, coor.getCurso());
			salvar.setString(3, coor.getCpf());
			salvar.setString(4, coor.getCampus());
			
			salvar.executeUpdate();
			salvar.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO SALVANDO COORDENADOR");
			System.exit(0);
		}
	}
	
	// BUSCAR ULTIMO COORDENADOR
	public int BuscaUltimoCoor(){
		int id = 0;
		try{
			String sql = "SELECT * from coordenadores";
			Connection con = conexao();
			PreparedStatement buscar = con.prepareStatement(sql);
			ResultSet rs = buscar.executeQuery();
			while(rs.next()){
				id = id+1;
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO AO BUSCAR ULTIMO COORDENADOR");
			System.exit(0);
		}
		
		return id;		
	}
	
	
	//BUSCA COORDENADOR POR ID---------------------------------------------------------------
	public Coordenador BuscaCoordenadorPorId(int id){
		Coordenador coordenador = new Coordenador();
		try{
			String sql = "SELECT * from coordenadores,usuarios where coordenadores.id=usuarios.idCoordenador and coordenadores.id=" + id;
			Connection con = conexao();
			PreparedStatement buscar = con.prepareStatement(sql);
			ResultSet rs = buscar.executeQuery();
			while(rs.next()){
				coordenador.setId(rs.getInt(1));
				coordenador.setNome(rs.getString(2));
				coordenador.setCurso(rs.getString(3));
				coordenador.setCpf(rs.getString(4));				
				coordenador.setCampus(rs.getString(5));
				coordenador.setEmail(rs.getString(7));
				coordenador.setAtivo(rs.getInt(10));
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO AO BUSCAR COORDENADOR");
			System.exit(0);
		}
		
		return coordenador;		
	}
			

}
