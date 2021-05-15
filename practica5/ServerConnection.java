import java.io.*;
import java.net.*;
import java.util.*;

public class ServerConnection implements Runnable{
	private Socket server;
	private BufferedReader in;

	public ServerConnection(Socket s) throws IOException{
		this.server = s;
		in = new BufferedReader(new InputStreamReader(server.getInputStream()));
	}

	public void run() {
			String serverResponse = null;
			try{
				while(true){
					serverResponse = in.readLine();
					if (serverResponse == null) break;
					System.out.println("Server says: " + serverResponse);
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
