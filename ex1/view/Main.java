package view;

import java.util.concurrent.Semaphore;

import controller.ThreadStuff;

public class Main {

	public static void main(String[] args) {
		
		//Areas de Customização
		int carros = 10; //quantos carros vão passsar no cruzamento
		Semaphore semaforo = new Semaphore(1); //quantos carros vão passar por direção
		
		//Area das Threads
		for (int i = 0; i < carros; i++) {
			Thread thread = new ThreadStuff(carros,semaforo);
			thread.start();
		}
		
	}

}
