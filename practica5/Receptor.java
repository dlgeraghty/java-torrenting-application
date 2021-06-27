import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Receptor extends Thread{
	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private String fileName;
	
	public Receptor(Socket s, String f) throws IOException{
		this.s = s;
		this.fileName = f;
		//this.ois = new ObjectInputStream(s.getInputStream());
		//this.oos = new ObjectOutputStream(s.getOutputStream());
		//System.out.println("He creadoooooo los canales receptor");
	}
	
	public void run() {
		
		try {
			System.out.println("Pidiendo fichero..." + fileName);
			this.oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(new Mensaje("MENSAJE_PEDIR_FICHERO", fileName));
			this.ois = new ObjectInputStream(s.getInputStream());
			
			Mensaje response = (Mensaje) ois.readObject();
			
			if(response.getTipo().equals("MENSAJE_CONFIRMAR_FICHERO")) {
				//leer fichero
				String content = (String) response.getDatos();
				System.out.println("Contenido del archivo " + fileName + " :");
				System.out.println(content);
				
				sleep(10000);
				
				//cerrar conexion
				this.ois.close();
				this.oos.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}