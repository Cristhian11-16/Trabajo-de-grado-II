package com.trabajodegrado.ucatolica.TrabajoGradoII.Direcciones;


import com.trabajodegrado.ucatolica.TrabajoGradoII.Canchas.Canchas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/canchas/direccion")
@CrossOrigin(origins = "*")
public class DireccionController {
    private final DireccionService direccionService;

    @Autowired
    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }
    //Obtener todas las canchas registradas
    @GetMapping(path="/")
    public List<Direccion> getCanchas(){
        return direccionService.getDireccion();
    }
    //Obtener las canchas por determinada localidad
    @GetMapping(path="/localidad")
    public List<Direccion> getDireccionByLocalidad(@RequestParam(name = "localidad") String localidad){
        return direccionService.getDireccionByLocalidad(localidad);
    }
    //Obtener las canchas por determinada localidad y barrio
    @GetMapping(path="/localidad/barrio")
    public List<Direccion> getDireccionByLocalidadAndBarrio(@RequestParam(name = "localidad") String localidad, @RequestParam(name = "barrio") String barrio){
        return direccionService.getDireccionByLocalidadBarrio(localidad,barrio);
    }
    //Obtiene la direccion de una cancha
    @GetMapping(path="/{cancha_id}")
    public Direccion getDireccionId(@PathVariable("cancha_id") Long id){
        return direccionService.getDireccionId(id);
    }
    //Registra la direccion de una cancha
    @PostMapping(path = "/registrar")
    public ResponseEntity<Object> registrarDireccion(@RequestBody Direccion direccion){
        return this.direccionService.newDireccion(direccion);
    }
    //Actualizar la direccion de una cancha
    @PutMapping(path = "/actualizar")
    public ResponseEntity<Object> actualizarDireccion(@RequestBody Direccion direccion){
        return this.direccionService.updateDireccion(direccion);
    }
}
