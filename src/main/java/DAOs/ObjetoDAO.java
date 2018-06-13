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
    public boolean insertObject(Objeto obj) throws SQLException, ExceptionDAO {
        boolean resp;
        if (existObjct(obj)) {
            return false;
            //throw new ExceptionDAO("Ya existe el objeto");
        } else {
            String query = "INSERT INTO objeto VALUES (NULL,?,?,?,?,?)";
            PreparedStatement ps = Conectar.connection.prepareStatement(query);

            //ps.setInt(1, obj.getId());
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getTipo());
            ps.setString(3, obj.getDescripcion());
            ps.setInt(4, obj.getValor());
            ps.setInt(5, obj.getCoste());

            ps.executeUpdate();
            ps.close();
            resp = true;
        }
        return resp;

    }

    //Select
    public boolean validateObjeto(String Objname) throws SQLException {
        boolean validar = false;
        String select = "SELECT * FROM objeto WHERE nombre='" + Objname + "'";
        Statement st = Conectar.connection.createStatement();

        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            validar = true;
        }
        rs.close();
        st.close();

        return validar;
    }

    //para tal como cree el objeto me de el identificador
    public int darElIdentificador(String Objname)throws SQLException{
        int idObjeto = 0;
        String select = "SELECT id FROM objeto WHERE nombre='" + Objname + "'";
        Statement st = Conectar.connection.createStatement();
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            idObjeto = rs.getInt("id");
        }
        rs.close();
        st.close();

        return idObjeto;
    }

    public Objeto returnObject (int identificador) throws SQLException, ExceptionDAO{
        String query = "SELECT * FROM objeto WHERE id='" + identificador + "'";
        Statement st = Conectar.connection.createStatement();

        ResultSet rs = st.executeQuery(query);
        Objeto obj = new Objeto();

        if(rs.next()){
            obj.setId(rs.getInt("id"));
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


    //DELETE
    public boolean deleteObject (Objeto obj) throws SQLException {
        boolean result = false;
        Statement st = Conectar.connection.createStatement();
        try {
            String delete = "DELETE FROM objeto WHERE id='" + obj.getId() + "'";
            st.executeUpdate(delete);
            result = true;
        } catch (SQLException ex) {
            throw new ExceptionDAO("No se ha podido borrar el objeto");
        } finally {
            st.close();
            return result;
        }
    }
    //UPDATES
        //son ejemplos debemos poner los que podemos hacer
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

    /*
     * Funcion que comprueva si un objeto existe en la base de datos
     *
     * @param u
     * @return
     * @throws SQLException
     */
    private boolean existObjct(Objeto o) throws SQLException {
        String select = "SELECT * FROM objeto WHERE nombre='" + o.getNombre() + "'";
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
    public boolean insertInventario (Usuario user, Objeto obj) throws SQLException{
        boolean faena = true;
        String query = "INSERT INTO inventario VALUES (NULL,?,?)";
        PreparedStatement ps = Conectar.connection.prepareStatement(query);

        ps.setString(1, user.getNombre());
        ps.setInt(2, obj.getId());

        ps.executeUpdate();
        ps.close();
        return faena;
    }
    public boolean sacarDeInventario (Usuario user, int idObjeto)throws SQLException{
        boolean result = false;
        Statement st = Conectar.connection.createStatement();
        try {
            String delete = "DELETE FROM inventario WHERE objeto='" + idObjeto +  "'";
            st.executeUpdate(delete);
            result = true;
        } catch (SQLException ex) {
            throw new ExceptionDAO("No se ha podido borrar el objeto");
        } finally {
            st.close();
            return result;
        }
    }

    //montar inventario en una lista de objetos

    public List<Objeto> dameInventario (Usuario user) throws SQLException, ExceptionDAO{
        Statement st = Conectar.connection.createStatement();
        String query = "SELECT objeto FROM inventario WHERE usuario='" + user.getNombre() + "'";
        List<Objeto> listaObjetos = new ArrayList<Objeto>();
        ResultSet rs = st.executeQuery(query);
        try {
            while (rs.next()) {
                listaObjetos.add(returnObject(rs.getInt("objeto")));
            }
        } catch (SQLException ex) {
            throw new ExceptionDAO("No se ha podido borrar el objeto");
        } finally {
            rs.close();
            st.close();
            return listaObjetos;
        }



    }

    //dame un objeto de un usuario
    public Objeto objetoDeUnUsuario (Usuario user, int idObj)throws SQLException, ExceptionDAO{
        String query = "SELECT objeto FROM inventario WHERE usuario='" + user.getNombre() + "'";
        Statement st = Conectar.connection.createStatement();

        ResultSet rs = st.executeQuery(query);
        Objeto objeto = null;

        if(rs.next()){
            objeto = returnObject(rs.getInt("objeto"));
        }
        if(idObj != objeto.getId()){
            objeto = null;
        }
        rs.close();
        st.close();

        return objeto;
    }

    public boolean objetoDeUnUsuarioWEB (Usuario user, int idObj)throws SQLException, ExceptionDAO {
        boolean resultado = true;

        String query = "SELECT objeto FROM inventario WHERE usuario='" + user.getNombre() + "'";
        Statement st = Conectar.connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        Objeto objeto = null;

        if (rs.next()) {
            objeto = returnObject(rs.getInt("objeto"));
        }
        if(idObj != objeto.getId()){
            resultado = false;
        }

        rs.close();
        st.close();

        return resultado;
    }

}



