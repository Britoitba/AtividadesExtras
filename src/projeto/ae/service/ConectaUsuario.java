package projeto.ae.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import projeto.ae.model.Administrador;
import projeto.ae.model.Aluno;
import projeto.ae.model.Coordenador;
import projeto.ae.model.Professor;
import projeto.ae.model.Usuario;

public class ConectaUsuario extends ConectaDataBase{

	// SALVAR USUARIO DO ADMINISTRADOR ---------------------------------------------
	public void salvarUsuarioAdmin(int id, Administrador adm){
		try{
			String sql = "INSERT INTO usuarios (email, senha, tipoUsuario, ativo, idAdmin) VALUES (?,?,?,?,?)";
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(sql);
			salvar.setString(1, adm.getEmail());
			salvar.setString(2, adm.getSenha());
			salvar.setString(3, adm.getTipoUsuario());
			salvar.setInt(4, adm.getAtivo());
			salvar.setInt(5, id);
			salvar.executeUpdate();
			salvar.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO SALVANDO USUARIO DO ADMINISTRADOR");
			System.exit(0);
		}

	}
	
	// SALVAR USUARIO DO ALUNO ---------------------------------------------
	public void salvarUsuarioAluno(int id, Aluno aluno){
		try{
			String sql = "INSERT INTO usuarios (email, senha, tipoUsuario, ativo, idAluno) VALUES (?,?,?,?,?)";
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(sql);
			salvar.setString(1, aluno.getEmail());
			salvar.setString(2, aluno.getSenha());
			salvar.setString(3, aluno.getTipoUsuario());
			salvar.setInt(4, aluno.getAtivo());
			salvar.setInt(5, id);
			salvar.executeUpdate();
			salvar.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO SALVANDO USUARIO DO ALUNO");
			System.exit(0);
		}

	}
	
	// SALVAR USUARIO DO COORDENADOR---------------------------------------
	public void salvarUsuarioCoordenador(int id, Coordenador coor){
		try{
			String sql = "INSERT INTO usuarios (email, senha, tipoUsuario, ativo, idCoordenador) VALUES (?,?,?,?,?)";
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(sql);
			salvar.setString(1, coor.getEmail());
			salvar.setString(2, coor.getSenha());
			salvar.setString(3, coor.getTipoUsuario());
			salvar.setInt(4, coor.getAtivo());
			salvar.setInt(5, id);
			salvar.executeUpdate();
			salvar.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO SALVANDO USUARIO DO COORDENADOR");
			System.exit(0);
		}

	}
		
	// SALVAR USUARIO DO PROFESSOR-----------------------------------------
	public void salvarUsuarioProfessor(int id, Professor prof){
		try{
			String sql = "INSERT INTO usuarios (email, senha, tipoUsuario, ativo, idProfessor) VALUES (?,?,?,?,?)";
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(sql);
			salvar.setString(1, prof.getEmail());
			salvar.setString(2, prof.getSenha());
			salvar.setString(3, prof.getTipoUsuario());
			salvar.setInt(4, prof.getAtivo());
			salvar.setInt(5, id);
			salvar.executeUpdate();
			salvar.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO SALVANDO USUARIO DO PROFESSOR");
			System.exit(0);
		}

	}
	
	// VERIFICAR TIPO DE USUARIO AO FAZER LOGIN
	public Usuario verificarLogin(String email, String senha){
		Usuario user = new Usuario();
		try{			
			String sql = "SELECT * FROM usuarios WHERE email='" + email + "' AND senha= '" + senha +"'";
			Connection con = conexao();
			PreparedStatement buscar = con.prepareStatement(sql);
			ResultSet rs = buscar.executeQuery();
			while(rs.next()){
				user = extraiUsuario(rs);
			}
				
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO AO VERIFICAR LOGIN");
			System.exit(0);
		}
		
		return user;
	}
	
	// SELECIONANDO USUARIO BUSCADO PARA LOGIN
	public Usuario extraiUsuario(ResultSet rs) throws SQLException, ParseException{
		Usuario user = new Usuario();
		try{	
			user.setId(rs.getInt(1));
			user.setEmail(rs.getString(2));
			user.setSenha(rs.getString(3));
			user.setTipoUsuario(rs.getString(4));	
			user.setAtivo(rs.getInt(5));
			
			if(user.getTipoUsuario().equals("administrador")){
				user.setIdPessoa(rs.getInt(9));
			}else if(user.getTipoUsuario().equals("aluno")){
				user.setIdPessoa(rs.getInt(6));
			}else if(user.getTipoUsuario().equals("professor")){
				user.setIdPessoa(rs.getInt(8));
			}else if(user.getTipoUsuario().equals("coordenador")){
				user.setIdPessoa(rs.getInt(7));
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO AO EXTRAIR USUARIO");
			System.exit(0);
		}
		
		return user;
	}
	
	
	
}
