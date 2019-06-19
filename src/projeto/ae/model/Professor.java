package projeto.ae.model;

public class Professor extends Usuario {
	
	//Atributos-------------------------------
	private int id;
	private String nome;
	private String cpf;
	private String campus;
	
	//Construtores------------------------------
	public Professor(int id,String email,String senha,String tipoUsuario, int ativo, String nome, String cpf, String campus){
		super(email, senha, tipoUsuario,  ativo);
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.campus = campus;
	}
	public Professor(){
		super();
	}

	//Getters e setters-----------------------
	
	public String getNome() {
		return nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}
	
	
}
