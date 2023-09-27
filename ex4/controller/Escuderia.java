package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Escuderia {
    private String nome;
    private List<Carro> carros = new ArrayList<>();

    public Escuderia(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarCarro(Carro carro) {
        carros.add(carro);
    }

    public List<Carro> getCarros() {
        return carros;
    }
}
