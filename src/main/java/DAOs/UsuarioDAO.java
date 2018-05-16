package DAOs;

import Proyecto.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {

    //INSERTS
    public boolean insertUser(Usuario u) throws SQLException, ExceptionDAO {
        boolean resp = false;
        if (existUser(u)) {
            throw new ExceptionDAO("Usuario ya existe");
        } else {
            String query = "INSERT INTO usuario VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = Conectar.connection.prepareStatement(query);

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getPassword());
            ps.setInt(3, u.getVida());
            ps.setInt(4, u.getAtaque());
            ps.setInt(5, u.getDefensa());
            ps.setInt(6, u.getResistencia());

            ps.executeUpdate();
            ps.close();
            resp = true;
        }
        return resp;

    }

    //UPDATES
    /**
     * Dado un usuario i un nombre de usuario antiguo se modifica la contraseña
     * i el nombre del usuario
     *
     * @param username nombre del usuario guardado en la bbdd
     * @param acctUser usuario modificado
     * @throws SQLException
     * @throws ExceptionDAO
     */
    public void modificarUsuario(String username, Usuario acctUser) throws SQLException, ExceptionDAO {
        Statement st = Conectar.connection.createStatement();
        try {

            String updateExp = "update stucomcrossing.user set password='" + acctUser.getPassword() + "', username='" + acctUser.getNombre() + "' where username='" + username + "'";
            st.executeUpdate(updateExp);

        } catch (SQLException ex) {
            throw new ExceptionDAO("No se ha podido modificar el usuario");
        } finally {
            st.close();
        }
    }

    //SELECTS
    public boolean validateUser(String username, String password) throws SQLException {
        boolean validar = false;
        String select = "SELECT * FROM usuario WHERE username='" + username + "' AND password='" + password + "'";
        Statement st = Conectar.connection.createStatement();

        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            validar = true;
        }
        rs.close();
        st.close();

        return validar;
    }
    public int validarNombre (String username) throws SQLException, ExceptionDAO {
        int i = 0;
        String select = "SELECT * FROM usuario WHERE username='" + username +"'";
        Statement st = Conectar.connection.createStatement();
        ResultSet rs = st.executeQuery(select);
        try {

            if (rs.next()) {
                i = 1;
            }

        }catch (SQLException ex){
            throw new ExceptionDAO("No existe el usuario");
        }finally {
            rs.close();
            st.close();
            return i;
        }
    }
    public Usuario returnUser(String username, String password) throws SQLException {
        Usuario user = new Usuario();
        String select = "SELECT * FROM usuario WHERE username='" + username + "' AND password='" + password + "'";
        Statement st = Conectar.connection.createStatement();

        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            user.setNombre(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setVida(rs.getInt("vida"));
            user.setAtaque(rs.getInt("ataque"));
            user.setDefensa(rs.getInt("defensa"));
            user.setResistencia(rs.getInt("resistencia"));
        }
        rs.close();
        st.close();

        return user;
    }
    //solo con el nombre
    public Usuario infoUser(String username) throws SQLException {
        Usuario user = new Usuario();
        String select = "SELECT * FROM usuario WHERE username='" + username + "'";
        Statement st = Conectar.connection.createStatement();

        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            user.setNombre(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setVida(rs.getInt("vida"));
            user.setAtaque(rs.getInt("ataque"));
            user.setDefensa(rs.getInt("defensa"));
            user.setResistencia(rs.getInt("resistencia"));
        }
        rs.close();
        st.close();

        return user;
    }




    /**
     * Funcion que comprueva si un usuario existe en la base de datos
     *
     * @param u
     * @return
     * @throws SQLException
     */
    private boolean existUser(Usuario u) throws SQLException {
        String select = "SELECT * FROM usuario WHERE username='" + u.getNombre() + "'";
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

    //DELETE
    public boolean deleteUser(String user) throws SQLException, ExceptionDAO {
        boolean result = false;
        Statement st = Conectar.connection.createStatement();
        try {
            String delete = "DELETE FROM usuario WHERE username='" + user + "'";
            st.executeUpdate(delete);
            result = true;

        } catch (SQLException ex) {
            throw new ExceptionDAO("No se ha podido modificar el usuario");
        } finally {
            st.close();
            return result;
        }
    }

}
