import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cliente{
	
	private static String username;
	private static String ip;
	private static String port;
	private static ArrayList<String> fileList;
	private static String f;
	private static boolean fin = true;

	public static void main(String[] args) throws IOException{

		//leer el nombre del teclado
		Scanner keyboard = new Scanner(System.in);
		System.out.println(" Cual es tu nombre de usuario " );
		username = keyboard.nextLine(); 
		
		System.out.println(" En que ip esta el servidor? " );
		ip = keyboard.nextLine(); 
		
		System.out.println(" En que puerto esta el servidor? " );
		port = keyboard.nextLine();

		//crear el socker con el servidor
		Socket s = new Socket( ip, Integer.parseInt(port));

		//crear un nuevo OyenteServidor para leer el socket
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());;
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		OyenteServidor serverConn = new OyenteServidor(s, oos, ois);

		//cargar los archivos de este usuario:
		File files = new File("./Archivos/" + username + "/");
		String[] fileList_array = files.list();

		fileList = new ArrayList<String>();
		System.out.println("Archivos de este usuario: \n--------------------------");
		for(String f : fileList_array) {
			System.out.println(f);
			fileList.add(f);
		}
		System.out.println("--------------------------");
		
		HashMap<String, ArrayList<String>> ficheros_de_usuario = new HashMap<String, ArrayList<String>>();
		ficheros_de_usuario.put(username, fileList);
		
		//enviar MENSAJE_CONEXION
		serverConn.start();
		serverConn.stablishConnection(username);
		serverConn.setFiles(ficheros_de_usuario);
			
		
		fin = false;
		while(fin);
		//establecer menu con usuario
		while(!fin){
			System.out.println("\n Menu \n 1. Consultar lista usuarios \n 2. Pedir fichero \n 3. Consultar ficheros disponibles \n 4. Salir \n");
			String command = keyboard.nextLine();

			if(command.equals("1")){
				serverConn.listUsers();
			}
			else if(command.equals("2")){
				System.out.println("Que archivo quieres?");
				f = keyboard.nextLine();
				serverConn.requestFile(f);
			}
			else if(command.equals("3")){
				serverConn.listFicheros();
			}
			else if(command.equals("4")){
				serverConn.closeConnection(username);
				//esto no lo podemos hacer por que rompe el socket (hay que poner un lock esperando que termine la operacion anterior)
				//fin();
			}
			else{
				System.out.println("Opcion no valida");
			}

			//out.println(command);
		}

		s.close();
	}
	
	public static void start() {
		fin = false;
	}

	public static String getUsername(){
		return username;
	}

	public static String getIp(){
		return ip;
	}
	public static String getF(){
		return f;
	}
	public static void fin(){
		fin = true;
	}

	public static void getFile(int i){
		//abrir un socket nuevo para enviar un archivo 
		try {
			Socket s = new Socket( ip, i);
			Receptor r = new Receptor(s, f);
			r.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
