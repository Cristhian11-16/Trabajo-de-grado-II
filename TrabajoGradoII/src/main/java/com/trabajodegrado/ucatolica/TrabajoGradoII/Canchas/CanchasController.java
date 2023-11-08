package com.trabajodegrado.ucatolica.TrabajoGradoII.Canchas;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@RestController
@RequestMapping("/canchas")
@CrossOrigin(origins = "*")
public class CanchasController {

	private final CanchasService canchasService;
	private final IUploadFileService iUploadFileService;
	private ResourceLoader resourceLoader;


	@Autowired
	public CanchasController(CanchasService canchasService, IUploadFileService iUploadFileService) {
		this.canchasService = canchasService;
		this.iUploadFileService = iUploadFileService;
	}
	//Obtener la informacion de una cancha
	@GetMapping(path="/{cancha_id}")
	public Canchas getCancha(@PathVariable("cancha_id") Long id_cancha){
		return canchasService.getCancha(id_cancha);
	}
	//Obtener canchas que tengan futbol 8
	@GetMapping(path="/futbol8")
	public List<Canchas> getCanchasFutbol8(@RequestParam(name = "futbol8") boolean futbol_8, @RequestParam(name = "id_cancha") long id_cancha){
		return canchasService.getCanchaFutbol8(futbol_8, id_cancha);
	}
	//Obtener todas las canchas que tengan parqueadero
	@GetMapping(path="/parqueadero")
	public List<Canchas> getCanchasParqueadero(@RequestParam(name = "parqueadero") boolean parquedaero, @RequestParam(name = "id_cancha") long id_cancha){
		return canchasService.getCanchaParqueadero(parquedaero, id_cancha);
	}
	//Obtener todas las canchas registradas
	@GetMapping(path="/name")
	public List<Canchas> getCanchas(){
		return canchasService.getCancha();
	}
	//Registrar una nueva cancha
	@PostMapping(path = "/registrar")
	public ResponseEntity<Object> registrarCancha(@RequestBody Canchas canchas){
		return this.canchasService.newCancha(canchas);
	}
	//Actualizar una nueva cancha
	@PutMapping(path = "/actualizar")
	public ResponseEntity<Object> actualizarCancha(@RequestBody Canchas canchas){
		return this.canchasService.updateCancha(canchas);
	}
	//Eliminar una cancha
	@DeleteMapping(path = "/eliminar/{canchaId}")
	public ResponseEntity<Object> eliminarCancha(@PathVariable("canchaId") Long id){
		return this.canchasService.deleteCancha(id);
	}
	//Subir imagen de la cancha
	@PostMapping("/upload/{nameImg}")
	public void handleFileUpload(@RequestPart("file") MultipartFile file, @PathVariable("nameImg") String nameImg) throws IOException {
		iUploadFileService.uploadFile(file.getInputStream(), nameImg);
	}
	//Obtener imagen de la cancha
	@GetMapping("/imagen/{nombreArchivo}")
	public ResponseEntity<Resource> getImage(@PathVariable String nombreArchivo) {
		Resource resource = resourceLoader.getResource("src/main/resources/upload" + nombreArchivo);
		if (resource.exists()) {
			return ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_OCTET_STREAM)
					.body(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
