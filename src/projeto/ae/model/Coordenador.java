package projeto.ae.model;

public class Coordenador extends Usuario {
	
	//Atributos-------------------------------
	private int id;
	private String nome;
	private String curso;
	private String cpf;
	private String campus;
	
	//Construtor------------------------------
	public Coordenador(int id,String email,String senha,String tipoUsuario, int ativo,String nome, String curso, String cpf, String campus){
		super(email, senha, tipoUsuario,  ativo);
		this.id = id;
		this.nome = nome;
		this.curso = curso;
		this.cpf = cpf;
		this.campus = campus;
	}
	
	//Getters e setters-----------------------
		
	public Coordenador() {
		
	}

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

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
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
