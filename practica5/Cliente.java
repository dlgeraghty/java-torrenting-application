import java.net.*;
import java.io.*;
import java.util.*;

public class Cliente{

	private static String username;
	private static String ip;

	public static void main(String[] args) throws IOException{

		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(" Cual es tu nombre de usuario " );
		username = keyboard.readLine(); 

		Socket s = new Socket("localhost", 8080);

		OyenteServidor serverConn = new OyenteServidor(s);

		PrintWriter out = new PrintWriter(s.getOutputStream(), true);

		new Thread(serverConn).start();

		while(true){
			System.out.println("> ");
			String command = keyboard.readLine();

			if( command == "quit") break;

			out.println(command);
		}

		s.close();
	}
}
