
import java.io.Serializable;

public class Mensaje implements Serializable {

	private static final long serialVersionUID = 5950169519310163575L;
	private Object datos;
	private String tipo;

	public Mensaje(String tipo, Object datos){
		this.tipo = tipo;
		this.datos = datos;
	}

	public Mensaje(String tipo) {
		this.tipo = tipo;
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
