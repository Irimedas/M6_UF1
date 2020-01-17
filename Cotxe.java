import java.io.Serializable;

public class Cotxe implements Serializable{
	//Implementa la interfície Serializable
	private String matricula;
	private String marca;
	private String model;
	private int any;
	
	//constructor amb paràmetres
	public Cotxe (String matricula, String marca, String model, int any){
		this.matricula = matricula;
		this.marca = marca;
		this.model = model;
		this.any = any;
	}
	
	//constructor per defecte
	public Cotxe(){
		this.matricula = null;
		this.marca = null;
		this.model = null;
		this.any = 0;
	}
	
	//G&S -> matricula
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	//G&S -> marca
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	//G&S -> model
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	//G&S -> any
	public int getAny() {
		return any;
	}
	public void setAny(int any) {
		this.any = any;
	}
}
