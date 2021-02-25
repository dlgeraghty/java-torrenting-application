package main;

import main.ops;

public class Parte2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 1000;
		int resultado = 0;
		
		ops a = new ops();
		
		Thread[] thread_pool = new Thread[N];
		for(int i = 0; i < N; i++) {
			thread_pool[i] =  new Thread(new Parte2().new RunnableTest(a));
			thread_pool[i].start();
		} 	
		for(int i = 0; i < N; i++) {
			try {
				thread_pool[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(a.getR());
	}
	
	private class RunnableTest implements Runnable{
		ops a;
		
		public RunnableTest(ops a) {
			this.a = a;
		}

		@Override
		public void run() {
			a.decremento();
			a.incremento();
		}
	}
	
	
}
