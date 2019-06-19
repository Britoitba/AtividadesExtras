package projeto.ae.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import projeto.ae.model.Aluno;

public class ConectaAluno extends ConectaDataBase{
	
	//SALVAR ALUNO
	public void salvarAluno(Aluno aluno){
		try{
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(INSERIRALUNO);
			salvar.setString(1, aluno.getNome());
			salvar.setString(2, aluno.getCurso());
			salvar.setString(3, aluno.getRa());
			salvar.setString(4, aluno.getCampus());
			salvar.executeUpdate();
			salvar.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO SALVANDO ALUNO");
			System.exit(0);
		}
	}
	
	//ATIVAR ALUNO
	public void ativarAluno(int id){
		Aluno aluno = new Aluno();
		try{
			String sql = "UPDATE alunos,usuarios SET ativo = 1 where alunos.id=usuarios.idAluno and alunos.id=" + id;
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(sql);

			salvar.executeUpdate();
			salvar.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO ATIVANDO ALUNO");
			System.exit(0);
		}
	}
	
	//RECUSAR ALUNO
	public void recusarAluno(int id){
		Aluno aluno = new Aluno();
		try{
			String sql = "DELETE FROM alunos where id=" + id;
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(sql);

			salvar.executeUpdate();
			salvar.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO DESATIVANDO ALUNO");
			System.exit(0);
		}
	}
	
	// BUSCAR ULTIMO ALUNO
	public int buscaUltimoAluno(){
		int id = 0;
		try{
			String sql = "SELECT max(id) from alunos";
			Connection con = conexao();
			PreparedStatement buscar = con.prepareStatement(sql);
			ResultSet rs = buscar.executeQuery();
			while(rs.next()){
				id = rs.getInt(1);
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO AO BUSCAR ULTIMO ALUNO");
			System.exit(0);
		}
		
		return id;		
	}
	
	//BUSCA ALUNO POR ID---------------------------------------------------------------
	public Aluno buscaAlunoPorId(int id){
		Aluno aluno = new Aluno();
		try{
			String sql = "SELECT * from alunos,usuarios where alunos.id=usuarios.idAluno and alunos.id=" + id;
			Connection con = conexao();
			PreparedStatement buscar = con.prepareStatement(sql);
			ResultSet rs = buscar.executeQuery();
			while(rs.next()){
				aluno.setId(rs.getInt(1));
				aluno.setNome(rs.getString(2));
				aluno.setCurso(rs.getString(3));
				aluno.setRa(rs.getString(4));
				aluno.setCampus(rs.getString(5));
				aluno.setEmail(rs.getString(7));	
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO AO BUSCAR ALUNO");
			System.exit(0);
		}
		
		return aluno;		
	}
	
	//BUSCA  TODOS ALUNOS--------------------------------------------------------------
    public ArrayList buscaAlunos(){
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        try{
            String sql = "SELECT * from alunos,usuarios where alunos.id=usuarios.idAluno";
            Connection con = conexao();
            PreparedStatement buscar = con.prepareStatement(sql);
            ResultSet rs = buscar.executeQuery();
            while(rs.next()){
            	Aluno aluno;
            	aluno = extraiAluno(rs);
               
                if(aluno.getAtivo() == 0){
                	alunos.add(aluno);
                }              
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERRO AO BUSCAR ARRAY DE ALUNOS");
            System.exit(0);
        }

        return alunos;
    }
    
    private Aluno extraiAluno(ResultSet rs) throws SQLException, ParseException{
		Aluno aluno = new Aluno();
        aluno.setId(rs.getInt(1));
        aluno.setNome(rs.getString(2));
        aluno.setCurso(rs.getString(3));
        aluno.setRa(rs.getString(4));
        aluno.setCampus(rs.getString(5));
        aluno.setEmail(rs.getString(7));
        aluno.setAtivo(rs.getInt(10));
    	return aluno;
    	
    }
    
    // BUSCAR PARA GERAR RELATORIO
    public ArrayList geraRelatorio(){
    	ArrayList<Aluno> relatorio = new ArrayList<Aluno>();
    	Aluno aluno;
   		int i,max = 0,id=0, valor = 0;   		
   		try{
   			String sql1 = "SELECT max(id) from alunos";
            Connection con = conexao();
            PreparedStatement buscar = con.prepareStatement(sql1);
            ResultSet rs = buscar.executeQuery();
            while(rs.next()){
            	max = rs.getInt(1);
            }
            
   		}catch(Exception a){
            a.printStackTrace();
            System.out.println("ERRO AO BUSCAR ULTIMO ALUNO PARA RELATORIO");
            System.exit(0);
   		}
   		
   		
   		for(i= 0; i< max; i++){
   			id++;
   			valor = 0;
   			aluno = new Aluno();
	        try{
	            String sql = "SELECT nome,chValidada FROM alunos,requisicoes WHERE alunos.id=requisicoes.idAluno and requisicoes.status = 4 and alunos.id =" + id;
	            Connection con = conexao();
	            PreparedStatement buscar = con.prepareStatement(sql);
	            ResultSet rs = buscar.executeQuery();
	            while(rs.next()){
	            	
	                aluno.setNome(rs.getString(1));
	                valor = valor + rs.getInt(2);    
	                
	            }
	            aluno.setChTotal(valor);
	            
	            relatorio.add(aluno);
	            con.close();
	        }catch(Exception e){
	            e.printStackTrace();
	            System.out.println("ERRO AO BUSCAR ARRAY DE ALUNOS PARA RELATORIO");
	            System.exit(0);
	        }
   		}

        return relatorio;
    }
    
}
