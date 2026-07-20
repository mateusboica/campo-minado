package org.yourcompany.yourproject.view;

import org.yourcompany.yourproject.controller.CampoController;
import org.yourcompany.yourproject.model.Tabuleiro;

public class Tela {

    public void gerarTela() {
        Tabuleiro tabuleiro = new Tabuleiro(9, 9);
        tabuleiro.criarTabuleiro();

        tabuleiro.getHashMap().forEach((key, value) -> {
            System.out.println("Posição: (" + key.eixoX + ", " + key.eixoY + ") - Tem Mina: " + value.temMina + ", Está Aberto: " + value.estaAberto + ", Está Marcado: " + value.estaMarcado + ", Quantas Minas Vizinhas: " + value.quantasMinasVizinhas);
        });

        Integer qntdBombas = 0;
        for(Tabuleiro key : tabuleiro.getHashMap().keySet()) {
            if (tabuleiro.getHashMap().get(key).temMina) {
                qntdBombas++;
            }
        }

        CampoController testAbertura = new CampoController();

        testAbertura.abrirCampo(new Tabuleiro(1, 1), tabuleiro.getHashMap());
        System.out.println("Quantidade de Bombas: " + qntdBombas);
    }

}
