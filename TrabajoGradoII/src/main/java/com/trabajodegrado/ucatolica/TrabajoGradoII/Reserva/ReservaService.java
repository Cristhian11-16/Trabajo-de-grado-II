package com.trabajodegrado.ucatolica.TrabajoGradoII.Reserva;

import com.trabajodegrado.ucatolica.TrabajoGradoII.Canchas.Canchas;
import com.trabajodegrado.ucatolica.TrabajoGradoII.Canchas.CanchasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    HashMap<String, Object> datos;
    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    //Obtener las reservas de una cancha
    public List<Reserva> getReservaByCanchaId(long id) {
        return reservaRepository.findByIdCancha(id);
    }
    //Obtener las reservas de un usuario
    public List<Reserva> getReservaByUserId(long id) {
        return reservaRepository.findByIdUser(id);
    }
    //Obtener las reservas de un usuario en una cancha
    public List<Reserva> getReservaByUserIdCancha(long id_usuario, long id_cancha) {
        return reservaRepository.findByIdUserCancha(id_usuario, id_cancha);
    }
    //Obtener las reservas de un usuario en determinada fecha
    public List<Reserva> getReservaByUserDate(long id_usuario, LocalDate fecha) {
        return reservaRepository.findByIdUserDate(id_usuario, fecha);
    }
    //Obtener las reservas de un usuario por el estado
    public List<Reserva> getReservaByUserEstado(long id_usuario, String estado) {
        return reservaRepository.findByUserEstado(id_usuario, estado);
    }
    //Obtener las reservas de una cancha en determinada fecha
    public List<Reserva> getReservaByCanchaDate(long id_cancha, LocalDate fecha) {
        return reservaRepository.findByIdCanchaDate(id_cancha, fecha);
    }
    //Obtener las reservas de una cancha por el estado
    public List<Reserva> getReservaByCanchaEstado(long id_cancha, String estado) {
        return reservaRepository.findByCanchaEstado(id_cancha, estado);
    }
    public ResponseEntity<Object> newReserva(Reserva reserva) {
        datos = new HashMap<>();
        datos.put("message", "Se guardo con exito");
        datos.put("datos", reserva);
        reservaRepository.save(reserva);
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateReserva(Reserva reserva) {
        //Optional<Canchas> id = canchasRepository.findById(canchas.getId_cancha());
        boolean existe = this.reservaRepository.existsById(reserva.getId_reserva());
        datos = new HashMap<>();
        if (existe) {
            datos.put("datos", datos);
            datos.put("message", "Se actualizo con exito");
            reservaRepository.save(reserva);
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
