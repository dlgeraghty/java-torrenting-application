package p2;

import p2.ops;

public class rompeEmpate{
	
	public static void main(String args[]){
		int N = 10;

		ops a = new ops();
		bakery lock = new bakery(N);

		Thread[] thread_pool = new Thread[N];
		for(int i = 0; i < N; i++) {
			thread_pool[i] =  new Thread(new rompeEmpate().new SC(a, lock, i));
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

	private class SC implements Runnable{

		ops a;
		bakery l;
		int i;

		public SC(ops a, bakery lock, int i){
			this.a = a;
			this.l = lock;
			this.i = i;
		}

		public void run(){
			l.acquireLock(i);
			a.decremento();
			l.releaseLock(i);

			l.acquireLock(i);
			a.incremento();
			l.releaseLock(i);
				
		}
		
	}
}
