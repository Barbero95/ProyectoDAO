package DAOs;
import Proyecto.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainDao {

    public static void main(String[] args) throws SQLException {
        Mundo mundo = new Mundo();
        Conectar connect = new Conectar();
        UsuarioDAO userDAO = new UsuarioDAO();
        ObjetoDAO objDAO = new ObjetoDAO();
        Usuario user;
        Objeto obj;
        user = new Usuario("Pitufo2","x",1,1,1,1,100,1,1,1);
        obj = new Objeto("espada","fuego","esta espada esta muy rota",1,1);
        List<Objeto> listaObjetos;
        try {
            //connect.conectar();
            //funciona
            //mundo.crearUsuarioDAO(user);
            //funciona
            mundo.eliminarUsuarioDAO(user.getNombre());
            //int modiId = objDAO.darElIdentificador(obj.getNombre());
            //obj.setId(modiId);
            //funciona
            //mundo.crearObjetoDAO(obj);

//            listaObjetos = new ArrayList<>(mundo.getListaObjetosUsuario(user.getNombre()));
//            System.out.println("1 objeto  " + listaObjetos.get(0));
            //mundo.añadirObjetoAUsuarioDAO(user,obj);
            //mundo.eliminarObjetosDeUsuarioDAO(user,1);
            //System.out.println("id objeto" + obj.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
//        finally {
//            connect.desconectar();
//        }
    }
}
