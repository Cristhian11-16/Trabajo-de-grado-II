package com.trabajodegrado.ucatolica.TrabajoGradoII.Reserva;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reserva")
@CrossOrigin(origins = "*")
public class ReservaController {
    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }
    //Obtener las reservas de una cancha
    @GetMapping(path="/cancha/{cancha_id}")
    public List<Reserva> getReservaByCanchaId(@PathVariable("cancha_id") Long id){
        return reservaService.getReservaByCanchaId(id);
    }
    //Obtener las reservas de un usuario
    @GetMapping(path="/user/{user_id}")
    public List<Reserva> getReservaByUserId(@PathVariable("user_id") Long id){
        return reservaService.getReservaByUserId(id);
    }
    //Obtener las reservas de un usuario para una cancha
    //Obtener las reservas de una cancha para un usuario
    @GetMapping(path="/")
    public List<Reserva> getReservaByUserIdCancha(@RequestParam(name = "id_usuario") long id_usuario,@RequestParam(name = "id_cancha") long id_cancha){
        return reservaService.getReservaByUserIdCancha(id_usuario, id_cancha);
    }
    //Obtener las reservas de un usuario en determinada fecha
    @GetMapping(path="/user/")
    public List<Reserva> getReservaByUserDate(@RequestParam(name = "id_usuario") long id_usuario,@RequestParam(name = "fecha") LocalDate fecha){
        return reservaService.getReservaByUserDate(id_usuario, fecha);
    }

    //Obtener las reservas de un usuario por el estado
    @GetMapping(path="/user/estado/")
    public List<Reserva> getReservaByUserestado(@RequestParam(name = "id_usuario") long id_usuario,@RequestParam(name = "estado") String estado){
        return reservaService.getReservaByUserEstado(id_usuario, estado);
    }
    //Obtener las reservas de una cancha en determinada fecha
    @GetMapping(path="/cancha/")
    public List<Reserva> getReservaByCanchaDate(@RequestParam(name = "id_cancha") long id_cancha,@RequestParam(name = "fecha") LocalDate fecha){
        return reservaService.getReservaByCanchaDate(id_cancha, fecha);
    }
    //Obtener las reservas de una cancha por el estado
    @GetMapping(path="/cancha/estado/")
    public List<Reserva> getReservaByCanchaEstado(@RequestParam(name = "id_cancha") long id_cancha, @RequestParam(name = "estado") String estado){
        return reservaService.getReservaByCanchaEstado(id_cancha, estado);
    }
    //Hacer una reserva
    @PostMapping(path = "/registrar")
    public ResponseEntity<Object> registrarReserva(@RequestBody Reserva reserva){
        return this.reservaService.newReserva(reserva);
    }
    //Modificar reserva
    @PutMapping(path = "/actualizar")
    public ResponseEntity<Object> actualizarReserva(@RequestBody Reserva reserva){
        return this.reservaService.updateReserva(reserva);
    }
}
