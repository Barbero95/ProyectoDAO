package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {
    public static Connection connection;


    /**
     * Funcion para conectar a la bbdd
     *
     * @throws SQLException
     */
    public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/stucomcrossing";
        String user = "root";
        String pass = "";
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
