package Retrofit;
import Proyecto.*;
import Rest.*;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.*;
import javax.ws.rs.PathParam;
import java.io.IOException;
import java.util.List;

public class App 
{
    //public static final String API_URL = "https://api.github.com";
    public static final String API_URL = "http://localhost:8080/myapp/json";


    public static class Login{
        public final String username;

        public final String password;


        public Login(String username, String password) {
            this.username = username;

            this.password = password;
        }
    }


    public interface ServiciosRetrofit {
        @GET("/user/{name}")
        Call<Usuario> consultarUsuario(@Path("name") String nombre);

        @GET("/obj/{user}/{obj}")
        Call<Objeto> consultarObjeto (@Path("user") String user,@Path("obj") String nomObj);

        @POST("/user")
        Call<Response> crearUser (@Body Usuario usuario);

    }

    public static void main( String[] args ) throws IOException
    {

        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        //Creamos un usuario
        Usuario usuario = new Usuario();

        // Create an instance of our GitHub API interface.
        ServiciosRetrofit servicios = retrofit.create(ServiciosRetrofit.class);

        // Create a call instance for looking up Retrofit contributors.
        //Call<List<Contributor>> call = github.contributors("RouteInjector", "route-injector");
        Call<Usuario> call1 = servicios.consultarUsuario("David");
        Call<Objeto> call2 = servicios.consultarObjeto("David","espada");
        Call<Response> call3 = servicios.crearUser(usuario);



        System.out.println(call1);
        System.out.println(call2);
        System.out.println(call3);
        // Fetch and print a list of the contributors to the library.
        //List<Contributor> contributors = call.execute().body();
        Usuario user = call1.execute().body();
        Objeto obj = call2.execute().body();
        //Response resp1 = call3.execute().body();

        //for (Contributor contributor: contributors) {
        /*
        for (Follower follower : follow) {
            System.out.println(follower.login + " " + follower.avatar_url);
            //System.out.println(contributor.login + " (" + contributor.contributions + ") "+ contributor.avatar_url);
            //System.out.println(contributor.login + ( + contributor.)
        }
        */
    }
}
