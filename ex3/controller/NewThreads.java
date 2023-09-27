package controller;

import java.util.concurrent.Semaphore;

public class NewThreads {

	private int codigoConta;
    private int saldo;
    private Semaphore saqueSemaphore = new Semaphore(1); // Semáforo para saques
    private Semaphore depositoSemaphore = new Semaphore(1); // Semáforo para depósitos
    
    public NewThreads(int codigoConta, int saldo) {
        this.codigoConta = codigoConta;
        this.saldo = saldo;
    }

    
    public void realizarTransacao(int codigoConta, int valor) {
        if (codigoConta == this.codigoConta) {
            if (valor < 0) {
                realizarSaque(valor);
            } else {
                realizarDeposito(valor);
            }
        }
    }

    private void realizarSaque(int valor) {
        try {
            saqueSemaphore.acquire(); // Adquire o semáforo de saque
            System.out.println("Iniciando saque de " + Math.abs(valor) + ". Saldo atual: " + saldo+
            		"\nConta: "+this.codigoConta+"\n");

            if (saldo >= Math.abs(valor)) {
                saldo -= Math.abs(valor);
                System.out.println("Saque de " + Math.abs(valor) + " realizado. Saldo atual: " + saldo+
                		"\nConta: "+this.codigoConta+"\n");
            } else {
                System.out.println("Saldo insuficiente para o saque de " + Math.abs(valor)+
                		"\nConta: "+this.codigoConta+"\n");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            saqueSemaphore.release(); // Libera o semáforo de saque
        }
    }

    private void realizarDeposito(int valor) {
        try {
            depositoSemaphore.acquire(); // Adquire o semáforo de depósito
            System.out.println("Iniciando depósito de " + valor + ". Saldo atual: " + saldo+
            		"\nConta: "+this.codigoConta+"\n");

            saldo += valor;
            System.out.println("Depósito de " + valor + " realizado. Saldo atual: " + saldo+
            		"\nConta: "+this.codigoConta+"\n");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            depositoSemaphore.release(); // Libera o semáforo de depósito
        }
    }
	    
	}
	
