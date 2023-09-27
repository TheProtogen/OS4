package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Carro extends Thread {
    public Escuderia escuderia;
    private int numeroVolta = 0;
    private List<Integer> temposVoltas = new ArrayList<>();
    private Semaphore semaphore;

    public Carro(Escuderia escuderia, Semaphore semaphore) {
        this.escuderia = escuderia;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire(); // Aguarda permissão para entrar na pista
            while (numeroVolta < 3) {
                int tempoVolta = simularTempoVolta();
                temposVoltas.add(tempoVolta);
                System.out.println(escuderia.getNome() + " - Carro " + this.getId() + " - Volta " + (numeroVolta + 1) + ": " + tempoVolta + " ms");
                numeroVolta++;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release(); // Libera a pista após completar as voltas
        }
    }

    private int simularTempoVolta() {
        // Simula o tempo de volta (entre 1 e 1000 ms)
        return new Random().nextInt(1000) + 1;
    }

    public List<Integer> getTemposVoltas() {
        return temposVoltas;
    }
}