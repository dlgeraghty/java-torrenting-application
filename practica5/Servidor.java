import java.net.*;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;

public class Servidor{

	private static ArrayList<String> users;
	private static String PORT;
	static String filename;
	private static int nextPort;
	private static HashMap<String, ArrayList<String>> ficheros_de_usuario;
	
	public static void main(String[] args) throws IOException{
		
		Servidor serv = new Servidor();
		
		users = new ArrayList<String>();
		ficheros_de_usuario = new HashMap<String, ArrayList<String>>();
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

		Scanner keyboard = new Scanner(System.in);
		System.out.println(" En que puerto deberia escuchar? " );
		PORT = keyboard.nextLine(); 
		
		ServerSocket listener = new ServerSocket(Integer.parseInt(PORT));

		System.out.println("Servidor preparado \n Esperando a que se conecte un cliente...");
		
		//crear un ObjectOutputStream para pasarle al OyenteCliente
		
		while(true){
			
			Socket client = listener.accept();
			//TODO: Habria que pasarle los monitores tambien al OyenteCliente:
			
			OyenteCliente clientThread = new OyenteCliente(client, serv);
			clientThread.start();
		}
	}
	
	public static ArrayList<String> getUsers() {
		return users;
	}
	
	public static void addUser(String s) {
		System.out.println("meto a " + s + " en la lista de usuarios");
		users.add(s);
	}

	public void deleteUser(String s) {
		// TODO Auto-generated method stub
		System.out.println("quito a " + s + " de la lista de usuarios");
		users.remove(s);
	}

	public void setUserFiles(Map ficheros) {
		//System.out.println("Inicializando los ficheros " + ficheros);
		ficheros_de_usuario.putAll(ficheros);
		//System.out.println("solo para asegurar " + ficheros_de_usuario);
		
	}

	public String getOwner(String archivo) {
		// TODO Auto-generated method stub
		for(Entry<String, ArrayList<String>> entry: ficheros_de_usuario.entrySet()) {
			ArrayList<String> l = entry.getValue();
			for(String s: l) {
				if(s.equals(archivo)) {
					return entry.getKey();
				}
			}
		}
		return null;
	}
}
