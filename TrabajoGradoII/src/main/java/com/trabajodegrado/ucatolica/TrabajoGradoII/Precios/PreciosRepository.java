package com.trabajodegrado.ucatolica.TrabajoGradoII.Precios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreciosRepository extends JpaRepository<Precios, Long> {
    /*
    @Query("SELECT p  FROM Precios p WHERE p.id_cancha=%:id_cancha%")
    List<Precios> findByIdCancha(@Param("id_cancha") long id_cancha);

    @Query("SELECT p  FROM Precios p WHERE p.hora=?1 AND p.id_cancha=?2")
    List<Precios> findByIdCanchaByHoraAndById_Cancha(int hora,long id_cancha);
    */
    //Obtener todos los precios de todas las cancha por dia
    @Query("SELECT p  FROM Precios p WHERE p.dia=%:dia%")
    List<Precios> findByIdCanchaByDia(@Param("dia") String dia);

    //Obtener el precio de una cancha por hora y dia
    @Query("SELECT p  FROM Precios p WHERE p.hora=?1 AND p.id_cancha=?2 AND p.dia=?3")
    List<Precios> findByIdCanchaByHoraAndById_Cancha(int hora,long id_cancha, String dia);
    @Query("SELECT p  FROM Precios p WHERE p.id_cancha=?1 AND p.dia=?2")
    List<Precios> findByIdCanchaByDiaAndById_Cancha(long id_cancha, String dia);

    @Modifying
    @Transactional
    @Query("DELETE FROM Precios p WHERE p.id_cancha=%:id_cancha%")
    void deleteByIdCancha(@Param("id_cancha") long id);
}
