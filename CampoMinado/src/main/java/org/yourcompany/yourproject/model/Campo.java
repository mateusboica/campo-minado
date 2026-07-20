package org.yourcompany.yourproject.model;

public class Campo {

    public boolean temMina;
    public boolean estaAberto;
    public boolean estaMarcado;
    public Integer quantasMinasVizinhas;

    public Campo() {
    }

    public Campo(boolean temMina, boolean estaAberto, boolean estaMarcado, Integer quantasMinasVizinhas) {
        this.temMina = temMina;
        this.estaAberto = estaAberto;
        this.estaMarcado = estaMarcado;
        this.quantasMinasVizinhas = quantasMinasVizinhas;
    }

}
