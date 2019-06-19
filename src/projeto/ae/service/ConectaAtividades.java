package projeto.ae.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import projeto.ae.model.Requisicao;
import projeto.ae.view.App;

public class ConectaAtividades extends ConectaDataBase{
	
	// BOTAO GERAR RELATORIO REQUISICOES
	public boolean VerificaBanco(){
		boolean existe = false;
		int valor = 0;
		try{
			String sql = "SELECT * FROM requisicoes WHERE status = 4";
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
	
	//SALVAR REQUISICAO
	public void salvarRequisicao(Requisicao requisicao){
		try{
			String sql = "INSERT INTO requisicoes(atividade,modalidade,ch,local,semestre,"
					+ "ano,relatorio,status,idAluno,comprovante,filecomprovante) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			Connection con = conexao();
			PreparedStatement salvar = con.prepareStatement(sql);
			salvar.setString(1, requisicao.getAtividade());
			salvar.setString(2, requisicao.getModalidade());
			salvar.setInt(3, requisicao.getCargaHoraria());
			salvar.setString(4, requisicao.getLocal());
			salvar.setInt(5, requisicao.getSemestre());
			salvar.setInt(6, requisicao.getAno());
			salvar.setString(7, requisicao.getRelatorio());
			salvar.setInt(8, 0);
			salvar.setInt(9, requisicao.getIdAluno());
			salvar.setString(10, requisicao.getComprovanteName());
			salvar.setBytes(11, requisicao.getComprovante());
			
			salvar.executeUpdate();
			salvar.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO SALVANDO REQUISICAO");
			System.exit(0);
		}
	}
	
	//ATUALIZAR REQUISICAO COM PARECER
	public void salvarParecer(Requisicao requisicao){
		try{
			String sql = "";
	    	if(App.TipoUser.equals("professor")){
	    		sql = "UPDATE requisicoes SET chValidada=?, parecer=?, status=?, idProfessor=? WHERE id=" + requisicao.getId();
			}else{
				sql = "UPDATE requisicoes SET chValidada=?, parecer=?, status=?, idCoordenador=? WHERE id=" + requisicao.getId();
			}	
			Connection con = conexao();
			PreparedStatement atualizar = con.prepareStatement(sql);
			atualizar.setInt(1, requisicao.getChValidada());
			atualizar.setString(2, requisicao.getParecer());
			atualizar.setInt(3, requisicao.getStatus());
	    	if(App.TipoUser.equals("professor")){
	    		atualizar.setInt(4, requisicao.getIdProfessor());
			}else{
				atualizar.setInt(4, requisicao.getIdCoordenador());
			}
			
	    	
			atualizar.executeUpdate();
			atualizar.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO SALVANDO PARECER");
			System.exit(0);
		}
	}
	
	//SALVAR PARECER FINAL DO COORDENADOR
	public void salvarFinal(Requisicao requisicao){
		try{
			String sql = "UPDATE requisicoes SET chValidada=?, status=4, idCoordenador=? WHERE id="+requisicao.getId();
			
			Connection con = conexao();
			PreparedStatement atualizar = con.prepareStatement(sql);
			atualizar.setInt(1, requisicao.getChValidada());
			atualizar.setInt(2,App.idUser);			
	    	
			atualizar.executeUpdate();
			atualizar.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO SALVANDO PARECER");
			System.exit(0);
		}
	}
	
	
	//ATUALIZAR REQUISICAO DEPOIS DO PARECER
	public void salvarConfirmaAluno(int status){
		try{
			String sql = "UPDATE requisicoes SET status=?  WHERE id=" + App.idRequisicao;
			
			Connection con = conexao();
			PreparedStatement atualizar = con.prepareStatement(sql);
			atualizar.setInt(1, status);  	
			atualizar.executeUpdate();
			atualizar.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO SALVANDO REQUISICAO DEPOIS DO PARECER");
			System.exit(0);
		}
	}
	
	
	//BUSCA REQUISICOES STATUS = 0 
    public ArrayList buscaRequisicoes(){
        ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
        try{
            String sql = "SELECT * from requisicoes,alunos where alunos.id=requisicoes.idAluno";    
            Connection con = conexao();

            PreparedStatement buscar = con.prepareStatement(sql);
            ResultSet rs = buscar.executeQuery();
            while(rs.next()){
            	Requisicao requisicao;
            	requisicao = extraiRequisicao(rs);
            	if(requisicao.getStatus() == 0){
            		requisicoes.add(requisicao);
            	}
                
            }

            con.close();

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERRO AO BUSCAR ARRAY DE REQUISIÇÕES");
            System.exit(0);
        }

        return requisicoes;
    }
    
    // EXTRAI REQUISICAO STATUS = 0
    private Requisicao extraiRequisicao(ResultSet rs) throws SQLException, ParseException{
		Requisicao requisicao = new Requisicao();
        requisicao.setId(rs.getInt(1));
        requisicao.setAtividade(rs.getString(2));
        requisicao.setModalidade(rs.getString(3));
        requisicao.setCargaHoraria(rs.getInt(5));
        requisicao.setLocal(rs.getString(7));
        requisicao.setSemestre(rs.getInt(8));
        requisicao.setAno(rs.getInt(9));
        requisicao.setRelatorio(rs.getString(10));
        
        requisicao.setIdAluno(rs.getInt(16));
        requisicao.setNome(rs.getString(20));
        requisicao.setCurso(rs.getString(21));
        requisicao.setStatus(rs.getInt(12));
    	return requisicao;
    }
    

    
    
    // BUSCA REQUISICAO STATUS = 1
    public ArrayList buscaRequisicoes1(){
        ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
        try{
        	String sql = "SELECT * from requisicoes,professores where idCoordenador is null and idProfessor is not null and requisicoes.idAluno = " + App.idUser;
            Connection con = conexao();
            PreparedStatement buscarProfessor = con.prepareStatement(sql);
        	ResultSet rs = buscarProfessor.executeQuery();
            while(rs.next()){
            	Requisicao requisicao;
            	requisicao = extraiRequisicao(rs);
            	if(requisicao.getStatus() == 1){
            		requisicoes.add(requisicao);
            	}
            }
        	sql = "SELECT * from requisicoes,coordenadores where idProfessor is null and idCoordenador is not null and requisicoes.idAluno = " + App.idUser;          	
        	PreparedStatement buscarCoordenador = con.prepareStatement(sql);
        	ResultSet rs1 = buscarCoordenador.executeQuery();
            while(rs1.next()){
            	Requisicao requisicao;
            	requisicao = extraiRequisicao(rs1);
            	if(requisicao.getStatus() == 1){
            		requisicoes.add(requisicao);
            	}
            }

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERRO AO BUSCAR ARRAY DE REQUISIÇÕES = 1");
            System.exit(0);
        }

        return requisicoes;
    }
    
    // BUSCA REQUISICAO STATUS = 2--beta
    public ArrayList buscaRequisicoes2(){
        ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
        try{
        	String sql = "SELECT * from requisicoes,alunos where requisicoes.status=2 and requisicoes.idAluno=alunos.id";
            Connection con = conexao();
            PreparedStatement buscarProfessor = con.prepareStatement(sql);
        	ResultSet rs2 = buscarProfessor.executeQuery();
            while(rs2.next()){
            	Requisicao requisicao;
            	requisicao = extraiRequisicao2(rs2);
       
            	requisicoes.add(requisicao);
            	
            }

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERRO AO BUSCAR ARRAY DE REQUISIÇÕES = 2");
            System.exit(0);
        }

        return requisicoes;
    }
    // EXTRAI REQUISICAO STATUS = 2--beta
    private Requisicao extraiRequisicao2(ResultSet rs) throws SQLException, ParseException{
		Requisicao requisicao = new Requisicao();
        requisicao.setId(rs.getInt(1));
        requisicao.setAtividade(rs.getString(2));
        requisicao.setModalidade(rs.getString(3));
        requisicao.setCargaHoraria(rs.getInt(5));
        requisicao.setLocal(rs.getString(7));
        requisicao.setSemestre(rs.getInt(8));
        requisicao.setAno(rs.getInt(9));
        requisicao.setRelatorio(rs.getString(10));
        requisicao.setParecer(rs.getString(11));
        
        requisicao.setRecurso(rs.getString(15));
        requisicao.setIdAluno(rs.getInt(16));
        requisicao.setNome(rs.getString(20));
        requisicao.setCurso(rs.getString(21));
        requisicao.setStatus(rs.getInt(12));
    	return requisicao;
    }
    
	//BUSCA  REQUISIÇÃO POR ID --------------------------------------------------------------
    public Requisicao buscaRequisicoesId(int id){
    	Requisicao requisicao = new Requisicao();
        try{
            String sql = "SELECT * from requisicoes,alunos where alunos.id=requisicoes.idAluno and requisicoes.id="+ id ;
            Connection con = conexao();
            PreparedStatement buscar = con.prepareStatement(sql);
            ResultSet rs = buscar.executeQuery();
            while(rs.next()){
            	requisicao = extraiRequisicaoId(rs);

            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERRO AO BUSCAR REQUISIÇÃO POR ID");
            System.exit(0);
        }

        return requisicao;
    }
    
    // EXTRAI REQUISICAO POR ID 
    private Requisicao extraiRequisicaoId(ResultSet rs) throws SQLException, ParseException{
		Requisicao requisicao = new Requisicao();
        requisicao.setId(rs.getInt(1));
        requisicao.setAtividade(rs.getString(2));
        requisicao.setModalidade(rs.getString(3));
        requisicao.setCargaHoraria(rs.getInt(5));
        requisicao.setLocal(rs.getString(7));
        requisicao.setSemestre(rs.getInt(8));
        requisicao.setAno(rs.getInt(9));
        requisicao.setRelatorio(rs.getString(10));
        
        requisicao.setRecurso(rs.getString(15));
        requisicao.setIdAluno(rs.getInt(16));
        requisicao.setNome(rs.getString(20));
        requisicao.setCurso(rs.getString(21));
        requisicao.setStatus(rs.getInt(12));
    	return requisicao;
    }
	

	// BUSCAR PARA VER SE TEM CURSO CADASTRADO
	public String buscaModalidade(String atividade){
		String modalidade = "";
		try{
			String sql = "SELECT modalidade FROM atividades WHERE nome = '" + atividade + "'";
			Connection con = conexao();
			PreparedStatement verificar = con.prepareStatement(sql);
			ResultSet rs = verificar.executeQuery();
			while(rs.next()){
				modalidade =  rs.getString(1);
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERRO AO BUSCAR MODALIDADE SELECIONADA");
			System.exit(0);
		}
		
		return modalidade;
	}
		
	// VERIFICA CAMPO COORDENADOR É NULO
	public boolean CoordIsNull(int id){
		boolean retorno = false;
        try{
            String sql = "SELECT * from requisicoes where id=" + id + " and idCoordenador is null";
            
            Connection con = conexao();
		
	    	PreparedStatement buscar = con.prepareStatement(sql);
	    	ResultSet rs = buscar.executeQuery();
	        while(rs.next()){
	        	retorno = true;
	        }
            con.close();

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERRO AO BUSCAR ARRAY DE REQUISIÇÕES");
            System.exit(0);
        }
        
        return retorno;
	}
	
	
	// EXIBE DETALHES DA REQUISICAO SELECIONADA NA TABELA -------------
	public Requisicao ExibeRequisicaoAluno(int id, int tipo){
		Requisicao requisicao = new Requisicao();
        try{
        	String sql;
    		if(tipo == 1){
    			sql = "SELECT * from requisicoes,professores where professores.id=requisicoes.idProfessor and requisicoes.id="+ id ;
    		}else{
    			sql = "SELECT * from requisicoes,coordenadores where coordenadores.id=requisicoes.idCoordenador and requisicoes.id="+ id ;
    		}
            
            Connection con = conexao();
            PreparedStatement buscar = con.prepareStatement(sql);
            ResultSet rs = buscar.executeQuery();
            while(rs.next()){
            	requisicao = extraiExibeRequisicao(rs, tipo);
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERRO AO BUSCAR REQUISIÇÃO PARA EXIBIR NA TABELA");
            System.exit(0);
        }
		
		return requisicao;
	}
	
	// SALVA SOLICITACAO DE RECURSO----------------------------------------------
		public void salvarRecurso(String recurso){
			try{
				String sql = "UPDATE requisicoes SET status=2, recurso='"+recurso+"' WHERE id=" + App.idRequisicao;
				
				Connection con = conexao();
				PreparedStatement atualizar = con.prepareStatement(sql); 	
				atualizar.executeUpdate();
				atualizar.close();
				
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("ERRO SALVANDO RECURSO");
				System.exit(0);
			}
		}
		
		//BUSCA REQUISICOES PARA HISTORICO 
	    public ArrayList buscaRequisicoesHistorico(){
	        ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
	        try{
	            String sql = "SELECT * from requisicoes where status = 4 and idAluno = " + App.idUser ;
	            Connection con = conexao();

	            PreparedStatement buscar = con.prepareStatement(sql);
	            ResultSet rs = buscar.executeQuery();
	            while(rs.next()){
	                Requisicao requisicao;
	                requisicao = extraiRequisicaoHistorico(rs);
	                   requisicoes.add(requisicao);

	            }

	            con.close();

	        }catch(Exception e){
	            e.printStackTrace();
	            System.out.println("ERRO AO BUSCAR HISTORICO");
	            System.exit(0);
	        }

	        return requisicoes;
	    }

	    // EXTRAI REQUISICAO HISTORICO
	    private Requisicao extraiRequisicaoHistorico(ResultSet rs) throws SQLException, ParseException{
	        Requisicao requisicao = new Requisicao();
	        requisicao.setId(rs.getInt(1));
	        requisicao.setAtividade(rs.getString(2));
	        requisicao.setChValidada(rs.getInt(6));
	        requisicao.setSemestre(rs.getInt(8));
	        requisicao.setAno(rs.getInt(9));
	        return requisicao;
	    }

	
    // EXTRAI REQUISICAO PARA EXIBIR NA TABELA 
    private Requisicao extraiExibeRequisicao(ResultSet rs, int tipo) throws SQLException, ParseException{
    	Requisicao requisicao = new Requisicao();
		if(tipo == 1){
	        requisicao.setId(rs.getInt(1));
	        requisicao.setAtividade(rs.getString(2));
	        requisicao.setModalidade(rs.getString(3));
	        requisicao.setCargaHoraria(rs.getInt(5));
	        requisicao.setChValidada(rs.getInt(6));
	        requisicao.setLocal(rs.getString(7));
	        requisicao.setSemestre(rs.getInt(8));
	        requisicao.setAno(rs.getInt(9));
	        requisicao.setParecer(rs.getString(11));
	        
	        requisicao.setIdProfessor(rs.getInt(17));
	        requisicao.setNome(rs.getString(20));
	    	
		}else{
	        requisicao.setId(rs.getInt(1));
	        requisicao.setAtividade(rs.getString(2));
	        requisicao.setModalidade(rs.getString(3));
	        requisicao.setCargaHoraria(rs.getInt(5));
	        requisicao.setChValidada(rs.getInt(6));
	        requisicao.setLocal(rs.getString(7));
	        requisicao.setSemestre(rs.getInt(8));
	        requisicao.setAno(rs.getInt(9));
	        requisicao.setParecer(rs.getString(11));
	        
	        requisicao.setIdCoordenador(rs.getInt(18));
	        requisicao.setNome(rs.getString(20));
		}
		return requisicao;
    }
    
    public Requisicao DownloadCertificado(Requisicao requisicao){
        try{
            String sql = "SELECT comprovante, filecomprovante from requisicoes where id = " + requisicao.getId();
            Connection con = conexao();

            PreparedStatement buscar = con.prepareStatement(sql);
            ResultSet rs = buscar.executeQuery();
            while(rs.next()){
                
                requisicao.setComprovanteName(rs.getString(1));
                requisicao.setComprovante(rs.getBytes(2));

            }

            con.close();

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ERRO AO FAZER DOWNLOAD DE CERTIFICADO");
            System.exit(0);
        }
        return requisicao;
    }
	
}
