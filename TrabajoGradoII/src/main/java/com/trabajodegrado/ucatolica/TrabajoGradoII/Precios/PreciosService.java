package com.trabajodegrado.ucatolica.TrabajoGradoII.Precios;

import com.trabajodegrado.ucatolica.TrabajoGradoII.Canchas.CanchasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PreciosService {
    HashMap<String, Object> datos;
    private final PreciosRepository preciosRepository;
    private final CanchasRepository canchasRepository;


    @Autowired
    public PreciosService(PreciosRepository preciosRepository, CanchasRepository canchasRepository) {
        this.preciosRepository = preciosRepository;
        this.canchasRepository = canchasRepository;
    }
    /*
    //Obtener todos los precios de una cancha
    public List<Precios> getPreciosId(long id) {
        return preciosRepository.findByIdCancha(id);
    }

    //Obtener todos los precios de una cancha por hora
    public List<Precios> getPreciosByhoraAndIdCancha(int hora, long id) {
        return preciosRepository.findByIdCanchaByHoraAndById_Cancha(hora, id);
    }
    */

    //Obtener todos los precios de todas las canchas por dia
    public List<Precios> getPreciosByDia(String dia) {
        return preciosRepository.findByIdCanchaByDia(dia);
    }

    //Obtener el precio de una cancha de un dia y hora en especifico
    public List<Precios> getPreciosByhoraAndIdCancha(int hora, long id, String dia) {
        return preciosRepository.findByIdCanchaByHoraAndById_Cancha(hora,id,dia);
    }
    //Obtener el precio de una cancha en un dia especifico
    public List<Precios> getPreciosByDiaByhora(long id, String dia) {
        return preciosRepository.findByIdCanchaByDiaAndById_Cancha(id, dia);
    }
    public ResponseEntity<Object> newPrecios(Precios precios) {
        boolean existe = this.canchasRepository.existsById(precios.getId_cancha());
        datos = new HashMap<>();
        if (existe){
            datos.put("datos", datos);
            datos.put("message", "Se guardo el precio correctamente");
            preciosRepository.save(precios);
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CREATED
            );
        }
        datos.put("error", true);
        datos.put("message", "No existe cancha");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CONFLICT
        );
    }

    public ResponseEntity<Object> updatePrecios(Precios precios) {
        //Optional<Canchas> id = canchasRepository.findById(canchas.getId_cancha());
        boolean existe = this.canchasRepository.existsById(precios.getId_cancha());
        datos = new HashMap<>();
        if (existe) {
            datos.put("datos", datos);
            datos.put("message", "Se actualizo con exito");
            preciosRepository.save(precios);
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CREATED
            );
        }
        datos.put("error", true);
        datos.put("message", "No existe cancha");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CONFLICT
        );
    }


}
