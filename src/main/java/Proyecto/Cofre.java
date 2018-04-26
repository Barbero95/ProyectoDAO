package Proyecto;

public class Cofre extends Casilla{
    Tipo tipo;
    enum Tipo {armas, dinero, echizos}
    public int numero(){
        return 1;
    }
}
