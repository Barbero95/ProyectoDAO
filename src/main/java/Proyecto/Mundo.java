package Proyecto;

import DAOs.*;
import com.google.gson.internal.bind.SqlDateTypeAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mundo {
    private List<Usuario> usuarioList = new ArrayList<Usuario>();
    private HashMap<String,Usuario> mapaUsuarios = new HashMap<String,Usuario>();
    private HashMap<String,Objeto> mapaObjetos = new HashMap<>();
    UsuarioDAO userDao = new UsuarioDAO();
    ObjetoDAO objDAO = new ObjetoDAO();

    Conectar connect = new Conectar();


    public Mundo() {

    }
    public boolean loginDAO (Login log)throws SQLException, ExceptionDAO{
        connect.conectar();
        boolean result = userDao.validateUser(log.getUsername(), log.getPassword());
        connect.desconectar();
        return result;
    }
    //enviamos ya tod la lista de objetos y el ataque y defensa del usuario para ir ya avanzando,
    //modificamos la contraseña para no revelarla
    public Usuario damePosicionYMapa (String username)throws SQLException, ExceptionDAO{
        connect.conectar();
        Usuario u = userDao.posYmapa(username);
        u.setPassword("***");
        connect.desconectar();
        u.setObjetoList(getListaObjetosUsuario(username));
        return u;
    }
    public boolean ponPosYmapa (String username,int x, int y, int mapa)throws SQLException, ExceptionDAO{
        connect.conectar();
        boolean result = userDao.modPosYMapa(x,y,mapa,username);
        connect.desconectar();
        return result;
    }

    public boolean ModificarDinero (String username, int money)throws  SQLException, ExceptionDAO{
        connect.conectar();
        boolean result = userDao.modificarDinero(username, money);
        connect.desconectar();
        return result;
    }

    public boolean ModificarAtaque (String username, int ataque)throws  SQLException, ExceptionDAO{
        connect.conectar();
        boolean result = userDao.modificarAtaque(username, ataque);
        connect.desconectar();
        return result;
    }

    public RespLog resp (String username) throws SQLException, ExceptionDAO {
        Usuario u = consultarUsuarioDAO(username);
        RespLog respLog = new RespLog(u.getNombre(),u.getMoney());
        return respLog;
    }
    public boolean crearObjetoDAO (Objeto obj)throws SQLException, ExceptionDAO{
        connect.conectar();
        boolean resp = objDAO.insertObject(obj);
        int idObj = objDAO.darElIdentificador(obj.getNombre());
        obj.setId(idObj);
        String r = String.valueOf(idObj);
        mapaObjetos.put(r,obj);
        connect.desconectar();
        return resp;
    }
    public boolean crearUsuarioDAO (Usuario user) throws SQLException, ExceptionDAO{
        connect.conectar();
        boolean resp = userDao.insertUser(user);
        connect.desconectar();
        return resp;
    }
    public Usuario consultarUsuarioDAO (String userName) throws SQLException, ExceptionDAO{
        Usuario usuario;
        connect.conectar();
        usuario = userDao.infoUser(userName);
        connect.desconectar();
        return usuario;
    }
    public Objeto consultarObjetoDAO (int id)throws SQLException, ExceptionDAO{
        Objeto obj;
        connect.conectar();
        obj = objDAO.returnObject(id);
        connect.desconectar();
        return obj;
    }

    public List<Objeto> getListaObjetosUsuario (String userName) throws SQLException, ExceptionDAO {
        Usuario user = consultarUsuarioDAO(userName);
        connect.conectar();
        List<Objeto> listaObjetos = objDAO.dameInventario(user) ;
        connect.desconectar();
        return listaObjetos;
    }

    public boolean eliminarUsuarioDAO(String user)throws SQLException, ExceptionDAO {
        connect.conectar();
        boolean resp = userDao.deleteUser(user);
        connect.desconectar();
        return resp;
    }
    //elimina el objeto del juego (base de datos)
    public boolean eliminarObjetoDAO (Objeto obj)throws SQLException,ExceptionDAO{
        connect.conectar();
        boolean result = objDAO.deleteObject(obj);
        connect.desconectar();
        return result;

    }
    //añade a inventario la realcion entre el usuario y el objeto
    public void añadirObjetoAUsuarioDAO (Usuario u, Objeto o) throws SQLException, ExceptionDAO{
        connect.conectar();
        objDAO.insertInventario(u,o);
        connect.desconectar();
    }
    public Objeto consultarObjetoDeUsuarioDAO (Usuario user, int idObjeto)throws SQLException, ExceptionDAO{
        connect.conectar();
        Objeto obj = objDAO.objetoDeUnUsuario(user,idObjeto);
        connect.desconectar();
        return obj;
    }
    public boolean eliminarObjetoDeUsuarioDAO (Usuario u, int idObjeto)throws SQLException,ExceptionDAO{
        connect.conectar();
        boolean result = objDAO.sacarDeInventario(u,idObjeto);
        connect.desconectar();
        return result;
    }
    public boolean transferirObjetoEntreUsuariosDAO (Usuario origen, Usuario destino, Objeto obj)throws SQLException,ExceptionDAO {
        connect.conectar();
        boolean resp = objDAO.sacarDeInventario(origen, obj.getId());
        if (resp) {
            objDAO.insertInventario(destino, obj);
            Objeto comprobacion = consultarObjetoDeUsuarioDAO(destino, obj.getId());
            if (comprobacion == null) {
                return false;
            }else{
                return true;
            }
        }
        connect.desconectar();
        return resp;
    }


    ///////////////////////////////////////////////
    //sin base de datos
    public boolean crearUsuario(Usuario user) {
        //Usuario u = this.mapaUsuarios.get(user.getNombre());
        int encontrado=0;
        for(int i=0; i<usuarioList.size();i++)
        {
            if (usuarioList.get(i)==user)
                encontrado=1;

        }
        if(encontrado==1)
            return false;
        else
        {
            usuarioList.add(user);
            return true;
        }
    }
    public boolean eliminarUsuario(String nombre) {

        for (int i = 0; i<usuarioList.size();i++) {
            if (usuarioList.get(i).getNombre().equals(nombre)) {
                usuarioList.remove(i);
                return true;
            }
        }
        return false;
    }
    public Usuario consultarUsuario(String nombre) {
        int i=0;
        while(i<usuarioList.size())
        {
            if (usuarioList.get(i).getNombre().equals(nombre)) {
                return usuarioList.get(i);
            }
            i++;
        }
        return null;
    }

    public void añadirObjetoAUsuario(Usuario u, Objeto o){
        u.getObjetoList().add(o);
    }


    public Objeto consultarObjetoDeUsuario(Usuario u, String nombreObjeto){

        for(int j = 0; j< u.getObjetoList().size(); j++) {
            if (u.getObjetoList().get(j).getNombre().equals(nombreObjeto)) {
                return u.getObjetoList().get(j);
            }
        }
        return null;
    }


    public boolean eliminarObjetosDeUsuario(Usuario u, String nombreObjeto){
        Objeto o = consultarObjetoDeUsuario(u,nombreObjeto);
        u.getObjetoList().remove(o);
        return true;
    }




    public void transferirObjetoEntreUsuarios(Usuario origen, Usuario destino, Objeto o)
    {
        destino.getObjetoList().add(o);
        origen.getObjetoList().remove(o);
    }


}
