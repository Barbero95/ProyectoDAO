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

import TO.BestFriendsUsersTO;
import TO.BestUserTO;
import enums.enumPlaces;
import enums.enumStudy;
import enums.enumTypeItem;
import exceptions.ContactException;
import exceptions.InventoryException;
import exceptions.UserException;

public class UsuarioDAO {





    //INSERTS
    public void insertUser(Usuario u) throws SQLException, UserException, InventoryException, ContactException {
        if (existUser(u)) {
            throw new UserException(1);
        }
        String query = "INSERT INTO user VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = Conectar.connection.prepareStatement(query);

        ps.setString(1, u.getNombre());
        ps.setString(2, u.getPassword());
        ps.setInt(3, u.getVida());
        ps.setInt(4, u.getAtaque());
        ps.setInt(5, u.getDefensa());
        ps.setInt(6, u.getResistencia());

        ps.executeUpdate();
        ps.close();

        inventoryDAO.insertUsersInventory(u);
        contactDAO.insertUserContacts(u);
    }

    //UPDATES
    /**
     * Dado un usuario i un nombre de usuario antiguo se modifica la contraseña
     * i el nombre del usuario
     *
     * @param username nombre del usuario guardado en la bbdd
     * @param acctUser usuario modificado
     * @throws SQLException
     * @throws UserException
     */
    public void modificarUsuario(String username, Usuario acctUser) throws SQLException, UserException {
        Statement st = ConectDAO.connection.createStatement();
        try {

            String updateExp = "update stucomcrossing.user set password='" + acctUser.getPassword() + "', username='" + acctUser.getNombre() + "' where username='" + username + "'";
            st.executeUpdate(updateExp);

        } catch (SQLException ex) {
            throw new UserException(2);
        } finally {
            st.close();
        }
    }

    /**
     * Funcion que recibe u Usuario i un
     *
     * @param u
     * @param place
     * @throws SQLException
     * @throws UserException
     */
    public void modificarSitioUsu(Usuario u, enumPlaces place) throws SQLException, UserException {
        Statement st = ConectDAO.connection.createStatement();
        try {
            String updatePlace = "update user set place='" + place.place() + "' where username='" + u.getNombre() + "'";
            st.executeUpdate(updatePlace);
        } catch (SQLException ex) {
            throw new UserException(3);
        } finally {
            st.close();
        }
    }

    //SELECTS
    public boolean validateUser(String username, String password) throws SQLException {
        boolean validar = false;
        String select = "SELECT * FROM User WHERE username='" + username + "' AND password='" + password + "'";
        Statement st = Conectar.connection.createStatement();

        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            validar = true;
        }
        rs.close();
        st.close();

        return validar;
    }


    /**
     * Funcion que comprueva si un usuario existe en la base de datos
     *
     * @param u
     * @return
     * @throws SQLException
     */
    private boolean existUser(Usuario u) throws SQLException {
        String select = "SELECT * FROM User WHERE username='" + u.getNombre() + "'";
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

    /*
    public User findUserByName(String name) throws UserException, SQLException {
        /*
        He hecho la versin Lazy, que dado un usuario nos devuelve con todo el listado de Item i  todo el Listado de Contactos
         */
    /*
    User u = new User();
    Statement st = ConectDAO.connection.createStatement();
    String select = "Select * from user where username='" + name + "'";
    ResultSet rs = st.executeQuery(select);

        if (rs.next()) {
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setLvl(rs.getInt("level"));
        enumPlaces p = enumPlaces.AULA_22;
        p.setName(rs.getString("place"));
        u.setPlace(p);
        u.setPoints(rs.getInt("points"));
        u.setStucoins(rs.getInt("stucoins"));

        //Seleccion del Inventario del Usuario
        ArrayList<Inventory> i = inventoryDAO.getInventoryByUsername(name);
        u.setInventory(i);

        //Seleccion de los Contactos del Usuario
        ArrayList<Contact> c = contactDAO.getContactByUsername(name);
        u.setContacts(c);

    } else {
        throw new UserException(2);
    }

        rs.close();
        st.close();
        return u;
}

    public List<BestUserTO> ranking() throws SQLException {
        String select = "select username, user.level from stucomcrossing.user order by level desc limit 10;";
        Statement st = ConectDAO.connection.createStatement();
        ResultSet rs = st.executeQuery(select);
        List<BestUserTO> ranking = new ArrayList<>();
        while (rs.next()) {
            BestUserTO r = new BestUserTO(rs.getString("username"), rs.getInt("level"));
            ranking.add(r);
        }
        rs.close();
        st.close();
        return ranking;
    }

    public List<BestFriendsUsersTO> rankingAmigos() throws SQLException {
        String select = "select user, count(*) as friends from contact group by user order by friends desc";
        Statement st = ConectDAO.connection.createStatement();
        ResultSet rs = st.executeQuery(select);
        List<BestFriendsUsersTO> rankingAmigos = new ArrayList<>();
        while (rs.next()) {
            BestFriendsUsersTO r = new BestFriendsUsersTO(rs.getString("user"), rs.getInt("friends"));
            rankingAmigos.add(r);
        }
        rs.close();
        st.close();
        return rankingAmigos;
    }

    /**
     * Funcion que nos devielve todos los Characters que no tiene un usuario en contactos
     * @param u Usuario
     * @return Characters no en contactos
     * @throws SQLException
     */
    /*
    public List<Character> noContactos(User u) throws SQLException {
        String select = "select * from stucomcrossing.character where stucomcrossing.character.name not in (select stucomcrossing.contact.character from contact where user = '" + u.getUsername() + "')";
        Statement st = ConectDAO.connection.createStatement();
        ResultSet rs = st.executeQuery(select);
        List<Character> noContactos = new ArrayList<>();
        while (rs.next()) {
            enumStudy study = enumStudy.ASIX;
            study.setStudy(rs.getString("study"));

            enumPlaces place = enumPlaces.AULA_22;
            place.setName(rs.getString("place"));

            enumTypeItem ti = enumTypeItem.APUNTES;
            ti.setType(rs.getString("preference"));

            Character r = new Character(rs.getString("name"), study, place, ti);
            noContactos.add(r);
        }
        rs.close();
        st.close();
        return noContactos;
    }

    public void giveItemToCharacter(User u, Item i, Character c){
        ConectDAO.connection.setAutoCommit(false);
        try{

        }finally{
            ConectDAO.connection.setAutoCommit(true);
        }
    }
     */
}
