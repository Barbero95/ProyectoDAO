package Proyecto;

import DAOs.*;

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


    public Mundo() {

    }
    public void crearObjetoDAO (Objeto obj)throws SQLException, ExceptionDAO{
        int idObj;
        try{
            objDAO.insertObject(obj);
            idObj = objDAO.darElIdentificador(obj.getNombre());
            obj.setId(idObj);
            //String r= String.valueOf(idObj);
            //mapaObjetos.put(r,obj);
        } catch (SQLException ex){
            throw new ExceptionDAO("No se ha podido crear");
        }
    }
    public void crearUsuarioDAO (Usuario user) throws SQLException, ExceptionDAO{
        try{
            userDao.insertUser(user);
        } catch (SQLException ex){
            throw new ExceptionDAO("No se ha podido connectar");
        }
    }
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
    public void eliminarUsuarioDAO(Usuario user)throws SQLException, ExceptionDAO {
        try{
            userDao.deleteUser(user);
        } catch (SQLException ex){
            throw new ExceptionDAO("No se ha podido connectar");
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
    public Usuario consultarUsuarioDAO (Usuario user) throws SQLException, ExceptionDAO{
        Usuario usuario=null;
        try{
            usuario = userDao.returnUser(user.getNombre(),user.getPassword());
        } catch (SQLException ex){
            throw new ExceptionDAO("No se ha podido connectar");
        }
        finally {
            return usuario;
        }
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
    //añade a inventario la realcion entre el usuario y el objeto
    public void añadirObjetoAUsuarioDAO (Usuario u, Objeto o) throws SQLException, ExceptionDAO{
        try{
            objDAO.insertInventario(u,o);
        } catch (SQLException ex){
            throw new ExceptionDAO("No se ha podido añadir objeto");
        }
    }
    public void añadirObjetoAUsuario(Usuario u, Objeto o){
        u.getObjetoList().add(o);
    }

    public Objeto consultarObjetoDeUsuarioDAO (Usuario user, int idObjeto)throws SQLException, ExceptionDAO{
        Objeto obj=null;
        try{
            obj = objDAO.objetoDeUnUsuario(user,idObjeto);
        } catch (SQLException ex){
            throw new ExceptionDAO("No se ha podido consultar el objeto");
        }
        finally {
            return obj;
        }
    }
    public Objeto consultarObjetoDeUsuario(Usuario u, String nombreObjeto){

        for(int j = 0; j< u.getObjetoList().size(); j++) {
            if (u.getObjetoList().get(j).getNombre().equals(nombreObjeto)) {
                return u.getObjetoList().get(j);
            }
        }
        return null;
    }

    public boolean eliminarObjetosDeUsuarioDAO (Usuario u, int idObjeto)throws SQLException,ExceptionDAO{
        boolean result = false;
        try{
            result = objDAO.sacarDeInventario(u,idObjeto);
        } catch (SQLException ex){
            throw new ExceptionDAO("No se ha podido eliminar");
        }
        finally {
            return result;
        }
    }
    public boolean eliminarObjetosDeUsuario(Usuario u, String nombreObjeto){
        Objeto o = consultarObjetoDeUsuario(u,nombreObjeto);
        u.getObjetoList().remove(o);
        return true;
    }

    //elimina el objeto del juego (base de datos)
    public boolean eliminarObjeto (Objeto obj)throws SQLException,ExceptionDAO{
        boolean result = false;
        try{
            result = objDAO.deleteObject(obj);
        } catch (SQLException ex){
            throw new ExceptionDAO("No se ha podido eliminar objeto");
        }
        finally {
            return result;
        }
    }


    public void transferirObjetoEntreUsuarios(Usuario origen, Usuario destino, Objeto o)
    {
        destino.getObjetoList().add(o);
        origen.getObjetoList().remove(o);
    }
    public void transferirObjetoEntreUsuariosDAO (Usuario origen, Usuario destino, Objeto obj)throws SQLException,ExceptionDAO{
        try{
            objDAO.sacarDeInventario(origen,obj.getId());
            objDAO.insertInventario(destino,obj);
        } catch (SQLException ex){
            throw new ExceptionDAO("No se ha podido connectar");
        }
    }

}
