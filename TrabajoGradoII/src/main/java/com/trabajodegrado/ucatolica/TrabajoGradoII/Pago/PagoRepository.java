package com.trabajodegrado.ucatolica.TrabajoGradoII.Pago;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    //Obtener pago por el estado (Cancha)
    @Query("SELECT p FROM Pago p WHERE p.id_cancha=?1 AND p.estado=?2")
    List<Pago> findByEstadoCancha(long idCancha, boolean estado);

    //Obtener pago por el estado (Usuario)
    @Query("SELECT p FROM Pago p WHERE p.id_usuario=?1 AND p.estado=?2")
    List<Pago> findByEstadoUsuario(long idUser, boolean estado);
    //Obtener los pagos de una cancha
    @Query("SELECT p FROM Pago p WHERE p.id_cancha=%:id_cancha%")
    List<Pago> findByCancha(@Param("id_cancha") long id);
    //Obtener los pagos de un usuario
    @Query("SELECT p FROM Pago p WHERE p.id_usuario=%:id_usuario%")
    List<Pago> findByUsuario(@Param("id_usuario") long id);
}
