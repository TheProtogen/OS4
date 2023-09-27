package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

import controller.Carro;
import controller.Escuderia;

public class TreinoFormula1 {

	public static void main(String[] args) throws InterruptedException {
		Escuderia[] escuderias = new Escuderia[7];
        List<Carro> carrosNaPista = new ArrayList<>();
        Semaphore semaphore = new Semaphore(5); // Semáforo para controlar a entrada na pista

        // Inicializa as escuderias e carros
        for (int i = 0; i < escuderias.length; i++) {
            escuderias[i] = new Escuderia("Escuderia " + (i + 1));
            for (int j = 0; j < 2; j++) {
                Carro carro = new Carro(escuderias[i], semaphore);
                escuderias[i].adicionarCarro(carro);
            }
        }

        // Inicia o treino
        for (Escuderia escuderia : escuderias) {
            for (Carro carro : escuderia.getCarros()) {
                carro.start();
                carrosNaPista.add(carro);
            }
        }

        // Aguarda a conclusão do treino
        for (Carro carro : carrosNaPista) {
            carro.join();
        }

        // Exibe o grid de largada ordenado pelo tempo da melhor volta
        carrosNaPista.sort((c1, c2) -> Collections.min(c1.getTemposVoltas()) - Collections.min(c2.getTemposVoltas()));
        System.out.println("\nGrid de Largada:");
        for (int i = 0; i < carrosNaPista.size(); i++) {
            Carro carro = carrosNaPista.get(i);
            int melhorVolta = Collections.min(carro.getTemposVoltas());
            System.out.println("Posição " + (i + 1) + ": " + carro.escuderia.getNome() + " - Carro " + carro.getId() +
                    " - Melhor Volta: " + melhorVolta + " ms");
        }
	}

}
