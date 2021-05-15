import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class server{

	private static ArrayList<ClientHandler> clients = new ArrayList<>();
	private static final int PORT = 8080;
	private static ExecutorService pool = Executors.newFixedThreadPool(4);

	public static void main(String[] args) throws IOException{


		ServerSocket listener = new ServerSocket(PORT);

		while(true){
			System.out.println(" Esperando a que se conecte un cliente...");
			Socket client = listener.accept();
			System.out.println(" Conexion establecida con un cliente");
			ClientHandler clientThread = new ClientHandler(client, clients);
			clients.add(clientThread);
			pool.execute(clientThread);

		}
	}
}
