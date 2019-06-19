package projeto.ae.model;

import java.io.File;

public class Requisicao {
	int id;
	String atividade;
	String modalidade;
	int cargaHoraria;
	int chValidada;
	String parecer;
	String local;
	String comprovanteName;
	byte [] comprovante;
	int semestre;
	int ano;
	String relatorio;
	String recurso;
	int idAluno;
	int idProfessor;
	int idCoordenador;
	String nome;
	String curso;
	int status;
	
	// CONSTRUTORES --------------------------------------
	
	public Requisicao(int id, String atividade, String modalidade, int cargaHoraria, String local, byte [] comprovante,
			int semestre, int ano, String relatorio, String recurso, int idAluno, String nome, String curso, String parecer, int chValidada,
			int idProfessor, int idCoordenador, int status, String comprovanteName) {
		this.id = id;
		this.atividade = atividade;
		this.modalidade = modalidade;
		this.cargaHoraria = cargaHoraria;
		this.local = local;
		this.comprovante = comprovante;
		this.semestre = semestre;
		this.ano = ano;
		this.relatorio = relatorio;
		this.recurso = recurso;
		this.idAluno = idAluno;
		this.nome = nome;
		this.curso = curso;
		this.chValidada = chValidada;
		this.parecer = parecer;
		this.idCoordenador = idCoordenador;
		this.idProfessor = idProfessor;
		this.status = status;
		this.comprovanteName = comprovanteName;
	}
	
	public Requisicao(){};
	
	
	//GETTERS AND SETTERS ----------------------
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getChValidada() {
		return chValidada;
	}

	public void setChValidada(int chValidada) {
		this.chValidada = chValidada;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public String getAtividade() {
		return atividade;
	}
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	public String getModalidade() {
		return modalidade;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
	}

	public int getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}

	public int getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(int idProfessor) {
		this.idProfessor = idProfessor;
	}

	public int getIdCoordenador() {
		return idCoordenador;
	}

	public void setIdCoordenador(int idCoordenador) {
		this.idCoordenador = idCoordenador;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public String getComprovanteName() {
		return comprovanteName;
	}

	public void setComprovanteName(String comprovanteName) {
		this.comprovanteName = comprovanteName;
	}

	public byte[] getComprovante() {
		return comprovante;
	}

	public void setComprovante(byte[] comprovante) {
		this.comprovante = comprovante;
	}

	
}
