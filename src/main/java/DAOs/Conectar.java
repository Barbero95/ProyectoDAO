package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Conectar {
    public static Connection connection;

    /**
     * Funcion para conectar a la bbdd
     *
     * @throws SQLException
     */
    public void conectar() throws SQLException {
//        String url = "jdbc:mysql://localhost:3306/proyecto";
//        String user = "root";
//        String pass = "david";

        String url = I18NManager.getInstance().getText("serverLinux", "T1");
        String user = I18NManager.getInstance().getText("serverLinux", "T2");
        String pass = I18NManager.getInstance().getText("serverLinux", "T3");

//        String url = I18NManager.getInstance().getText("local", "T1");
//        String user = I18NManager.getInstance().getText("local", "T2");
//        String pass = I18NManager.getInstance().getText("local", "T3");

        connection = DriverManager.getConnection(url, user, pass);
    }

    /**
     *  Funcion para desconectar de la bbdd
     *
     *  @throws SQLException
     */

    public void desconectar() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
