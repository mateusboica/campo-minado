package org.yourcompany.yourproject.model;

public class Campo {

    public boolean temMina;
    public boolean estaAberto;
    public boolean estaMarcado;
    public boolean minaVizinha;
    
    public Campo(boolean temMina, boolean estaAberto, boolean estaMarcado, boolean minaVizinha){
        this.temMina = temMina;
        this.estaAberto = estaAberto;
        this.estaMarcado = estaMarcado;
        this.minaVizinha = minaVizinha;
    }
    

    public boolean temMinaVizinha(){
        return minaVizinha;
    }

}
