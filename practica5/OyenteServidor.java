import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class OyenteServidor extends Thread{
	private Socket server;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private String requested_file;
	private static boolean lock;
	
	
	public OyenteServidor(Socket s, ObjectOutputStream oos, ObjectInputStream ois) throws IOException{
		this.server = s;
		this.ois = ois;
		this.oos = oos;
		this.lock = true;
		
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
					System.out.println("-----------------------------");
					for (String s: users) {
						System.out.println(s);
					}
					System.out.println("-----------------------------");
				}
				else if(m.getTipo().equals("MENSAJE_CONFIRMACION_LISTA_FICHEROS")){
					//imprimir lista usuarios por standard output
					
					System.out.println("Imprimiendo ficheros disponibles en el sistema : ");
					System.out.println("-----------------------------");
					System.out.println( m.getDatos());
					System.out.println("-----------------------------");
					
				}
				else if(m.getTipo().equals("MENSAJE_EMITIR_FICHERO")){
					//(nos llega nombre de cliente C1 e informacion pedida 3)
					//enviar MENSAJE_PREPARADO_CIENTESERVIDOR
					//crear proceso EMISOR y esperar en accept la conexion
					int port = (int) m.getDatos();
					System.out.println("El puerto por el que nos vamos a comunicar, es: " + port);
					ServerSocket ss = new ServerSocket(port);
					//lock = false;
					while(true) {
						Socket s = ss.accept();
						Emisor e = new Emisor(s);
						e.start();
					}
				}
				else if(m.getTipo().equals("MENSAJE_PREPARADO_SERVIDORCLIENTE")){
					//(llega direccion Ip y puerto del propietario de fichero)
				    //crear proceso RECEPTOR
					int port = (int) m.getDatos();
					System.out.println("Vamos a hablar por el puerto: " + port );
					//while(lock);
					Cliente.getFile(port);
					
				}
				else if(m.getTipo().equals("MENSAJE_CONFIRMACION_DESCONEXION")){
					//imprimir adios por standard output
					System.out.println("Cliente " + m.getDatos() + " se ha desconectado correctamente");
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

	public void listFicheros(){
		try{
			oos.writeObject(new Mensaje("MENSAJE_LISTA_FICHEROS"));
			//outputStream.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	};
	
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
			//requested_file = f;
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

