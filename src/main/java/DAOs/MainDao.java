package DAOs;
import Proyecto.*;

import java.sql.SQLException;

public class MainDao {

    public static void main(String[] args) throws SQLException {
        Mundo mundo = new Mundo();
        Conectar connect = new Conectar();
        UsuarioDAO userDAO = new UsuarioDAO();
        ObjetoDAO objDAO = new ObjetoDAO();
        Usuario user;
        Objeto obj;
        user = new Usuario("Pitufo2","x",1,1,1,1);
        obj = new Objeto("espada","fuego","esta espada esta muy rota",1,1);
        try {
            connect.conectar();
            //funciona
            //mundo.crearUsuarioDAO(user);
            //funciona
            //mundo.eliminarUsuarioDAO(user);
            int modiId = objDAO.darElIdentificador(obj.getNombre());
            obj.setId(modiId);
            //funciona
            //mundo.crearObjetoDAO(obj);


            mundo.añadirObjetoAUsuarioDAO(user,obj);

            //System.out.println("id objeto" + obj.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        finally {
            connect.desconectar();
        }
    }
}
