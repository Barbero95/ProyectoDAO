package Proyecto;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class Mapa {
    Casilla[][] casillas;
    String nombre;
    int altura;
    int anchura;

    Logger logger = Logger.getLogger(Mapa.class.getName());

    public Mapa(int altura, int anchura){
        this.casillas = new Casilla[altura][anchura];
        this.altura = altura;
        this.anchura = anchura;
    }

    public boolean llenarMapa(List<Casilla> casillasArg){
        if(casillas.length == altura*anchura){
            return false;
        }
        for(int i = 0; i<altura; i++){
            for(int j = 0; j<anchura; j++){
                casillas[i][j] = casillasArg.get(i*anchura+j);
            }
        }
        return true;
    }
    public void mostrarMapa (){
        for(int i = 0; i<casillas.length; i++){
            for (int j=0; j<casillas[0].length;j++) {
                System.out.print(casillas[i][j].numero);
            }
            System.out.println();
        }
    }
}
