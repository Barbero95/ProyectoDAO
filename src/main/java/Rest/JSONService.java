package Rest;
import DAOs.*;
import Proyecto.*;
import com.google.gson.JsonObject;
import retrofit2.http.Body;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/json")
public class JSONService {



    protected Mundo mundo;

    public JSONService() {
        mundo = Singleton.getInstance().getMundo();
    }
    @GET
    @Path("/consultarUsuarioDAO/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfoUsuario(@PathParam("user") String user) throws SQLException, ExceptionDAO {
        Usuario u = mundo.consultarUsuarioDAO(user);
        return Response.status(201).entity(u).build();

    }
    @GET
    @Path("/consultarObjetoDAO/{obj}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfoObjeto(@PathParam("obj") int obj) throws SQLException, ExceptionDAO {
        return Response.status(201).entity(mundo.consultarObjetoDAO(obj)).build();
    }

    //le pasamos el nombre de usuario y el identificador del objeto
    @GET
    @Path("/infoObjeto/{user}/{obj}")
    @Produces(MediaType.APPLICATION_JSON)
    public Objeto getInfoObjDeUser(@PathParam("user") String user,@PathParam("obj") int nomObj) throws SQLException, ExceptionDAO {
        Usuario u = mundo.consultarUsuarioDAO(user);
        if (u==null){
            return null;
            //return Response.status(409).entity("User already exists").build();
        } else{
            return mundo.consultarObjetoDeUsuarioDAO(u, nomObj );
        }
    }
    //para ver como me enseña una lista de strings
    @GET
    @Path("/pruebaLista")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getListaObjetosUsuario() {
        List<String> lista = new ArrayList<>();
        lista.add("David");
        lista.add("password");
        return lista;
    }
    //lista de los objetos que tiene un usuario
    @GET
    @Path("/listaObjetosUsuario/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Objeto> getListaObjetosUsuario(@PathParam("user") String usuario) throws SQLException, ExceptionDAO {
        return mundo.getListaObjetosUsuario(usuario);
    }
    //Dame la posicion del mapa y el mapa donde esta el jugador
    @GET
    @Path("/damePosYMapa/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getPosYmapa (@PathParam("user") String usuario) throws SQLException, ExceptionDAO {
        return mundo.damePosicionYMapa(usuario);
    }
    @POST
    @Path("/login")
    @Consumes (MediaType.APPLICATION_JSON)
    public Response login (Login log) throws SQLException, ExceptionDAO {
        boolean result = mundo.loginDAO(log);
        //RespLog resp = mundo.resp(log.getUsername());
        if (result){
            //login correct

            return Response.status(201).entity(result).build();
        }else{
            //user don't exist
            result = false;
            return Response.status(409).entity(result).build();
        }
    }
    @POST
    @Path("/ponPosYmapa")
    @Consumes (MediaType.APPLICATION_JSON)
    public Response postPosYmapa (Usuario u) throws SQLException, ExceptionDAO {
        boolean result = mundo.ponPosYmapa(u.getNombre(),u.getPosX(),u.getPosY(),u.getMapaActual(),u.getVida());
        if (result){
            //se han colocado los datos correctamente
            return Response.status(201).entity(result).build();
        }else{
            //error
            result = false;
            return Response.status(409).entity(result).build();
        }
    }

    @POST
    @Path("/RestarDinero")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response DescontarDinero (Money u) throws SQLException, ExceptionDAO{
        boolean result = mundo.ModificarDinero(u.getUsername(), u.getMoney());
        if (result){
            //se han colocado los datos correctamente
            return Response.status(201).entity(result).build();
        }else{
            //error
            result = false;
            return Response.status(409).entity(result).build();
        }
    }

    @POST
    @Path("/ModificarAtaque")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ModificarAtak (Usuario u) throws SQLException, ExceptionDAO{
        int i = 1;
        boolean result = mundo.ModificarAtaque(u.getNombre(), u.getAtaque());
        boolean result2 = mundo.añadirObjetoAUsuarioDAO(u.getNombre(), i);
        if (result){
            //se han colocado los datos correctamente
            return Response.status(201).entity(result).build();
        }else{
            //error
            result = false;
            return Response.status(409).entity(result).build();
        }
    }

    @POST
    @Path("/ModificarDefensaEscudo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ModificarDefEsc (Usuario u) throws SQLException, ExceptionDAO{
        int i = 2;
        boolean result = mundo.ModificarDefensa(u.getNombre(), u.getDefensa());
        boolean result2 = mundo.añadirObjetoAUsuarioDAO(u.getNombre(), i);
        if (result){
            //se han colocado los datos correctamente
            return Response.status(201).entity(result).build();
        }else{
            //error
            result = false;
            return Response.status(409).entity(result).build();
        }
    }

    @POST
    @Path("/ModificarDefensaArmadura")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ModificarDefArmadura (Usuario u) throws SQLException, ExceptionDAO{
        int i = 3;
        boolean result = mundo.ModificarDefensa(u.getNombre(), u.getDefensa());
        boolean result2 = mundo.añadirObjetoAUsuarioDAO(u.getNombre(), i);
        if (result){
            //se han colocado los datos correctamente
            return Response.status(201).entity(result).build();
        }else{
            //error
            result = false;
            return Response.status(409).entity(result).build();
        }
    }

    @POST
    @Path("/ModificarDefensaCasco")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ModificarDef (Usuario u) throws SQLException, ExceptionDAO{
        int i = 5;
        boolean result = mundo.ModificarDefensa(u.getNombre(), u.getDefensa());
        boolean result2 = mundo.añadirObjetoAUsuarioDAO(u.getNombre(), i);
        if (result){
            //se han colocado los datos correctamente
            return Response.status(201).entity(result).build();
        }else{
            //error
            result = false;
            return Response.status(409).entity(result).build();
        }
    }

    @POST
    @Path("/ModificarResis")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ModificarRes (Usuario u) throws SQLException, ExceptionDAO{
        int i = 4;
        boolean result = mundo.ModificarRes(u.getNombre(), u.getResistencia());
        boolean result2 = mundo.añadirObjetoAUsuarioDAO(u.getNombre(), i);
        if (result){
            //se han colocado los datos correctamente
            return Response.status(201).entity(result).build();
        }else{
            //error
            result = false;
            return Response.status(409).entity(result).build();
        }
    }
/*
    @POST
    @Path("/addObjectAUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AddObj (Usuario u, int i) throws SQLException, ExceptionDAO{
        boolean result = mundo.añadirObjetoAUsuarioDAO(u.getNombre(), i);
        if (result){
            //se han colocado los datos correctamente
            return Response.status(201).entity(result).build();
        }else{
            //error
            result = false;
            return Response.status(409).entity(result).build();
        }
    }
*/
    @POST
    @Path("/consultarUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consultaInfoUser (Login name) throws SQLException, ExceptionDAO {
        Usuario u = mundo.consultarUsuarioDAO(name.getUsername());
        if (u == null) {
            return Response.status(409).entity(u).build();
        } else {
            u.setPassword("***");
            return Response.status(201).entity(u).build();
        }
    }
    @POST
    @Path("/newUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUserDAO(Usuario user) throws SQLException, ExceptionDAO {
        boolean result = mundo.crearUsuarioDAO(user);
        if(result) {
            return Response.status(201).entity(result).build();
        } else {
            //"User already exists"
            result = false;
            return Response.status(409).entity(result).build();
        }
    }
    @POST
    @Path("/newObject")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newObjectDAO(Objeto obj) throws SQLException, ExceptionDAO {
        boolean result = mundo.crearObjetoDAO(obj);
        if(result) {
            return Response.status(201).entity(result).build();
        } else {
            return Response.status(409).entity(result).build();
        }
    }

    @POST
    @Path("/addObjectAUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addObjectAUserDAO ( List<String> datos) throws SQLException, ExceptionDAO  {
        String user = datos.get(0);
        String idObj = datos.get(1);
        int id =  Integer.parseInt(idObj);
        boolean result = mundo.añadirObjetoAUsuarioDAO(user, id);
        if (result){
            return Response.status(201).entity(result).build();

        }else{
            result=false;
            return Response.status(409).entity(result).build();
        }
    }


    // como ya estara logeado no hace falta pedir el password con el username ya directamente eliminamos el usuario
    @POST
    @Path("/deleteUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser (String user) throws SQLException, ExceptionDAO {
        Usuario usuario = this.mundo.consultarUsuarioDAO(user);
        if (usuario == null) {
            return Response.status(409).entity("User don't exist").build();
        } else {
            boolean resp = mundo.eliminarUsuarioDAO(user);
            if (resp){
                return Response.status(201).entity("User removed correctly").build();
            }else{
                return Response.status(201).entity("Error to remove").build();
            }
        }
    }
    //eliminamos un objeto de la base de datos
    @POST
    @Path("/deleteObject")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteObject (int idObjeto) throws SQLException, ExceptionDAO {
        Objeto obj = mundo.consultarObjetoDAO(idObjeto);
        if (obj == null) {
            return Response.status(409).entity("Object don't exist").build();
        } else {
            boolean resp = mundo.eliminarObjetoDAO(obj);
            if (resp){
                return Response.status(201).entity("Object removed correctly").build();
            }else {
                return Response.status(409).entity("Error to remove").build();
            }
        }
    }
    @POST
    @Path("/deleteObjectUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteObectUser (List<String> datos)throws SQLException, ExceptionDAO {
        String user = datos.get(0);
        String idObj = datos.get(1);
        int id =  Integer.parseInt(idObj);
        Usuario usuario = mundo.consultarUsuarioDAO(user);
        if (usuario == null) {
            return Response.status(409).entity("User don't exist").build();
        } else {
            Objeto obj = mundo.consultarObjetoDAO(id);
            if(obj==null){
                return Response.status(409).entity("Object don't exist").build();
            }else{
                boolean resp = mundo.eliminarObjetoDeUsuarioDAO(usuario,id);
                if(resp){
                    return Response.status(201).entity("Object removed correctly").build();
                } else {
                    return Response.status(201).entity("Error to removed").build();
                }
            }
        }
    }
    @POST
    @Path("/transferObject")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response transferObjectUsers (List<String> datos)throws SQLException, ExceptionDAO {
        String usuarioOrigen = datos.get(0);
        String usuarioDestino = datos.get(1);
        String idObj = datos.get(2);
        int id =  Integer.parseInt(idObj);
        Usuario userO = mundo.consultarUsuarioDAO(usuarioOrigen);
        Usuario userD = this.mundo.consultarUsuarioDAO(usuarioDestino);
        Objeto obj = this.mundo.consultarObjetoDeUsuarioDAO(userO,id);
        if (userO==null){
            return Response.status(409).entity("User don't exist").build();
        } else{
            if (obj==null){
                return Response.status(409).entity("This Object don't exist for this User").build();
            } else{
                if (userD==null){
                    return Response.status(409).entity("User don't exist").build();
                } else{
                    boolean resp = mundo.transferirObjetoEntreUsuariosDAO(userO, userD,obj);
                    if(resp){
                        return Response.status(201).entity("transfer correctly").build();
                    }else{
                        return Response.status(201).entity("Error transfer").build();
                    }

                }
            }
        }
    }
    /////////////////////////////////////////////////////////////////////////////
    //sin base de datos
    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUser(@PathParam("id") String id) {
        return mundo.consultarUsuario(id);
    }

    @GET
    @Path("/obj/{user}/{obj}")
    @Produces(MediaType.APPLICATION_JSON)
    public Objeto getObj(@PathParam("user") String user,@PathParam("obj") String nomObj) {
        Usuario u = mundo.consultarUsuario(user);
        if (u==null){
            return null;
            //return Response.status(409).entity("User already exists").build();
        } else{
            return mundo.consultarObjetoDeUsuario(u, nomObj );
        }

    }
    @POST
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(Usuario user) {
        boolean result = mundo.crearUsuario(user);

        if(result) {
            return Response.status(201).entity("User added correctly").build();
        } else {
            return Response.status(409).entity("User already exists").build();
        }

    }
    @POST
    @Path("/newobj/{user}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newObj (@PathParam("user") String user, Objeto o) {
        Usuario u = mundo.consultarUsuario(user);
        if (u == null) {
            return Response.status(409).entity("User don't exist").build();
        } else {
            mundo.añadirObjetoAUsuario(u, o);
            return Response.status(201).entity("Object added correctly").build();
        }
    }
    @POST
    @Path("/elimobj/{obj}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response elimObj (@PathParam("obj") String objecto, Usuario user) {
        Usuario usuario = this.mundo.consultarUsuario(user.getNombre());
        if (usuario == null) {
            return Response.status(409).entity("User don't exist").build();
        } else {
            mundo.eliminarObjetosDeUsuario(user, objecto);
            return Response.status(201).entity("Object removed correctly").build();
        }
    }
    @POST
    @Path("/elimuser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response elimUser (Usuario user) {
        Usuario usuario = this.mundo.consultarUsuario(user.getNombre());
        if (usuario == null) {
            return Response.status(409).entity("User don't exist").build();
        } else {
            mundo.eliminarUsuario(user.getNombre());
            return Response.status(201).entity("User removed correctly").build();
        }
    }
    //versio 1 te el problema que els noms dels ususaris estan a la url
    @POST
    @Path("/transf/{userO}/{userD}")
    @Consumes(MediaType.APPLICATION_JSON)
    //public Response transf (Usuario origen, Usuario destino, Objeto o) {
    public Response transf (@PathParam("userO") String usuarioOrigen, @PathParam("userD") String usuarioDestino, Objeto obj) {
        Usuario userO = this.mundo.consultarUsuario(usuarioOrigen);
        Usuario userD = this.mundo.consultarUsuario(usuarioDestino);
        if (userO==null){
            return Response.status(409).entity("User don't exist").build();
        } else{
            // falta comprobar que el objeto existe para este usuario esta puesto en la version 2 ya que sino lo marca
            // todo como duplicado
            if (userD==null){
                return Response.status(409).entity("User don't exist").build();
            } else{
                mundo.transferirObjetoEntreUsuarios(userO, userD, obj);
                return Response.status(201).entity("transfer correctly").build();
            }
        }
    }

    //versio 2 paso el nom del objecte i paso per el body una llista de usuaris en el ordre de primer el origen i despres desti
    @POST
    @Path("/transferenciaObjeto/{obj}")
    @Consumes(MediaType.APPLICATION_JSON)
    //public Response transf (Usuario origen, Usuario destino, Objeto o) {
    public Response transferencia (@PathParam("obj") String objeto, List<Usuario> listaUsuarios) {
        Usuario usuarioOrigen = listaUsuarios.get(0);
        Usuario usuarioDestino = listaUsuarios.get(1);
        Usuario userO = this.mundo.consultarUsuario(usuarioOrigen.getNombre());
        Usuario userD = this.mundo.consultarUsuario(usuarioDestino.getNombre());
        Objeto obj = this.mundo.consultarObjetoDeUsuario(userO, objeto);
        if (userO==null){
            return Response.status(409).entity("User don't exist").build();
        } else{
            if (obj==null){
                return Response.status(409).entity("This Object don't exist for this User").build();
            } else{
                if (userD==null){
                    return Response.status(409).entity("User don't exist").build();
                } else{
                    mundo.transferirObjetoEntreUsuarios(userO, userD, obj);
                    return Response.status(201).entity("transfer correctly").build();
                }
            }
        }
    }
}