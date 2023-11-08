package com.trabajodegrado.ucatolica.TrabajoGradoII.Usuario;

import com.trabajodegrado.ucatolica.TrabajoGradoII.Direcciones.Direccion;
import com.trabajodegrado.ucatolica.TrabajoGradoII.Direcciones.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping(path="/usuarioregistrados")
    public List<Usuario> getUsuarios(){
        return usuarioService.getUsuarios();
    }
    //Iniciar sesion
    @GetMapping(path="/login/")
    public ResponseEntity<Object> loginUsuario(@RequestParam("correo") String correo, @RequestParam("contraseña") String contraseña ){
        return usuarioService.loginUsuario(correo, contraseña);
    }
    @GetMapping(path="/login2")
    public String loginUsuario1(@RequestParam("correo") String correo, @RequestParam("contraseña") String contraseña ){
        return usuarioService.loginUsuario2(correo, contraseña);
    }
    //Consultar informacion de un usuario
    @GetMapping(path="/{correo}")
    public Usuario getUsuario(@PathVariable("correo") String correo){
        return usuarioService.getUsuarioCorreo(correo);
    }
    //Registrar un usuario
    @PostMapping(path = "/registrar")
    public ResponseEntity<Object> registrarUsuario(@RequestBody Usuario usuario){
        return this.usuarioService.newUsuario(usuario);
    }

    //Actualizar datos del usuario
    @PutMapping(path = "/actualizar")
    public ResponseEntity<Object> actualizarUsuario(@RequestBody Usuario usuario){
        return this.usuarioService.updateUsuario(usuario);
    }
    //Eliminar datos del usuario
    @DeleteMapping(path = "/eliminar/{usuario_Id}")
    public ResponseEntity<Object> eliminarUsuario(@PathVariable("usuario_Id") Long id){
        return this.usuarioService.deleteUsuario(id);
    }
}
