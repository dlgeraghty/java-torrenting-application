import java.net.*;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class OyenteCliente extends Thread{
	private Socket client;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ArrayList<OyenteCliente> clients;

	public OyenteCliente(Socket clientSocket) throws IOException{
		this.client = clientSocket;
		this.oos = new ObjectOutputStream(clientSocket.getOutputStream());
		this.ois = new ObjectInputStream(clientSocket.getInputStream());
	}

	public void run(){
		boolean conexion = true;
		while(conexion){
			try{
				Mensaje m = (Mensaje) this.ois.readObject();
				if(m.getTipo().equals("MENSAJE_CONEXION")){
					//guardar informacion del usuario
					Servidor.addUser((String) m.getDatos());
					//envio mensaje confirmacion conexion fout
					oos.writeObject(new Mensaje("MENSAJE_CONFIRMACION_CONEXION"));
				}
				else if( m.getTipo().equals("MENSAJE_LISTA_USUARIOS")){
					//crear un mensaje con la informacion de usuarios en sistema
					//envio mensaje confirmacion lista usuarios fout
					//for(OyenteCliente c: clients){
					//	println(c.username);
					//}
				}
				else if( m.getTipo().equals("MENSAJE_CERRAR_CONEXION")){
					//eliminar inforacion del usuario
					//envio mensaje confirmacion cerrar conexion fout
				}
				else if( m.getTipo().equals("MENSAJE_PEDIR_FICHERO")){
					//buscar usuario que contiene el fichero y obtener fout2
					//envio mensaje MENSAJE_EMITIR_FICHERO por fout2
					//TODO:
					//decidir el tipo de archivo que vamos a usar, por ejemplo foto, video, musica,etc
					//y en base a eso lo tenemos que mandar por el mensaje
					//Tipo_de_archivo archivo = Tipo_de_arhivo.read(f);
					//por ejemplo podriamos hacer:
					/*
					BufferedImage imagen = ImageIO.read(new File("./Files/" + Cliente.getUsername() + "/" + fileName));
					this.oos.writeObject(new Mensaje("MENSAJE_EMITIR_FICHERO", imagen));
					sleep(200);
					conexion = false;
					*/
				}
				else if( m.getTipo().equals("MENSAJE_PREPARADO_CLIENTESERVIDOR")){
					//buscar fout1 (flujo del cliente al que hay que enviar la informacion)
					//envio fout1 mensaje MENSAJE_PREPARADO_SERVIDORCLIENTE
				}
			

				
			}catch(IOException e){
				e.printStackTrace();
			}catch (ClassNotFoundException e){
				e.printStackTrace();
			}
		}
		
		try {
			this.oos.close();
			this.ois.close();
			this.client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
