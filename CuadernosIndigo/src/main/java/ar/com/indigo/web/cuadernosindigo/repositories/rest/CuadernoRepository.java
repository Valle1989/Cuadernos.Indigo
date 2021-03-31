
package ar.com.indigo.web.cuadernosindigo.repositories.rest;

import ar.com.indigo.web.cuadernosindigo.entities.Cuaderno;
import ar.com.indigo.web.cuadernosindigo.interfaces.I_CuadernoRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;


public class CuadernoRepository implements I_CuadernoRepository {
    
    private String url;

    public CuadernoRepository(String url) {
        this.url = url;
    }

    @Override
    public void save(Cuaderno cuaderno) {
        String url2 = url + "/alta?tapas="+ cuaderno.getTapas() + 
                            "&interiores=" + cuaderno.getInteriores();
        String resp = new Service().response(url2);
        try {
            cuaderno.setId(Integer.parseInt(resp));
        } catch (Exception e) {
            System.out.println("*********************************************");
            System.out.println(e);
            System.out.println("*********************************************");
        }         
    }

    @Override
    public void update(Cuaderno cuaderno) {
        String upd = url + "/update?tapas=" + cuaderno.getTapas()+
                           "&interiores=" + cuaderno.getInteriores()+
                           "&id=" + cuaderno.getId();
        String resp = new Service().response(upd);
        try {
            cuaderno.setId(Integer.parseInt(upd));
        } catch (Exception e) {
            System.out.println("*********************************************");
            System.out.println(e);
            System.out.println("*********************************************");
        }
    }

    @Override
    public void remove(Cuaderno cuaderno) {
        String url2 = url + "/delete?id=" + cuaderno.getId();
        String resp = new Service().response(url2);
        try {
            cuaderno.setId(Integer.parseInt(resp));
        } catch (Exception e) {
            System.out.println("*********************************************");
            System.out.println(e);
            System.out.println("*********************************************");
        }
    }

    @Override
    public List<Cuaderno> getAll() {
        List<Cuaderno> list = new ArrayList();
        try {
            list = new Gson()
                .fromJson(new Service().response(url+"/all"), 
                        new TypeToken<List<Cuaderno>>(){}.getType());
        } catch (Exception e) {
            System.out.println("*********************************************");
            System.out.println(e);
            System.out.println("*********************************************");
        }
        return list;
    }

    @Override
    public Cuaderno getById(int id) {
        Cuaderno cuaderno = new Cuaderno();
        try {
            cuaderno = new Gson()
                .fromJson(new Service().response(
                        url+"/byId?id="+id), 
                        new TypeToken<Cuaderno>(){}.getType());
        } catch (Exception e) {
            System.out.println("*********************************************");
            System.out.println(e);
            System.out.println("*********************************************");
        }
        return cuaderno;
    }
    
    
    
}
