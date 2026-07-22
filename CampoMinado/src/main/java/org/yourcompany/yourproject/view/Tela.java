package org.yourcompany.yourproject.view;

import java.util.Scanner;

import org.yourcompany.yourproject.controller.CampoController;
import org.yourcompany.yourproject.model.Campo;
import org.yourcompany.yourproject.model.Tabuleiro;

public class Tela {
    Tabuleiro tabuleiro = new Tabuleiro(9, 9);
    {
        tabuleiro.criarTabuleiro();
    }
    Scanner scanner = new Scanner(System.in);
    CampoController campoController = new CampoController();

    public void gerarTela() {
        System.out.println("----- Campo Minado -----");
        System.out.println("Legenda: ");
        System.out.println(" # = Campo fechado");
        System.out.println("   = Campo aberto");
        System.out.println(" X = Campo marcado\n");

        System.out.println("      x");
        System.out.print("     ");
        for (int coluna = 1; coluna <= tabuleiro.eixoX; coluna++) {
            System.out.printf(" %d ", coluna);
        }
        System.out.println();
        System.out.print("     ");
        for (int coluna = 1; coluna <= tabuleiro.eixoX; coluna++) {
            System.out.print(" _ ");
        }
        System.out.println();

        for (int linha = 1; linha <= tabuleiro.eixoY; linha++) {
            String indicadorY = linha == 1 ? "y " : "  ";
            System.out.printf("%s%d |", indicadorY, linha);

            for (int coluna = 1; coluna <= tabuleiro.eixoX; coluna++) {
                Campo campo = tabuleiro.getHashMap().get(new Tabuleiro(coluna, linha));

                if (campo.estaMarcado) {
                    System.out.print(" X ");
                } else if (!campo.estaAberto) {
                    System.out.print(" # ");
                } else if (campo.temMina) {
                    System.out.print(" 💣");
                } else if (campo.quantasMinasVizinhas > 0) {
                    System.out.print(" " + campo.quantasMinasVizinhas + " ");
                } else {
                    System.out.print("   ");
                }
            }

            System.out.println();
        }
    }

    public void rodarJogo() {
        while (true) {
            limparTela();
            gerarTela();
            System.out.println("");
            System.out.println("O que deseja fazer? (1) Abrir campo (2) Marcar campo (3) Resetar tabuleiro (4) Sair");
            String acao = scanner.nextLine();
            if (acao.equals("1")) {

                System.out.println("Digite a posição que deseja abrir (x,y) — x é a coluna e y é a linha: ");
                String posicao = scanner.nextLine().trim();
                String[] coordenadas = posicao.split(",");
                int x = Integer.parseInt(coordenadas[0]);
                int y = Integer.parseInt(coordenadas[1]);

                Tabuleiro consulta = new Tabuleiro(x, y);
                Campo campoConsulta = tabuleiro.getHashMap().get(consulta);

                if (campoConsulta.temMina) {
                    campoConsulta.estaAberto = true;
                    limparTela();
                    gerarTela();
                    System.out.println("\nVocê perdeu! Havia uma mina na posição (" + x + ", " + y + ")");
                    break;
                }
                campoController.abrirCampo(consulta, tabuleiro.getHashMap());
                if(campoController.venceuJogo(tabuleiro.getHashMap())){
                    System.out.println("Parabéns! Você venceu o jogo!!");
                    break;
                }
            }

            if (acao.equals("2")) {
                System.out.println("Digite a posição que deseja marcar (x,y) — x é a coluna e y é a linha: ");
                String posicao = scanner.nextLine();
                String[] coordenadas = posicao.split(",");
                int x = Integer.parseInt(coordenadas[0]);
                int y = Integer.parseInt(coordenadas[1]);

                campoController.marcarCampo(new Tabuleiro(x, y), tabuleiro.getHashMap());

            }
            if (acao.equals("3")) {
                tabuleiro.resetarTabuleiro();
            }

            if (acao.equals("4")) {
                System.exit(0);
            }
        }
    }

    public void limparTela() {
        try {
            String sistemaOperacional = System.getProperty("os.name");

            if (sistemaOperacional.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
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
