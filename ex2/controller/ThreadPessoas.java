package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadPessoas extends Thread {

	private final int CORREDOR_METROS = 200;
	
	private int pessoaPos = 0;
	
	Semaphore semaforo;
	Random random = new Random();
	
	
	public ThreadPessoas(Semaphore semaforo) {
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		
		//RUNNNNNN, RUN HUMANS RUN
		while (pessoaPos < this.CORREDOR_METROS) {
			//this.CORREDOR_METROS quer dizer que cada pessoa tem seu próprio corredor
			try {
				sleep(1000); //1 segundo :')
				this.pessoaPos += random.nextInt(3)+4;
				if (this.pessoaPos > CORREDOR_METROS) {
					System.out.println("[ ~ ] Thread n."+getId()+" ia andar "+(this.pessoaPos - 
							CORREDOR_METROS+" metros á mais, e se reajustou"));
					this.pessoaPos = 200;
				}
				System.out.println(getId()+" caminhou: "+this.pessoaPos+" metros");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println("[ HALT ] Thread n."+getId()+" chegou na porta");
		
		//Comando para executar quando eles chegarem na porta
		try {
			semaforo.acquire();
			portaAction();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void portaAction() throws InterruptedException {
		System.out.println("[ X ] Thread n."+getId()+" está usando a porta!");
		sleep((random.nextInt(2)+1)*1000);
		System.out.println("[ O ] Thread n."+getId()+" deixou a porta livre agora");
		
	}
}