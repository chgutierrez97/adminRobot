package com.accusy.robotpro.robotadmin.services;

import com.accusy.robotpro.robotadmin.dto.InputDto;
import com.accusy.robotpro.robotadmin.dto.PantallaDto;
import com.accusy.robotpro.robotadmin.model.InputIO;
import com.accusy.robotpro.robotadmin.model.ListaMacroIO;
import com.accusy.robotpro.robotadmin.model.PantallaIO;
import com.accusy.robotpro.robotadmin.model.TextoPantallaIO;
import com.accusy.robotpro.robotadmin.model.TransaccionIO;
import com.accusy.robotpro.robotadmin.model.TransaccionOI;
import com.accusy.robotpro.robotadmin.model.UsuarioIO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ServicesRobot {

    public List<TransaccionIO> getTransaccionAll() {
        final String url = "http://localhost:8080/api/findAllTransaccion";
        RestTemplate restTemplate = new RestTemplate();
        ListaMacroIO result = restTemplate.getForObject(url, ListaMacroIO.class);
        System.out.println(result);
        return result.getTransaccionList();
    }
    
    
    
    public List<TransaccionIO> getTransacionByTipoUsuario(Integer idTipo,Integer idUsuario) {
        final String url = "http://localhost:8080/api/findTransacionByTipoUsuario";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("idUsuario", idUsuario)
                .queryParam("idTipo", idTipo);
        ListaMacroIO result = restTemplate.getForObject(builder.toUriString(), ListaMacroIO.class);
        System.out.println(result);
        return result.getTransaccionList();
    }
    
    
    public TransaccionIO getTransacionById(Integer idTransaccion) {
        final String url = "http://localhost:8080/api/findTransaccionById";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("id", idTransaccion);
        TransaccionIO result = restTemplate.getForObject(builder.toUriString(), TransaccionIO.class);
        System.out.println(result);
        return result;
    }
    
    
    public List<TransaccionIO> getTransacionByTipo(Integer idTipo) {
        final String url = "http://localhost:8080/api/findTransacionByTipo";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("idTipo", idTipo);
        ListaMacroIO result = restTemplate.getForObject(builder.toUriString(), ListaMacroIO.class);
        System.out.println(result);
        return result.getTransaccionList();
    }

    public List<PantallaDto> getPantallaByIdTransaccion(Integer idTransaccion) {
        List<PantallaDto> listPatalla = new ArrayList<>();
        final String url = "http://localhost:8080/api/findPantallaByIdTransaccion";
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("idTransaccion", idTransaccion);
        ListaMacroIO result = restTemplate.getForObject(builder.toUriString(), ListaMacroIO.class);
        for (PantallaIO pantallaIO : result.getPantallasList()) {
            PantallaDto pant = new PantallaDto();
            pant.setAction(pantallaIO.getPantallaAction());
            pant.setId(Long.valueOf(""+pantallaIO.getId()));
          
            pant.setActive( pantallaIO.getPantallaActive());
            pant.setActiveKey(pantallaIO.getPantallaActivekey());
            
            pant.setIdTransaccion(idTransaccion);
            
            pant.setScrips(pantallaIO.getPantallaScrips());
            List<InputDto> inputList = new ArrayList<>();
            for (InputIO input : pantallaIO.getInputCollection()) {
                InputDto inputDto = new InputDto();
                inputDto.setId(input.getInputId());
                inputDto.setLabel(input.getInputLabel());
                inputDto.setName(input.getInputName());
                inputDto.setRequired(input.getInputRequired());
                inputDto.setType(input.getInputType());
                inputDto.setValue(input.getInputValue());
                
                inputList.add(inputDto);
            }
            pant.setInputs(inputList);
            List<String> textoPantallaList = new ArrayList<>();
            for (TextoPantallaIO textoPantallaIO :  pantallaIO.getTextoPantallaCollection()) {
                textoPantallaList.add(textoPantallaIO.getTexto());
            }
            pant.setTextoPantalla(textoPantallaList);
           
            
           listPatalla.add(pant);
        }
        return listPatalla;

    }
    
    
   public UsuarioIO getUsuarioByLoginAndStatus(String login,Integer idStatus) {
        final String url = "http://localhost:8080/api/findUsuarioByLoginAndStatus";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("login", login)
                .queryParam("idStatus", idStatus);
        UsuarioIO result = restTemplate.getForObject(builder.toUriString(), UsuarioIO.class);
        System.out.println(result.toString());
        return result;
    }

      public UsuarioIO getUsuarioByLogin(String login) {
        final String url = "http://localhost:8080/api/findUsuarioByLogin";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("login", login);
        UsuarioIO result = restTemplate.getForObject(builder.toUriString(), UsuarioIO.class);
        System.out.println(result.toString());
        return result;
    }
   
   
   public TransaccionIO guardarTransaccion(TransaccionOI trans){
   final String url = "http://localhost:8080/api/saveTransaccion";
        RestTemplate restTemplate = new RestTemplate();
            TransaccionIO result = restTemplate.postForObject(url, trans, TransaccionIO.class);
     return result;
   
   }
   public PantallaIO guardarPantalla(PantallaIO pantalla){
   final String url = "http://localhost:8080/api/savePantalla";
        RestTemplate restTemplate = new RestTemplate();
            PantallaIO result = restTemplate.postForObject(url, pantalla, PantallaIO.class);
     return result;
   
   }
}
