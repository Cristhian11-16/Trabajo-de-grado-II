package com.trabajodegrado.ucatolica.TrabajoGradoII.Pago;

import com.trabajodegrado.ucatolica.TrabajoGradoII.Reserva.Reserva;
import com.trabajodegrado.ucatolica.TrabajoGradoII.Reserva.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva/pago")
@CrossOrigin(origins = "*")
public class PagoController {
    private final PagoService pagoService;

    @Autowired
    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }
    @GetMapping(path="")
    public List<Pago> getPago(){
        return pagoService.getPago();
    }
    //Obtener pago por el estado (Cancha)
    @GetMapping(path="/estado/cancha/")
    public List<Pago> getPagoByEstadoCancha(@RequestParam(name = "id_cancha") long id_cancha, @RequestParam(name = "estado") boolean estado){
        return pagoService.getPagosByCanchaEstado(id_cancha,estado);
    }
    //Obtener pago por el estado (Usuario)
    @GetMapping(path="/estado/usuario/")
    public List<Pago> getPagoByEstadoUsuario(@RequestParam(name = "id_usuario") long id_usuario, @RequestParam(name = "estado") boolean estado){
        return pagoService.getPagosByUserEstado(id_usuario,estado);
    }
    //Obtener los pagos de una cancha
    @GetMapping(path="/cancha/{cancha_id}")
    public List<Pago> getPagoByCanchaId(@PathVariable("cancha_id") long id){
        return pagoService.getPagoByCanchaId(id);
    }
    //Obtener los pagos de un usuario
    @GetMapping(path="/user/{user_id}")
    public List<Pago> getPagoByUserId(@PathVariable("user_id") long id){
        return pagoService.getPagoByUserId(id);
    }
    @PostMapping(path = "/registrar")
    public ResponseEntity<Object> registrarPago(@RequestBody Pago pago){
        return this.pagoService.newPago(pago);
    }
    @PutMapping(path = "/actualizar")
    public ResponseEntity<Object> actualizarPago(@RequestBody Pago pago){
        return this.pagoService.updatePago(pago);
    }
}
