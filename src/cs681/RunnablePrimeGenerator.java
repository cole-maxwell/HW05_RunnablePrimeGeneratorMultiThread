package cs681;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import java.time.Instant;
import java.time.Duration;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {
	
	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}
	
	public void run() {
		generatePrimes();
	}

	public static void main(String[] args) {


		RunnablePrimeGenerator gen1 = new RunnablePrimeGenerator(1L, 1000000L);
		RunnablePrimeGenerator gen2 = new RunnablePrimeGenerator(1000001L, 2000000L);
		
		Thread t1 = new Thread(gen1);
		Thread t2 = new Thread(gen2);
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {}

		Instant start = Instant.now();    

		gen1.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		gen2.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );

		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();


		long primeNum = gen1.getPrimes().size() + gen2.getPrimes().size();
		System.out.println("\n" + primeNum + " prime numbers are found in total.");
		System.out.println("Elapsed time using two threads: " + timeElapsed + " miliseconds.");
        
	}

}
