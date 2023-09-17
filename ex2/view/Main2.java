package view;

import java.util.concurrent.Semaphore;

import controller.ThreadPessoas;

public class Main2 {

	public static void main(String[] args) {
		
		//Área das Customizações
		final int pessoas = 4; //Pessoas andando no corredor
		Semaphore semaforo = new Semaphore(1); //Pessoas que podem usar a porta

		//Área das Threads
		for (int i = 0; i < pessoas; i++) {
			Thread thread = new ThreadPessoas(semaforo);
			thread.start();
		}
		
	}

}
