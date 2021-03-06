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
            return resp;
            //throw new ExceptionDAO("Usuario ya existe");
        } else {
            String query = "INSERT INTO usuario VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = Conectar.connection.prepareStatement(query);

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getPassword());
            ps.setInt(3, u.getVida());
            ps.setInt(4, u.getAtaque());
            ps.setInt(5, u.getDefensa());
            ps.setInt(6, u.getResistencia());
            ps.setInt(7, u.getMoney());
            ps.setInt(8,u.getPosX());
            ps.setInt(9,u.getPosY());
            ps.setInt(10,u.getMapaActual());

            ps.executeUpdate();
            ps.close();
            resp = true;
        }
        return resp;

    }

    //UPDATES
    public boolean modificarAtaque(String username, int ataque) throws SQLException, ExceptionDAO {
        Statement st = Conectar.connection.createStatement();
        boolean result = false;
        try {
            String updateExp = "update usuario set ataque='" + ataque + "' where username='" + username + "'";
            st.executeUpdate(updateExp);
            result = true;
            return result;

        } catch (SQLException ex) {
            throw new ExceptionDAO("No se ha podido modificar el usuario");
        } finally {
            st.close();
            return result;
        }
    }
    public boolean modPosYMapa(int posX, int posY, int mapaActual, int vida, String username) throws SQLException, ExceptionDAO {
        Statement st = Conectar.connection.createStatement();
        boolean result=false;
        try {
            String updateExp = "update usuario set posx='" + posX + "', posy='" + posY + "', mapaactual='" + mapaActual + "', vida='" + vida + "' where username='" + username + "'";
            st.executeUpdate(updateExp);
            result=true;
        } catch (SQLException ex) {
            throw new ExceptionDAO("No se ha podido modificar el usuario");
        } finally {
            st.close();
            return result;
        }
    }
    public boolean modificarDefensa(String username, int defensa) throws SQLException, ExceptionDAO {
        Statement st = Conectar.connection.createStatement();
        boolean result = false;
        try {
            String updateExp = "update usuario set defensa='" + defensa + "' where username='" + username + "'";
            st.executeUpdate(updateExp);
            result = true;

        } catch (SQLException ex) {
            throw new ExceptionDAO("No se ha podido modificar el usuario");
        } finally {
            st.close();
            return result;
        }
    }

    public boolean modificarVida(String username, int vida) throws SQLException, ExceptionDAO {
        Statement st = Conectar.connection.createStatement();
        boolean result = false;
        try {
            String updateExp = "update usuario set vida='" + vida + "' where username='" + username + "'";
            st.executeUpdate(updateExp);
            result = true;

        } catch (SQLException ex) {
            throw new ExceptionDAO("No se ha podido modificar el usuario");
        } finally {
            st.close();
            return result;
        }
    }

    public boolean modificarResistencia(String username, int resistencia) throws SQLException, ExceptionDAO {
        Statement st = Conectar.connection.createStatement();
        boolean result = false;
        try {
            String updateExp = "update usuario set resistencia='" + resistencia + "' where username='" + username + "'";
            st.executeUpdate(updateExp);
            result = true;
            return result;

        } catch (SQLException ex) {
            throw new ExceptionDAO("No se ha podido modificar el usuario");
        } finally {
            st.close();
            return result;
        }
    }
    public boolean modificarDinero (String username, int moneyNow) throws SQLException, ExceptionDAO{
        Statement st = Conectar.connection.createStatement();
        boolean result = false;
        try {
            String updateExp = "update usuario set money='" + moneyNow + "' where username='" + username + "'";
            st.executeUpdate(updateExp);
            result = true;
            return result;

        } catch (SQLException ex) {
            throw new ExceptionDAO("No se ha podido modificar el usuario");
        } finally {
            st.close();
            return result;
        }
    }

    //SELECTS
    public boolean validateUser(String username, String password) throws SQLException {
        String select = "SELECT * FROM usuario WHERE username='" + username + "' AND password='" + password + "'";
        Statement st = Conectar.connection.createStatement();
        boolean exist = false;
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            return true;
        }
        rs.close();
        st.close();
        return exist;
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
            user.setMoney(rs.getInt("money"));
        }
        rs.close();
        st.close();

        return user;
    }
    //dame la posicion y mapa tambien enviaremos la info del jugador menos contra para luchar contra los malos
    public Usuario posYmapa(String username) throws SQLException{
        Usuario user = new Usuario();
        String select = "SELECT * FROM usuario WHERE username='" + username + "'";
        Statement st = Conectar.connection.createStatement();
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            user.setNombre(rs.getString("username"));
            user.setVida(rs.getInt("vida"));
            user.setAtaque(rs.getInt("ataque"));
            user.setDefensa(rs.getInt("defensa"));
            user.setResistencia(rs.getInt("resistencia"));
            user.setMoney(rs.getInt("money"));
            user.setPosX(rs.getInt("posx"));
            user.setPosY(rs.getInt("posy"));
            user.setMapaActual(rs.getInt("mapaactual"));
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
