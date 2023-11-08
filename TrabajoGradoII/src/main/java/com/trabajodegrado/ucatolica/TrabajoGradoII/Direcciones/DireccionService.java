package com.trabajodegrado.ucatolica.TrabajoGradoII.Direcciones;

import com.trabajodegrado.ucatolica.TrabajoGradoII.Canchas.CanchasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Service
public class DireccionService {
    HashMap<String, Object> datos;
    private final DireccionRepository direccionRepository;
    private final CanchasRepository canchasRepository;

    @Autowired
    public DireccionService(DireccionRepository direccionRepository, CanchasRepository canchasRepository) {
        this.direccionRepository = direccionRepository;
        this.canchasRepository = canchasRepository;
    }

    //Obtener las canchas por determinada localidad
    public List<Direccion> getDireccionByLocalidad(String localidad) {
        return direccionRepository.findByLocalidad(localidad);
    }
    //Obtener las canchas por determinada localidad
    public List<Direccion> getDireccionByLocalidadBarrio(String localidad, String barrio) {
        return direccionRepository.findByLocalidadBarrio(localidad, barrio);
    }

    //Obtiene la direccion de una cancha
    public List<Direccion> getDireccion() {
        return this.direccionRepository.findAll();
    }

    //Registra la direccion de una cancha
    public Direccion getDireccionId(long id) {
        Optional<Direccion> direccion = direccionRepository.findById(id);
        if (direccion.isPresent()){
            return direccion.get();
        }
        throw new RuntimeException("Cancha no existe");
    }
    //Actualizar la direccion de una cancha
    public ResponseEntity<Object> newDireccion(Direccion direccion) {
        boolean existe = this.canchasRepository.existsById(direccion.getId_cancha());
        datos = new HashMap<>();
        if (existe){
            datos.put("datos", datos);
            datos.put("message", "Se guardo direccion correctamente");
            direccionRepository.save(direccion);
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

    public ResponseEntity<Object> updateDireccion(Direccion direccion) {
        //Optional<Canchas> id = canchasRepository.findById(canchas.getId_cancha());
        boolean existe = this.canchasRepository.existsById(direccion.getId_cancha());
        datos = new HashMap<>();
        if (existe) {
            datos.put("datos", datos);
            datos.put("message", "Se actualizo con exito");
            direccionRepository.save(direccion);
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
