import java.net.*;
import java.io.*;
import java.util.*;

public class Cliente{

	private static String username;
	private static String ip;
	private static String port;
	private static String fileList[];
	private static boolean fin = false;

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
		OyenteServidor serverConn = new OyenteServidor(s);
		serverConn.start();
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		new Thread(serverConn).start();

		//cargar los archivos de este usuario:
		File files = new File("./Archivos/" + username + "/");
		fileList = files.list();

		//enviar MENSAJE_CONEXION
		serverConn.stablishConnection(username);

		//establecer menu con usuario
		while(!fin){
			System.out.println("Menu \n 1. Consultar lista usuarios \n 2. Pedir fichero \n 3. Salir ");
			String command = keyboard.nextLine();

			if(command.equals("1")){
				serverConn.listUsers();
			}
			else if(command.equals("2")){
				System.out.println("Que archivo quieres?");
				String f = keyboard.nextLine();
				serverConn.requestFile(f);
			}
			else if(command.equals("3")){
				serverConn.closeConnection();
			}
			else{
				System.out.println("Opcion no valida");
			}

			out.println(command);
		}

		s.close();
	}

	public static String getUsername(){
		return username;
	}

	public static void fin(){
		fin = true;
	}

	public static void getFile(int i){
		//abrir un socket nuevo para enviar un archivo 
	}
}
