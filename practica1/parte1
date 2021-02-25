package main;



public class Parte1 {
	
	public static void main(String[] args) {

		int N = 10;
		Thread[] thread_pool = new Thread[N];
		for(int i = 0; i < N; i++) {
			thread_pool[i] = new Thread(new Parte1().new RunnableTest());
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

	}
	
	private class RunnableTest implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getId());
		}
	}
	
}
