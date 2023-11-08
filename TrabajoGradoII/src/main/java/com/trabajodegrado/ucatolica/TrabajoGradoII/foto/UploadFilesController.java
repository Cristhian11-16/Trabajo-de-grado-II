package com.trabajodegrado.ucatolica.TrabajoGradoII.foto;

import com.trabajodegrado.ucatolica.TrabajoGradoII.Canchas.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadFilesController {
    @Autowired
    IUploadoFilesService iUploadFileService;

    @PostMapping("/picture")
    private ResponseEntity<String> uploadPicture(@RequestParam("file") MultipartFile file) throws Exception{
        return new ResponseEntity<>(iUploadFileService.handleFileUpload(file), HttpStatus.OK);
    }
}
