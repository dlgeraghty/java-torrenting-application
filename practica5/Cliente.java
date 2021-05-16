import java.net.*;
import java.io.*;
import java.util.*;

public class Cliente{

	private String username;
	private String ip;

	public static void main(String[] args) throws IOException{
		Socket s = new Socket("localhost", 8080);

		OyenteServidor serverConn = new OyenteServidor(s);

		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
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
