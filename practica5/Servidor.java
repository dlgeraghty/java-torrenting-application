import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Servidor{

	private static ArrayList<String> users;
	private static String PORT;
	static String filename;
	private static int nextPort;
	
	public static void main(String[] args) throws IOException{
		
		
		
		users = new ArrayList<String>();
		//TODO: Crear los monitores para asegurar concurrencia:
		//TODO: MonitorDelSocket mSocket = new MonitorDelSocket();
		//TODO: MonitorDeArchivos mArchivos = new MonitorDeArchivos();

		try {
			/*
			File archivo = new File ("C:\\users.txt");
			FileReader fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			
			
			
			while((linea=br.readLine())!=null) {
				users.add(linea);
			}
			
			fr.close();
			*/
			
		}catch(Exception e){
	         e.printStackTrace();
	     
	         
		}
		
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(" En que puerto deberia escuchar? " );
		PORT = keyboard.readLine(); 
		
		ServerSocket listener = new ServerSocket(Integer.parseInt(PORT));

		while(true){
			System.out.println("Servidor preparado \n Esperando a que se conecte un cliente...");
			Socket client = listener.accept();
			//TODO: Habria que pasarle los monitores tambien al OyenteCliente:
			OyenteCliente clientThread = new OyenteCliente(client);
			clientThread.start();
		}
	}
	
	public static ArrayList<String> getUsers() {
		return users;
	}
	
	public static void addUser(String s) {
		users.add(s);
	}
}
