package projeto.ae.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaDataBase implements ConectaBD{
	final String USUARIO = "root";
	final String SENHA = "";
	final String URL_BANCO = "jdbc:mysql://localhost:3306/atividadesextras";

	//constantes de acesso
	final String CLASSE_DRIVER = "com.mysql.jdbc.Driver";
	
	
	//comandos SQL
	final String INSERIRADM = "INSERT INTO administradores (nome) VALUES (?)";
	final String INSERIRALUNO = "INSERT INTO alunos(nome,curso,ra,campus) VALUES(?,?,?,?)";
	final String INSERIRPROFESSOR = "INSERT INTO professores(nome,cpf,campus) VALUES(?,?,?)";
	final String INSERIRCOORDENADOR = "INSERT INTO coordenadores(nome,curso,cpf,campus) VALUES(?,?,?,?)";

	
	Connection conexao(){
		try{
			Class.forName(CLASSE_DRIVER);
			return DriverManager.getConnection(URL_BANCO, USUARIO, SENHA);
		} catch(Exception e){
			e.printStackTrace();
			if(e instanceof ClassNotFoundException){
				System.err.println("Verifique o driver de conexão.");
			} else{
				System.err.println("Verifique se o DB está rodando.");
			}
			System.exit(0);
			//o sistema deverá sair antes de chegar aqui...
			return null;
		}
	}
}
