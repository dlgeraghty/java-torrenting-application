import java.net.*;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;

public class Servidor{

	private static ArrayList<String> users;
	private static String PORT;
	static String filename;
	private static Boolean fin = false;
	private static int NEXT_PORT;
	private static HashMap<String, ArrayList<String>> ficheros_de_usuario;
	private static HashMap<String, ObjectOutputStream> oos_de_usuarios;
	private static HashMap<String, ObjectInputStream> ois_de_usuarios;
	
	public static void main(String[] args) throws IOException{
		
		Servidor serv = new Servidor();
		
		users = new ArrayList<String>();
		ficheros_de_usuario = new HashMap<String, ArrayList<String>>();
		oos_de_usuarios = new HashMap<String, ObjectOutputStream>();
		ois_de_usuarios = new HashMap<String, ObjectInputStream>();

		Scanner keyboard = new Scanner(System.in);
		System.out.println(" En que puerto deberia escuchar? " );
		PORT = keyboard.nextLine(); 
		NEXT_PORT = Integer.parseInt(PORT) + 1;
		
		ServerSocket listener = new ServerSocket(Integer.parseInt(PORT));

		System.out.println("Servidor preparado \n Esperando a que se conecte un cliente... \n");
		
		//crear un ObjectOutputStream para pasarle al OyenteCliente
		
		while(!fin){
			
			Socket client = listener.accept();
			//TODO: Habria que pasarle los monitores tambien al OyenteCliente:
			
			OyenteCliente clientThread = new OyenteCliente(client, serv);
			clientThread.start();
		}
	}
	
	public ArrayList<String> getUsers() {
		return users;
	}
	
	public void addUser(String s, ObjectInputStream ois, ObjectOutputStream oos) {
		System.out.println("Meto a " + s + " en la lista de usuarios \n");
		users.add(s);
		oos_de_usuarios.put(s, oos);
		ois_de_usuarios.put(s, ois);
	}

	public void deleteUser(String s) {
		// TODO Auto-generated method stub
		System.out.println("Quito a " + s + " de la lista de usuarios \n");
		users.remove(s);
	}

	public void setUserFiles(Map ficheros) {
		//System.out.println("Inicializando los ficheros " + ficheros);
		ficheros_de_usuario.putAll(ficheros);
		//System.out.println("solo para asegurar " + ficheros_de_usuario);
		
	}

	public String getOwner(String archivo) {
		// TODO Auto-generated method stub
		// busca en la estructura de datos, de quien es un determinado archivo
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
	
	public int getNextPort() {
		//devuelvo NEXT_PORT pero tambien le sumo 1 
		int tmp = NEXT_PORT;
		NEXT_PORT = NEXT_PORT + 1;
		return tmp;
	}
	
	public ObjectOutputStream get_oos_de_usuario(String s) {
		return oos_de_usuarios.get(s);
	}
	
	public ObjectInputStream get_ois_de_usuario(String s) {
		return ois_de_usuarios.get(s);
	}

	
	public void fin() {
		// TODO Auto-generated method stub
		fin = true;
	}

	public Object getFilesInTheSistem() {
		String ficheros = "";
		for (String s : users) {
			for(String s2 : ficheros_de_usuario.get(s)) {
				ficheros += s2 + "\n";
			}
		}
		
		return ficheros;
	}
}
