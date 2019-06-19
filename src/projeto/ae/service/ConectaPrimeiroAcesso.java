package projeto.ae.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConectaPrimeiroAcesso extends ConectaDataBase {
	
	// BUSCAR PARA PRIMEIRO ACESSO
	public boolean VerificaBanco(){
		boolean existe = false;
		int valor = 0;
		try{
			String sql = "SELECT nome FROM administradores WHERE id=1";
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
			System.out.println("ERRO AO VERIFICAR DADOS PARA PRIMEIRO ACESSO");
			System.exit(0);
		}
		
		return existe;
	}
	
}
