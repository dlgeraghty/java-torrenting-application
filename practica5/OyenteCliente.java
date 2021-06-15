import java.net.*;
import java.io.*;
import java.util.*;
import practica.Mensaje;

public class OyenteCliente extends Thread{
	private Socket client;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	private ArrayList<OyenteCliente> clients;

	public OyenteCliente(Socket clientSocket) throws IOException{
		this.client = clientSocket;
		this.oos = new ObjectOutputStream(clientSocket.getOutputStream());
		this.ois = new ObjectInputStream(clientSocket.getInputStream());
	}

	public void run(){
		while(true){
			try{
				Mensaje m = (Mensaje) this.ois.readObject();
				String fileName = m.getDatos().toString();
				if(m.getTipo().equals("MENSAJE_CONEXION")){
					//guardar informacion del usuario
					//envio mensaje confirmacion conexion fout
				}
				else if( m.getTipo().equals("MENSAJE_LISTA_USUARIOS")){
					//crear un mensaje con la informacion de usuarios en sistema
					//envio mensaje confirmacion lista usuarios fout
					//for(OyenteCliente c: clients){
					//	println(c.username);
					//}
				}
				else if( m.getTipo().equals("MENSAJE_CERRAR_CONEXION")){
					//eliminar inforacion del usuario
					//envio mensaje confirmacion cerrar conexion fout
				}
				else if( m.getTipo().equals("MENSAJE_PEDIR_FICHERO")){
					//buscar usuario que contiene el fichero y obtener fout2
					//envio mensaje MENSAJE_EMITIR_FICHERO por fout2
					//for(OyenteCliente c: clients){
					//	println(c.username);
					//}
				}
				else if( m.getTipo().equals("MENSAJE_PREPARADO_CLIENTESERVIDOR")){
					//buscar fout1 (flujo del cliente al que hay que enviar la informacion)
					//envio fout1 mensaje MENSAJE_PREPARADO_SERVIDORCLIENTE
				}
			}catch (IOException e){
				e.printStackTrace();
			}catch (ClassNotFoundException e){
				e.printStackTrace();
			}
		}
		try{
			this.ois.close();
			this.oos.close();
			this.client.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
