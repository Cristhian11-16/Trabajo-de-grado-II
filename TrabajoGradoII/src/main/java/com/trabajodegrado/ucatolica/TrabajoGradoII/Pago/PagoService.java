package com.trabajodegrado.ucatolica.TrabajoGradoII.Pago;

import com.trabajodegrado.ucatolica.TrabajoGradoII.Reserva.Reserva;
import com.trabajodegrado.ucatolica.TrabajoGradoII.Reserva.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PagoService {
    HashMap<String, Object> datos;
    private final PagoRepository pagoRepository;

    @Autowired
    public PagoService(PagoRepository pagoRepository) { this.pagoRepository = pagoRepository;}

    public List<Pago> getPago() {
        return this.pagoRepository.findAll();
    }

    //Obtener pago por el estado (Cancha)
    public List<Pago> getPagosByCanchaEstado(long id_cancha, boolean estado){
        return pagoRepository.findByEstadoCancha(id_cancha,estado);
    }
    //Obtener pago por el estado (Usuario)
    public List<Pago> getPagosByUserEstado(long id_user, boolean estado){
        return pagoRepository.findByEstadoUsuario(id_user,estado);
    }
    //Obtener los pagos de una cancha
    public List<Pago> getPagoByCanchaId(long id) {
        return pagoRepository.findByCancha(id);
    }
    //Obtener los pagos de un usuario
    public List<Pago> getPagoByUserId(long id) {
        return pagoRepository.findByUsuario(id);
    }
    public ResponseEntity<Object> newPago(Pago pago) {
        Optional<Pago> res = pagoRepository.findById(pago.getId_pago());
        datos = new HashMap<>();
        if (res.isPresent()) {
            datos.put("error", true);
            datos.put("message", "Ya existe un pago con ese ID");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se guardo con exito");
        datos.put("datos", pago);
        pagoRepository.save(pago);
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updatePago(Pago pago) {
        //Optional<Canchas> id = canchasRepository.findById(canchas.getId_cancha());
        datos = new HashMap<>();
            datos.put("datos", datos);
            datos.put("message", "Se actualizo con exito");
            pagoRepository.save(pago);
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CREATED
            );
        }


}

