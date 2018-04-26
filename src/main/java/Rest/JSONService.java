package Rest;
import Proyecto.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/json")
public class JSONService {



    protected Mundo mundo;

    public JSONService() {
        //tracks = Singleton.getInstance().getTrack();
        mundo = Singleton.getInstance().getMundo();

        Usuario u = new Usuario("aa", "aaa",1,1,1,1);
        mundo.crearUsuario(u);

    }

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

    //versio 2 paso el nom del objecte y paso per el body una llista de usuaris en el ordre de primer el origen y despues destino
    @POST
    @Path("/transferenciaObjeto/{obj}")
    @Consumes(MediaType.APPLICATION_JSON)
    //public Response transf (Usuario origen, Usuario destino, Objeto o) {
    public Response transf (@PathParam("obj") String objeto, List<Usuario> listaUsuarios) {
        Usuario usuarioOrigen = listaUsuarios.get(0);
        Usuario usuarioDestino = listaUsuarios.get(1);
        Usuario userO = this.mundo.consultarUsuario(usuarioOrigen.getNombre());
        Usuario userD = this.mundo.consultarUsuario(usuarioDestino.getNombre());
        Objeto obj = this.mundo.consultarObjetoDeUsuario(userO, objeto);
        if (userO==null){
            return Response.status(409).entity("User don't exist").build();
        } else{
            if (obj==null){
                return Response.status(409).entity("This Object don't exist for this User ").build();
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