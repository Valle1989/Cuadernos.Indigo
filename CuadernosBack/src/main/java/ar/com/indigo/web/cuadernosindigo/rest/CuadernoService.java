
package ar.com.indigo.web.cuadernosindigo.rest;

import ar.com.indigo.web.cuadernosindigo.entities.Cuaderno;
import ar.com.indigo.web.cuadernosindigo.enums.Interiores;
import ar.com.indigo.web.cuadernosindigo.repositories.interfaces.I_CuadernoRepository;
import ar.com.indigo.web.cuadernosindigo.repositories.jdbc.Connector;
import ar.com.indigo.web.cuadernosindigo.repositories.jdbc.CuadernoRepository;
import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("cuadernos/v1")
public class CuadernoService {
    
    private I_CuadernoRepository cr = new CuadernoRepository(Connector.getConnection());
    
    @GET
    public String info(){
        return "Servicio Cuadernos Activo!";
    }
    
    @Path("alta")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String alta(@QueryParam("tapas") int tapas,
                       @QueryParam("interiores") String interiores){
    try{
        Cuaderno cuaderno = new Cuaderno(tapas, Interiores.valueOf(interiores));
        cr.save(cuaderno);
        return cuaderno.getId()+"";
    }catch(Exception e){
        return "0";
        }
    }
    
    @Path("baja")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String baja(@QueryParam("id") int id,
                       @QueryParam("tapas")int tapas, 
                       @QueryParam("interiores")String interiores){
       Cuaderno cuaderno = new Cuaderno(id, tapas, Interiores.valueOf(interiores));
       cr.remove(cuaderno);
       return "true";
    }
    
    @Path("modifica")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String modifica(@QueryParam("id") int id,
                           @QueryParam("tapas") int tapas,
                           @QueryParam("interiores") String interiores){
        Cuaderno cuaderno = new Cuaderno(id, tapas, Interiores.valueOf(interiores));
        cr.update(cuaderno);
        return "true";
    }
    
    @Path("byId")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getById(@QueryParam("id") int id){
        return new Gson().toJson(cr.getById(id));
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(){
        return new Gson().toJson(cr.getAll());
    }
    
}
