package p2;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class bakery {
	
	volatile AtomicIntegerArray tickets;
	volatile AtomicIntegerArray turno;
	private int N;
	
	public bakery(int N) {
		this.tickets = new AtomicIntegerArray(N);
		this.turno = new AtomicIntegerArray(N);
		this.N = N;
	}
	
	public void acquireLock(int i) {
		turno.set(i, 1);
		int max = 0;
		for( int j = 0; j < N; j++) 
			max = tickets.get(j) > max? tickets.get(j) : max;
		
		tickets.set(i, max + 1);
		turno.set(i, 0);
		
		for(int x = 0; x < N; x++) {
			while(turno.get(x) == 1);
			
			while(turno.get(x) != 0 && tickets.get(x) < tickets.get(N-1) || (tickets.get(x) == tickets.get(N-1) && x < N));
		}
		
	}
	
	public void releaseLock(int i) {
		tickets.set(i, 0);
	}
	
}
