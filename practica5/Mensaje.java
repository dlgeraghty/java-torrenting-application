package practica;
import java.io.Serializable;

public class Mensaje implements Serializable {

	private Object datos;
	private String tipo;

	public Mensaje(String tipo, Object datos){
		this.tipo = tipo;
		this.datos = datos;
	}

	public String getTipo(){
		return this.tipo;
	}
	public void getOrigen(){};
	public void getDestino(){};

	public Object getDatos(){
		return this.datos;
	}
}
