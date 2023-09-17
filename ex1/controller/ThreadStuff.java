package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadStuff extends Thread{

	private Semaphore semaforo;
	private int carros;
	private final String[] direct = {"esquerda","direita","cima","baixo"};
	private static int sentido;
	Random random = new Random();
	
	public ThreadStuff(int carros, Semaphore semaforo) {
		this.carros = carros;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		try {
			semaforo.acquire();
			carroAnda();
			//sleep(500); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		
		
		
	}
	
	public void carroAnda() {
		//vrooom Vrooomm VROOOOMMM
		sentido = random.nextInt(4);
		System.out.println("Thread n."+getId()+" andou foi para a "+direct[sentido]);
	}
	
}
