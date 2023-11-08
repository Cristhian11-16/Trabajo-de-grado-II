package com.trabajodegrado.ucatolica.TrabajoGradoII.Reserva;

import com.trabajodegrado.ucatolica.TrabajoGradoII.Direcciones.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    //Obtener las reservas de una canha
    @Query("SELECT r FROM Reserva r WHERE r.id_cancha=%:id_cancha%")
    List<Reserva> findByIdCancha(@Param("id_cancha") long id_cancha);

    //Obtener las reservas de una canha
    @Query("SELECT r FROM Reserva r WHERE r.id_usuario=%:user_id%")
    List<Reserva> findByIdUser(@Param("user_id") long user_id);

    //Obtener las reservas de un usuario por cancha o de una cancha por usuario
    @Query("SELECT r FROM Reserva r WHERE r.id_usuario=%?1 AND r.id_cancha=?2")
    List<Reserva> findByIdUserCancha(long user_id, long id_cancha);
    //Obtener las reservas de un usuario por fecha
    @Query("SELECT r FROM Reserva r WHERE r.id_usuario=%?1 AND r.fecha=?2")
    List<Reserva> findByIdUserDate(long user_id, LocalDate fecha);
    //Obtener las reservas de un usuario por el estado
    @Query("SELECT r FROM Reserva r WHERE r.id_usuario=%?1 AND r.estado=?2")
    List<Reserva> findByUserEstado(long user_id, String estado);
    //Obtener las reservas de una cancha por fecha
    @Query("SELECT r FROM Reserva r WHERE r.id_cancha=%?1 AND r.fecha=?2")
    List<Reserva> findByIdCanchaDate(long id_cancha, LocalDate fecha);
    //Obtener las reservas de una cancha por el estado
    @Query("SELECT r FROM Reserva r WHERE r.id_cancha=%?1 AND r.estado=?2")
    List<Reserva> findByCanchaEstado(long id_cancha, String estado);
}
