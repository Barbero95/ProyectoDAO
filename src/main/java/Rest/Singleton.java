package Rest;

import Proyecto.*;

public class Singleton {

    private static Singleton instance;
    private Singleton() {
        // Exists only to defeat instantiation.
    }
    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }



    private Mundo mundo = new Mundo();

    public Mundo getMundo() {
        return mundo;
    }
}