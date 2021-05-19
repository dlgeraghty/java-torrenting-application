import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Servidor{

	private static ArrayList<OyenteCliente> clients = new ArrayList<>();
	private static final int PORT = 8080;
	private static ExecutorService pool = Executors.newFixedThreadPool(4);

	public static void main(String[] args) throws IOException{


		ServerSocket listener = new ServerSocket(PORT);

		while(true){
			System.out.println(" Esperando a que se conecte un cliente...");
			Socket client = listener.accept();
			OyenteCliente clientThread = new OyenteCliente(client, clients);
			clients.add(clientThread);
			pool.execute(clientThread);
		}
	}
}
