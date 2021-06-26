import java.io.*;
import java.net.*;
import java.util.*;

public class OyenteServidor extends Thread{
	private Socket server;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	
	public OyenteServidor(Socket s, ObjectOutputStream oos, ObjectInputStream ois) throws IOException{
		this.server = s;
		this.ois = ois;
		this.oos = oos;
	}

	public void run() {
		
		Boolean conexion = true;
		
		while(conexion){
			try{
				
				Mensaje m = (Mensaje) this.ois.readObject();
	
		
				if(m.getTipo().equals("MENSAJE_CONFIRMACION_CONEXION")){
					//imprimir conexion establecida por standard output
					System.out.println("Conexion establecida");
					Cliente.start();
				}
				else if(m.getTipo().equals("MENSAJE_CONFIRMACION_LISTA_USUARIOS")){
					//imprimir lista usuarios por standard output
					ArrayList<String> users = (ArrayList<String>) m.getDatos();
					//Cliente.printUsers();
					
					System.out.println("Imprimiendo lista de usuarios: ");
					for (String s: users) {
						System.out.println(s);
					}
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
				else if(m.getTipo().equals("MENSAJE_CONFIRMACION_DESCONEXION")){
					//imprimir adios por standard output
					System.out.println("Cliente " + m.getDatos() + "desconectado");
					conexion = false;
					this.oos.close();
					this.ois.close();
					this.server.close();
					
				}
				else if(m.getTipo().equals("MENSAJE_CONFIRMACION_INICIALIZAR_FICHEROS")) {
					System.out.println("Ficheros inicializados por el servidor correctamente");
				}
			}catch(IOException e){
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void listUsers(){
		try{
			oos.writeObject(new Mensaje("MENSAJE_LISTA_USUARIOS"));
			//outputStream.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	};
	public void requestFile(String f){
		try{
			oos.writeObject(new Mensaje("MENSAJE_PEDIR_FICHERO", f));
			//outputStream.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	};
	public void closeConnection(String s){
		try{
			oos.writeObject(new Mensaje("MENSAJE_CERRAR_CONEXION", s));
			//outputStream.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	};
	public void stablishConnection(String s){

		try{
			oos.writeObject(new Mensaje("MENSAJE_CONEXION", s));
			//oos.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void setFiles(HashMap<String, ArrayList<String>> ficheros_de_usuario) {
		try{
			oos.writeObject(new Mensaje("MENSAJE_INICIALIZAR_FICHEROS", ficheros_de_usuario));
			//outputStream.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
}

