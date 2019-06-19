package projeto.ae.model;

public class Usuario {
	
	//Atributos-----------------------------------
	private int id;
	private int idPessoa;
	private String email;
	private String senha;
	private String tipoUsuario;
	private int ativo;
	
	//Construtor----------------------------------
	public Usuario(String email,String senha,String tipoUsuario, int ativo){
		super();
		this.email=email;
		this.senha=senha;
		this.tipoUsuario=tipoUsuario;
		this.ativo=ativo;
	}
	
	public Usuario() {
		
	}

	//Getters e setters---------------------------
		
	public String getEmail() {
		return email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	
}
