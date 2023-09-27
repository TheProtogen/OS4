package view;

import controller.NewThreads;

public class Main3 {

	//esse jeito sem run() é mais tranquilo de manusear
	
	public static void main(String[] args) {
		NewThreads conta = new NewThreads(1, 1000);

		for (int i = 0; i < 20; i++) {
            int codigoConta = (int) (Math.random() * 2) + 1;
            int valor = (int) (Math.random() * 1000) - 500;

            Thread thread = new Thread(() -> {
                conta.realizarTransacao(codigoConta, valor);
            });
            thread.start();
        }
	}
}
