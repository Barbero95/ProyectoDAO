package DAOs;
/*
 * DAO para funciones de objeto
 */
package DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjetoDAO {

    //INSERTS
    public void insertUser(Proyecto.Objeto o) throws SQLException/*, InventoryException, ContactException*/ {
        /*if (existUser(u)) {
            throw new UserException(1);
        }
        */
        String query = "INSERT INTO Objeto VALUES (?,?,?,?,?)";
        PreparedStatement ps = ConectDAO.connection.prepareStatement(query);

        ps.setString(1, o.getNombre());
        ps.setString(2, o.getTipo());
        ps.setString(3, o.getDescripcion());
        ps.setInt(4, o.getValor());
        ps.setInt(5, o.getCoste());


        ps.executeUpdate();
        ps.close();

        /*
        inventoryDAO.insertUsersInventory(u);
        */

    }
//UPDATES
    /*
     * Dado un objeto se modifica su valor
     *
     * @param username nombre del usuario guardado en la bbdd
     * @param acctUser usuario modificado
     * @throws SQLException
     * @throws UserException
     */
    public void modificarObjeto(String Objetoname, Proyecto.Objeto acctObj) throws SQLException{
        Statement st = ConectDAO.connection.createStatement();
        try {

            String updateExp = "update objeto set valor='" + acctObj.getValor() + "', nombre='" + acctObj.getNombre() + "' where username='" + Objetoname + "'";
            st.executeUpdate(updateExp);

        } catch (SQLException ex) {
            throw new UserException(2);
        } finally {
            st.close();
        }
    }

    public boolean validateObjeto(String Objname) throws SQLException {
        boolean validar = false;
        String select = "SELECT * FROM Object WHERE Objname='" + Objname + "'";
        Statement st = ConectDAO.connection.createStatement();

        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            validar = true;
        }
        rs.close();
        st.close();

        return validar;
    }

    /*
     * Funcion que comprueva si un objeto existe en la base de datos
     *
     * @param u
     * @return
     * @throws SQLException
     */
    private boolean existObjct(Proyecto.Objeto o) throws SQLException {
        String select = "SELECT * FROM Objeto WHERE username='" + o.getNombre() + "'";
        Statement st = ConectDAO.connection.createStatement();
        boolean exist = false;
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            exist = true;
        }
        rs.close();
        st.close();
        return exist;
    }


    }
}


