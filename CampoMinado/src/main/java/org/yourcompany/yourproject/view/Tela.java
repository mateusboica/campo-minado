package org.yourcompany.yourproject.view;

import java.util.Scanner;

import org.yourcompany.yourproject.controller.CampoController;
import org.yourcompany.yourproject.model.Campo;
import org.yourcompany.yourproject.model.Tabuleiro;

public class Tela {

    int contador = 0;
    Scanner scanner = new Scanner(System.in);
    CampoController campoController = new CampoController();

    public void gerarTela() {
        Tabuleiro tabuleiro = new Tabuleiro(9, 9);
        tabuleiro.criarTabuleiro();

        while(true){

            System.out.println("----- Campo Minado -----");
            System.out.println("Legenda: ");
            System.out.println(" # = Campo fechado");
            System.out.println("   = Campo aberto");
            System.out.println(" X = Campo marcado\n");
            
        tabuleiro.getHashMap().forEach((key, value) -> {
            if (value.estaAberto && !value.estaMarcado && !value.temMina && value.quantasMinasVizinhas == 0) {
                System.out.print(" ");
                contador++;
            }
            if (value.estaAberto && value.quantasMinasVizinhas > 0 && !value.temMina) {
                System.out.print(" " + value.quantasMinasVizinhas + " ");
                contador++;
            }
            if (!value.estaAberto) {
                System.out.print(" # ");
                contador++;
            }
            if (value.estaAberto && value.estaMarcado) {
                System.out.print(" X ");
                contador++;
            }
            // System.out.println("Posição: (" + key.eixoX + ", " + key.eixoY + ") - Tem
            // Mina: " + value.temMina + ", Está Aberto: " + value.estaAberto + ", Está
            // Marcado: " + value.estaMarcado + ", Quantas Minas Vizinhas: " +
            // value.quantasMinasVizinhas);
            if (contador == 9) {
                System.out.println();
                contador = 0;
            }
        });

        System.out.println("O que deseja fazer? (1) Abrir campo (2) Marcar campo (3) Resetar tabuleiro (4) Sair");
        String acao = scanner.nextLine();
        if (acao.equals("1")) {

            System.out.println("Digite a posição que deseja abrir (x,y): ");
            String posicao = scanner.nextLine().trim();
            String[] coordenadas = posicao.split(",");
            int x = Integer.parseInt(coordenadas[0]);
            int y = Integer.parseInt(coordenadas[1]);

            Tabuleiro consulta = new Tabuleiro(x, y);
            Campo campoConsulta = tabuleiro.getHashMap().get(consulta);
            System.out.println("Tem mina? " + campoConsulta.temMina);

            if (campoConsulta.temMina) {
                campoConsulta.estaAberto = true;
                System.out.println("Você perdeu! Havia uma mina na posição (" + x + ", " + y + ")");
                tabuleiro.resetarTabuleiro();
                break;
            }
            campoController.abrirCampo(consulta, tabuleiro.getHashMap());
       
        }

        if(acao.equals("2")){
            System.out.println("Digite a posição que deseja marcar (x,y): ");
            String posicao = scanner.nextLine();
            String[] coordenadas = posicao.split(",");
            int x = Integer.parseInt(coordenadas[0]);
            int y = Integer.parseInt(coordenadas[1]);

            campoController.marcarCampo(new Tabuleiro(x, y), tabuleiro.getHashMap());

        }
        if(acao.equals("3")){
            tabuleiro.resetarTabuleiro();
        }

        if(acao.equals("4")){
            System.exit(0);
        }
    }
}
        // Integer qntdBombas = 0;
        // for(Tabuleiro key : tabuleiro.getHashMap().keySet()) {
        // if (tabuleiro.getHashMap().get(key).temMina) {
        // qntdBombas++;
        // }
        // }

        // CampoController testAbertura = new CampoController();

        // testAbertura.abrirCampo(new Tabuleiro(1, 1), tabuleiro.getHashMap());
        // System.out.println("Quantidade de Bombas: " + qntdBombas);
}
