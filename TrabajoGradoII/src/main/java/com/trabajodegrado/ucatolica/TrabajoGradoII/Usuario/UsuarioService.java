package com.trabajodegrado.ucatolica.TrabajoGradoII.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Service
public class UsuarioService {
    HashMap<String, Object> datos;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //Obtener todos los usuarios
    public List<Usuario> getUsuarios() {
        return this.usuarioRepository.findAll();
    }

    //Obtener el usuario por id
    public Usuario getUsuarioCorreo(String correo) {
        Optional<Usuario> usuario = usuarioRepository.findByCorreo(correo);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new RuntimeException("Usuario no existe");
    }

    //Registrar el usuario
    public ResponseEntity<Object> newUsuario(Usuario usuario) {
        Optional<Usuario> res = usuarioRepository.findUsuarioByCorreo(usuario.getCorreo());
        usuario.setContraseña(encryptPassword(usuario.getContraseña()));
        usuario.setRol("usuario");
        datos = new HashMap<>();
        if (res.isPresent()) {
            datos.put("error", true);
            datos.put("message", "Ya existe una usuario con este correo");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se guardo con exito");
        datos.put("datos", usuario);
        usuarioRepository.save(usuario);
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    //Actualizar datos del usuario
    public ResponseEntity<Object> updateUsuario(Usuario usuario) {
        //Optional<Canchas> id = canchasRepository.findById(canchas.getId_cancha());
        boolean existe = this.usuarioRepository.existsById(usuario.getId_usuario());
        datos = new HashMap<>();
        if (existe) {
            datos.put("datos", datos);
            datos.put("message", "Se actualizo con exito");
            usuarioRepository.save(usuario);
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CREATED
            );
        }
        datos.put("error", true);
        datos.put("message", "No existe el usuario");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CONFLICT
        );
    }

    //Eliminar el usuario
    public ResponseEntity<Object> deleteUsuario(Long id) {
        boolean existe = this.usuarioRepository.existsById(id);
        datos = new HashMap<>();
        if (!existe) {
            datos.put("error", true);
            datos.put("message", "No existe el usuario");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        usuarioRepository.deleteById(id);
        datos.put("message", "Producto eliminado");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public String encryptPassword(String contraseña) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encodeContraseña=bcrypt.encode(contraseña);
        return encodeContraseña;
    }

    //login de usuario
    public ResponseEntity<Object> loginUsuario(String correo, String contraseña) {
        datos = new HashMap<>();
        Optional<Usuario> usuario = usuarioRepository.findByContraseña(correo, contraseña);
            if (usuario.isPresent()){
                datos.put("acces", true);
                datos.put("message", "Correo y contraseña validos");
                return new ResponseEntity<>(
                        datos,
                        HttpStatus.CONFLICT
                );
            }else {
                datos.put("error", true);
                datos.put("message", "Correo/contraseña incorrectos");
                return new ResponseEntity<>(
                        datos,
                        HttpStatus.CONFLICT
                );
            }

    }
    //login de usuario
    public String loginUsuario2(String correo, String contraseña)  {
        //Optional<Usuario> usuario = usuarioRepository.findByContraseña(correo, contraseña);
        Optional<Usuario> usuario = usuarioRepository.findUsuarioByCorreo(correo);
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        Usuario dbUsuario = usuario.get();
        if (usuario.isPresent()) {
            if (bcrypt.matches(contraseña, dbUsuario.getContraseña())) {
                return dbUsuario.getRol();
            } else {
                return "Acceso denegado contraseña incorrecta";
            }
        }else {
            return "Usuario no existe";
        }
    }

}
