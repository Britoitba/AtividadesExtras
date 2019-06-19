package projeto.ae.model;

public class Administrador extends Usuario {
	
	private String nome;
	

	//Apenas um construtor
	public Administrador(String email,String senha,String tipoUsuario, int ativo, String nome){
		super(email, senha, tipoUsuario,  ativo);
		this.nome = nome;
	}

	public Administrador() {
		super();
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
