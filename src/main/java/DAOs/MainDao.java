package DAOs;
import Proyecto.*;

import java.sql.SQLException;

public class MainDao {

    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        Usuario user;
        Objeto obj;
        user = new Usuario("Pitufo","x",1,1,1,1);
        obj = new Objeto("espada","fuego","esta espada esta muy rota",1,1);
        try {
            //mundo.crearUsuarioDAO(user);
            //mundo.crearObjetoDAO(obj);
            //mundo.añadirObjetoAUsuarioDAO(user,obj);
            mundo.eliminarUsuarioDAO(user);
            System.out.println("id objeto" + obj.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
    }
}
