package com.trabajodegrado.ucatolica.TrabajoGradoII.Precios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/canchas/precios")
@CrossOrigin(origins = "*")
public class PreciosController {
    private final PreciosService preciosService;

    @Autowired
    public PreciosController(PreciosService preciosService) {
        this.preciosService = preciosService;
    }
    /*
        //Obtener todos los precios de una cancha
        @GetMapping(path="/{cancha_id}")
        public List<Precios> getPrecio(@PathVariable("cancha_id") Long id){
            return preciosService.getPreciosId(id);
        }
        //Obtener todos los precios de una cancha por hora
        @GetMapping(path="/canchabyhora")
        public List<Precios> getPrecioByHoraAndId_cancha(@RequestParam(name = "hora") int hora, @RequestParam(name = "id_cancha") long id){
            return preciosService.getPreciosByhoraAndIdCancha(hora, id);
        }
     */
    //Obtener el precio de una cancha por hora y dia
    @GetMapping(path="/canchahora&dia")
    public List<Precios> getPrecioByHoraByDiaAndIdcancha(@RequestParam(name = "hora") int hora,@RequestParam(name = "dia") String dia, @RequestParam(name = "id_cancha") long id){
        return preciosService.getPreciosByhoraAndIdCancha(hora, id, dia);
    }
    //Obtener todos los precios de todas las cancha por dia
    @GetMapping(path="/dia/{dia}")
    public List<Precios> getPrecioByDia(@PathVariable("dia") String dia){
        return preciosService.getPreciosByDia(dia);
    }

    //Obtener los precios de una cancha por dia
    @GetMapping(path="/dia/canchadia")
    public List<Precios> getPrecioByDiaByHora(@RequestParam(name = "dia") String dia, @RequestParam(name = "id_cancha") long id){
        return preciosService.getPreciosByDiaByhora(id, dia);
    }
    @PostMapping(path = "/registrar")
    public ResponseEntity<Object> registrarPrecio(@RequestBody Precios precios){
        return this.preciosService.newPrecios(precios);
    }

    @PutMapping(path = "/actualizar")
    public ResponseEntity<Object> actualizarPrecios(@RequestBody Precios precios){
        return this.preciosService.updatePrecios(precios);
    }
}
