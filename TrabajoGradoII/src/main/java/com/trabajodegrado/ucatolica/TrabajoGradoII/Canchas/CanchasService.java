package com.trabajodegrado.ucatolica.TrabajoGradoII.Canchas;


import com.trabajodegrado.ucatolica.TrabajoGradoII.Direcciones.DireccionRepository;
import com.trabajodegrado.ucatolica.TrabajoGradoII.Precios.PreciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CanchasService {
    HashMap<String, Object> datos;
    private final CanchasRepository canchasRepository;
    private final DireccionRepository direccionRepository;
    private final PreciosRepository preciosRepository;

    @Autowired
    public CanchasService(CanchasRepository canchasRepository, DireccionRepository direccionRepository, PreciosRepository preciosRepository) {
        this.canchasRepository = canchasRepository;
        this.direccionRepository = direccionRepository;
        this.preciosRepository = preciosRepository;
    }
    //Obtener la informacion de una cancha
    public Canchas getCancha(long id_cancha) {
        Optional<Canchas> canchas=canchasRepository.findById(id_cancha);
        if (canchas.isPresent()){
            return canchas.get();
        }
        throw new RuntimeException("Cancha no existe");
    }

    //Obtener las canchas que tengan futbol 8
    public List<Canchas> getCanchaFutbol8(boolean futbol_8, long id_cancha) {

        return canchasRepository.findByIdCanchaByFutbol8(futbol_8,id_cancha);
    }
    //Obtener las canchas que tengan parqueadero
    public List<Canchas> getCanchaParqueadero(boolean parqueadero, long id_cancha) {

        return canchasRepository.findByIdCanchaByParqueadero(parqueadero,id_cancha);
    }

    //Obtenemos todas las canchas registradas
    public List<Canchas> getCancha() {
        return this.canchasRepository.findAll();
    }
    //Registramos una nueva cancha
    public ResponseEntity<Object> newCancha(Canchas canchas) {
        Optional<Canchas> res = canchasRepository.findCanchaByName(canchas.getName());
        datos = new HashMap<>();
        if (res.isPresent()) {
            datos.put("error", true);
            datos.put("message", "Ya existe una cancha con ese nombre");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se guardo con exito");
        datos.put("datos", canchas);
        canchasRepository.save(canchas);
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }
    //Actualizamos una cancha
    public ResponseEntity<Object> updateCancha(Canchas canchas) {
        //Optional<Canchas> id = canchasRepository.findById(canchas.getId_cancha());
        boolean existe = this.canchasRepository.existsById(canchas.getId_cancha());
        datos = new HashMap<>();
        if (existe) {
            datos.put("datos", datos);
            datos.put("message", "Se actualizo con exito");
            canchasRepository.save(canchas);
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
    //Eliminamos toda la cancha
    public ResponseEntity<Object> deleteCancha(long id){
        boolean existe=this.canchasRepository.existsById(id);
        datos = new HashMap<>();
        if (!existe){
            datos.put("error", true);
            datos.put("message", "No existe cancha");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }else {
            canchasRepository.deleteById(id);
            direccionRepository.deleteById(id);
            preciosRepository.deleteByIdCancha(id);
            datos.put("message", "Producto eliminado");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.ACCEPTED
            );
        }

    }
}


