import java.net.*;
import java.io.*;
import java.util.*;

public class Cliente{

	private static String username;
	private static String ip;

	public static void main(String[] args) throws IOException{

		//leer el nombre del teclado
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(" Cual es tu nombre de usuario " );
		username = keyboard.readLine(); 

		//crear el socker con el servidor
		Socket s = new Socket("localhost", 8080);

		//crear un nuevo OyenteServidor para leer el socket
		OyenteServidor serverConn = new OyenteServidor(s);
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		new Thread(serverConn).start();

		//enviar MENSAJE_CONEXION

		//establecer menu con usuario
		while(true){
			System.out.println("Menu \n 1. Consultar lista usuarios \n 2. Pedir fichero \n 3. Salir ");
			String command = keyboard.readLine();

			if( command == "quit") break;

			out.println(command);
		}

		s.close();
	}
}
