import java.net.*;
import java.io.*;
import java.util.*;

public class OyenteCliente implements Runnable{
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	private ArrayList<OyenteCliente> clients;

	public OyenteCliente(Socket clientSocket, ArrayList<OyenteCliente> clients) throws IOException{
		this.client = clientSocket;
		this.clients = clients;
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream(), true);

	}

	public void run(){
		try{
			while(true){
				String request = in.readLine();
				if(request.equals("MENSAJE_CONEXION")){
					//guardar informacion del usuario
					//envio mensaje confirmacion conexion fout
				}
				else if( request.equals("MENSAJE_LISTA_USUARIOS")){
					//crear un mensaje con la informacion de usuarios en sistema
					//envio mensaje confirmacion lista usuarios fout
					//for(OyenteCliente c: clients){
					//	println(c.username);
					//}
				}
				else if( request.equals("MENSAJE_CERRAR_CONEXION")){
					//eliminar inforacion del usuario
					//envio mensaje confirmacion cerrar conexion fout
				}
				else if( request.equals("MENSAJE_PEDIR_FICHERO")){
					//buscar usuario que contiene el fichero y obtener fout2
					//envio mensaje MENSAJE_EMITIR_FICHERO por fout2
					//for(OyenteCliente c: clients){
					//	println(c.username);
					//}
				}
				else if( request.equals("MENSAJE_PREPARADO_CLIENTESERVIDOR")){
					//buscar fout1 (flujo del cliente al que hay que enviar la informacion)
					//envio fout1 mensaje MENSAJE_PREPARADO_SERVIDORCLIENTE
				}
			}
		} catch(IOException e){
			System.err.println("IOException in client handler");	
			System.err.println(e.getStackTrace());
		} finally {
			out.close();
			try{
				in.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
