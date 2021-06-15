import java.io.*;
import java.net.*;
import java.util.*;

public class OyenteServidor extends Thread{
	private Socket server;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;

	public OyenteServidor(Socket s) throws IOException{
		this.server = s;
		this.inputStream = new ObjectInputStream(server.getInputStream());
		this.outputStream = new ObjectOutputStream(server.getOutputStream());
	}

	public void run() {
		String serverResponse = null;
		while(true){
			try{
				Mensaje m = (Mensaje) this.inputStream.readObject();
				if(m.getTipo().equals("MENSAJE_CONFIRMACION_CONEXION")){
					//imprimir conexion establecida por standard output
					System.out.println("conexion establecida");
				}
				else if(m.getTipo().equals("MENSAJE_CONFIRMACION_LISTA_USUARIOS")){
					//imprimir lista usuarios por standard output
				}
				else if(m.getTipo().equals("MENSAJE_EMITIR_FICHERO")){
					//(nos llega nombre de cliente C1 e informacion pedida 3)
					//enviar MENSAJE_PREPARADO_CIENTESERVIDOR
					//crear proceso EMISOR y esperar en accept la conexion
				}
				else if(m.getTipo().equals("MENSAJE_PREPARADO_SERVIDORCLIENTE")){
					//imprimir adios por standard output
					System.out.println("adios");
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	public void listUsers(){};
	public void requestFile(String f){};
	public void closeConnection(){};
	public void stablishConnection(String s){

		try{
			outputStream.writeObject(new Mensaje("NUEVA_CONEXION", s));
			outputStream.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

