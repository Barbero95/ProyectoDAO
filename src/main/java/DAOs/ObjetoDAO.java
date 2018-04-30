package DAOs;
/*
 * DAO para funciones de objeto
 */


import Proyecto.Objeto;
import Proyecto.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjetoDAO {

    //INSERTS
    public void insertObject(Objeto obj) throws SQLException, ExceptionDAO {
        if (existObjct(obj)) {
            throw new ExceptionDAO("Ya existe el objeto");
        }
        String query = "INSERT INTO objeto VALUES (?,?,?,?,?)";
        PreparedStatement ps = Conectar.connection.prepareStatement(query);

        ps.setString(1, obj.getNombre());
        ps.setString(2, obj.getTipo());
        ps.setString(3, obj.getDescripcion());
        ps.setInt(4, obj.getValor());
        ps.setInt(5, obj.getCoste());

        ps.executeUpdate();
        ps.close();

    }

    //Select

    public boolean selectObjeto (String username, String password) throws SQLException {
        boolean validar = false;
        return validar;
    }
//UPDATES
        //
    public void modificarObjeto(int identificador, Objeto acctObj) throws SQLException, ExceptionDAO{
        Statement st = Conectar.connection.createStatement();
        try {

            String updateExp = "update objeto set valor='" + acctObj.getValor() + "', nombre='" + acctObj.getNombre() + "' where id='" + identificador + "'";
            st.executeUpdate(updateExp);

        } catch (SQLException ex) {
            throw new ExceptionDAO("No se ha podido modificar el objecto");
        } finally {
            st.close();
        }
    }

    public boolean validateObjeto(String Objname) throws SQLException {
        boolean validar = false;
        String select = "SELECT * FROM objecto WHERE Objname='" + Objname + "'";
        Statement st = Conectar.connection.createStatement();

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
        Statement st = Conectar.connection.createStatement();
        boolean exist = false;
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            exist = true;
        }
        rs.close();
        st.close();
        return exist;
    }

    //inventari
    public void insertInventario (Usuario user, Objeto obj) throws SQLException, ExceptionDAO{
        String query = "INSERT INTO objeto VALUES (?,?)";
        PreparedStatement ps = Conectar.connection.prepareStatement(query);

        ps.setString(1, user.getNombre());
        ps.setInt(2, obj.getId());

        ps.executeUpdate();
        ps.close();
    }


    //montar inventario

    public List<Objeto> insertInventario (Usuario user) throws SQLException, ExceptionDAO{
        String query = "SELECT objeto FROM inventario WHERE usuario='" + user.getNombre() + "'";
        List<Objeto> listaObjetos = new ArrayList<Objeto>();
        Statement st = Conectar.connection.createStatement();

        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            listaObjetos.add(returnObject(rs.getInt("objeto")));
        }
        rs.close();
        st.close();

        return listaObjetos;
    }

    private Objeto returnObject (int identificador) throws SQLException, ExceptionDAO{
        String query = "SELECT * FROM objeto WHERE id='" + identificador + "'";
        Statement st = Conectar.connection.createStatement();

        ResultSet rs = st.executeQuery(query);
        Objeto obj = new Objeto();

        if(rs.next()){
            obj.setNombre(rs.getString("nombre"));
            obj.setTipo(rs.getString("tipo"));
            obj.setDescripcion(rs.getString("descripcion"));
            obj.setValor(rs.getInt("valor"));
            obj.setCoste(rs.getInt("coste"));
        }
        rs.close();
        st.close();

        return obj;
    }
}



