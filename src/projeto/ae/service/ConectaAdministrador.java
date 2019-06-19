package projeto.ae.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import projeto.ae.model.Administrador;

public class ConectaAdministrador extends ConectaDataBase{
	
	// SALVAR ADM
	public void salvaradm(Administrador adm){
		try{
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(INSERIRADM);
			salvar.setString(1, adm.getNome());
			salvar.executeUpdate();
			salvar.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO SALVANDO ADMINISTRADOR");
			System.exit(0);
		}
	}
	
	// BUSCAR ULTIMO ADM
	public int BuscaUltimoAdm(){
		int id = 0;
		try{
			String sql = "SELECT * from administradores";
			Connection con = conexao();
			PreparedStatement buscar = con.prepareStatement(sql);
			ResultSet rs = buscar.executeQuery();
			while(rs.next()){
				id = id+1;
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO AO BUSCAR ULTIMO ADMIN");
			System.exit(0);
		}
		
		return id;		
	}
	
	// BUSCAR ADMINISTRADOR POR ID
	public Administrador BuscaAdmPorID(int id){
		Administrador admin = new Administrador();
		try{
			String sql = "SELECT * FROM administradores,usuarios WHERE administradores.id=usuarios.idAdmin AND administradores.id="+ id;
			Connection con = conexao();
			PreparedStatement buscar = con.prepareStatement(sql);
			ResultSet rs = buscar.executeQuery();
			while(rs.next()){
				admin.setId(rs.getInt(1));
				admin.setNome(rs.getString(2));
				admin.setEmail(rs.getString(4));
				admin.setAtivo(rs.getInt(7));
			}			
			
			con.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO AO BUSCAR ADMIN POR ID");
			System.exit(0);
		}
		
		return admin;		
	}
	
	
}
