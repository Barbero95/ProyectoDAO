package Proyecto;

import Proyecto.Escenario;
import Proyecto.Mundo;
import Proyecto.Objeto;
import Proyecto.Usuario;

import java.util.Scanner;

public class MainBasico {
    public static Mundo mun = new Mundo();
    public static Escenario esce = new Escenario();


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("QUE QUIERES HACER?:");
            System.out.println("1 : Crear Usuario");
            System.out.println("2 : Eliminar Usuario");
            System.out.println("3 : Consultar Usuario");
            System.out.println("4 : Añadir objeto a un Usuario");
            System.out.println("5 : Consultar Objeto de un Usuario");
            System.out.println("6 : Eliminar Objetos de un Usuario");
            System.out.println("7 : Tranferir un Objeto entre Usuarios");
            System.out.println("8 : Crear Escenario");
            System.out.println("0 : Salir de la Aplicación\n");

            String input = scan.nextLine();
            int choice = 0;
            choice = Integer.parseInt(input);

            switch (choice) {
                case -1: {
                    System.out.println("Numero inválido");
                    break;
                }
                case 0: {
                    return;
                }
                case 1: {
                    System.out.print("Nombre: ");
                    String nombre = scan.nextLine();
                    System.out.print("Password: ");
                    String passwordd = scan.nextLine();
                    int Vida = 0;
                    int ataque = 0;
                    int defensa = 0;
                    int resistencia = 0;
                    int money = 1000;
                    while (true) {
                        try {
                            System.out.print("Vida: ");
                            String vid = scan.nextLine();
                            Vida = Integer.parseInt(vid);
                            System.out.print("Ataque: ");
                            String atac = scan.nextLine();
                            ataque = Integer.parseInt(atac);
                            System.out.print("Defensa: ");
                            String defens = scan.nextLine();
                            defensa = Integer.parseInt(defens);
                            System.out.print("Resistencia: ");
                            String resis = scan.nextLine();
                            resistencia = Integer.parseInt(resis);
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    Usuario u = new Usuario(nombre, passwordd, Vida, ataque, defensa, resistencia,money);
                    if (mun.crearUsuario(u) != false)
                        System.out.println("Usuario añadido.");
                    else
                        System.out.println("Usuario con nombre " + nombre + " ya existe.");
                    break;
                }
                case 2: {
                    System.out.print("Nombre de usuario que desea eliminar eliminar: ");
                    String nombre = scan.nextLine();
                    if (mun.eliminarUsuario(nombre) == true)
                        System.out.println("Usuario eliminado");
                    else
                        System.out.println("El usuario que desea eliminar no existe");
                    break;
                }
                case 3:{
                    System.out.print("Nombre de usuario a consultar: ");
                    String nombre = scan.nextLine();
                    Usuario u = mun.consultarUsuario(nombre);
                    if (u == null) {
                        System.out.println("Usuario no existente");
                    } else
                        u.mostrarPorPantala();

                    break;


                }
                case 4: {
                    System.out.print("Nombre de usuario a añadir el objeto: ");
                    String nombre = scan.nextLine();
                    Usuario u = mun.consultarUsuario(nombre);
                    if (u == null) {
                        System.out.println("Usuario no existente");
                        break;
                    }
                    System.out.print("Objeto que desea añadir: ");
                    String objeto = scan.nextLine();
                    String Tipo;
                    String Descripcion;
                    int valor;
                    int coste;

                    System.out.print("Tipo: ");
                    Tipo = scan.nextLine();
                    System.out.print("Descripcion: ");
                    Descripcion = scan.nextLine();
                    System.out.print("Valor: ");
                    String val = scan.nextLine();
                    valor = Integer.parseInt(val);
                    System.out.print("Coste: ");
                    String cost = scan.nextLine();
                    coste = Integer.parseInt(cost);
                    Objeto o = new Objeto(objeto, Tipo, Descripcion, valor, coste);

                    mun.añadirObjetoAUsuario(u, o);
                    System.out.println("Realizado con exito");
                    break;
                }
                case 5: {
                    System.out.print("Nombre de usuario: ");
                    String nombre = scan.nextLine();
                    Usuario u = mun.consultarUsuario(nombre);
                    System.out.print("Objeto que quieres consultar: ");
                    String object = scan.nextLine();
                    Objeto j = mun.consultarObjetoDeUsuario(u,object);
                    if (j == null) {
                        System.out.println("Objeto no existente");
                    }
                    else
                        j.mostrarPorPantalaObjeto();

                    break;
                }
                case 6: {
                    System.out.print("Nombre de usuario: ");
                    String nombre = scan.nextLine();
                    Usuario u = mun.consultarUsuario(nombre);
                    System.out.print("Objeto que quieres eliminar: ");
                    String object = scan.nextLine();
                    if(mun.eliminarObjetosDeUsuario(u,object) == true)
                        System.out.println("Objeto eliminado correctamente");
                    else
                        System.out.println("El objeto no existe");
                    break;
                }
                case 7: {
                    System.out.print("Nombre del usuario origen del objeto : ");
                    String nomo = scan.nextLine();
                    Usuario uo = mun.consultarUsuario(nomo);
                    System.out.print("Nombre del usuario destino del objeto: ");
                    String nomd = scan.nextLine();
                    Usuario ud = mun.consultarUsuario(nomd);

                    System.out.print("Objeto que deseas transferir");
                    String objeto = scan.nextLine();
                    String Tipo;
                    String Descripcion;
                    int valor;
                    int coste;

                    System.out.print("Tipo: ");
                    Tipo = scan.nextLine();
                    System.out.print("Descripcion: ");
                    Descripcion = scan.nextLine();
                    System.out.print("Valor: ");
                    String val = scan.nextLine();
                    valor = Integer.parseInt(val);
                    System.out.print("Coste: ");
                    String cost = scan.nextLine();
                    coste = Integer.parseInt(cost);
                    Objeto obj = new Objeto(objeto, Tipo, Descripcion, valor, coste);

                    mun.transferirObjetoEntreUsuarios(uo, ud, obj);
                    System.out.println("Objeto transferido correctamente\n");
                    /* Si cambiamos el void por el boolean
                    if(mun.transferirObjetoEntreUsuarios(uo, ud, obj) == true)
                        System.out.println("Objeto transferido correctamente\n");
                    else
                        System.out.println("El usuario origen/destino no existe o el objecto no existe\n");
                    break;
                    */
                    break;

                }
                case 8:{
                    System.out.print("Nombre para el escenario : ");
                    String esc = scan.nextLine();
                    System.out.print("Descripción  : ");
                    String des = scan.nextLine();
                    esce.pintar (esc,des);
                    break;
                }

            }
        }
    }
}
