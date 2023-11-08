package com.trabajodegrado.ucatolica.TrabajoGradoII.Direcciones;

import com.trabajodegrado.ucatolica.TrabajoGradoII.Canchas.Canchas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    //Obtener la cancha por localidad
    @Query("SELECT d FROM Direccion d WHERE d.localidad=%:localidad%")
    List<Direccion> findByLocalidad(@Param("localidad") String localidad);

    //Obtener la cancha por localidad y barrio
    @Query("SELECT d FROM Direccion d WHERE d.localidad=?1 ANd d.barrio=?2")
    List<Direccion> findByLocalidadBarrio(String localidad, String barrio);

}
