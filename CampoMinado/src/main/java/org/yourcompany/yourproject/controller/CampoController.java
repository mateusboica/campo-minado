package org.yourcompany.yourproject.controller;

import java.util.LinkedHashMap;

import org.yourcompany.yourproject.model.Campo;
import org.yourcompany.yourproject.model.Tabuleiro;


public class CampoController {

     static public Integer descobrirQuantasMinasVizinhas(Tabuleiro posicao, LinkedHashMap<Tabuleiro, Campo> acessarMap) {
        Integer quantasMinasVizinhas = 0;
        for (int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if (i == 0 && j == 0) {
                    continue;
                }
                Tabuleiro vizinho = new Tabuleiro(posicao.eixoX + i, posicao.eixoY + j);
                Campo campoVizinho = acessarMap.get(vizinho);
                if (campoVizinho != null && campoVizinho.temMina) {
                    quantasMinasVizinhas++;
                }
            }
        }
        return quantasMinasVizinhas;
    }

    public void abrirCampo(Tabuleiro posicao, LinkedHashMap<Tabuleiro, Campo> acessarMap) {
    Campo atual = acessarMap.get(posicao);

    if (atual == null || atual.estaAberto || atual.estaMarcado) {
        return; 
    }
    if (atual.temMina) {
        return;
    }
    atual.estaAberto = true;

    if (atual.quantasMinasVizinhas > 0) {
        return;
    }
    int[][] direcoes = {
        {-1, -1}, {-1, 0}, {-1, 1}, // Esquerda (cima, meio, baixo)
        { 0, -1},          { 0, 1}, // Cima e Baixo
        { 1, -1}, { 1, 0}, { 1, 1}  // Direita (cima, meio, baixo)
    };

    for (int[] dir : direcoes) {
        Tabuleiro vizinho = new Tabuleiro(posicao.eixoX + dir[0], posicao.eixoY + dir[1]);
        
        abrirCampo(vizinho, acessarMap);
    }
}
    public void marcarCampo(Tabuleiro posicao, LinkedHashMap<Tabuleiro, Campo> acessarMap){
        Campo atual = acessarMap.get(posicao);

        if (atual == null || atual.estaAberto) {
            return; 
        }
        atual.estaMarcado = !atual.estaMarcado;
    }
}
