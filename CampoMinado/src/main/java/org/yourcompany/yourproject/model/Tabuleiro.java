package org.yourcompany.yourproject.model;

import java.util.LinkedHashMap;
import java.util.Random;

import org.yourcompany.yourproject.controller.CampoController;

public class Tabuleiro {

    public Integer eixoX;
    public Integer eixoY;
    private final Integer quantasBombasPorJogo = 10;
    
    
    Random random = new Random();


    public Tabuleiro() {
    }

    public Tabuleiro(Integer x, Integer y) {
        this.eixoX = x;
        this.eixoY = y;
    }

    private final LinkedHashMap<Tabuleiro, Campo> tabuleiroGerado = new LinkedHashMap<>();

    public void criarTabuleiro() {
        for (int i = 1; i <= eixoY; i++) {
            for (int o = 1; o <= eixoX; o++) {
                tabuleiroGerado.put(new Tabuleiro(i, o),
                        new Campo(false, false, false, 0));
            }
        }
        
        Integer bombasGeradas = 0;
        while (bombasGeradas < quantasBombasPorJogo) {
            Tabuleiro pos = new Tabuleiro(random.nextInt(eixoY) + 1, random.nextInt(eixoX) + 1);
            if (!tabuleiroGerado.get(pos).temMina) {
                tabuleiroGerado.get(pos).temMina = true;
                bombasGeradas++;
            }
        }
        for (int i = 1; i <= eixoY; i++) {
            for (int o = 1; o <= eixoX; o++) {
                Tabuleiro pos = new Tabuleiro(i, o);
                int quantasMinas = CampoController.descobrirQuantasMinasVizinhas(pos, tabuleiroGerado);
                tabuleiroGerado.get(pos).quantasMinasVizinhas = quantasMinas;
            }
        }
    }

    public void resetarTabuleiro(){
        tabuleiroGerado.clear();
        criarTabuleiro();
    }

    public LinkedHashMap<Tabuleiro, Campo> getHashMap() {
        return tabuleiroGerado;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tabuleiro other = (Tabuleiro) obj;
        return eixoX.equals(other.eixoX) && eixoY.equals(other.eixoY);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + eixoX.hashCode();
        result = 31 * result + eixoY.hashCode();
        return result;
    }

}
