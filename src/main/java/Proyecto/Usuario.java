package Proyecto;

import java.util.LinkedList;
import java.util.List;

public class Usuario {

    private String nombre;
    private String password;
    private int vida;
    private int ataque;
    private int defensa;
    private int resistencia;
    private List<Objeto> objetoList = new LinkedList<Objeto>();

    public Usuario() {
    }

    public Usuario(String nombre, String password, int vida, int ataque, int defensa, int resistencia){
        this.nombre = nombre;
        this.password = password;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.resistencia = resistencia;
    }

    public void mostrarPorPantala() {
        System.out.println("NOMBRE: " + getNombre());
        System.out.println("CONTRASEÑA: " + getPassword());
        System.out.println("VIDA: " + getVida());
        System.out.println("ATAQUE: " + getAtaque());
        System.out.println("DEFENSA: " + getDefensa());
        System.out.println("RESISTENCIA: " + getResistencia());


    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public List<Objeto> getObjetoList() {
        return objetoList;
    }

    public void setObjetoList(List<Objeto> objetoList) {
        this.objetoList = objetoList;
    }
}
