package com.trabajodegrado.ucatolica.TrabajoGradoII.Canchas;

import com.trabajodegrado.ucatolica.TrabajoGradoII.Precios.Precios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CanchasRepository extends JpaRepository<Canchas,Long> {
    Optional<Canchas> findCanchaByName(String name);
    //Obtener si la cancha tiene futbol 8 por id
    @Query("SELECT c FROM Canchas c WHERE c.futbol_8=?1 AND c.id_cancha=?2")
    List<Canchas> findByIdCanchaByFutbol8(boolean futbol_8,long id_cancha);

    //Obtener si la cancha tengan parqueadero por id
    @Query("SELECT c FROM Canchas c WHERE c.parqueadero=?1 AND c.id_cancha=?2")
    List<Canchas> findByIdCanchaByParqueadero(boolean parqueadero,long id_cancha);
}
