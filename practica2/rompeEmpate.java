package main;

import main.ops;

public class rompeEmpate{
	
	public static void main(String args[]){
		int N = 1000;

		ops a = new ops();

		Thread p1 = new Thread(new rompeEmpate().new SC(a));
		Thread p2 = new Thread(new rompeEmpate().new SC(a));

		try{
			p1.join();
			p2.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}

		System.out.println(a.getR());
	}

	private class SC implements Runnable{

		ops a;

		public SC(ops a){
			this.a = a;
		}

		public void run(){
			for( long i = 0; i < 1000000; i++){
				for( long j = 0; j < 1000000; j++){
					a.decremento();
					a.incremento();
				}
			}
		}
		
	}
}
