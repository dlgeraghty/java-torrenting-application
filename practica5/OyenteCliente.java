import java.net.*;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class OyenteCliente extends Thread{
	private Socket client;
	private Servidor serv;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ArrayList<OyenteCliente> clients;

	public OyenteCliente(Socket clientSocket, Servidor serv) throws IOException{
		this.client = clientSocket;
		this.serv = serv;
		
	}

	public void run(){
		boolean conexion = true;
		
		try {
			this.ois = new ObjectInputStream(client.getInputStream());
			this.oos = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(conexion){
			try{
				Mensaje m = (Mensaje) this.ois.readObject();
				if(m.getTipo().equals("MENSAJE_CONEXION")){
					//guardar informacion del usuario
					serv.addUser((String) m.getDatos(), ois, oos);
					//envio mensaje confirmacion conexion fout
					System.out.println("Estableciendo conexion...");
					oos.writeObject(new Mensaje("MENSAJE_CONFIRMACION_CONEXION"));
					System.out.println("Conexion establecida con el usuario "+ m.getDatos());
					//oos.flush();
				}
				else if( m.getTipo().equals("MENSAJE_LISTA_USUARIOS")){
					//crear un mensaje con la informacion de usuarios en sistema
					//envio mensaje confirmacion lista usuarios fout
					System.out.println("Enviando lista de usuarios conectados");
					oos.writeObject(new Mensaje("MENSAJE_CONFIRMACION_LISTA_USUARIOS", serv.getUsers()));
				}
				else if( m.getTipo().equals("MENSAJE_CERRAR_CONEXION")){
					//eliminar inforacion del usuario
					serv.deleteUser((String) m.getDatos());
					//envio mensaje confirmacion cerrar conexion fout
					oos.writeObject(new Mensaje("MENSAJE_CONFIRMACION_DESCONEXION", m.getDatos()));
					
				}
				else if( m.getTipo().equals("MENSAJE_PEDIR_FICHERO")){
					//buscar usuario que contiene el fichero y obtener fout2
					String archivo = m.getDatos().toString();
					String owner = serv.getOwner(archivo);
					System.out.println("Buscando el archivo " + archivo + " en el sistema...el propietario es: " + owner);
					if(owner != null) {
						int port = serv.getNextPort();
						ObjectOutputStream fout2 = serv.get_oos_de_usuario(owner);
						//envio mensaje MENSAJE_EMITIR_FICHERO por fout2
						fout2.writeObject(new Mensaje("MENSAJE_EMITIR_FICHERO", port));
						
						oos.writeObject(new Mensaje("MENSAJE_PREPARADO_SERVIDORCLIENTE", port));
					}		
					
				}
				else if( m.getTipo().equals("MENSAJE_PREPARADO_CLIENTESERVIDOR")){
					//buscar fout1 (flujo del cliente al que hay que enviar la informacion)
					//envio fout1 mensaje MENSAJE_PREPARADO_SERVIDORCLIENTE
				}
				else if(m.getTipo().equals("MENSAJE_INICIALIZAR_FICHEROS")) {
					System.out.println("Inicializando ficheros...");
					HashMap ficheros = (HashMap) m.getDatos();
					//System.out.println(ficheros);
					serv.setUserFiles(ficheros);
					oos.writeObject(new Mensaje("MENSAJE_CONFIRMACION_INICIALIZAR_FICHEROS"));					
				}
			

				
			}catch(IOException e){
				e.printStackTrace();
			}catch (ClassNotFoundException e){
				e.printStackTrace();
			}
		}
		
		try {
			//se saleeeeeeee
			this.oos.close();
			this.ois.close();
			this.client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
