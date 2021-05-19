import java.io.*;
import java.net.*;
import java.util.*;

public class OyenteServidor implements Runnable{
	private Socket server;
	private BufferedReader in;

	public OyenteServidor(Socket s) throws IOException{
		this.server = s;
		in = new BufferedReader(new InputStreamReader(server.getInputStream()));
	}

	public void run() {
			String serverResponse = null;
			try{
				while(true){
					serverResponse = in.readLine();
					if(serverResponse.equals("MENSAJE_CONFIRMACION_CONEXION")){
						//imprimir conexion establecida por standard output
						System.out.println("conexion establecida");
					}
					else if(serverResponse.equals("MENSAJE_CONFIRMACION_LISTA_USUARIOS")){
						//imprimir lista usuarios por standard output
					}
					else if(serverResponse.equals("MENSAJE_EMITIR_FICHERO")){
						//(nos llega nombre de cliente C1 e informacion pedida 3)
						//enviar MENSAJE_PREPARADO_CIENTESERVIDOR
						//crear proceso EMISOR y esperar en accept la conexion
					}
					else if(serverResponse.equals("MENSAJE_PREPARADO_SERVIDORCLIENTE")){
						//imprimir adios por standard output
						System.out.println("adios");
					}
				}
			}catch (IOException e){
				e.printStackTrace();
			}finally{
				try{
					in.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
	}
}
