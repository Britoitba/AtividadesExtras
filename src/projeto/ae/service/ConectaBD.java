package projeto.ae.service;

public interface ConectaBD {
	
	public static ConectaDataBase getNewInstance(){
		return new ConectaDataBase();
	}	
}
