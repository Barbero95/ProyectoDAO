package Proyecto;

public class Objeto {

    private int id;

    private String nombre;

    private String tipo;

    private String descripcion;

    private int valor;

    private int coste;

    public Objeto() {
    }

    public Objeto(String nombre, String tipo, String descripcion, int valor, int coste) {

        this.nombre = nombre;

        this.tipo = tipo;

        this.descripcion = descripcion;

        this.valor = valor;

        this.coste = coste;



    }

    public void mostrarPorPantalaObjeto() {
        System.out.println("NOMBRE: "+getNombre());
        System.out.println("Tipo: "+getTipo());
        System.out.println("Descripcion: "+getDescripcion());
        System.out.println("Valor: "+getValor());
        System.out.println("Coste: "+getCoste());


    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
