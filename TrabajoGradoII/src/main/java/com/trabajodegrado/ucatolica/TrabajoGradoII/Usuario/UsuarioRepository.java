package com.trabajodegrado.ucatolica.TrabajoGradoII.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //Valide si hay un usuario ya creado con este correo
    Optional<Usuario> findUsuarioByCorreo(String correo);
    //Validar contraseña
    @Query("SELECT u FROM Usuario u WHERE u.correo=?1 AND u.contraseña=?2")
    Optional<Usuario> findByContraseña( String correo, String contraseña);
    @Query("SELECT u FROM Usuario u WHERE u.correo=?1")
    Optional<Usuario> findByCorreo(String correo);
}
