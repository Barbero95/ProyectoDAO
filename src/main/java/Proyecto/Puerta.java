package Proyecto;

public class Puerta extends Casilla {

    Estado estado;
    enum Estado{Abierta, cerrada}

    public int numero(){
        return 2;
    }
}
