import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Servidor{

	private static final int PORT = 8080;

	public static void main(String[] args) throws IOException{

		//TODO: Crear los monitores para asegurar concurrencia:
		//TODO: MonitorDelSocket mSocket = new MonitorDelSocket();
		//TODO: MonitorDeArchivos mArchivos = new MonitorDeArchivos();

		ServerSocket listener = new ServerSocket(PORT);

		while(true){
			System.out.println(" Esperando a que se conecte un cliente...");
			Socket client = listener.accept();
			//TODO: Habria que pasarle los monitores tambien al OyenteCliente:
			OyenteCliente clientThread = new OyenteCliente(client);
		}
	}
}
