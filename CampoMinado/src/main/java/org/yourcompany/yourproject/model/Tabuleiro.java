package org.yourcompany.yourproject.model;

import java.util.HashMap;
import java.util.Random;

public class Tabuleiro {

    public Integer eixoX;
    public Integer eixoY;

    int contador = 0;

    Random random = new Random();

    public Tabuleiro(Integer x, Integer y) {
        this.eixoX = x;
        this.eixoY = y;
    }

    public HashMap<Tabuleiro, Campo> tabuleiroGerado = new HashMap<>();

    public void criarTabuleiro() {
        for (int i = 1; i <= eixoY; i++) {
            for (int o = 1; o <= eixoX; o++) {
                tabuleiroGerado.put(new Tabuleiro(i, o), new Campo(random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean()));
                System.out.print("#");
            }
            System.out.println();
        }
    }

}
