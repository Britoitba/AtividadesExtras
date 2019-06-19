package projeto.ae.model;

public class Aluno extends Usuario {
	
	//Atributos-------------------------------
	private int id;
	private String nome;
	private String curso;
	private String ra;
	private String campus;
	private int chTotal;


	//Construtor------------------------------
	public Aluno(int id,String email,String senha,String tipoUsuario, int ativo, String nome, String curso, String ra, String campus, int chTotal){
		super( email, senha, tipoUsuario,  ativo);
		this.id =id; 
		this.nome = nome;
		this.curso = curso;
		this.ra = ra;
		this.campus = campus;
		this.chTotal = chTotal;
	}
	
	public Aluno() {
		
	}

	//Getters e setters-----------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getChTotal() {
		return chTotal;
	}
	
	public void setChTotal(int chTotal) {
		this.chTotal = chTotal;
	}

	
	public String getNome() {
		return nome;
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

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}
	
	
}
