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
				if(request.contains("name")){
					out.println(getRandomName());
				}else if (request.startsWith("say")){
					int firstSpace = request.indexOf(" ");
					if (firstSpace != -1){
						outToAll(request.substring(firstSpace+1));
					}
				}else{
					out.println("Type 'tell me a name' to get a random name");
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

	private void outToAll(String msg){
		for(OyenteCliente c: clients){
			c.out.println(msg);
		}
	}

	private String getRandomName(){
		return "Pepe";
	}
}