import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Emisor extends Thread{
	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public Emisor(Socket s) throws IOException {
		this.s = s;
		this.ois = new ObjectInputStream(s.getInputStream());
		this.oos = new ObjectOutputStream(s.getOutputStream());
	}
	
	public void run() {
		
		
		boolean conexion = true;
		while(conexion) {
			try {
				Mensaje m = (Mensaje) this.ois.readObject();
				String fileName = (String) m.getDatos();
				System.out.println("Enviando fichero..." + fileName);
				if(m.getTipo().equals("MENSAJE_PEDIR_FICHERO")) {
					String content = Files.readString(Paths.get("./Archivos/" + Cliente.getUsername() + "/" + fileName));
					System.out.println("Enviando contenido del archivo " + fileName + " : \n" + content + "\n");
					//this.oos.writeObject(new Mensaje("MENSAJE_CONFIRMAR_FICHERO", content));
					this.oos.writeObject(new Mensaje("MENSAJE_CONFIRMAR_FICHERO", content));

					sleep(10000);
					
					conexion = false;
					
				}
			}
			catch(IOException e) {
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
}